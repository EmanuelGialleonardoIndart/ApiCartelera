package ttps.cartelera.model.objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="notificacion")
public class Notificacion {
	
	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_notificacion")
	private int id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="usuario_id")
	@JsonIgnore
	private Usuario usuario;
	
	private String text;
	private boolean leido;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cartelera_id")
	private Cartelera cartelera;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Cartelera getCartelera() {
		return cartelera;
	}

	public void setCartelera(Cartelera cartelera) {
		this.cartelera = cartelera;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}
 
}