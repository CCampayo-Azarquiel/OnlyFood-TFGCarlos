package servlets;
//6Le4BMEUAAAAAHMIVSNNjzGE785IsZXziM54rtxl recaptcha
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoCadena;
import dao.DaoUsuario;
import entidades.Cadena;
import entidades.Oferta;
import entidades.Usuario;
import exception.UsuarioException;
import recaptcha.VerificarRecaptcha;


/**
 * Servlet implementation class Controller
 */
@WebServlet({ "/Controller", "/controller" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
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
			case "listarCadenas":
				try {		
					daoCadena=new DaoCadena();
					List<Cadena> listadoCadenas= daoCadena.getAllCadenas();
					request.setAttribute("listadoCadenas", listadoCadenas);
					System.out.println("llega a listarcadeanas");
					request.getRequestDispatcher("inicio.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case "ofertasPorCadena":
				try {		
					daoCadena=new DaoCadena();
					String id=(String) request.getParameter("id");
					cadena= daoCadena.seachCadenabyId(id);
					request.setAttribute("cadena", cadena);
					//cadena.getOfertas().si
					System.out.println("llega a listarofertas");
					request.getRequestDispatcher("ofertas.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case "altaUsuario":
				System.out.println("--------------entra en altaUsuario---------------");
				//aqui se ve la forma de recoger una fecha y darle formato para posteriormente introducirla en una base de datos
				DaoUsuario daoUsuario=new DaoUsuario();
				Usuario usuario=new Usuario();
				usuario.setUsuario(request.getParameter("email"));
				usuario.setClave(request.getParameter("clave"));
				String clave=request.getParameter("clave");
				try {
					String grecaptcharesponse = request.getParameter("g-recaptcha-response");
					//request.setAttribute("confirmacion", confirmacion);
					request.setAttribute("email",(String) request.getParameter("email"));
					request.setAttribute("clave", clave);
					if(VerificarRecaptcha.validate(grecaptcharesponse)) {
						System.out.println("recaptcha valido");
						if(daoUsuario.encontrarUsuario(usuario.getUsuario())!=null)
							throw new UsuarioException("Ya existe un usuario con esas credenciales.");
						daoUsuario.insertaUsuario(usuario);//llamo al metodo que realiza la operacion
						System.out.println("termina a insertar usuario");
						String confirmacion="Socio "+usuario.getUsuario()+" dado de alta.";
						//usuario=daoUsuario.encontrarUsuario((String) request.getParameter("email"));
						//request.setAttribute("usuario",usuario);
						request.setAttribute("confirmacion",confirmacion);
						System.out.println("redirige a identificate");
						request.getRequestDispatcher("/seguridad/identificate.jsp").forward(request, response);
					}else{
						request.setAttribute("error", "Validación de captcha erronea");
						request.getRequestDispatcher("/altausuario.jsp").forward(request, response);
					}
				}catch(UsuarioException e){
					String error=e.getMessage();
					request.setAttribute("error", error);
					request.getRequestDispatcher("/altausuario.jsp").forward(request, response);
					
				}catch (RollbackException e) {
					String error="Error en la base de datos, escriba un correo con la incidencia a incidenciasOnlyFood@gmail.com";
					request.setAttribute("error", error);
					request.getRequestDispatcher("/altausuario.jsp").forward(request, response);
				}catch (Exception e) {
					String error="Error: "+e.getMessage()+", escriba un correo con la incidencia a incidenciasOnlyFood@gmail.com";
					request.setAttribute("error", error);
					request.getRequestDispatcher("/altausuario.jsp").forward(request, response);
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
