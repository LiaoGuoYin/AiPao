import requests
import re

url = 'http://sportsapp.aipao.me/Manage/UserDomain_SNSP_Records.aspx/MyResutls?userId='

print('loading...')
#392995

for i in range(475334,675339):
    response = requests.get(url=url+str(i))
#print(response.text)
    pattern1 = re.compile('<span>(\d+).*?<span.*?name">(.*?)</span><i.*?Gender">(.*?)</span>.*?numshow">(.*?)</div>.*?mod01">.*?<span>(\d+)</span>.*?<a class="item box-col">(.*?)</a>',re.S)
#pattern2 = re.compile('<div class="layout-box".*?<span class="time">(.*?).*?<span class="time">')
    pipei = response.text
    out_info =re.findall(pattern1,pipei)
    out_info.append(i)
    with open('out.txt','a') as f:
        f.write(str(out_info)+'\n')
        f.close()



print('done')
