import requests

def get_one_person(id):
    url = 'http://sportsapp.aipao.me/MyResults.ashx?sunnyId={}&pageNo=1&type=0'.format(id)
    response = requests.get(url)
    return response


def get_one_id_from_imeicode(imeicode):
    url = 'http://client3.aipao.me/api/%7Btoken%7D/QM_Users/Login_AndroidSchool?IMEICode={}'.format(imeicode)
    response = requests.get(url)
    #TODO




def parse_one_person(response):
    running_recorder = response.json()
    if running_recorder['Records'] !=[]:
        yield{
            'Id':running_recorder['Records'][0]['SunnyId'],
            'Name':running_recorder['Records'][0]['NickName'],
            'Last_time':running_recorder['Records'][0]['ResultDateFmt'],
            'Time':running_recorder['TotalRecords']
        }
    else:
        return None

def save_txt(out_info):
    with open('aipao_log.txt','a+') as f:
        f.write(str(out_info)+'\n')
        f.close()

def print_out(result_info):
    if result_info['Time']>=40:
        result_info['Time']=str(result_info['Time'])+' done done done！！！'
    print('姓名：{},\t ID：{}, 最后一次: {}, 次数: {}'\
                  .format(result_info['Name'],result_info['Id'],result_info['Last_time'],result_info['Time']))


def read_id_from_text():
    id = []
    with open('imei.txt','r',encoding='gbk') as f:
        for i in f.readlines():
            id.append(i[36:42])
    with open('imei2.txt','r',encoding='gbk') as f:
        for i in f.readlines():
            id.append(i[36:42])
    return id

def main():
    print('loading...')
    for i in read_id_from_text():
        html = get_one_person(i)
        for result_info in parse_one_person(html):
            print_out(result_info)
    input('waiting...')

if __name__=='__main__':
    main()
