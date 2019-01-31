package ttps.cartelera.model.clasesDAO;


import java.io.Serializable;

import ttps.cartelera.model.objetos.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	
	public Usuario obtener_con_notificaciones(Serializable id);
	public Usuario obtener_por_username(String username);

}
