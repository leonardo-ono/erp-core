package erp.infra.form;

import erp.infra.entity.EntityDao;
import erp.infra.entity.EntityModel;
import erp.infra.mode.ModeModel;
import java.util.ArrayList;
import java.util.List;

/**
 * FormModel class.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (03/02/2013 20:26)
 */
public class FormModel<T> {

    private EntityModel<T> entityModel = new EntityModel<T>();
    private String property = "";
    private ModeModel modeModel = new ModeModel();
    private EntityDao<T> entityDao;
    private List<FormModelListener> listeners 
            = new ArrayList<FormModelListener>();
    
    public EntityModel<T> getEntityModel() {
        return entityModel;
    }

    public void setEntityModel(EntityModel<T> entityModel) {
        if (entityModel == null) {
            return;
        }
        this.entityModel = entityModel;
        fireEntityModelChanged();
    }
        
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        if (property == null) {
            property = "";
        }
        this.property = property;
    }

    public ModeModel getModeModel() {
        return modeModel;
    }

    public void setModeModel(ModeModel modeModel) {
        this.modeModel = modeModel;
    }

    public EntityDao<T> getEntityDao() {
        return entityDao;
    }

    public void setEntityDao(EntityDao<T> entityDao) {
        this.entityDao = entityDao;
    }

    // -- methods for CRUD ---
    
    public void reload() throws Exception {
        System.out.println("reload");
        if (entityDao != null) {
            T entity = entityModel.getEntity();
            entityModel.setEntity(entity);
        }
    }

    public void update() throws Exception {
        if (modeModel.getMode().equals(ModeModel.CrudMode.READY_ONLY)) {
            modeModel.setMode(ModeModel.CrudMode.UPDATE);
        }
        else {
            throw new Exception("Can't update in actual mode !");
        }
    }

    public void insert() throws Exception {
        if ((modeModel.getMode().equals(ModeModel.CrudMode.EMPTY) 
                || modeModel.getMode().equals(ModeModel.CrudMode.READY_ONLY)) 
                && entityDao != null) {
            
            T newInstance = entityDao.createNewInstance();
            entityModel.setEntity(newInstance);
            modeModel.setMode(ModeModel.CrudMode.INSERT);
        }
        else {
            throw new Exception("Can't insert in actual mode !");
        }
    }

    public void delete() throws Exception {
        if (modeModel.getMode().equals(ModeModel.CrudMode.READY_ONLY) 
                && entityModel.getEntity() != null && entityDao != null) {
            
            System.out.println("delete");
            List<T> ts = new ArrayList<T>();
            ts.add(entityModel.getEntity());
            entityDao.delete(ts);
        }
        else {
            throw new Exception("Can't delete in actual mode !");
        }
        entityModel.setEntity(null);
    }
    
    public void cancel() throws Exception {
        if (modeModel.getMode().equals(ModeModel.CrudMode.UPDATE)) {
            modeModel.setMode(ModeModel.CrudMode.READY_ONLY);
        }
        else if (modeModel.getMode().equals(ModeModel.CrudMode.INSERT)) {
            entityModel.setEntity(null);
        }
        else {
            throw new Exception("Can't cancel in actual mode !");
        }
        reload();
    }    
 
    public void save() throws Exception {
        if (modeModel.getMode().equals(ModeModel.CrudMode.UPDATE) && entityDao != null) {
            System.out.println("update");
            fireUpdateModel();
            List<T> ts = new ArrayList<T>();
            ts.add(entityModel.getEntity());
            entityDao.update(ts);
            modeModel.setMode(ModeModel.CrudMode.READY_ONLY);
        }
        else if (modeModel.getMode().equals(ModeModel.CrudMode.INSERT) && entityDao != null) {
            System.out.println("insert");
            fireUpdateModel();
            List<T> ts = new ArrayList<T>();
            ts.add(entityModel.getEntity());
            entityDao.insert(ts);
            modeModel.setMode(ModeModel.CrudMode.READY_ONLY);
        }
        else {
            throw new Exception("Can't save in actual mode !");
        }
        reload();
    }
    
    // --- Listener ---
    
    public void addListener(FormModelListener listener) {
        if (listeners.contains(listener)) {
            return;
        }
        listeners.add(listener);
    }

    public void removeListener(FormModelListener listener) {
        listeners.remove(listener);
    }

    protected void fireEntityModelChanged() {
        for (FormModelListener listener : listeners) {
            listener.entityModelChanged();
        }
    }

    protected void fireUpdateModel() throws Exception {
        for (FormModelListener listener : listeners) {
            listener.updateModel();
        }
    }
    
}
