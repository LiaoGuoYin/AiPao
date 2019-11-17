import requests
from lxml.html import fromstring

from AipaoRunning.DBModels import StudentInfo


def check_valid(imeicode):
    student = StudentInfo(IMEICode=imeicode)
    url = "http://client3.aipao.me/api/%7Btoken%7D/QM_Users/Login_AndroidSchool?IMEICode={}".format(imeicode)
    try:
        rsp = requests.get(url)
        rspJson = rsp.json()
        if rspJson["Success"]:
            student.isValid = True
            student.userId = rspJson["Data"]["UserId"]
            get_info(student, student.userId)
            # client.students.append("{}----{}----{}\n".format(student.imeicode, student.userId, student.info))
        else:
            print("IMEICode has invalid: {}".format(imeicode))
    except Exception as e:
        print("Bad Network...")
    finally:
        return student


def get_info(student: StudentInfo, userId):
    url = "http://sportsapp.aipao.me/Manage/UserDomain_SNSP_Records.aspx/MyResutls?userId={userId}".format(
        userId=userId)
    response = requests.get(url)
    document_html = fromstring(response.text)

    selector = "/html/body/header/div[2]/div[2]/div/div[2]/span[1]/text()"
    student.name = document_html.xpath(selector)[0]

    selector = "/html/body/header/div[2]/div[3]/span[1]/text()"
    student.allCounts = document_html.xpath(selector)[0]

    selector = "/html/body/header/div[2]/div[1]/span[2]/text()"
    student.morningCounts = document_html.xpath(selector)[0]

    selector = "/html/body/header/div[2]/div[2]/div/div[2]/span[2]/text()"
    student.sex = document_html.xpath(selector)[0]

    selector = "/html/body/header/div[2]/div[2]/div/div[2]/div/text()"
    student.studentNumber = document_html.xpath(selector)[0]

    selector = "/html/body/header/div[3]/a/text()"
    school_info = document_html.xpath(selector)
    school_infos = "".join([i for i in school_info if i is not None])
    student.schoolMajor = str(school_infos)

# client = Client()
# input_imeicodes = []
#
# with open("IMEICode.txt", "r") as fp:
#     IMEICodes = fp.readlines()
#     for IMEICode in IMEICodes:
#         input_imeicodes.append(IMEICode[:32])
# fp.close()
#
# for imeicode in input_imeicodes:
#     check_valid(client, imeicode)
#
# with open("output.txt", "a+") as fp:
#     for each in client.students:
#         fp.write(each)
# fp.close()
#
# print(client.students)
