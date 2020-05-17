package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OFERTAS database table.
 * 
 */
@Entity
@Table(name="OFERTAS")
@NamedQuery(name="Oferta.findAll", query="SELECT o FROM Oferta o")
public class Oferta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OfertaPK id;

	private String codigo;

	private String descripcion;

	private String imagen;

	private String web;

	//bi-directional many-to-one association to Cadena
	@ManyToOne
	@JoinColumn(name="CADENA")
	private Cadena cadenaBean;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="USUARIO")
	private Usuario usuarioBean;

	public Oferta() {
	}

	public OfertaPK getId() {
		return this.id;
	}

	public void setId(OfertaPK id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public Cadena getCadenaBean() {
		return this.cadenaBean;
	}

	public void setCadenaBean(Cadena cadenaBean) {
		this.cadenaBean = cadenaBean;
	}

	public Usuario getUsuarioBean() {
		return this.usuarioBean;
	}

	public void setUsuarioBean(Usuario usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

}