package ttps.cartelera.model.clasesDAO;



import java.io.Serializable;
import java.util.List;

import ttps.cartelera.model.objetos.Cartelera;
import ttps.cartelera.model.objetos.Usuario;



public interface CarteleraDAO extends GenericDAO<Cartelera> {
	
	public List<Cartelera> conInteres();
	public List<Cartelera> conPublicadores();
	public boolean titulo_existe(String titulo);
	public List<Usuario> obtener_lista_usuarios_relacionados(String lista, Serializable id);

}
