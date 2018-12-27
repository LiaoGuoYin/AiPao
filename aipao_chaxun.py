import requests
from lxml.html import fromstring

url = 'http://sportsapp.aipao.me/Manage/UserDomain_SNSP_Records.aspx/MyResutls?userId=%20633651'


def main():
    response = requests.get(url)
    html = fromstring(response.text)
    user_info = html.cssselect('div .user-info *')
    user_info2 = html.cssselect('div .running-info span')

    Name = str(user_info[0].text_content())
    Sex = str(user_info[2].text_content())
    XueHao = str(user_info[3].text_content())

    ChengPao = user_info2[1].text_content()
    Total_Time = user_info2[2].text_content()

    print(Name,Sex,XueHao,ChengPao,Total_Time)

if __name__ == '__main__':
    main()
