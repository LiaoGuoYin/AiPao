import json
from random import randint, uniform

import requests


def encrypt(number):
    key = "xfvdmyirsg"
    numbers = list(map(int, list(str(number))))
    return_key = "".join([key[i] for i in numbers])
    return return_key


def pretty_print(jsonStr):
    print(json.dumps(json.loads(jsonStr), indent=4, ensure_ascii=False))


class Aipaoer(object):
    def __init__(self, IMEICode):
        self.IMEICode = IMEICode
        self.userName = ""
        self.userId = ""
        self.schoolName = ""
        self.token = ""
        self.runId = ""
        self.distance = 2400
        self.minSpeed = 2.0
        self.maxSpeed = 3.0

    def __str__(self):
        return str(self.__dict__).replace("\'", "\"")

    def check_imeicode(self):
        IMEICode = self.IMEICode
        url = "http://client3.aipao.me/api/%7Btoken%7D/QM_Users/Login_AndroidSchool?IMEICode={IMEICode}".format(
            IMEICode=IMEICode)
        rsp = requests.get(url)
        try:
            if rsp.json()["Success"]:
                okJson = rsp.json()
                self.token = okJson["Data"]["Token"]
                self.userId = okJson["Data"]["UserId"]
        except KeyError:
            print("IMEICode 失效")

    def get_info(self):
        token = self.token
        url = "http://client3.aipao.me/api/{token}/QM_Users/GS".format(token=token)
        rsp = requests.get(url)
        try:
            if rsp.json()["Success"]:
                okJson = rsp.json()
                self.userName = okJson["Data"]["User"]["NickName"]
                self.schoolName = okJson["Data"]["SchoolRun"]["SchoolName"]
                self.minSpeed = okJson["Data"]["SchoolRun"]["MinSpeed"]
                self.maxSpeed = okJson["Data"]["SchoolRun"]["MaxSpeed"]
                self.distance = okJson["Data"]["SchoolRun"]["Lengths"]
        except KeyError:
            print("Unknown error in get_info")

    def get_runId(self):
        token = self.token
        distance = self.distance
        url = "http://client3.aipao.me/api/{token}/QM_Runs/SRS?S1=40.62828&S2=120.79108&S3={distance}" \
            .format(token=token, distance=distance)
        rsp = requests.get(url)
        try:
            if rsp.json()["Success"]:
                self.runId = rsp.json()["Data"]["RunId"]
        except KeyError:
            print("Unknown error in get_runId")

    def upload_record(self):
        my_speed = round(uniform(self.minSpeed + 0.3, self.maxSpeed - 0.5), 2)
        my_distance = self.distance + randint(1, 5)
        my_costTime = int(my_distance // my_speed)
        my_step = randint(1555, 2222)
        print(my_speed, my_distance, my_costTime, my_step)
        myParams = {
            "token": self.token,
            "runId": self.runId,
            "costTime": encrypt(my_costTime),
            "distance": encrypt(my_distance),
            "step": encrypt(my_step)}
        url = "http://client3.aipao.me/api/{token}/QM_Runs/ES?" \
              "S1={runId}&S4={costTime}&S5={distance}&S6=A0A2A1A3A0&S7=1&S8=xfvdmyirsg&S9={step}".format(**myParams)
        rsp = requests.get(url)
        try:
            if rsp.json()["Success"]:
                print("成功!")
        except KeyError:
            print("失败")


def main():
    imeicodes = []
    with open("IMEICode.txt", "r") as fp:
        IMEICodes = fp.readlines()
        for IMEICode in IMEICodes:
            imeicodes.append(IMEICode[:32])
    fp.close()
    print("读入 IMEICode完成，共 {}".format(len(IMEICodes)))
    print(imeicodes)

    # aipaoer = Aipaoer("9b6f828bc1d644aabe8f42f46e56d754")
    # aipaoer.check_imeicode()
    # aipaoer.get_info()
    # aipaoer.get_runId()
    # aipaoer.upload_record()
    # pretty_print(str(aipaoer))


if __name__ == "__main__":
    main()
