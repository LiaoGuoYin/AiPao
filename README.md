# 阳光体育paopaopao

老夫一秒一个 `阳关体育` （强行JAVA脚本）

## Usage

#### 1. 获取 IMEICode
  - 打开抓包软件, 再打开阳光体育APP, 在抓包软件中找后缀有 `IMEICode= ` 的URL请求, 等号后面的字段即 `IMEICode`
   
  - (若多次抓不到: 来回切换几次飞行模式, 再打开阳光体育)
    
#### 2. 运行
  - 点击本页面的 `realease` 
  
  - 下载压缩包并解压, 双击 `start.bat` (mac命令行下: `bash start.bat`)
  
  - 看到输入提示后, 粘贴 Imeicode 即可 (每跑一次，会在本地 output.txt 生成一行记录)
  
     
## Todo
   - [x] 判断性别, 随机生成对应的路程和时间
   - [x] 每操作一步，返回一定的信息
   - [ ] 异步式批量跑
   - [ ] 添加 headers 中的 `auth` 和 `sign` 字段
   - [ ] 从本地读取保存 `IMEICode` 
   - [x] 错误提示
 
## Recommend Capture App
   IOS: [Stream](https://itunes.apple.com/cn/app/stream/id1312141691?mt=8&ct=appshare-cn) Android: [Packet Capture](https://play.google.com/store/apps/details?id=app.greyshirts.sslcapture)
 
## Design this Project Youself
   - Fork(我是不会跑路的, 球球你给我 Star 8)后 IDEA 打开项目, 然后 import Gradle project.
 
   - 进入 [src/test](src/test/java/com/liaoguoyin/aipao/RunnerTest.java) 目录, 点击左侧的绿色箭头运行, 在控制台输入 `IMEICode` 回车即可.
 
   - 本项目还有 `Python` 版和 `Kotlin` 版, 请切换分支自取.
   
## Other
     **Author: [LiaoGuoYin](https://github.com/Biubang)**

     **基于HTTP框架: [Retrofit2](https://square.github.io/retrofit/)**  
     
     **核心API出自: [zyc199847](https://github.com/zyc199847/Sunny-Running) 逆向成果**
   
     **License GPL v3.0**

## Warning
     本文仅供研究，使用者造成的任何后果由使用者自行承担，与作者无关。
   