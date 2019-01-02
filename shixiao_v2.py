import requests


def shixiao(imeicode):
    url = 'http://client3.aipao.me/api/%7Btoken%7D/QM_Users/Login_AndroidSchool?IMEICode={}'.format(imeicode[:32])
    response = requests.get(url)
    if response.json()['Success'] == False:
        print('{} 失效！'.format(imeicode[42:].strip()))#shixiao
    else:
        return None


def main():
    with open('imei2.txt','r',encoding='gbk') as f:
        imeicodes = f.readlines()
    for i in imeicodes:
        shixiao(i)
    f.close()
    input('\ndone.')

if __name__ =='__main__':
    main()




