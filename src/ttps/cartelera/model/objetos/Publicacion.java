package ttps.cartelera.model.objetos;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="publicacion")
public class Publicacion {
	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_publicacion")
	private int id;
	
	private String titulo;
	
	private Date fecha;
	
	private String contenido;
	
	private boolean noComen;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cartelera_id")
	private Cartelera cartelera;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="usuario_id")
	private Usuario publicador;
	
	@OneToMany(mappedBy="publicacion")
	private List<Comentario> comentarios;

	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Cartelera getCartelera() {
		return cartelera;
	}

	public void setCartelera(Cartelera cartelera) {
		this.cartelera = cartelera;
	}

	public Usuario getPublicador() {
		return publicador;
	}

	public void setPublicador(Usuario publicador) {
		this.publicador = publicador;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public boolean isNoComen() {
		return noComen;
	}

	public void setNoComen(boolean comen) {
		this.noComen = comen;
	}
	
  
	
	
}
