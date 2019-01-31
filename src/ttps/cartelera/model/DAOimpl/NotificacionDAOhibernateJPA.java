package ttps.cartelera.model.DAOimpl;

import org.springframework.stereotype.Repository;

import ttps.cartelera.model.clasesDAO.NotificacionDAO;
import ttps.cartelera.model.objetos.Notificacion;
@Repository
public class NotificacionDAOhibernateJPA extends GenericDAOhibernateJPA<Notificacion> implements NotificacionDAO {
	
	public NotificacionDAOhibernateJPA() {
		super(Notificacion.class);
	}

}
