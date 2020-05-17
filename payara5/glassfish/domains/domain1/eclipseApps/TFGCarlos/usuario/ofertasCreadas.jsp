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
						<img src="resources/img/menu.png"
							class="img-fluid mt-4 dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
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
			<div class="col-12 row">
				<c:if test="${requestScope.confirmacion != null}">
					<div id="divconfirmacion"
						class="centrarTexto mx-auto col-7 mt-2 mb-2">
						<p>
							<strong><c:out value="confirmacion" /></strong> <br>
							<c:out value="${requestScope.confirmacion}" />
						</p>
					</div>
				</c:if>
				<c:if test="${requestScope.error != null}">
					<div id="diverror" class="centrarTexto mx-auto col-7 mt-2 mb-2">
						<p>
							<strong><c:out value="Error" /></strong> <br>
							<c:out value="${requestScope.error}" />
						</p>
					</div>
				</c:if>
			</div>
			<div class="col-1"></div>
			<div class="row col-10 contenido index-f1">
				<div class="col-6 mt-3 mb-3">
					<h2>Ofertas de ${usuario.usuario}</h2>
				</div>
				<div class="col-6">
					<a class="btn btn-danger mt-3 mb-3"
						href="${pageContext.request.contextPath}/controllerUsuario?operacion=irNuevaOferta">Nueva
						oferta</a>
				</div>
				<c:if test="${listadoOfertas.size() ==0}">
					<h4>No hay ofertas por ti </h4>
				</c:if>
				<c:if test="${listadoOfertas.size() >0}">
					<c:forEach items="${listadoOfertas}" var="oferta">
						<a> </a>
						<div class="row col-12">
							<div class="col-2">
								<img class="img-fluid" alt="${oferta.codigo}"
									src="resources/img/${oferta.imagen}">
							</div>
							<div class="col-2">
								<h4>${oferta.codigo}</h4>
							</div>
							<div class="col-6">
								<p>${oferta.descripcion}</p>
							</div>
							<div class="col-1">
								<a href="${oferta.web}" target="_blank">
									<button class="btn btn-danger">Enlace Oferta</button>
								</a>
							</div>
							<div class="col-1">
								<a
									href="${pageContext.request.contextPath}/controllerUsuario?operacion=eliminarOferta&idoferta=${oferta.id.id}&cadena=${oferta.id.cadena}">
									<img class="img-fluid" src="resources/img/eliminar.png">
								</a>
							</div>
						</div>
					</c:forEach>
				</c:if>
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