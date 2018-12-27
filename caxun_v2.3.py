import requests

def read_imeicode_from_text():
    imeicode = []
    with open('imei.txt','r',encoding='gbk') as f:
        for i in f.readlines():
            imeicode.append(i[:32])
    with open('imei2.txt','r',encoding='gbk') as f:
        for i in f.readlines():
            imeicode.append(i[:32])
    return imeicode

def get_one_id_from_imeicode(imeicode):
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
            'Name':running_recorder['Records'][0]['NickName'],
            'Last_time':running_recorder['Records'][0]['ResultDateFmt'],
            'Time':running_recorder['TotalRecords']
        }
        return info
    else:
        return None

def save_info(info):
    with open('Info.txt','a+') as f:
        f.write(str(info)+'\n')
        f.close()

def print_out(result_info):
    if result_info['Time']>=40:
        result_info['Time']=str(result_info['Time'])+' done done done！！！'
    print('{}\t ID：{} 最后一次: {} 次数: {}'\
                  .format(result_info['Name'],result_info['Id'],result_info['Last_time'],result_info['Time']))



def main():
    imei_list = read_imeicode_from_text()
    all_info ={}
    for i in imei_list:
        all_info['Imeicode'] = i
        #all_info['Imeicode'] = input('Please make input your imeicode :')
        all_info['Id'] = get_one_id_from_imeicode(all_info['Imeicode'])#return id list
        info = get_info_from_id(all_info['Id'])
        if info != None:
            all_info_result = all_info.copy()
            all_info_result.update(info)
            save_info(all_info_result)
        else:
            print('Imeicode: {} 已失效!'.format(all_info['Imeicode']))
        #save_info(all_info)



if __name__=='__main__':
    main()
