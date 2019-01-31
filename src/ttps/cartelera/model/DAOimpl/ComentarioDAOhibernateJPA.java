package ttps.cartelera.model.DAOimpl;

import org.springframework.stereotype.Repository;

import ttps.cartelera.model.clasesDAO.ComentarioDAO;
import ttps.cartelera.model.objetos.Comentario;
@Repository
public class ComentarioDAOhibernateJPA extends GenericDAOhibernateJPA<Comentario> implements ComentarioDAO{
	
	public ComentarioDAOhibernateJPA() {
		super(Comentario.class);
	}

}
