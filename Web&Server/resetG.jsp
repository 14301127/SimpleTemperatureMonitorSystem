<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>

<!DOCTYPE html>
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<TITLE>后台管理</TITLE>
<SCRIPT src="js/jquery-1.9.1.min.js" type="text/javascript"></SCRIPT>

<STYLE>
body {
	background: #ebebeb;
	font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei",
		"\9ED1\4F53", Arial, sans-serif;
	color: #222;
	font-size: 12px;
}

* {
	padding: 0px;
	margin: 0px;
}

.top_div {
	background: #008ead;
	width: 100%;
	height: 150px;
}

a {
	text-decoration: none;
}

.ipt {
	border: 1px solid #d3d3d3;
	padding: 10px 10px;
	width: 290px;
	border-radius: 4px;
	padding-left: 35px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

.tou {
	background: url("images/tou.png") no-repeat;
	width: 97px;
	height: 92px;
	position: absolute;
	top: 64px;
	margin-left: 47%;
}

.left_hand {
	background: url("images/hand.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: 140px;
	margin-left: 42%;
}

.right_hand {
	background: url("images/hand.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: 140px;
	margin-left: 57%;
}

.content {
	margin-top: 20px;
	display: block;
}

.content_left {
	position: absolute;
	top: 170px;
	left: 0;
	background: #008ead;
	height: 550px;
	width: 20%;
	padding-top: 50px;
}

.content_left ul {
	
}

.content_left ul li {
	border-radius: 30px;
	height: 50px;
	padding-top: 20px;
}

.content_left ul li a {
	text-align: center;
	display: block;
	height: 50px;
	font-size: large;
	font-family: "Microsoft YaHei";
	font-weight: bold;
	color: white;
}

.content_left ul li:hover {
	background-color: #0493ad;
	-moz-box-shadow: 0 0 8px #DDD;
	-webkit-box-shadow: 0 0 8px #DDD;
}

.active {
	background-color: #0493ad;
	-moz-box-shadow: 0 0 8px #DDD;
	-webkit-box-shadow: 0 0 8px #DDD;
}

.content_right {
	margin-left: 40%;
}
</STYLE>


<META name="GENERATOR" content="MSHTML 11.00.9600.17496">
</HEAD>
<BODY>
	<DIV class="top_div">
		<div class="tou"></div>
		<div class="left_hand" id="left_hand"></div>
		<div class="right_hand" id="right_hand"></div>
	</DIV>
	<DIV class="content">
		<div class="content_left">
			<ul>
				<li class="active" ><a>用户管理</a></li>
				<li><a href="queryG.jsp">数据查询</a></li>
			</ul>
		</div>

		<div class="content_right">
			<form action="/Weather_s/serv/ResetServlet" method="get">
				<P style="padding: 50px 0px 10px; position: relative;">
					<INPUT class="ipt" type="text" placeholder="请输入用户名" name="name"
						value="">
				</P>

				<select id="choose" onchange="getPer(this.id)">
					<option value="info">重置资料</option>
					<option value="pass">重置密码</option>
				</select>
				<P id="password" style="padding: 8px 0px 10px; position: relative;">
					<INPUT class="ipt" name="password" type="password"
						placeholder="请输入密码" value="">
				</P>
				
				<button
					 style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;">确定</button>
			</form>
			<p class="ipt"><strong ><%=request.getSession().getAttribute("information")%></strong></p>
			<script type="text/javascript">
					//首先定位
					var partShow = document.getElementById("password");
					//然后再先隐藏
					partShow.style.display = "none";
					//其次给onchange()事件写方法
					function getPer(obj) {
						var flag = document.getElementById(obj).value;
						if (flag == 'pass') {
							partShow.style.display = "block";
						}
						if (flag == 'info') {
							partShow.style.display = "none";
						}
					}
					
						
					
				</script>
		</div>
	</DIV>

	

</HTML>
