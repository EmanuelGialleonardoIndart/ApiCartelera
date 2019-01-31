package ttps.cartelera.model.DAOimpl;

import org.springframework.stereotype.Repository;

import ttps.cartelera.model.clasesDAO.PerfilDAO;
import ttps.cartelera.model.objetos.Perfil;
@Repository
public class PerfilDAOhibernateJPA extends GenericDAOhibernateJPA<Perfil> implements PerfilDAO{
	

	public PerfilDAOhibernateJPA() {
		
		super(Perfil.class);
		}

}
