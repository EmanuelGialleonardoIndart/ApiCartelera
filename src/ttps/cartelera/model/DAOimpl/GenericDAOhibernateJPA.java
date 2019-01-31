package ttps.cartelera.model.DAOimpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import ttps.cartelera.model.clasesDAO.GenericDAO;

@Transactional
public class GenericDAOhibernateJPA<T> implements GenericDAO<T> {
	
	
	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected Class<T> persistentClass;

	public GenericDAOhibernateJPA(Class<T> clase) {
		this.persistentClass = clase;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public T persistir(T entity) {
		this.getEntityManager().persist(entity);
		 return entity;

	}

	public T actualizar(T entity) {
		T entity2=this.getEntityManager().merge(entity);
		return entity2;

	}

	@Override
	public void borrar(T entity) {
		this.getEntityManager().remove(this.getEntityManager().contains(entity) ? entity : this.getEntityManager().merge(entity));
	}

	public T borrar(Serializable id) {
		T entity=this.getEntityManager().find(this.getPersistentClass(), id);
		if (entity != null) {
			this.borrar(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> recuperarTodos(String columnOrder) {
		Query consulta = this.getEntityManager()
				.createQuery("select e from " + getPersistentClass().getSimpleName() + " e order by e." + columnOrder);
		List<T> resultado = (List<T>) consulta.getResultList();
		return resultado;
	}
   
	@Transactional
	public T recuperar(Serializable id) {
		T entity = this.getEntityManager().find(this.getPersistentClass(), id);
		return entity;
	}

	public boolean existe(Serializable id) {
			T entity = this.getEntityManager().find(this.getPersistentClass(), id);
			if (entity != null) {
				return true;
			} else {
				return false;
			}
	}

}
