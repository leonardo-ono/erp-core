package erp.infra.entity;

import erp.infra.filter.Filter;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * EntityDao class.
 *
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (03/02/2013 23:52)
 */
public abstract class EntityDao<T> {
    
    public Class getEntityClass() throws Exception {
        Class entityClass = (Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return entityClass;
    }

    public T createNewInstance() throws Exception {
        return (T) getEntityClass().newInstance();
    }
    
    // --- Must be implemented by client ---
    
    public abstract void insert(List<T> entities) throws Exception;
    public abstract void update(List<T> entities) throws Exception;
    public abstract void delete(List<T> entities) throws Exception;
    public abstract List<T> executeQuery(Filter filter) throws Exception;

}
