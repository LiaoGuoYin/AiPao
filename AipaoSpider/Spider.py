import requests

from AipaoSpider import DBManager
from AipaoSpider.ModelsAipao import Aipaoer

INFO_URL = "http://sportsapp.aipao.me/MyResults.ashx"


class AipaoSpider(object):
    def __init__(self, start_userId):
        self.userId = start_userId
        self.aipaoer = Aipaoer(start_userId)

    def get_errorRecords(self, pageNo=1):
        response = requests.get(INFO_URL, params={"sunnyId": self.userId, "pageNo": pageNo, "type": "1"})
        return response.json()

    def get_okRecords(self, pageNo=1):
        response = requests.get(INFO_URL, params={"sunnyId": self.userId, "pageNo": pageNo, "type": "0"})
        return response.json()

    def dump_model(self, ok_json, error_json):
        try:
            self.aipaoer.userId = ok_json["Records"][0]["SunnyId"]
            self.aipaoer.nickName = ok_json["Records"][0]["NickName"]
            self.aipaoer.schoolId = ok_json["Records"][0]["SchoolId"]
            self.aipaoer.sex = ok_json["Records"][0]["Sex"]
            self.aipaoer.okRecords = ok_json["Records"]
            self.aipaoer.errorRecords = error_json["Records"]
        except Exception as e:
            print(e)

    def write_db(self):
        # Mysql Mode
        DBManager.insert_db(aipaoer_dict=self.aipaoer.__dict__)

        # Local TXT Mode
        # with open("ok.txt", "a+") as fp:
        #     fp.writelines(self.aipaoer.__str__())
        # fp.close()


def main():
    spider = AipaoSpider(733333)

    for user_id in range(533333, 733333):
        spider.aipaoer.clear()
        spider.userId = user_id
        ok_json = spider.get_okRecords()

        if ok_json["TotalRecords"] == 0:
            continue
        else:
            error_json = spider.get_errorRecords()
            spider.dump_model(ok_json, error_json)

            page_count = ok_json["PageCount"]
            for i in range(2, page_count + 1):
                ok_json = spider.get_okRecords(pageNo=i)
                spider.aipaoer.okRecords.extend(ok_json["Records"])

            spider.write_db()
            print(spider.aipaoer.userId)
    else:
        print("done..")


if __name__ == "__main__":
    main()
