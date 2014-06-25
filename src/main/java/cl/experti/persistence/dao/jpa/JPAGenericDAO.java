package cl.experti.persistence.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import cl.experti.persistence.dao.GenericDAO;
import cl.experti.persistence.model.IdEntity;

public abstract class JPAGenericDAO<T extends IdEntity> implements GenericDAO<T> {
    private Class<T> clazz = null;
    private static final Logger logger = LoggerFactory.getLogger(JPAGenericDAO.class);
    @PersistenceContext
    protected EntityManager entityManager;

    public JPAGenericDAO(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Transactional
    public T persist(T object) {
        T result = null;
        if (object != null) {
            logger.trace("Persisting entity with id {} class {}", object.getId(), object.getClass());
            result = entityManager.merge(object);
            logger.trace("Entity with id {} and class {} persisted succesfully!", result.getId(), result.getClass());
        }
        return result;
    }

    @Transactional
    public void delete(T object) {
        if (object != null) {
            logger.trace("Removing entity with id {} class {}", object.getId(), this.clazz);
            entityManager.remove(entityManager.merge(object));
        }
    }

    @Transactional(readOnly = true)
    public T find(long id) {
        logger.trace("Finding entity with id {} class {}", id, this.clazz);
        T result = (T) entityManager.find(clazz, id);
        return result;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        logger.trace("Finding all entities for class {}", this.clazz);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Object> cq = criteriaBuilder.createQuery();
        cq.select(cq.from(clazz));
        TypedQuery typedQuery = entityManager.createQuery(cq);
	return typedQuery.getResultList();
    }

    @Transactional(readOnly = true)
    public List<T> findByField(String field, String data) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cQuery = builder.createQuery(clazz);
        Root<T> from = cQuery.from(clazz);
        CriteriaQuery<T> select = cQuery.select(from);
        Expression<String> name = from.get(field);
        Predicate eq = builder.equal(name, data);
        select.where(eq);
        TypedQuery<T> typedQuery = entityManager.createQuery(select);
        return typedQuery.getResultList();
    }

    public Class<T> getEntityClass() {
        return this.clazz;
    }

    public void detach(T t) {
        this.getEntityManager().detach(t);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}