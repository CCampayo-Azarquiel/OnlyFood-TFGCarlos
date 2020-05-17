<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion Pedido</title>
<jsp:directive.include file="./WEB-INF/includes/includefiles.jspf" />
</head>
<body>
	<div id="container">
		<c:if test="${requestScope.confirmacion != null}">
			   	<div id="divconfirmacion">
			   		<strong><c:out value="confirmacion"></c:out></strong>
			   		<br/>
			   		<c:out value="${requestScope.confirmacion}"/>
			   	</div>
		   </c:if>
		<h2>Seleccione los criterios de busqueda y pulse
			buscar</h2>
		<form>
			<div>
				<c:out value="Cliente"></c:out>
				<select name="cliente">
					<option value="-1" <c:if test="${cliente==-1 || cliente==null}"> selected </c:if>>Todos</option>
					<c:if test="${listadoclientes != null}">
						<c:if test="${not empty listadoclientes}">
							<c:forEach items="${listadoclientes}" var="micliente">
								<option value="${micliente.idCliente}" <c:if test="${cliente==micliente.idCliente}">selected</c:if>>${micliente.nombreCliente}</option>
							</c:forEach>
						</c:if>
					</c:if>
				</select>
			</div>

			<div>
				<c:out value="Situación de envío"></c:out>
				<input type="radio" id="contactChoice1" name="situacion" value="-1" <c:if test="${situacion==-1 || situacion==null}"> checked </c:if>>
				<label for="contactChoice1">Todos</label> 
				<input type="radio"
					id="contactChoice2" name="situacion" value="ENVIADO" <c:if test="${situacion.equals(\"ENVIADO\")}">checked</c:if>> 
				<label
					for="contactChoice2">Enviado</label> 
				<input type="radio"
					id="contactChoice3" name="situacion" value="PENDIENTE" <c:if test="${situacion.equals(\"PENDIENTE\")}"> checked </c:if>> 
				<label
					for="contactChoice3">Pendiente</label>
			</div>
			
			<div>
				<c:out value="Estado de cobro"></c:out>
				<input type="radio" id="contactChoice11" name="estado" value="-1" <c:if test="${estado==-1 || estado==null}"> checked </c:if>>
				<label for="contactChoice11">Todos</label> 
				<input type="radio"
					id="contactChoice22" name="estado" value="SI" <c:if test="${estado.equals(\"SI\")}"> checked </c:if>> 
				<label
					for="contactChoice22">Cobrado</label> 
				<input type="radio"
					id="contactChoice33" name="estado" value="NO" <c:if test="${estado.equals(\"NO\")}"> checked </c:if>> 
				<label
					for="contactChoice33">No cobrado</label>
			</div>
			
			<div>
			<input type="hidden" name="operacion" id="operacion"
							value="buscarpedido" /> 
						<input type="submit" name="submit"
							value="Buscar" />
			</div>
		</form>
		<c:if test="${listadopedidos != null}">
			<c:if test="${empty listadopedidos}">
			  <div >
				<h2>No hay pedido con esos datos</h2>
			  </div>
			</c:if>
			<c:if test="${not empty listadopedidos}">
				<div id="tabla">
					<table width="100%" border="1">
						<caption>Listado de pedidos solicitados</caption>
						<tr>
							<th scope="col">#Pedido</th>
							<th scope="col">Direccion</th>
							<th scope="col">Fecha</th>
							<th scope="col"></th>
							<th scope="col"></th>
						</tr>
						<c:forEach items="${listadopedidos}" var="pedido">
							<tr>
								<td>${pedido.idPedido}</td>
								<td>${pedido.direccionEnvio}</td>
								<td>${pedido.fecha}</td>
								<td><a
									href="${pageContext.request.contextPath}/Controller?operacion=detallepedido&id=${pedido.idPedido}&cliente=${cliente}&situacion=${situacion}&estado=${estado}">Ver detalle</a></td>
								<td><a
									href="${pageContext.request.contextPath}/Controller?operacion=removepedido&id=${pedido.idPedido}&cliente=${cliente}&situacion=${situacion}&estado=${estado}">Eliminar</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</c:if>
		<c:if test="${micarro != null}">
			<c:if test="${not empty micarro}">
				<div id="tabla">
					<table width="100%" border="1">
						<caption>Detalle del Pedido ${id} del cliente ${detallecliente.nombreCliente}</caption>
						<tr>
							<th scope="col">#Linea</th>
							<th scope="col">Producto</th>
							<th scope="col">Cantidad</th>
							<th scope="col">Precio Unitario</th>
							<th scope="col">Total</th>
						</tr>
						<c:forEach items="${micarro.elementos}" var="productocarro">
							<tr>
								<td>${productocarro.idProducto}</td>
								<td>${productocarro.nombreProducto}</td>
								<td>${productocarro.cantidad}</td>
								<td>${productocarro.precioNormal}</td>
								<td>${productocarro.precioNormal*productocarro.cantidad}</td>
							</tr>
						</c:forEach>
						<tr>
							<td>Total Pedido</td>
							<td>${preciototal}</td>
						</tr>
					</table>
				</div>
			</c:if>
		</c:if>
	</div>
</body>
</html>