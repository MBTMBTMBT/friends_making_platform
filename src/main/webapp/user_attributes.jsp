<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if (request.getAttribute("msg") == null) {
    request.setAttribute("msg", "no info");
  }
  String username = (String) request.getAttribute("user_username");
  int userID = (int) request.getAttribute("user_id");
  // System.out.println(username);
  // System.out.println(userID);
%>
<%
  String message0 = (String) request.getAttribute("msg0");
  String message1 = (String) request.getAttribute("msg1");
  String message2 = (String) request.getAttribute("msg2");
  String message3 = (String) request.getAttribute("msg3");
  String message4 = (String) request.getAttribute("msg4");
  String message5 = (String) request.getAttribute("msg5");
  String message6 = (String) request.getAttribute("msg6");
  String message7 = (String) request.getAttribute("msg7");
  String message8 = (String) request.getAttribute("msg8");
  String message9 = (String) request.getAttribute("msg9");
  String message10 = (String) request.getAttribute("msg10");
  String message11 = (String) request.getAttribute("msg11");
  String usernameMsg = (request.getAttribute("msg_username") != null) ? (String) request.getAttribute("msg_username"): "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>attributes</title>
    <link rel="stylesheet" href="static/css/user_attributes.css">
  <style>
    table{border-spacing: 30px}
  </style>
</head>
<body>
<form action="setUserAttributesServlet" method="post"><br>
  <table align="center">
    <tr>
      <td>
        <input type="hidden" name="user_username" id="user_username" value=<%=username%>>
        <input type="hidden" name="user_id" id="user_id" value=<%=userID%>>
        <label for="screenname" style="color: white;font-size: larger;font-weight:bolder">username</label>
        <br><span style="font-size: 16px;color:red"><%=request.getAttribute("msg0")%></span><br>
      </td>
      <td><input type="text" name="screenname" id="screenname" style="font-size:smaller;font-family: Arial, Helvetica, sans-serif;" value=<%=message0%>></td>
      <td colspan="2" align="center"><input type="submit" value="submit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>
    <tr>
      <td>
        <label for="email" style="color: white;font-size: larger;font-weight:bolder">E-mail</label>
        <br> <span style="font-size: 16px;color:red"><%=(usernameMsg.equals(""))? request.getAttribute("msg1") : usernameMsg%></span><br>
      </td>
      <%
        if (message1.equals("entre your email")) message1 = null;
      %>
      <td><input type="email" name="email" id="email" style="font-size: smaller;font-family: Arial, Helvetica, sans-serif;" placeholder="entre your email"
                 value=<%=(message1 == null)? "": message1%>></td>
      <td colspan="2" align="center"><input type="submit" value="submit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>
    <tr>
      <td>
        <label for="Wechat" style="color: white;font-size: larger;font-weight:bolder">Wechat</label>
        <%
          if (message2.equals("entre your wechat")) message2 = null;
        %>
        <br> <span style="font-size: 16px;color:red"><%=request.getAttribute("msg2")%></span><br>
      </td>
      <td><input type="text" name="Wechat" id="Wechat" style="font-size: smaller;font-family: Arial, Helvetica, sans-serif;" placeholder="entre your wechat"
                 value=<%=(message2 == null)? "": message2%>></td>
      <td colspan="2" align="center"><input type="submit" value="submit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>
    <tr>
      <td>
        <label for="birthday" style="color: white;font-size: larger;font-weight:bolder">birthday</label>
        <br><span style="font-size: 16px;color:red"><%=request.getAttribute("msg3")%></span>
      </td>

      <br>
      <td><input type="date" name="birthday" id="birthday" ></td>
      <td colspan="2" align="center"><input type="submit" value="submit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>

    <tr>
      <td>
        <label style="color: white;font-size: larger;font-weight:bolder">gender preference</label>
        <br><span style="font-size: 16px;color:red"><%=request.getAttribute("msg4")%></span><br>
      </td>
      <td>
        <input type="radio" name="orientation" value="male">male
        <input type="radio" name="orientation" value="female">female
      </td>
      <td colspan="2" align="center"><input type="submit" value="submit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>

    <tr>
      <td>
        <label for="slogan" style="color: white;font-size: larger;font-weight:bolder">slogan</label>
        <br><span style="font-size: 16px;color:red"><%=request.getAttribute("msg5")%></span><br>
      </td>
        <%
          if (message5 == null || message5.equals("entre your slogan")) message5 = "";
        %>
      <td>
        <textarea type="text" rows="10" cols="40" name="slogan" id="slogan" style="font-size: smaller;font-family: Arial, Helvetica, sans-serif;" placeholder="entre your slogan"></textarea>
      </td>
      <td colspan="2" align="center"><input type="submit" value="submit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>
    <script>
      document.getElementById("slogan").value="<%=message5%>"
    </script>

    <tr>
      <td>
        <label style="color: white;font-size: larger;font-weight:bolder">industry</label>
        <br><span style="font-size: 16px;color:red"><%=request.getAttribute("msg6")%></span><br>
      </td>
      <td>
        <input type="radio" name="work" value="not selected">not selected<br>
        <input type="radio" name="work" value="student">student<br>
        <input type="radio" name="work" value="culture/art">culture/art<br>
        <input type="radio" name="work" value="entertainment business">entertainment business<br>
        <input type="radio" name="work" value="finance">finance<br>
        <input type="radio" name="work" value="medicine">medicine<br>
        <input type="radio" name="work" value="manufacture">manufacture<br>
        <input type="radio" name="work" value="IT">IT<br>
        <input type="radio" name="work" value="media">media<br>
        <input type="radio" name="work" value="education/research">education/research<br>
        <input type="radio" name="work" value="sales">sales<br>
        <input type="radio" name="work" value="others">others<br>
      </td>
      <td colspan="2" align="center"><input type="submit" value="submit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>

    <tr>
      <td>
        <label style="color: white;font-size: larger;font-weight:bolder"> Sports</label>
        <br><span style="font-size: 16px;color:red"><%=request.getAttribute("msg7")%></span><br>
      </td>

      <td>
        <input type="checkbox" name="sports" value="gym">gym
        <input type="checkbox" name="sports" value="ski">ski
        <input type="checkbox" name="sports" value="swim">swim
        <input type="checkbox" name="sports" value="run">run<br>
        <input type="checkbox" name="sports" value="bicycle">bicycle
        <input type="checkbox" name="sports" value="yoga">yoga
        <input type="checkbox" name="sports" value="basketball">basketball<br>
        <input type="checkbox" name="sports" value="football">football
        <input type="checkbox" name="sports" value="skateboard">skateboard
        <input type="checkbox" name="sports" value="table tennis">table tennis<br>
        <input type="checkbox" name="sports" value="tennis">tennis
        <input type="checkbox" name="sports" value="golf">golf
        <input type="checkbox" name="sports" value="billiards">billiards
        <input type="checkbox" name="sports" value="dance">dance<br>
        <input type="checkbox" name="sports" value="street dance">street dance
        <input type="checkbox" name="sports" value="archery">archery
        <input type="checkbox" name="sports" value="fencing">fencing<br>
        <input type="checkbox" name="sports" value="shooting">shooting
        <input type="checkbox" name="sports" value="boxing">boxing
        <input type="checkbox" name="sports" value="Taekwondo">taekwondo<br>
        <input type="checkbox" name="sports" value="mountain climbing">mountain climbing
        <input type="checkbox" name="sports" value="horseback riding">horseback riding<br>
        <input type="checkbox" name="sports" value="extreme sports">extreme sports
        <input type="checkbox" name="sports" value="volleyball">volleyball
        <input type="checkbox" name="sports" value="sleep">sleep
      </td>
      <td colspan="2" align="center"><input type="submit" value="submit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>
    <tr>
      <td>
        <label style="color: white;font-size: larger;font-weight:bolder">Food</label>
        <br><span style="font-size: 16px;color:red"><%=request.getAttribute("msg8")%></span><br>
      </td>

      <td>
        <input type="checkbox" name="foods" value="Beijing Roast Duck">Beijing Roast Duck
        <input type="checkbox" name="foods" value="Hong Kong morning tea">Hong Kong morning tea<br>
        <input type="checkbox" name="foods" value="hot pot">hot pot
        <input type="checkbox" name="foods" value="barbecue">barbecue
        <input type="checkbox" name="foods" value="spicy pot">spicy pot<br>
        <input type="checkbox" name="foods" value="lobster">lobster
        <input type="checkbox" name="foods" value="dumplings">dumplings
        <input type="checkbox" name="foods" value="braised pork rice">braised pork rice<br>
        <input type="checkbox" name="foods" value="sushi">sushi
        <input type="checkbox" name="foods" value="sashimi">sashimi
        <input type="checkbox" name="foods" value="Japanese ramen">Japanese ramen<br>
        <input type="checkbox" name="foods" value="Japanese style Teppanyaki">Japanese style Teppanyaki
        <input type="checkbox" name="foods" value="bibimbap">bibimbap<br>
        <input type="checkbox" name="foods" value="Korean barbecue">Korean barbecue
        <input type="checkbox" name="foods" value="Thai food">Thai food
        <input type="checkbox" name="foods" value="steak">steak<br>
        <input type="checkbox" name="foods" value="pasta">pasta
        <input type="checkbox" name="foods" value="Mexican Tacos">Mexican Tacos
        <input type="checkbox" name="foods" value="pizza">pizza<br>
        <input type="checkbox" name="foods" value="hamburg">hamburg
        <input type="checkbox" name="foods" value="French fries">French fries
        <input type="checkbox" name="foods" value="American Fried Chicken">American Fried Chicken<br>
        <input type="checkbox" name="foods" value="kabob">kabob
        <input type="checkbox" name="foods" value="vegetarian diet">vegetarian diet
        <input type="checkbox" name="foods" value="tiramisu">tiramisu<br>
        <input type="checkbox" name="foods" value="Mousse cake">Mousse cake
        <input type="checkbox" name="foods" value="cheese">cheese
        <input type="checkbox" name="foods" value="chocolate">chocolate
        <input type="checkbox" name="foods" value="ice cream">ice cream
      </td>
      <td colspan="2" align="center"><input type="submit" value="submit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>
    <tr>
      <td>
        <label style="color: white;font-size: larger;font-weight:bolder">Travel Footprint</label>
        <br><span style="font-size: 16px;color:red"><%=request.getAttribute("msg9")%></span><br>
      </td>

      <td>
        <input type="checkbox" name="Travel Footprint" value="Chengdu">Chengdu
        <input type="checkbox" name="Travel Footprint" value="Guilin">Guilin
        <input type="checkbox" name="Travel Footprint" value="Sanya">Sanya
        <input type="checkbox" name="Travel Footprint" value="Lijiang">Lijiang<br>
        <input type="checkbox" name="Travel Footprint" value="Dali">Dali
        <input type="checkbox" name="Travel Footprint" value="Xianggelila">Xianggelila
        <input type="checkbox" name="Travel Footprint" value="Xizang">Xizang
        <input type="checkbox" name="Travel Footprint" value="Gulangyu">Gulangyu<br>
        <input type="checkbox" name="Travel Footprint" value="Zhangjiajie">Zhangjiajie
        <input type="checkbox" name="Travel Footprint" value="Jiuzhaigou">Jiuzhaigou<br>
        <input type="checkbox" name="Travel Footprint" value="Japan">Japan
        <input type="checkbox" name="Travel Footprint" value="Korean">Korean
        <input type="checkbox" name="Travel Footprint" value="Jeju">Jeju
        <input type="checkbox" name="Travel Footprint" value="Bali">Bali
        <input type="checkbox" name="Travel Footprint" value="Phuket">Phuket<br>
        <input type="checkbox" name="Travel Footprint" value="Boracay">Boracay
        <input type="checkbox" name="Travel Footprint" value="Saipan">Saipan
        <input type="checkbox" name="Travel Footprint" value="Singapore">Singapore
        <input type="checkbox" name="Travel Footprint" value="Malaysia">Malaysia<br>
        <input type="checkbox" name="Travel Footprint" value="Thailand">Thailand
        <input type="checkbox" name="Travel Footprint" value="Philippines">Philippines
        <input type="checkbox" name="Travel Footprint" value="Indonesia">Indonesia
        <input type="checkbox" name="Travel Footprint" value="India">India<br>
        <input type="checkbox" name="Travel Footprint" value="Nepal">Nepal
        <input type="checkbox" name="Travel Footprint" value="Dubai">Dubai
        <input type="checkbox" name="Travel Footprint" value="Turkey">Turkey
        <input type="checkbox" name="Travel Footprint" value="Greek">Greek
        <input type="checkbox" name="Travel Footprint" value="US">US<br>
        <input type="checkbox" name="Travel Footprint" value="Canada">Canada
        <input type="checkbox" name="Travel Footprint" value="Australia">Australia
        <input type="checkbox" name="Travel Footprint" value="Britain">Britain
        <input type="checkbox" name="Travel Footprint" value="France">France<br>
        <input type="checkbox" name="Travel Footprint" value="Italy">Italy
        <input type="checkbox" name="Travel Footprint" value="Spain">Spain
        <input type="checkbox" name="Travel Footprint" value="Portugal">Portugal
        <input type="checkbox" name="Travel Footprint" value="Netherlands">Netherlands<br>
        <input type="checkbox" name="Travel Footprint" value="Belgium">Belgium
        <input type="checkbox" name="Travel Footprint" value="Swiss">Swiss
        <input type="checkbox" name="Travel Footprint" value="Swedish">Swedish
        <input type="checkbox" name="Travel Footprint" value="Danish">Danish
        <input type="checkbox" name="Travel Footprint" value="Finland">Finland<br>
        <input type="checkbox" name="Travel Footprint" value="Czech">Czech
        <input type="checkbox" name="Travel Footprint" value="Cuba">Cuba
        <input type="checkbox" name="Travel Footprint" value="Argentina">Argentina
        <input type="checkbox" name="Travel Footprint" value="Brazil">Brazil
        <input type="checkbox" name="Travel Footprint" value="Russia">Russia<br>
        <input type="checkbox" name="Travel Footprint" value="England">England
        <input type="checkbox" name="Travel Footprint" value="Ireland">Ireland
        <input type="checkbox" name="Travel Footprint" value="Germany">Germany
      </td>
      <td colspan="2" align="center"><input type="submit" value="submit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>
    <tr>
      <td>
        <label style="color: white;font-size: larger;font-weight:bolder">Films</label>
        <br><span style="font-size: 16px;color:red"><%=request.getAttribute("msg10")%></span><br>
      </td>

      <td>
        <input type="checkbox" name="film" value="The Shawshank Redemption">The Shawshank Redemption
        <input type="checkbox" name="film" value="Forrest Gump">Forrest Gump<br>
        <input type="checkbox" name="film" value="Inception">Inception
        <input type="checkbox" name="film" value="The Matrix">The Matrix<br>
        <input type="checkbox" name="film" value="The Legend Of 1900">The Legend Of 1900
        <input type="checkbox" name="film" value="Spirited Away">Spirited Away<br>
        <input type="checkbox" name="film" value="Roman Holiday">Roman Holiday
        <input type="checkbox" name="film" value="Schindler's List">Schindler's List<br>
        <input type="checkbox" name="film" value="Amelie from Montmartre">Amelie from Montmartre
        <input type="checkbox" name="film" value="A Beautiful Mind">A Beautiful Mind<br>
        <input type="checkbox" name="film" value="Farewell My concubine">Farewell My concubine
        <input type="checkbox" name="film" value="Leon">Leon<br>
        <input type="checkbox" name="film" value="The Godfather">The Godfather
        <input type="checkbox" name="film" value="Titanic">Titanic<br>
        <input type="checkbox" name="film" value="Batman">Batman
        <input type="checkbox" name="film" value="Pulp Fiction">Pulp Fiction<br>
        <input type="checkbox" name="film" value="Fight Club">Fight Club
        <input type="checkbox" name="film" value="The Upside">The Upside<br>
        <input type="checkbox" name="film" value="Hachiko: A Dog's Story">Hachiko: A Dog's Story
        <input type="checkbox" name="film" value="Gone with the Wind">Gone with the Wind<br>
        <input type="checkbox" name="film" value="The Pursuit of Happiness">The Pursuit of Happiness
        <input type="checkbox" name="film" value="One Flew Over the Cuckoo's Nest">One Flew Over the Cuckoo's Nest<br>
        <input type="checkbox" name="film" value="Scent Of A Woman">Scent Of A Woman
        <input type="checkbox" name="film" value="Dead Poets Society">Dead Poets Society<br>
        <input type="checkbox" name="film" value="The Lord of the Rings">The Lord of the Rings
        <input type="checkbox" name="film" value="Harry Potter">Harry Potter<br>
        <input type="checkbox" name="film" value="Infernal Affairs">Infernal Affairs
        <input type="checkbox" name="film" value="American Dreams in China">American Dreams in China<br>
        <input type="checkbox" name="film" value="Chungking express">Chungking express
        <input type="checkbox" name="film" value="the silence of the lambs">the silence of the lambs
      </td>
      <td colspan="2" align="center"><input type="submit" value="submit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>
    <tr>
      <td>
        <label style="color: white;font-size: larger;font-weight:bolder">Books</label>
        <br><span style="font-size: 16px;color:red"><%=request.getAttribute("msg11")%></span><br>
      </td>
      <td>
        <input type="checkbox" name="Books" value="A Tale of Two Cities">A Tale of Two Cities
        <input type="checkbox" name="Books" value="Anna Karenina">Anna Karenina<br>
        <input type="checkbox" name="Books" value="Crime and Punishment">Crime and Punishment
        <input type="checkbox" name="Books" value="David Copperfield">David Copperfield<br>
        <input type="checkbox" name="Books" value="Emma">Emma
        <input type="checkbox" name="Books" value="For Whom the Bell Tolls">For Whom the Bell Tolls<br>
        <input type="checkbox" name="Books" value="Gone with the Wind">Gone with the Wind
        <input type="checkbox" name="Books" value="Great Expectations">Great Expectations<br>
        <input type="checkbox" name="Books" value="Hamlet">Hamlet
        <input type="checkbox" name="Books" value="Jane Eyre">Jane Eyre
        <input type="checkbox" name="Books" value="Jean Christophe">Jean Christophe<br>
        <input type="checkbox" name="Books" value="King Lear">King Lear
        <input type="checkbox" name="Books" value="Les Miserables">Les Miserables<br>
        <input type="checkbox" name="Books" value="Little Women">Little Women
        <input type="checkbox" name="Books" value="Oliver Twist">Oliver Twist<br>
        <input type="checkbox" name="Books" value="Pride and Prejudice">Pride and Prejudice
        <input type="checkbox" name="Books" value="Resurrection">Resurrection<br>
        <input type="checkbox" name="Books" value="Robinson Crusoe">Robinson Crusoe
        <input type="checkbox" name="Books" value="Sense and Sensibility">Sense and Sensibility<br>
        <input type="checkbox" name="Books" value="The Great Gatsby">The Great Gatsby
        <input type="checkbox" name="Books" value="The Old Man and the Sea">The Old Man and the Sea
        <input type="checkbox" name="Books" value="Walden">Walden<br>
      </td>
      <td colspan="2" align="center"><input type="submit" value="submit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><input type="submit" value="submit all" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
      <td colspan="2" align="center"><input type="button" onclick="window.location.href='user_mainpage.jsp';"
                                            value="confirm and exit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>
  </table>
</form>
</body>
</html>