import requests,json
from lxml.html import fromstring
from pymongo import MongoClient


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
    Total_Time = user_info2[2].text_content()
    sex_info = {Id:[Name,Sex,ChengPao,Total_Time]}
    return sex_info


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


def main():
    f = open('all_info.txt','r')
    client = MongoClient(host = 'localhost',port = 27017)
    db=client.test
    collection = db.info
    for i in f.readlines():
        info = sex_info(i[:6])
        info['imeicode'] = i[10:].strip()
        print(info)
        result = collection.insert_one(info)

        #print(info[].append(i[:10]))

    #imeicode_list = read_imeicode_from_text()
    # for imeicode in imeicode_list:
    #
    #     Id = get_id_from_imeicode(imeicode)
    #
    #     if Id != '500000':
    #         info[Id].append('正常')
    #     else:
    #         shixiao(imeicode)
    #         save_info(info[Id])
    #         continue
    #
    #     info[Id].append(imeicode)
    #     info[Id].append(Id)
    #     info2 = get_info_from_id(Id)
    #     info[Id].append(info2['Last_time'])
    #     info[Id].append(str(info2['Time']))
    #
    #     save_info(info[Id])
    #
    #     print(json.dumps(info,ensure_ascii= False))
    #
    #
    # #all_info['Imeicode'] = input('Please make input your imeicode :')
    # #save_info(all_info)




if __name__=='__main__':
    main()
