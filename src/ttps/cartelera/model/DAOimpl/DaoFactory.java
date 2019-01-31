package ttps.cartelera.model.DAOimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ttps.cartelera.model.clasesDAO.CarteleraDAO;
import ttps.cartelera.model.clasesDAO.ComentarioDAO;
import ttps.cartelera.model.clasesDAO.NotificacionDAO;
import ttps.cartelera.model.clasesDAO.PerfilDAO;
import ttps.cartelera.model.clasesDAO.PublicacionDAO;
import ttps.cartelera.model.clasesDAO.TagDAO;
import ttps.cartelera.model.clasesDAO.UsuarioDAO;


@Component
public class DaoFactory {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private PerfilDAO perfilDAO;

	@Autowired
	private NotificacionDAO notificacionDAO;

	@Autowired
	private CarteleraDAO carteleraDAO;

	@Autowired
	private PublicacionDAO publicacionDAO;

	@Autowired
	private TagDAO tagDAO;

	@Autowired
	private ComentarioDAO comentarioDAO;

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}

	public NotificacionDAO getNotificacionDAO() {
		return notificacionDAO;
	}

	public CarteleraDAO getCarteleraDAO() {
		return carteleraDAO;
	}

	public PublicacionDAO getPublicacionDAO() {
		return publicacionDAO;
	}

	public TagDAO getTagDAO() {
		return tagDAO;
	}

	public ComentarioDAO getComentarioDAO() {
		return comentarioDAO;
	}

}