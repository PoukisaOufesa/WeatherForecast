<%@page import="com.weather.controller.ViewController"%>
<%@page import="com.weather.xmlObjects.ForecastData"%>
<%@page import="java.util.*"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%

  ArrayList<LinkedHashMap<String, String>> forecasts = new ArrayList<LinkedHashMap<String, String>>();
  forecasts =  ViewController.forecasts;
  int numberOfForecasts = ViewController.forecastsListSizeModified;
  String nof = Integer.toString(numberOfForecasts);
  request.setAttribute("forecasts", forecasts);
  request.setAttribute("numberOfForecasts", nof);
  Integer.parseInt((String)request.getAttribute("numberOfForecasts"));

%>

<!DOCTYPE html>
<html lang="en">
<head>
	<link href="<spring:url value="/resources/css/main.css" />" rel="stylesheet">
	<spring:url value="/resources/js/main.js" var="mainJs" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> 
	<script src="${mainJs}"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Weather</title>	
</head>
<body>

<div id="title"></div>
<span id="description"></span>

<c:forEach var="counter" begin="0" end="${numberOfForecasts}">
	<div class="note">
	<c:forEach items="${forecasts[counter]}" var="forecast">
		<c:choose>
		<c:when test="${(counter % 2) == 0}">
		<div class="background night">
			<table style="width:100%">
			<tr>
			    <td>
				    <div class="background night left">
						<c:out value="${forecast.key}"/>
					</div>
			    </td>
			    <td>
			    	<div class="background night center">
						<c:out value="${forecast.value}"/> 
					</div>
			    </td>
			  </tr>
			</table>
		</div>
		</c:when>
		<c:otherwise>
		<div class="background day">		
			<table style="width:100%">
			<tr>
			    <td>
				    <div class="background day left">
						<c:out value="${forecast.key}"/>
					</div>
			    </td>
			    <td>
			    	<div class="background day center">
						<c:out value="${forecast.value}"/> 
					</div>
			    </td>
			  </tr>
			</table>
		</div>
		</c:otherwise>
		</c:choose>
	</c:forEach>
	</div> 
</c:forEach>

<div class="copyright">
${copyright}
</div>

</body>
</html>

