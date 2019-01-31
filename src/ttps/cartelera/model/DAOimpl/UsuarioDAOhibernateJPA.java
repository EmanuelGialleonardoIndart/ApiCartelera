package ttps.cartelera.model.DAOimpl;


import java.io.Serializable;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.cartelera.model.clasesDAO.UsuarioDAO;
import ttps.cartelera.model.objetos.Usuario;
@Repository
public class UsuarioDAOhibernateJPA extends GenericDAOhibernateJPA<Usuario> implements UsuarioDAO {

	public UsuarioDAOhibernateJPA() {

		super(Usuario.class);
	}

	
	public Usuario obtener_por_username(String username) {	
		    Usuario u=null;
		    try {
			Query q=this.getEntityManager().createQuery("Select u FROM Usuario u WHERE u.username=:username");
			q.setParameter("username", username);
			u=(Usuario) q.getSingleResult();
		    }catch(RuntimeException e) {
		    }
			return u;
		
		}
	public Usuario obtener_con_notificaciones(Serializable id) {
            Usuario u=null;
            try {
			Query q = this.getEntityManager().createQuery("Select u FROM Usuario u left join fetch u.notificaciones WHERE u.id=:id");
			q.setParameter("id", id);
			u = (Usuario) q.getSingleResult();
            }catch(RuntimeException e) { 
            }
			return u;

	}
	}


