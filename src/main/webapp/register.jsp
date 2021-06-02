<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if (request.getAttribute("msg") == null) {
  request.setAttribute("msg", "abaaba");
}%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>register</title>

    <style>
      body{
        background-image: linear-gradient(to left,#ab9cf8,#56bdf5);
        display:flex;
        justify-content: center;
      }
      .a{
      position:relative;
      top:100px;
      bottom: 100px;
      width:1100px;
      height:500px;
      box-shadow: 0 5px 15px rgba(0,0,0,0.8);
      display: flex;
    }
    .b{
      width:1100px;
      height: 550px;
      background-image:url("image/picture.gif");
      background-size: cover;
    }
    .c{
      width:300px;
      height:550px;
      background-color: white;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .d{
      width: 250px;
      height: 550px;
    }
    .d h1{
      font:900 30px '';
    }
    .e{
      width: 200px;
      margin: 10px 0;
      outline: none;
      border:0;
      padding: 10px;
      border-bottom: 3px solid rgb(80,80,80,170);
      font:900 15px '';
    }
    #register{
      width: 150px;
      height: 40px;
      background-color: #90a6ee;
    }

    
    </style>
</head>

<body>
<form action="userRegisterServlet" method="post" id="registerForm"><br><br><br>
    <div class="a">
      <div class="b">
        <div class="c">
          <div class="d">
          
            <h1>Register</h1>
            <tr>
              <td><input type="text" name="username" id="username" class="e" placeholder=" Input your username"></td>
            </tr>
            <tr>
              <td><input type="password" name="password" id="password" class="e" placeholder=" Input your password"></td>
            </tr>
            <tr>
              <td><input type="text" name="surname" id="surname" class="e" placeholder=" Input your surname"></td>
            </tr>
            <tr>
              <td><input type="text" name="forename" id="forename" class="e" placeholder=" Input your forename"></td>
            </tr>
            <tr>
              <td ><label>GENDER </label></td>
              <td>
                <input type="radio" name="sex" class="f" value="male">Male
                <input type="radio" name="sex" class="f" value="female">Female<br>
              </td>
            </tr>
            <br> <span style="font-size: 16px;color:red" align="center"><%=request.getAttribute("msg")%></span><br><br>
            <tr>
              <td colspan="2" align="center"><input type="submit"  id= "register" value="register" style="font-size: 25px;"></td>
            </tr>
          
          </div>
        </div>
      </div>
    </div>

</form>
</body>
</html>