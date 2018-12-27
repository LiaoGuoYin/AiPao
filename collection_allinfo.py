import requests

def get_one_person(id):
    url = 'http://sportsapp.aipao.me/MyResults.ashx?sunnyId={}&schoolId=lntu&pageNo=1&type=0'.format(id)
    response = requests.get(url)
    return response

def parse_one_person(response):
    running_recorder = response.json()
    if running_recorder['Records'] !=[]:
        yield{
            'Id':running_recorder['Records'][0]['SunnyId'],
            'Name':running_recorder['Records'][0]['NickName'],
            'last_time':running_recorder['Records'][0]['ResultDateFmt'],
            'time':running_recorder['TotalRecords']
        }
    else:
        return None

def save_txt(out_info):
    with open('output.txt','a+') as f:
        f.write(str(out_info)+'\n')
        f.close()


def main():
    print('loading...')
    for i in range(394444,446977):
        html = get_one_person(i)
        for out in parse_one_person(html):
            #if out !=None:
                #print('正在入裤..'+str(out['Id'] )+out['Name']+str( out['time']))
            save_txt(out)


if __name__=='__main__':
    main()
