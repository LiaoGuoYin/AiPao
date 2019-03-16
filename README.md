# 阳光体育paopaopao

一个几秒钟就能躺完的 `阳关体育` 脚本（强行JAVA脚本）

## 使用方法    

#### 1. 获取IMEICode
  - 打开抓包软件, 再打开阳光体育APP, 在抓包软件中找后缀有 `IMEICode= ` 的URL请求, 等号后面的字段即 `IMEICode`
   
  - (若多次抓不到: 来回切换几次飞行模式, 再打开阳光体育)
    
#### 2. 运行脚本
  - Fork后 IDEA 打开项目, 然后 import Gradle project.

  - 进入 [src/test](src/test/java/com/liaoguoyin/aipao/RunnerTest.java) 目录, 点击左侧的绿色箭头运行, 在控制台输入 `IMEICode` 回车即可.

     (其他IDE请自行导入 `Retrofit2` 等依赖库)
  
#### 推荐俩抓包软件:
     IOS: [Stream](https://itunes.apple.com/cn/app/stream/id1312141691?mt=8&ct=appshare-cn) Android: [Packet Capture](https://play.google.com/store/apps/details?id=app.greyshirts.sslcapture)

## TODO
   - [x] 判断性别, 随机生成对应的路程和时间
   - [ ] 每操作一步，返回一定的信息
   - [ ] 异步式批量跑
   - [ ] 添加 headers 中的 `auth` 和 `sign` 字段
   - [ ] 从本地读取保存 `IMEICode` 
 
## 其他
     **本项目基于 HTTP框架: [Retrofit2](https://square.github.io/retrofit/)**  
     
     **本项目核心api出自: [zyc199847](https://github.com/zyc199847/Sunny-Running) 逆向出的成型思路**
   
     **Author: [LiaoGuoYin](https://github.com/Biubang)**
     
     **License GPL v3.0**

## Warning
     本文仅供研究，使用者造成的任何后果由使用者自行承担，与作者无关。