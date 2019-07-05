<%@page import="sun.java2d.pipe.SpanClipRenderer"%>
<%@page import="Servlet.Shisutemu"%>
<%@page import="Servlet.ConnectDB"%>
<%@page import="Servlet.UpdateServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Product</title>
</head>
<body style="background-image: url(image/1.jpg);" >
	<h1 style="margin-left: 30%; color: Brown">SHIRITORI GAME - しりとりゲーム</h1>
	
	<form >
		<br></br>
			<% 
				String k1 = request.getParameter("kotoba");
				String k2= request.getParameter("kotoba2");
				
				//int ko2 = k2.length()-1;
			%>
			<%
				//${UpdateServlet.endgame};
			%>
			<table border="3px"  style="width: 40%; height:270px; margin-left: 5% ; background-color: gold">
			<td>
				<h2 style="color: gray; margin-left: 35%">勝ちましょう</h2>
				<label style="margin-left: 10%"></label>
				<%
					if(request.getParameter("kotoba") == null || request.getParameter("kotoba") == ""){
				%>
						プレーヤー: <input  type="text" style="width: 90px" readonly="readonly"/>
				<% 	} else{
						int ko1 = k1.length() - 1;
						if( k1.substring(ko1).equals("n") != true){ %>
			
						プレーヤー: <input  type="text" style="width: 90px" value="<%=request.getParameter("kotoba") %>" readonly="readonly"/>
				<% 
				//k1.charAt(ko1) == "n" ||
				}	}
				%>
				
				<label style="margin-left: 2%"></label>
				<%
					if(request.getParameter("kotoba") == null || request.getParameter("kotoba") == ""){
				%>
						システム: <input type="text"  name ="kotoba2" style="width: 90px; "  readonly="readonly"/>
				<% 	} else {
						Shisutemu sp = new ConnectDB().getProductByID(request.getParameter("kotoba")); %>
						システム: <input type="text"  name ="kotoba2" style="width: 90px; " value="<%=sp.getKotoba() %>" readonly="readonly"/>
				<% 
				}
				%>
				<br></br>
				<label style="margin-left: 25%"></label>
				答え:
				<input style=" width: 90px" id="kotoba" name="kotoba" type="text"   />
				<input style="margin-left: 2%" type="submit" name="ok" value="続ける" onclick="getcube()"/>
				<br></br>
				<input style="margin-left: 42%" type="submit" name="delete" value="すべて削除"/>
			</td>
				
			</table>
		
		<table border="3px" cellpadding="10px"  style=" float: right; background-color: red; margin-top: -20%">
			<tr>
				<td>ゲームのルール</td>
			</tr>
			<tr>
				<td>
					<div>しりとりとは・・・</div>
					<div>言葉遊び。前の人が言った最後の言葉をとって、それで始まる語を言う。</div>
 					<div>それを順々に続ける。</div>
					<div>（ex） せんせい → いす → スーツ → つき → ・・・・・・ </div>
					<div>【ルール】 </div>
					<div>①一度でた言葉を言ったら負け</div>
					<div>②言葉の最後が”ん”になったら負け （ex） ふじさん</div>
				</td>
			</tr>
		</table>
		<table style="background-image: url(image/backgroup.jpg); width: 400px; height: 170px; margin-left: 35%"></table>
		<h1 style="color: orange; margin-left: 20%">あなたがすべてのゲームを楽しんでいることを願っています</h1>
	
		
	</form>
</body>
<script type="text/javascript">
	function getcube() {
	var kotoba = document.getElementById("kotoba").value;
	document.setElementById("kotoba").value = kotoba;
	//alert(number * number * number);
	}
</script>
</html>