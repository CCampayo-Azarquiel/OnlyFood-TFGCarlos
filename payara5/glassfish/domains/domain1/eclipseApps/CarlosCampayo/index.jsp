<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<jsp:directive.include file="/WEB-INF/includes/includefiles.jspf" />
</head>
<body>
	<jsp:scriptlet>response.sendRedirect("controller?operacion=inicio");</jsp:scriptlet>
</body>
</html>