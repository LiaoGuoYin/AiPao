import requests,json
from lxml.html import fromstring

def read_imeicode_from_text():
    imeicode = []
    with open('imei.txt','r',encoding='gbk') as f:
        for i in f.readlines():
            imeicode.append(i)
    with open('imei2.txt','r',encoding='gbk') as f:
        for i in f.readlines():
            imeicode.append(i)
    f.close()
    return imeicode

def sex_info(Id):
    url = 'http://sportsapp.aipao.me/Manage/UserDomain_SNSP_Records.aspx/MyResutls?userId={}'.format(Id)
    response = requests.get(url)
    html = fromstring(response.text)
    user_info = html.cssselect('div .user-info *')
    user_info2 = html.cssselect('div .running-info span')

    Name = str(user_info[0].text_content())
    Sex = str(user_info[2].text_content())
    #XueHao = str(user_info[3].text_content())
    ChengPao = user_info2[1].text_content()
    #Total_Time = user_info2[2].text_content()
    sex_info = {Id:[Name,Sex,ChengPao]}

    return sex_info



def get_id_from_imeicode(imeicode):
    url = 'http://client3.aipao.me/api/%7Btoken%7D/QM_Users/Login_AndroidSchool?IMEICode={}'.format(imeicode)
    response = requests.get(url)
    response_dict = response.json()
    if response_dict['Success']==True:
        return response_dict['Data']['UserId']
    else:
        return str(500000)

def get_info_from_id(id):
    url = 'http://sportsapp.aipao.me/MyResults.ashx?sunnyId={}&pageNo=1&type=0'.format(id)
    response = requests.get(url)
    running_recorder = response.json()
    if running_recorder['Records'] != []:
        info = {
            #'Name':running_recorder['Records'][0]['NickName'],
            'Last_time':running_recorder['Records'][0]['ResultDateFmt'],
            'Time':running_recorder['TotalRecords']
        }
        return info
    else:
        return None

def save_info(info):
    with open('All_info.txt','a+') as f:
        f.write('{}----{}{}{}\n'.format(info[4],info[5],info[0],info[1]))
        f.close()

def print_out(result_info):
    if result_info['Time']>=40:
        result_info['Time']=str(result_info['Time'])+' done done done！！！'
    print('{}\t ID：{} 最后一次: {} 次数: {}'\
                  .format(result_info['Name'],result_info['Id'],result_info['Last_time'],result_info['Time']))

def shixiao(imeicode):
        imeicodes = read_imeicode_from_text()
        for i in imeicodes:
            if i[:32] == imeicode:
                print(i[36:].strip('\n')+'已失效！')

def main():
    imeicode_list = read_imeicode_from_text()
    for imeicode in imeicode_list:
        imeicode = imeicode[:32]
        Id = get_id_from_imeicode(imeicode)
        info = sex_info(Id)

        if Id != '500000':
            info[Id].append('正常')
        else:
            shixiao(imeicode)
            save_info(info[Id])
            continue

        info[Id].append(imeicode)
        info[Id].append(Id)
        info2 = get_info_from_id(Id)
        info[Id].append(info2['Last_time'])
        info[Id].append(str(info2['Time']))

        save_info(info[Id])

        print(json.dumps(info,ensure_ascii= False))


    #all_info['Imeicode'] = input('Please make input your imeicode :')
    #save_info(all_info)




if __name__=='__main__':
    main()
