package erp.infra.field;

import java.awt.Component;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Field class.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (27/01/2013 21:12)
 */
public abstract class Field extends JPanel {
    
    protected Component component;
    
    private String property;
    private String expression;
    
    private String labelText;
    private String labelSeparator = ":";
    
    private boolean insertable = true;
    private boolean updatable = true;
    private boolean required = false;
    
    public Field() {
        initComponents();
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
        this.label.setText(labelText + labelSeparator);
    }
    
    public String getLabelText() {
        return labelText;
    }

    public String getLabelSeparator() {
        return labelSeparator;
    }

    public void setLabelSeparator(String labelSeparator) {
        this.labelSeparator = labelSeparator;
        this.label.setText(labelText + labelSeparator);
    }

    public boolean isInsertable() {
        return insertable;
    }

    public void setInsertable(boolean insertable) {
        this.insertable = insertable;
    }

    public boolean isUpdatable() {
        return updatable;
    }

    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public Component getComponent() {
        return component;
    }

    // --- methods that must be implemented ---
    
    public abstract boolean isAcceptableType(Class type);
    public abstract Set<Class> acceptableTypes();
    public abstract void init(Class type);
    public abstract boolean isEditable();
    public abstract void setEditable(boolean editable);
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();

        label.setText("jLabel2");

        setMinimumSize(new java.awt.Dimension(110, 30));
        setPreferredSize(new java.awt.Dimension(100, 25));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables

}
