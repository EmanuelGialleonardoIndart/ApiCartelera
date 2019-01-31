package ttps.cartelera.controller.servicios;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ttps.cartelera.model.DAOimpl.DaoFactory;
import ttps.cartelera.model.objetos.Cartelera;
import ttps.cartelera.model.objetos.Tag;
import ttps.cartelera.model.objetos.Usuario;

@Controller
public class ServiciosCartelera {
	
	@Autowired
	private DaoFactory daoFactory;
	
	public DaoFactory getDaoFactory() {
		return this.daoFactory;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Cartelera> listaPerfil(String nombrePerfil, int id){
		List<Cartelera> l=new ArrayList();
		switch(nombrePerfil) {
		case "Alumno":
		  l=listaCarteleraIDusuarioAlumno(id);
		  break;
		case "Docente":
		  l=listaCarteleraIDusuarioPublicador(id);
		  break;
		case "Publicador":
		  l=listaCarteleraIDusuarioPublicador(id);
		  break;
		case "Administrador":
		  l=listaDeCarteleras();
		  break;
		}
		return l;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Cartelera> listaCarteleraIDusuarioAlumno(int id){
		List<Cartelera> interesa=new ArrayList();
		List<Cartelera> nointeresa=new ArrayList();
		List<Cartelera> c1=daoFactory.getCarteleraDAO().conInteres();
		Iterator i=c1.iterator();
		while (i.hasNext()) {
			Cartelera cart=(Cartelera)i.next();
			if(!cart.getInteres().isEmpty()) {
				Iterator i2=cart.getInteres().iterator();
				while(i2.hasNext()) {
					Usuario u=(Usuario)i2.next();
					if(u.getId()==id) {
					    interesa.add(cart);
					    break;
					}
				}
				
			}
			if(!interesa.contains(cart)) {
				nointeresa.add(cart);
			}
		}
		interesa.addAll(nointeresa);
		return interesa;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Cartelera> listaCarteleraIDusuarioPublicador(int id){
		List<Cartelera> publicador=new ArrayList();
		List<Cartelera> nointeresa=new ArrayList();
		List<Cartelera> c1=daoFactory.getCarteleraDAO().conPublicadores();
		Iterator i=c1.iterator();
		while (i.hasNext()) {
			Cartelera cart=(Cartelera)i.next();
			if(!cart.getPublicadores().isEmpty()) {
				Iterator i2=cart.getPublicadores().iterator();
				while(i2.hasNext()) {
					Usuario u=(Usuario)i2.next();
					if(u.getId()==id) {
					    publicador.add(cart);
					    break;
					}
				}
				
			}
			if(!publicador.contains(cart)) {
				nointeresa.add(cart);
			}
		}
		publicador.addAll(nointeresa);
		return publicador;
	}
	
	public List<Cartelera> listaDeCarteleras(){
		return daoFactory.getCarteleraDAO().recuperarTodos("id");
	}
	
	@SuppressWarnings("rawtypes")
	public String validadCartelera(Cartelera cartelera) {
		if (cartelera.getNombre() == null || cartelera.getDescripcion() == null) {
			return "[\"completa todos los campos\"]";
		}
		if( cartelera.getTags() == null || cartelera.getTags().size() < 1) {
			return "[\"debe seleccionar al menos un tag\"]";
		}
		if( cartelera.getTags().size() > 3) {
			return "[\"la maxima cantidad de tags es 3\"]";
		}
		if (daoFactory.getCarteleraDAO().titulo_existe(cartelera.getNombre())) {
			return "[\"el titulo ya existe\"]";
		}
		boolean tag = false;
		Iterator it = cartelera.getTags().iterator();
		while (it.hasNext()) {
			Tag p = (Tag) it.next();
			if (!daoFactory.getTagDAO().existe(p.getId())) {
				tag = true;
			}
		}
		if (tag) {
			return "[\"datos incorrectos\"]";
		}
		
		return null;
		
	}
	@SuppressWarnings({ "rawtypes", "unused" })
	private boolean result(List<Usuario> l,Usuario u) {
		Iterator it=l.iterator();
		Usuario aux;
		while (it.hasNext()) {
			aux=(Usuario)it.next();
			if(aux.getId()==u.getId()) {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean pertenece_o_no(Usuario u,Serializable id) {
		if(u.getPerfil().getNombre().equals("Alumno")) {
			return this.result(daoFactory.getCarteleraDAO().obtener_lista_usuarios_relacionados("interes", id),u);
		}
		if(u.getPerfil().getNombre().equals("Docente")||u.getPerfil().getNombre().equals("Publicador")) {
			return this.result(daoFactory.getCarteleraDAO().obtener_lista_usuarios_relacionados("publicadores", id),u);
		}
		return false;
		
		
	}
	


}
