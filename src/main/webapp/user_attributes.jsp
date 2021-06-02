<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if (request.getAttribute("msg") == null) {
  request.setAttribute("msg", "abaaba");
}%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>attributes</title>
    <link rel="stylesheet" href="./css/style.css">
  <style>
    table{border-spacing: 30px}
  </style>
</head>
<body>
<form action="#" method="post"><br>
  <table align="center" >
    <tr>
      <td>
        <label for="screenname"  style="color: white;font-size: larger;font-weight:bolder">screenname</label>
        <br> <span style="font-size: 16px;color:red" align="center"><%=request.getAttribute("msg")%></span><br>
      </td>
      <td><input type="text" name="screenname" id="screenname" style="font-size:smaller;font-family: Arial, Helvetica, sans-serif;" placeholder=" input your screenname"></td>
    </tr>
    <tr>
      <td><label for="email" style="color: white;font-size: larger;font-weight:bolder">E-mail</label></td>
      <td><input type="email" name="email" id="email" style="font-size: smaller;font-family: Arial, Helvetica, sans-serif;" placeholder=" input your E-mail"></td>
    </tr>
    <tr>
      <td><label for="Wechat" style="color: white;font-size: larger;font-weight:bolder">Wechat</label></td>
      <td><input type="text" name="Wechat" id="Wechat" style="font-size: smaller;font-family: Arial, Helvetica, sans-serif;" placeholder=" input your Wechat"></td>
    </tr>
    <tr>
      <td><label for="birthday" style="color: white;font-size: larger;font-weight:bolder">birthday</label></td>
      <td><input type="date" name="birthday" id="birthday" ></td>
    </tr>
    <tr>
      <td><label style="color: white;font-size: larger;font-weight:bolder"> gender orientation</label></td>
      <td>
        <input type="radio" name="orientation" value="hetero">hetero
        <input type="radio" name="orientation" value="homosexual">homosexual
      </td>
    </tr>
    <tr>
      <td><label for="slogan" style="color: white;font-size: larger;font-weight:bolder">slogan</label></td>
      <td><textarea type="text" rows="10" cols="40" name="slogan" id="slogan" style="font-size: smaller;font-family: Arial, Helvetica, sans-serif;" placeholder=" input your slogan"></textarea></td>
    </tr>
    <tr>
      <td><label style="color: white;font-size: larger;font-weight:bolder"> Sports</label></td>
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
    </tr>
    <tr>
      <td><label style="color: white;font-size: larger;font-weight:bolder">Foods</label></td>
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
    </tr>
    <tr>
      <td><label style="color: white;font-size: larger;font-weight:bolder">Travel Footprint</label></td>
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
        <input type="checkbox" name="Travel Footprint" value="Jiuzhaigou">Jiuzhaigou
        <input type="checkbox" name="Travel Footprint" value="Taiwan">Taiwan<br>
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
        <input type="checkbox" name="Travel Footprint" value="French">French<br>
        <input type="checkbox" name="Travel Footprint" value="Italy">Italy
        <input type="checkbox" name="Travel Footprint" value="Spain">Spain
        <input type="checkbox" name="Travel Footprint" value="Portugal">Portugal
        <input type="checkbox" name="Travel Footprint" value="Netherlands">Netherlands<br>
        <input type="checkbox" name="Travel Footprint" value="Belgium">Belgium
        <input type="checkbox" name="Travel Footprint" value="Swiss">Swiss
        <input type="checkbox" name="Travel Footprint" value="Swedish">Swedish
        <input type="checkbox" name="Travel Footprint" value="Danish">Danish
        <input type="checkbox" name="Travel Footprint" value="Finland">Finland
        <input type="checkbox" name="Travel Footprint" value="Danish">Danish<br>
        <input type="checkbox" name="Travel Footprint" value="Finland">Finland
        <input type="checkbox" name="Travel Footprint" value="Czech">Czech
        <input type="checkbox" name="Travel Footprint" value="Cuba">Cuba
        <input type="checkbox" name="Travel Footprint" value="Argentina">Argentina
        <input type="checkbox" name="Travel Footprint" value="Brazil">Brazil
        <input type="checkbox" name="Travel Footprint" value="Russia">Russia<br>
        <input type="checkbox" name="Travel Footprint" value="England">England
        <input type="checkbox" name="Travel Footprint" value="Ireland">Ireland
        <input type="checkbox" name="Travel Footprint" value="Germany">Germany

      </td>
    </tr>
    <tr>
      <td><label style="color: white;font-size: larger;font-weight:bolder">Music</label></td>
      <td>
        <input type="checkbox" name="music" value="Europe and US">Europe and US
        <input type="checkbox" name="music" value="Janpan and Korea">Janpan and Korea
        <input type="checkbox" name="music" value="popular">popular
        <input type="checkbox" name="music" value="rock">rock<br>
        <input type="checkbox" name="music" value="electronic">electronic
        <input type="checkbox" name="music" value="R&B">R&B
        <input type="checkbox" name="music" value="hip hop">hip hop
        <input type="checkbox" name="music" value="bruce">bruce
        <input type="checkbox" name="music" value="metal">metal
        <input type="checkbox" name="music" value="light">light<br>
        <input type="checkbox" name="music" value="classical">classical
        <input type="checkbox" name="music" value="Jay Chou">Jay Chou
        <input type="checkbox" name="music" value="Eason Chen">Eason Chen
        <input type="checkbox" name="music" value="Lihong Wang">Lihong Wang<br>
        <input type="checkbox" name="music" value="Jingteng Xiao">Jingteng Xiao
        <input type="checkbox" name="music" value="Ziqi Deng">Ziqi Deng
        <input type="checkbox" name="music" value="Ruoying Liu">Ruoying Liu
        <input type="checkbox" name="music" value="Yanzi Sun">Yanzi Sun<br>
        <input type="checkbox" name="music" value="Huimei Zhang">Huimei Zhang
        <input type="checkbox" name="music" value="Wenwei Mo">Wenwei Mo
        <input type="checkbox" name="music" value="Wanting Qu">Wanting Qu
        <input type="checkbox" name="music" value="Dongye Song">Dongye Song<br>
        <input type="checkbox" name="music" value="Xueyou Zhang">Xueyou Zhang
        <input type="checkbox" name="music" value="Guorong Zhang">Guorong Zhang
        <input type="checkbox" name="music" value="Dehua Liu">Dehua Liu
        <input type="checkbox" name="music" value="Zongsheng Li">Zongsheng Li<br>
        <input type="checkbox" name="music" value="Dayou Luo">Dayou Luo
        <input type="checkbox" name="music" value="Bai Wu">Bai Wu
        <input type="checkbox" name="music" value="Feng Wang">Feng Wang
        <input type="checkbox" name="music" value="Beyond">Beyond


      </td>
    </tr>
    <tr>
      <td colspan="2" align="center"><input type="submit"value="OK" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
    </tr>
  </table>
</form>
</body
</html>