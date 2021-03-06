package erp.infra.form;

/**
 * FormModelListener interface.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (27/01/2013 23:39)
 */
public interface FormModelListener {
    
    public abstract void updateModel();
    public abstract void modeChanged();
    public abstract void entityChanged();
    public abstract void reloaded();
    public abstract void updated();
    public abstract void inserted();
    public abstract void canceled();
    public abstract void deleted();
    
}
