package ttps.cartelera.model.DAOimpl;

import org.springframework.stereotype.Repository;

import ttps.cartelera.model.clasesDAO.PublicacionDAO;
import ttps.cartelera.model.objetos.Publicacion;
@Repository
public class PublicacionDAOhibernateJPA extends GenericDAOhibernateJPA<Publicacion> implements PublicacionDAO{
	
	public PublicacionDAOhibernateJPA() {
		super(Publicacion.class);
	}

} 
