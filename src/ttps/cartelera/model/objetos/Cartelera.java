package ttps.cartelera.model.objetos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;
@Entity
@Table(name="cartelera")
public class Cartelera {
	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cartelera")
	private int id;
	
	private String nombre;
	
	private String descripcion;
	
	@ManyToMany
	@JoinTable(name="cartelera_usuario_interes",
	   joinColumns= @JoinColumn(name="cartelera_id",referencedColumnName="id_cartelera"),
	   inverseJoinColumns= @JoinColumn(name="usuario_id",referencedColumnName="id_usuario")
	)
	
	@JsonIgnore 
	private List<Usuario> interes;
	
	@ManyToMany
	@JoinTable(name="cartelera_usuario_publicadores",
	   joinColumns= @JoinColumn(name="cartelera_id",referencedColumnName="id_cartelera"),
	   inverseJoinColumns= @JoinColumn(name="usuario_id",referencedColumnName="id_usuario")
	)
	
	@JsonIgnore 
	private List<Usuario> publicadores;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="cartelera_tag",
	   joinColumns= @JoinColumn(name="cartelera_id",referencedColumnName="id_cartelera"),
	   inverseJoinColumns= @JoinColumn(name="tag_id",referencedColumnName="id_tag")
	)
	
	
	private List<Tag> tags;
	
	@OneToMany(mappedBy="cartelera")
	@JsonIgnore 
	private List<Publicacion> publicaciones;

	
	
	
	
	
	
	
	
	
	public List<Usuario> getPublicadores() {
		return publicadores;
	}

	public void setPublicadores(List<Usuario> publicadores) {
		this.publicadores = publicadores;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Usuario> getInteres() {
		return interes;
	}

	public void setInteres(List<Usuario> interes) {
		this.interes = interes;
	}
	
	

}
