from bs4 import BeautifulSoup
import urllib.request
import re



f=open('imei.txt','r')
for imei in f.readlines():
	imeicode.append(imei[36:42])
f.close()
f=open('imei2.txt','r')
for imei in f.readlines():
	imeicode.append(imei[36:42])
f.close()

for i in imeicode:
	id=i
	url='http://sportsapp.aipao.me/Manage/UserDomain_SNSP_Records.aspx/MyResutls?userId='+str(id)
	html=urllib.request.urlopen(url).read().decode('utf-8')


	res_tr=r'<span>(.*?)</span>'
	m_tr=re.findall(res_tr,html)
	res_name=r'<span class="name">(.*?)</span><'
	m_name=re.findall(res_name,html)
	res_lastrecorder=r'<div class="list box-col"><span class="time">(.*?)<'
	m_lastrecorder=re.findall(res_lastrecorder,html)
	print(m_name[0],'\t',m_tr[1],'次，最后一次：'+m_lastrecorder[0])


name=input('admin:')
