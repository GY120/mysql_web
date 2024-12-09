<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="css/bootstrap.min.css" rel="stylesheet"/>
		
		<script
  src="https://code.jquery.com/jquery-1.12.4.min.js"
  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
  crossorigin="anonymous"></script>
		<script src="js/bootstrap.min.js"></script>
		<link href="css/beiji.css" rel="stylesheet"/>
	</head>
	<body>
		<style>
				      body {
				          background-image: url(images/bj.jpg);
				          background-size: cover;
				          background-repeat: no-repeat;
				          background-attachment: fixed;
				      }
				  </style> 
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
			<div class="row clearfix"style="margin-top: 300px;">
				<div class="col-md-4 col-sm-4 column">
				</div>
				<div class="col-md-4 col-sm-4 column">
					<form role="form"style="background: #ffffff;width: 500px;height: 300px;" ; action="login"; method="post">
						<h3 class="text-center" style="padding-bottom: 20px; padding-top:15px;">湖北工程学院新技术学院</h3>
						<div class="row form-group">
							<div class="col-md-3 col-sm-4 col-xs-4 text-right">
								<span class="glyphicon glyphicon-user"></span>
							 <label for="exampleInputEmail1">用户名：</label>
							 </div>
						<div class="col-md-6 col-sm-5 col-xs-6">
							 <input type="text" class="form-control" id="exampleInputEmail1" name="username"/>
						</div>
						</div>
						<div class="row form-group">
							<div class="col-md-3 col-sm-4 col-xs-4 text-right">
								<span class="glyphicon glyphicon-lock"></span>
							 <label for="exampleInputPassword1">密码：</label>
							 </div>
							 <div class="col-md-6 col-sm-5 col-xs-6">
							 <input type="password" class="form-control" id="exampleInputPassword1" name="password" />
							 </div>
						</div>
						<div class="row form-group">
						<div class="col-md-4 col-md-offset-2 col-sm-offset-2 col-xs-offset-4">
						<div class="checkbox">
							 <label><input type="checkbox" />记住密码</label>
						</div>
						</div>
						</div>
						<div class="row form-group">
						<div class="col-md-4 col-md-offset-2 col-sm-offset-2 col-xs-offset-4">
						<button type="submit" class="btn btn-default btn-success">登录</button>
						</div>
						</div>
						</form>
				</div>
				
			</div>
		</div>
	</div>
	</body>
</html>
