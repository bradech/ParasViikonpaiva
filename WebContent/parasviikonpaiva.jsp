<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Paras viikonpäivä</title>
<style>
#tulokset{
	position:absolute;
	top:46px;
	left:65px;
}
.palkki{
	background-color:lightblue;	
	font-size:18px;
	height:20px;
}
.palkki2{
	background-color:lightgray;	
	font-size:18px;
	height:20px;
}
</style>
</head>
<body>
Valitse paras viikonpäivä:<br><br>
<%

String [] paiva = {"ma","ti","ke","to","pe","la","su"};
out.println("<form action='HaeTallennaPaiva' method='get'>");
out.println("<input type='radio' name='paiva' value='"+0+"' checked='checked'/> " + paiva[0] + "<br>");
for(int i=1;i<7;i++){
	out.println("<input type='radio' name='paiva' value='"+i+"'/> " + paiva[i] + "<br>");
}
out.println("<br><input type='submit' value='Lähetä'>");
out.println("</form><br>");

if(request.getAttribute("paivat")!=null){
	out.println("<div id='tulokset'>");
	String [] paivat = String.valueOf(request.getAttribute("paivat")).split(";");
	for(int i=0;i<7;i++){		
		if(i % 2 ==0){
			out.println("<div class='palkki' style='width:"+(10+Integer.parseInt(paivat[i])*10)+"px' >&nbsp;</div>");	
		}else{
			out.println("<div class='palkki2' style='width:"+(10+Integer.parseInt(paivat[i])*10)+"px' >&nbsp;</div>");	
		}			
	}
	out.println("</div>");
}



%>
</body>
</html>
