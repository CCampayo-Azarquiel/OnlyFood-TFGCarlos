package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CADENA database table.
 * 
 */
@Entity
@NamedQuery(name="Cadena.findAll", query="SELECT c FROM Cadena c")
public class Cadena implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CADENA_ID_GENERATOR", sequenceName="CADENA_SECUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CADENA_ID_GENERATOR")
	private long id;

	private String imagen;

	private String nombre;

	private String web;

	//bi-directional many-to-one association to Oferta
	@OneToMany(mappedBy="cadenaBean")
	private List<Oferta> ofertas;

	public Cadena() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public List<Oferta> getOfertas() {
		return this.ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public Oferta addOferta(Oferta oferta) {
		getOfertas().add(oferta);
		oferta.setCadenaBean(this);

		return oferta;
	}

	public Oferta removeOferta(Oferta oferta) {
		getOfertas().remove(oferta);
		oferta.setCadenaBean(null);

		return oferta;
	}

}