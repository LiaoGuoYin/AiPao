import requests

#读取imei 输出imeicode和id

imeicode = ''
url = 'http://client3.aipao.me/api/%7Btoken%7D/QM_Users/Login_AndroidSchool?IMEICode='.format(imeicode)

f0 = open('success_imeicode.txt','w+')

with open('imei2.txt','r',encoding='gbk') as f:
    imeicodes = f.readlines()
    count = 0
    for imeicode in imeicodes:
        response = requests.get(url+imeicode[:32])
        rsp = response.json()
        if rsp['Success'] == True:
            f0.write('{}----{}\n'.format(rsp['Data']['IMEICode'],rsp['Data']['UserId']))
            count=count+1

            print(imeicode.strip() + ' \tdone')
        else:
            print(imeicode.strip()+' \t初始化失败！!')
    print('{} 条记录，{} 条 init Success！'.format(len(imeicodes),count))

f0.close()
f.close()

