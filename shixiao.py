import urllib.request
import json
import re
from bs4 import BeautifulSoup

#获取info字典
info={}
infoname=[]
infoid=[]
infotime=[]
infoimei=[]
f=open('imei.txt','r',encoding='gbk')
for x in f.readlines():
    infoimei.append(x[:32])
    infoid.append(x[36:42])
    infoname.append(x[42:].strip('\n'))
f.close()
f=open('imei2.txt','r',encoding='gbk')
for x in f.readlines():
    infoimei.append(x[:32])
    infoid.append(x[36:42])
    infoname.append(x[42:].strip('\n'))
f.close()
info=zip(infoname,infoimei,infoid)

print(type(info))
#查询失效1
def chaxun(imeicode):
    url='http://client3.aipao.me/api/%7Btoken%7D/QM_Users/Login_AndroidSchool?IMEICode='+str(imeicode)
    html=urllib.request.urlopen(url).read()
    global htmljson
    htmljson=json.loads(html)


#查询失效2
for x in info:
    chaxun(x[1])
    if (htmljson['Success'] == False):
        print(x[0]+"已失效")



name=input('\npause:')
