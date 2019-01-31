package ttps.cartelera.model.DAOimpl;

import org.springframework.stereotype.Repository;

import ttps.cartelera.model.clasesDAO.TagDAO;
import ttps.cartelera.model.objetos.Tag;
@Repository
public class TagDAOhibernateJPA extends GenericDAOhibernateJPA<Tag> implements TagDAO{
	
	public TagDAOhibernateJPA() {
		super(Tag.class);
	}

}
