package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the OFERTAS database table.
 * 
 */
@Embeddable
public class OfertaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long id;

	@Column(insertable=false, updatable=false)
	private long cadena;

	public OfertaPK() {
	}
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCadena() {
		return this.cadena;
	}
	public void setCadena(long cadena) {
		this.cadena = cadena;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OfertaPK)) {
			return false;
		}
		OfertaPK castOther = (OfertaPK)other;
		return 
			(this.id == castOther.id)
			&& (this.cadena == castOther.cadena);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.id ^ (this.id >>> 32)));
		hash = hash * prime + ((int) (this.cadena ^ (this.cadena >>> 32)));
		
		return hash;
	}
}