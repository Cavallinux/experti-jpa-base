package cl.experti.persistence.dao;

import java.util.List;

import cl.experti.persistence.model.IdEntity;

/**
 * Interfaz base para los metodos de persistencia en bases de datos
 * relacionales.
 * 
 * @author Paolo Mezzano Barahona (pmezzano@experti.cl)
 *
 * @param <T>
 *            Clase que debe heredar de {@link IdEntity}
 */
public interface GenericDAO<T extends IdEntity> {
    /**
     * Persiste en base de datos el objeto pasado como argumento.
     * 
     * @param object
     * @return
     */
    public T persist(T object);

    public void delete(T object);

    public T find(long id);

    public List<T> findAll();
    
    public List<T> findByField(String field, String data);

    public Class<T> getEntityClass();

    public void detach(T t);
}