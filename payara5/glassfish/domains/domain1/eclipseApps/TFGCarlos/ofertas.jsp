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
			<div class="col-1 row mx-auto">
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
			<div class="row col-10 contenido index-f1">
				<div class="col-12">
					<h2>Ofertas de ${cadena.nombre}</h2>
				</div>
				<c:if test="${cadena.ofertas.size() ==0}">
					<h4>No hay ofertas de ${cadena.nombre}</h4>
				</c:if>
				<c:if test="${cadena.ofertas.size() >0}">
					<c:forEach items="${cadena.ofertas}" var="oferta">
						<a> </a>
						<div class="row col-12">
							<div class="col-2">
								<img class="img-fluid" alt="${cadena.nombre}"
									src="resources/img/${oferta.imagen}">
							</div>
							<div class="col-2">
								<h4>${oferta.codigo}</h4>
							</div>
							<div class="col-7">
								<p>${oferta.descripcion}</p>
							</div>
							<div class="col-1">
								<a href="${oferta.web}" target="_blank">
									<button class="btn btn-danger">Enlace Oferta</button>
								</a>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<footer class="row mt-5">
			<div class="col-1"></div>
			<div class="footer-conteido col-10 mt-3 row mb-1">
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
				<small> © 2020 OnlyFood Inc. Todos los derechos reservados. </small>
			</div>
		</footer>
	</div>
</body>
</html>