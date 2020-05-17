<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OnlyFood</title>
<jsp:directive.include file="/WEB-INF/includes/includefiles.jspf" />
</head>
<body>
	<div class="container-fluid">
		<div class="row cabecera">
			<div class="col-10">
				<a href="${pageContext.request.contextPath}/index.jsp"> <img
					id="image-cabecera" alt="OnlyFood"
					src="resources/img/logo_only_r.jpg" />
				</a>
			</div>
		</div>
		<div class="row">
			<div class="col-1"></div>
			<div class="row contenido col-10 centrarTexto">
				<div class="col-12 mt-4 mb-4">
					<h2>Inicia sesión</h2>
				</div>
				<div class="row col-6 mx-auto">
					<form action="j_security_check" method="POST" class="col-12">
						<div class="form-group">
							<label for="Email">Correo electónico:</label> <input type="text"
								class="form-control" id="Email" name="j_username"
								aria-describedby="emailHelp" placeholder="Introduce el email">
							<small id="emailHelp" class="form-text text-muted">Ejemplo:usuario@gmail.com</small>
						</div>
						<div class="form-group">
							<label for="Password">Contraseña</label> <input type="password"
								class="form-control" name="j_password" id="Password"
								placeholder="contraseña">
						</div>
						<button type="submit" name="login" class="btn btn-primary">Entrar</button>
						<a href="/TFGCarlos/altausuario.jsp">Registrese</a>
					</form>
				</div>
			</div>
			<div id="diverror" class="centrarTexto mx-auto col-7 mt-2 mb-2">
				<p>
					<strong><c:out value="Error" /></strong> <br>
					<c:out value="Usuario o password incorrectos " />
				</p>
			</div>
		</div>
		<footer class="row mt-5">
			<div class="col-1"></div>
			<div class="footer-conteido col-10 row">
				<div class="col-6 mb-3">
					<div>
						<strong>OnlyFood</strong>
					</div>
					<div>
						<a href="">Acerca de</a>
					</div>
					<div>
						<a href="">Contacto y sugerencias</a>
					</div>
					<div>
						<a href="">Preguntas frecuentes</a>
					</div>
				</div>
				<div class="col-6 mb-3">
					<div>
						<strong>Conectar</strong>
					</div>
					<div>
						<a href="https://es-es.facebook.com/">Facebook</a>
					</div>
					<div>
						<a href="https://www.instagram.com/?hl=es">Instagram</a>
					</div>
					<div>
						<a href="https://twitter.com/explore">Twitter</a>
					</div>
				</div>
			</div>
			<div class="col-12">
				<small> © 2020 OnlyFood Inc. Todos los derechos reservados.
				</small>
			</div>
		</footer>
	</div>
</body>
</html>