package ttps.cartelera.model.DAOimpl;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ttps.cartelera.model.clasesDAO.CarteleraDAO;
import ttps.cartelera.model.objetos.Cartelera;
import ttps.cartelera.model.objetos.Usuario;
@Repository
public class CarteleraDAOhibernateJPA extends GenericDAOhibernateJPA<Cartelera> implements CarteleraDAO{

	public CarteleraDAOhibernateJPA() {
		super(Cartelera.class);
	}
	@SuppressWarnings("unchecked")
	public List<Cartelera> conInteres(){

		Query q = this.getEntityManager().createQuery("Select distinct u FROM Cartelera u left join fetch u.interes order by u.id");
		List<Cartelera> u = (List<Cartelera>) q.getResultList();		
		return u;
	}
	@SuppressWarnings("unchecked")
	public List<Cartelera> conPublicadores(){

		Query q = this.getEntityManager().createQuery("Select distinct u FROM Cartelera u left join fetch u.publicadores order by u.id");
		List<Cartelera> u = (List<Cartelera>) q.getResultList();		
		return u;
	}
	@SuppressWarnings("unchecked")
	public List<Usuario> obtener_lista_usuarios_relacionados(String lista, Serializable id){
		Query q =this.getEntityManager().createQuery("Select u."+lista+" FROM Cartelera u join u."+lista+" WHERE u.id=:id");
		q.setParameter("id", id);
		List<Usuario> u = (List<Usuario>) q.getResultList();
		return u;
	}
	@SuppressWarnings("unchecked")
	public boolean titulo_existe(String titulo) {
		Query q =this.getEntityManager().createQuery("Select c FROM Cartelera c WHERE c.nombre=:nombre");
		q.setParameter("nombre", titulo);
		List<Cartelera> l=q.getResultList();
		if (l.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	 
	

}
