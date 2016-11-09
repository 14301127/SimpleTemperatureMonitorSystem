# SimpleTemperatureMonitorSystem
安卓客户端可以注册登录并向服务器上传和查询监控的温度，网页端后台管理可以管理用户数据和温度。

学校的Java课程设计，所有的代码都是和Java相关的。

Android文件夹是客户端代码。在客户端上，用户可以完成注册登录和修改资料。因为没有硬件支持，所以监控的温度是通过随机数模拟的，用户可以自定义数据集体上传时间，也可以查询本账户某一时间段的温度的趋势（折线图）。数据传输使用了HTTP协议和对象流，折线图通过重写View的onDraw方法实现。

Web&Server文件夹中的WEB-INF子目录为服务器上的后台代码，其余部分为后台管理网站代码。后台管理网站可以重置用户资料密码和查询某一用户某一时间段温度(也是折线图展示)。后台主要使用的是Servlet和Jsp。


For this is one of my Java schoolwork,so all of this project is related to the Java.

The Android folder contains the codes for the Android client,on which we can login,register and modify our information.Limited by the hardware support,the temperature data is simulated by random numbers.The users will have the control for how long the data is uploaded.The trend of the data that users query for a certain period of the time of his own count will be displayed by a line chart.The data is transported by the Object Stream with HTTP,while the line chart is achieved by overriding the onDraw method of the View object.

The Web&Server folder contains two parts.One of these which is in the WEB-INF folder is the server code.And the other files in Web&Server are the backstage management website,by which the admin can reset user's information and password and get user's data in a period of time by line chart.Servlet and Jsp are the major codes in this.
