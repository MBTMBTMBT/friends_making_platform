<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if (request.getAttribute("msg") == null) {
request.setAttribute("msg", "Please entre your user/admin ID");
}%>
<html lang="en">
<head>
  <link rel="stylesheet" href="../csss/login_css.css">
  <title>user login</title>
</head>

<body>

<div class="header">
  
  <div class="lnner-header">
   <h1>LOGIN</h1>
  </div>
  
<div class="inputBox">
  <div style="text-align: center">
    <form action="loginServlet" method = "post" id="loginForm">
      <br>
      
         <input type = "text" placeholder="NAME" name = "username" style="font-size: large" 
         style="background-color:rgba(0,0,1,0.5)"> <br><br><br>
         <input type = "password" placeholder="PASSWORD" name = "password" style="font-size: large"> <br>

      <span id="msg" style="font-size: 12px;color:red"></span> <br> 

        <button type="button">login</button>   
        <button type="button">register</button>
  
    </form>
  </div>
</div>
  

<svg class="waves" viewBox="0 24 150 28"
  preserveAspectRatio="none" shape-rendering="auto">

  <defs>
    <path id="gentle-wave"
         d="M-160 44c30 0 58-18 88-18s 58 18 88 18
         58-18 88-18 58 18 88 18 v44h-352z" />
  </defs>

  <g class="parallax">
    <use xlink:href="#gentle-wave" x="48" y="0"
    fill="rgba(255,255,255,0.7)" />
    <use xlink:href="#gentle-wave" x="48" y="3"
    fill="rgba(255,255,255,0.5)" />
    <use xlink:href="#gentle-wave" x="48" y="5"
    fill="rgba(255,255,255,0.3)" />
    <use xlink:href="#gentle-wave" x="48" y="7"
    fill="#fff" />
  </g>
</svg>
</div>

</body>
</html>