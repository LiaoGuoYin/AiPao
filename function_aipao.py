# -*- coding: utf8 -*-
import json
import requests


imeicodes ='''
'6d770d2be4fb41258ebc5c31cad92b56':'395243'
'd9562cbc44e7462f892d8a32da6285aa':'396991'
'56bf75ae1ece457c8e149831a7c0f333':'629450'
99e2896dbf11475693cee6e525e582a5----395786
99e2896dbf11475693cee6e525e58215----395786
'''

# -*- coding: utf8 -*-
import json
# def main_handler(event, context):
#     print("Received event: " + json.dumps(event, indent = 2))
#     print("Received context: " + str(context))
#     print("Hello world")
#     return("Hello World")

events = {
    "395243": ["秦琪", "男", "14", "正常", "6d770d2be4fb41258ebc5c31cad92b56", "2018-12-28", "34"],
    "396991": ["丁仕航", "男", "13", "正常", "d9562cbc44e7462f892d8a32da6285aa", "2018-12-28", "38"],
    "629450": ["张展鸣", "男", "11", "正常", "56bf75ae1ece457c8e149831a7c0f333", "2018-12-28", "26"],
    "395786": ["詹潼潼", "男", "9", "正常", "99e2896dbf11475693cee6e525e582a5", "2018-12-28", "33"]
}

def start(event):
    for id,info in event.items():
        print(id,info)
        url = 'http://client3.aipao.me/api/%7Btoken%7D/QM_Users/Login_AndroidSchool?IMEICode={}'.format(event[id][4])
        response = requests.get(url)
        print (response.json())




def main_handler(event = events):
    return start(event)

if __name__ == '__main__':
    start(events)
