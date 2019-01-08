import win32api

imeicode = []

f=open('imei1.txt','r')

for imei in f.readlines():
	imeicode.append(imei[:32])
f.close()

for i in imeicode:
    win32api.ShellExecute(0, 'open', 'nv.py', i, '', 1)


#蜂鸣器
import ctypes
player=ctypes.windll.kernel32
player.Beep(998,2000)
#暂停
