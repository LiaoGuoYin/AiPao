import requests




root_url = 'http://client3.aipao.me/api/%7Btoken%7D/QM_Users/Login_AndroidSchool?IMEICode='
imei_id =[]

with open('imei.txt','r') as f, open('imei2.txt','r',encoding='gbk') as f2:
    lines = f.readlines()+f2.readlines()
    for i in lines:
        imeicode = i[:32]
        response = requests.get(root_url+imeicode)
        response1 =response.json()
        if response1['Success'] == True:
            imei_id.append('{}----{}'.format(response1['Data']['UserId'],imeicode))
        else:
            print('{} 失效！'.format(i[32:].strip()))
    f.close()

with open('all_info.txt','w') as f:
    for i in imei_id:
        f.write(i+'\n')
    f.close()

print('Done..')
