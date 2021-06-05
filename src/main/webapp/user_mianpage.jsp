<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
  <link rel="stylesheet" type="text/css" href="static/css/userpage_css/bootstrap.min.css">
  <link rel="stylesheet" href="static/css/userpage_css/style.css">
</head>
<style>
	.table
	{
		width:500px;
	}

	/* 容器 <div> - 需要定位下拉内容 */
	.dropdown {
		position: relative;
		display: inline-block;
	}

	/* 下拉内容 (默认隐藏) */
	.dropdown-content {
		display: none;
		position: absolute;
		background-color: #f9f9f9;
		min-width: 160px;
		box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
	}

	/* 下拉菜单的链接 */
	.dropdown-content a {
		color: black;
		padding: 12px 16px;
		text-decoration: none;
		display: block;
	}

	/* 鼠标移上去后修改下拉菜单链接颜色 */
	.dropdown-content a:hover {background-color: #ac79e6}

	/* 在鼠标移上去后显示下拉菜单 */
	.dropdown:hover .dropdown-content {
		display: block;
	}


</style>
<body>

<h1 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(167,10,10) 2.5px 1.5px 1.5px;">My Information</h1>

<br>
<div align="center">
<div class="dropdown">
	<button class="btn btn-toolbar">change</button>
	<div class="dropdown-content">
		<a class="btn btn-info" href="Users_Attributes.html">change my characters</a>
		<a class="btn btn-info" href="head_icon.html">change my head icon</a>
		<a class="btn btn-info" href="modify.html">change my information</a>
	</div>
</div>
	<a class="btn btn-danger" href="Recommendations.html">Recommendations</a>
	<a class="btn btn-primary" href="Events.html">Events</a>
	<a class="btn btn-warning" href="Likes.html">Likes</a>
	<a class="btn btn-success" href="my_mentor.html">My mentor</a>
	<a class="btn btn-info" href="login.html">log out</a>
</div>
<br>
	<div align="center" >
		<img border="0" src="static/images/headicon.png"  width="250" height="228">
		<br><br>
	<table  class="table table-hover table-bordered " >
		<tr class="active">
			<td align="center" width="100px">screenname</td>
			<td align="center">TMD</td>
		</tr>
		<tr class="success">
			<td align="center" width="100px">gender</td>
			<td align="center">TMD</td>
		</tr>
		<tr class="warning">
			<td align="center" width="100px">location</td>
			<td align="center" >TMD</td>
		</tr>
		<tr class="danger">
			<td align="center" width="100px">work</td>
			<td align="center">TMD</td>
		</tr>
		<tr class="info">
			<td align="center" width="100px">food</td>
			<td align="center" >TMD</td>
		</tr>
		<tr class="active">
			<td align="center" width="100px">films</td>
			<td align="center" >TMD</td>
		</tr>
		<tr class="success">
			<td align="center" width="100px">sports</td>
			<td align="center" >TMD</td>
		</tr>
		<tr class="warning">
			<td align="center" width="100px">books</td>
			<td align="center" >TMD</td>
		</tr>
		<tr class="danger">
			<td align="center" width="100px">slogan</td>
			<td align="center" >TMD</td>
		</tr>

	</table>

	</div>
</body>
</html>