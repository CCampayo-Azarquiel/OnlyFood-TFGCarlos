package servlets;
//6Le4BMEUAAAAAHMIVSNNjzGE785IsZXziM54rtxl recaptcha
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoCadena;
import dao.DaoOferta;
import dao.DaoUsuario;
import entidades.Cadena;
import entidades.Oferta;
import entidades.Usuario;


/**
 * Servlet implementation class Controller
 */
@WebServlet({ "/ControllerUsuario", "/controllerUsuario" })
@MultipartConfig
public class ControllerUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion=request.getSession();
		String operacion=request.getParameter("operacion");
		DaoCadena daoCadena=null;
		Cadena cadena=null;
		switch(operacion) {
			case "listarOfertasUsuario":
				try {
					DaoUsuario daoUsuario=new DaoUsuario();
				/*
				 * request.isUserInRole("usuario");
				 * System.out.println(request.isUserInRole("administrador"));
				 * System.out.println(request.getUserPrincipal()); System.out.println("llega");
				 */
					String usuario= request.getUserPrincipal().toString();
					Usuario user=daoUsuario.encontrarUsuario(usuario);
					request.setAttribute("usuario", user);
					boolean isAdmin=request.isUserInRole("administrador");
					List<Oferta> listadoOfertas;
					if(isAdmin==true) {
						DaoOferta daoOferta=new DaoOferta();
						listadoOfertas= daoOferta.getAllOfertas();
					}else
						listadoOfertas=user.getOfertas();
					System.out.println("el tamañaos del listadoofertasusuario es "+listadoOfertas.size());
					request.setAttribute("listadoOfertas", listadoOfertas);
					System.out.println("llega a listarOfertasUsuario");
					request.getRequestDispatcher("/usuario/ofertasCreadas.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case "irNuevaOferta":
				try {		
					daoCadena=new DaoCadena();
					List<Cadena> listadoCadenas= daoCadena.getAllCadenas();
					request.setAttribute("listadoCadenas", listadoCadenas);
					System.out.println("llega a listarcadeanas");
					request.getRequestDispatcher("/usuario/nuevaOferta.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case "insertarOferta":
				try {
					System.out.println("entra en nueva oferta");
					DaoUsuario daoUsuario=new DaoUsuario();
					//request.isUserInRole("usuario");
					String usuario= request.getUserPrincipal().toString();
					Usuario user=daoUsuario.encontrarUsuario(usuario);
					Oferta oferta=new Oferta();
					System.out.println("------------------"+request.getParameter("codigo"));
					if(request.getParameter("codigo").equalsIgnoreCase("")==false || request.getParameter("codigo")==null)
						oferta.setCodigo(request.getParameter("codigo"));
					else
						oferta.setCodigo("Consultar en local.");
					//oferta.setCodigo(request.getParameter("codigo"));
					oferta.setDescripcion(request.getParameter("descripcion"));
					oferta.setWeb(request.getParameter("web"));
					oferta.setUsuarioBean(user);
					cadena=new Cadena();
					daoCadena=new DaoCadena();
					System.out.println("cadena"+request.getParameter("cadena"));
					cadena=daoCadena.seachCadenabyId(request.getParameter("cadena"));
				/*
				 * if(request.getParameter("imagen")=="" ||
				 * request.getParameter("imagen")==null)
				 * oferta.setImagen(request.getParameter("imagen")); else
				 */
					oferta.setImagen(cadena.getImagen());						
					DaoOferta daoOferta=new DaoOferta();
					daoOferta.nuevaOferta(oferta, cadena);
					user=daoUsuario.refrescarUsuario(user);
					request.setAttribute("usuario", user);
					request.setAttribute("confirmacion", "Oferta añadida correctamenete");
					System.out.println("llega a final nueva oferta");
					request.getRequestDispatcher("/controllerUsuario?operacion=listarOfertasUsuario").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case "eliminarOferta":
				try {
					System.out.println("entra en eliminar oferta");
					DaoUsuario daoUsuario=new DaoUsuario();
					//request.isUserInRole("usuario");
					String usuario= request.getUserPrincipal().toString();
					Usuario user=daoUsuario.encontrarUsuario(usuario);
					String idcadena=request.getParameter("cadena");	
					String idoferta=request.getParameter("idoferta");	
					DaoOferta daoOferta=new DaoOferta();
					daoOferta.eliminarOferta(idcadena, idoferta);
					user=daoUsuario.refrescarUsuario(user);
					request.setAttribute("usuario", user);
					System.out.println("llega a final eliminar oferta");
					request.setAttribute("confirmacion", "Oferta eliminada");
					request.getRequestDispatcher("/controllerUsuario?operacion=listarOfertasUsuario").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case "logout":
				sesion.invalidate();
				response.sendRedirect("index.jsp");
			break;
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
