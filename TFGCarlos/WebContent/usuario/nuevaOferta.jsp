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
			<div class="col-1"></div>
			<div class="row contenido col-10 centrarTexto">
				<div class="col-12 mt-4 mb-4">
					<h2>Datos de la oferta a crear</h2>
				</div>
				<div class="row col-6 mx-auto">
					<form action="${pageContext.request.contextPath}/controllerUsuario"
						method="POST"  class="col-12">
						<div class="form-group">
							<label for="cadena">Cadena de comida:</label> <select
								name="cadena" class="form-control" id="cadena"
								value="${listadoCadenas[0].id}" required>
								<c:forEach items="${listadoCadenas}" var="cadena">
									<option value="${cadena.id}">${cadena.nombre}</option>
								</c:forEach>
								<option></option>
							</select>
						</div>
						<div class="form-group">
							<label for="codigo">Código oferta:</label> <input type="text"
								class="form-control" id="codigo" name="codigo"
								placeholder="Introduce el codigo de oferta">
						</div>
						<div class="form-group">
							<label for="descripcion">Descripcion:</label>
							<textarea class="form-control" id="descripcion"
								name="descripcion" placeholder="Describe la oferta" rows="3"
								required></textarea>
						</div>
<!-- 						<div class="form-group">
							<label for="imagen">Imagen:</label> <input type="text"
								class="form-control" id="imagen" name="imagen"
								placeholder="Introduce el nombre de la imagen">
						</div> -->
						<!-- <div class="custom-file">
							<input type="file" name="imagen" class="custom-file-input" id="customFileLang"
								lang="es"> <label class="custom-file-label" accept="image/*"
								for="customFileLang">Seleccionar Imagen</label>
						</div> -->
						<div class="form-group">
							<label for="web">Sitio web:</label> <input type="text"
								class="form-control" id="web" name="web"
								placeholder="Introduce el enlace web de la oferta">
						</div>
						<input type="hidden" name="operacion" id="operacion"
							value="insertarOferta" />
						<button type="submit" name="submit" class="btn btn-primary">Aceptar</button>
						<a class="btn btn-primary"
							href="${pageContext.request.contextPath}/controllerUsuario?operacion=listarOfertasUsuario">Cancelar</a>
					</form>
				</div>
			</div>
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