<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8" import="java.util.List,java.util.ArrayList,com.example.guhao.tempmon.Temp"%>
<!DOCTYPE html>
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
    <META content="IE=11.0000"  http-equiv="X-UA-Compatible">
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <TITLE>后台管理</TITLE>
    <SCRIPT src="js/jquery-1.9.1.min.js" type="text/javascript"></SCRIPT>

    <STYLE>
        body{
            background: #ebebeb;
            font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif;
            color: #222;
            font-size: 12px;
        }
        *{padding: 0px;margin: 0px;}
        .top_div{
            background: #008ead;
            width: 100%;
            height: 150px;
        }

        a{
            text-decoration: none;
        }

        .ipt{
            border: 1px solid #d3d3d3;
            margin-left: 10px;
            padding: 10px 10px;
            width: 150px;
            border-radius: 4px;
            padding-left: 20px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s
        }

        .tou{
            background: url("images/tou.png") no-repeat;
            width: 97px;
            height: 92px;
            position: absolute;
            top:64px;
            margin-left: 47%;
        }
        .left_hand{
            background: url("images/hand.png") no-repeat;
            width: 30px;
            height: 20px;
            position: absolute;
            top:140px;
            margin-left: 42%;
        }
        .right_hand{
            background: url("images/hand.png") no-repeat;
            width: 30px;
            height: 20px;
            position: absolute;
            top:140px;
            margin-left: 57%;
        }

        .content{
            margin-top: 20px;
            display: block;
        }
        .content_left{
            position:absolute;
            top:170px;
            left:0;
            background: #008ead;
            height:550px;
            width:20%;
            padding-top: 50px;
        }
        .content_left ul{

        }
        .content_left ul li {
            border-radius: 30px;
            height:50px;
            padding-top: 20px;
        }

        .content_left ul li a {
            text-align: center;
            display: block;
            height:50px;
            font-size: large;
            font-family: "Microsoft YaHei";
            font-weight: bold;
            color: white;
        }

        .content_left ul li:hover {
            background-color: #0493ad;
            -moz-box-shadow:0 0 8px #DDD;
            -webkit-box-shadow: 0 0 8px #DDD;
        }
        .active{
            background-color: #0493ad;
            -moz-box-shadow:0 0 8px #DDD;
            -webkit-box-shadow: 0 0 8px #DDD;
        }
        .content_right{
            margin-left: 20%;
        }

    </STYLE>


    <META name="GENERATOR" content="MSHTML 11.00.9600.17496"></HEAD>
<BODY>
<DIV class="top_div">
    <div class="tou"></div>
    <div class="left_hand" id="left_hand"></div>
    <div class="right_hand" id="right_hand"></div>
</DIV>
<DIV class="content">
    <div class="content_left">
        <ul>
            <li><a href="resetG.jsp">用户管理</a></li>
            <li class="active"><a>数据查询</a></li>
        </ul>
    </div>

    <div class="content_right">
<form action="/Weather_s/serv/Mydata" method="get">
        <P style="margin-left:30px;padding: 10px 0px 10px; position: relative;float: left;">
        <INPUT class="ipt" type="text" placeholder="请输入用户名" value="" name="name">
    </P>
        <P style="margin-left:100px;padding: 10px 0px 10px; position: relative;float: left;">
            <INPUT class="ipt" type="text" placeholder="请输入起始时间" value="" name="date1">
        </P>
        <P style="position: relative;float: left;margin-left:8px;padding-top: 12px;font-size: large">-</P>
        <P style="padding: 10px 0px 10px; position: relative;float: left;">
            <INPUT class="ipt" type="text" placeholder="请输入结束时间" value="" name="date2">
        </P>
        <button style="background: rgb(0, 142, 173); margin-top:10px;margin-left:30px;padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;float: left;" >查询</button>
</form>
        <canvas id="myCanvas" width="1000px" height="520px">
            您的浏览器不支持canvas标签。
        </canvas>
<%
	List<Temp> list = new ArrayList<Temp>();
	String mytem="";
	String myname="";
	int max=-100,min=100,temp=0;
	if(!session.isNew())
	{
	list = (List<Temp>) session.getAttribute("Temps");
	
	if (list !=null&&!list.isEmpty()) {
		for (int i = 0; i < list.size(); i++) {
			mytem+=list.get(i).getNumber();
			mytem+=",";
		}
		max=Integer.parseInt(list.get(0).getNumber());
		min=Integer.parseInt(list.get(0).getNumber());
		for (int i = 0; i < list.size()-1; i++) {
			temp=Integer.parseInt(list.get(i).getNumber());
			
			if(temp>max){
				max=temp;
			}
			if(temp<min){
				min=temp;
			}
		}
		temp=max-min;
		System.out.println(temp);
		myname+=list.get(0).getUserID();
	}
	
	}
%>
        <script type="text/javascript">
            //获取Canvas对象(画布)
            var canvas = document.getElementById("myCanvas");
            var data=[<%=mytem%>];
            var hight=480/<%=temp%>;
            var xWeight=950/(data.length-1);
            //简单地检测当前浏览器是否支持Canvas对象，以免在一些不支持html5的浏览器中提示语法错误
            if(canvas.getContext){
                //获取对应的CanvasRenderingContext2D对象(画笔)
                var ctx = canvas.getContext("2d");
                ctx.lineWidth = 2;//线条的宽度
                ctx.strokeStyle = "#008ead";//线条的颜色
                //注意，Canvas的坐标系是：Canvas画布的左上角为原点(0,0)，向右为横坐标，向下为纵坐标，单位是像素(px)。
                //画折线
                for(var i=0 ;i<data.length; i++){
                    
                        ctx .lineTo(10+i*xWeight,510-hight*(data[i]-<%=min%>));
 
                }
                ctx .stroke();

                //画点
                for(var i=0; i<data.length; i++){
                    ctx.beginPath();
                    ctx.fillStyle = "#008ead";
                    ctx.arc(10+i*xWeight,510-hight*(data[i]-<%=min%>), 5, 0, 5*Math.PI);//画点
                    ctx.fill();
                }

            }
        </script>

    </div>
</DIV>
</BODY>

</HTML>
