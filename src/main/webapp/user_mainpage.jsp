<%--
  Created by IntelliJ IDEA.
  User: ZFB
  Date: 2021/6/3
  Time: 6:38 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="static/userpage_css/bootstrap.min.css">
    <link rel="stylesheet" href="static/userpage_css/style.css">


</head>
<body>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="static/css/userpage_css/bootstrap.min.js"></script>
<h1 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(156, 153, 153) 2.5px 1.5px 1.5px;">My Information</h1>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Brand</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                <li><a href="#">Link</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Link</a></li>

            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>




<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <span class="sr-only">Switch navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">CHOISES</a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="login.html">Log out</a></li>

                <li class="dropdown"> <!--导航栏下拉框change 唉 我就用中文 就是玩-->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Changes <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="Users_Attributes.html">Change my characters</a></li>
                        <li class="divider"></li>
                        <li><a href="head_icon.html">Change my head icon</a></li>
                        <li class="divider"></li>
                        <li><a href="modify.html">Change my information</a></li>
                    </ul>
                </li>


                <li><a href="Users_Attributes.html">Change my characters</a></li>
                <li class="active"><a href="head_icon.html">Change my head icon</a></li>
                <li><a href="modify.html">Change my information</a></li>
                <li class="active"><a href="Recommendations.html">Recommendations</a></li>
                <li><a href="Events.html">Events</a></li>
                <li class="active"><a href="Likes.html">Likes</a></li>
                <li><a href="my_mentor.html">My mentor</a></li>
            </ul>
        </div>
    </div>
</nav>

<br>

<div align="center">
    <img border="0" src="images/headicon.png"  width="250" height="228">
    <dl class="dl-horizontal">
        <div>
            <dt><span class="text-center" style="font-size: 20px;color: rgb(85, 74, 90);">screenname</span></dt>
            <dd><p class="text-center" style="font-size: 20px;color: rgb(107, 87, 114);">handsomeboy</p><!--bootstrap-css-标签--></dd>
        </div>
    </dl>

    <dl class="dl-horizontal">
        <div >
            <dt><span class="text-center" style="font-size: 20px;color: rgb(85, 74, 90);">gender</span></dt>
            <dd><p class="text-center" style="font-size: 20px;color: rgb(107, 87, 114);">male</p></dd>
        </div>
    </dl>

    <dl class="dl-horizontal">
        <div >
            <dt><span class="text-center" style="font-size: 20px;color: rgb(85, 74, 90);">Location</span></dt>
            <dd><p class="text-center" style="font-size: 20px;color: rgb(107, 87, 114);">Your heart</p></dd>
        </div>
    </dl>


    <dl class="dl-horizontal">
        <div >
            <dt><span class="text-center" style="font-size: 20px;color: rgb(85, 74, 90);">Work</span></dt>
            <dd><dd><p class="text-center" style="font-size: 20px;color: rgb(107, 87, 114);">duck</p></dd></dd>
        </div>
    </dl>

    <dl class="dl-horizontal">
        <div >
            <dt><span class="text-center" style="font-size: 20px;color: rgb(85, 74, 90);">Food</span></dt>
            <dd><dd><p class="text-center" style="font-size: 20px;color: rgb(107, 87, 114);">air</p></dd></dd>
        </div>
    </dl>

    <dl class="dl-horizontal">
        <div >
            <dt><span class="text-center" style="font-size: 20px;color: rgb(85, 74, 90);">Films</span></dt>
            <dd><dd><p class="text-center" style="font-size: 20px;color: rgb(107, 87, 114);">the killer not cold</p></dd></dd>
        </div>
    </dl>

    <dl class="dl-horizontal">
        <div >
            <dt><span class="text-center" style="font-size: 20px;color: rgb(85, 74, 90);">Sports</span></dt>
            <dd><dd><p class="text-center" style="font-size: 20px;color: rgb(107, 87, 114);">basketball</p></dd></dd>
        </div>
    </dl>

    <dl class="dl-horizontal">
        <div >
            <dt><span class="text-center" style="font-size: 20px;color: rgb(85, 74, 90);">Books</span></dt>
            <dd><dd><p class="text-center" style="font-size: 20px;color: rgb(107, 87, 114);">how to be a green tea boy</p></dd></dd>
        </div>
    </dl>

    <dl class="dl-horizontal">
        <div >
            <dt><span class="text-center" style="font-size: 20px;color: rgb(85, 74, 90);">slogan</span></dt>
            <dd><table border="1"  width="200" >
                <tr>
                    <td style="height: 100px;font-size: 16px;color: rgb(107, 87, 114);" >I am a handsome boy!!!!</td>
                </tr>
            </table></dd>
        </div>
    </dl>

    <br>
</div>
</body>
</html>
