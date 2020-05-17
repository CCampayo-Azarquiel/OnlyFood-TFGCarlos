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
			<div class="col-8 col-md-8 col-lg-10">
				<a href="${pageContext.request.contextPath}/index.jsp"> <img
					id="image-cabecera" alt="OnlyFood"
					src="resources/img/logo_only_r.jpg" />
				</a>
			</div>
			<div class="col-2 col-md-2 col-lg-1 row mx-auto">
				<c:if test="${pageContext.request.remoteUser==null}">
					<a
						href="${pageContext.request.contextPath}/controllerUsuario?operacion=listarOfertasUsuario">
						<img class="fotoLogin mt-5" src="resources/img/singIn.png">
					</a>
				</c:if>
				<c:if test="${pageContext.request.remoteUser!=null}">
					<div class="btn-group">
						<img src="resources/img/menu.png" class="img-fluid mt-4 dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<div class="dropdown-menu dropdown-menu-right">
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/controllerUsuario?operacion=listarOfertasUsuario">Mis
								ofertas</a> <a class="dropdown-item"
								href="${pageContext.request.contextPath}/controllerUsuario?operacion=irNuevaOferta">Nueva
								Oferta</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/controllerUsuario?operacion=logout">Cerrar
								sesión</a>
						</div>
					</div>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col-1"></div>
			<div class="row contenido col-10">
				<div class="col-12 mt-4 mb-4">
					<h2>Elige la cadena que quieras ver sus ofertas</h2>
				</div>
				<div class="row col-12">
					<c:forEach items="${listadoCadenas}" var="cadena">
						<c:if test="${cadena.ofertas.size()>0}">
							<div class="col-12 col-md-6 col-lg-4 mb-4">
								<a
									href="${pageContext.request.contextPath}/controller?operacion=ofertasPorCadena&id=${cadena.id}">
									<img class="img-fluid img-inicio" alt="${cadena.nombre}"
									src="resources/img/${cadena.imagen}">
								</a>
							</div>
						</c:if>
					</c:forEach>
				</div>
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