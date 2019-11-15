import json

import requests


class Student(object):
    userId = 0
    imeicode = ""
    token = ""
    isValid = False


class Client(object):
    imeicodeDict = {}
    students = []


def check_valid(client, imeicode):
    url = "http://client3.aipao.me/api/%7Btoken%7D/QM_Users/Login_AndroidSchool?IMEICode=" + imeicode
    rsp = requests.get(url)
    rspJson = json.loads(rsp.text)

    student = Student()
    student.imeicode = imeicode
    status = rspJson["Success"]
    if status:
        student.isValid = status
        student.userId = rspJson["Data"]["UserId"]
        student.token = rspJson["Data"]["Token"]
        student.token = rspJson["Data"]["Token"]
        print("ok imeicode: {}".format(student.imeicode))
        client.students.append("{}----{}\n".format(student.imeicode, student.userId))
    else:
        print("invalid! imeicode: {}".format(student.imeicode))


def main():
    client = Client()
    input_imeicode = []
    with open("IMEICode.txt", "r") as fp:
        IMEICodes = fp.readlines()
        for IMEICode in IMEICodes:
            input_imeicode.append(IMEICode[:32])
    fp.close()
    for each in input_imeicode:
        check_valid(client, each)
    with open("output.txt", "a+") as fp:
        for each in client.students:
            fp.write(each)
    fp.close()
    print(client.students)


if __name__ == "__main__":
    main()
