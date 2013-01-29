package erp.infra;

import br.beanlinker.core.BeanLinker;
import br.beanlinker.core.BeanLinkerImpl;
import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Form class.
 *
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (27/01/2013 20:52)
 */
public class Form extends JPanel {

    public enum Mode { EMPTY, INSERT, UPDATE, READ_ONLY, CUSTOM }
    
    private Mode mode = Mode.EMPTY;
    private String property;
    private FormController controller;

    public Form() {
        initComponents();
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        repaint();
    }

    public Object getEntity() {
        return controller.getEntity();
    }

    public void setEntity(Object entity) {
        controller.setEntity(entity);
        try {
            if (entity != null) {
                reloadPrivate(entity);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public FormController getController() {
        return controller;
    }

    public void setController(FormController controller) {
        this.controller = controller;
    }

    public void delete() {
        if (controller == null) {
            return;
        }
        try {
            controller.delete();
        } catch (Exception ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void update() {
        if (controller == null) {
            return;
        }
        try {
            controller.update();
        } catch (Exception ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void insert() {
        if (controller == null) {
            return;
        }
        try {
            controller.insert();
        } catch (Exception ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void reload() {
        if (controller == null) {
            return;
        }
        try {
            controller.reload();
            if (controller.getEntity() == null) {
                setMode(Mode.EMPTY);
                return;
            }
            reloadPrivate(controller.getEntity());
        } catch (Exception ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }

    private void reloadPrivate(Object entityPrivate) throws Exception {
        for (Component c : getComponents()) {
            if (c instanceof Field) {
                BeanLinker linker = new BeanLinkerImpl();
                linker.registerClass("Entity", entityPrivate.getClass().getName());
                linker.registerClass("Field", Field.class.getName());
                Field field = (Field) c;
                linker.assign("entity", entityPrivate);
                linker.assign("field", field);
                if (field.getExpression() != null && !field.getExpression().trim().isEmpty()) {
                    linker.eval("field.fieldText = " + field.getExpression());
                }
                else if (field.getProperty() == null || field.getProperty().trim().isEmpty()) {
                    continue;
                }
                else {
                    linker.linkProperty("Entity." + field.getProperty(), "Field.fieldText", "", "", "", "");
                    linker.update("entity", "field");
                }
            }
            else if (c instanceof Lookup) {
                BeanLinker linker = new BeanLinkerImpl();
                linker.registerClass("Entity", entityPrivate.getClass().getName());
                linker.registerClass("Lookup", Lookup.class.getName());
                Lookup lookup = (Lookup) c;
                linker.assign("entity", entityPrivate);
                linker.assign("lookup", lookup);
                if (lookup.getExpression() != null && !lookup.getExpression().trim().isEmpty()) {
                    linker.eval("lookup.entity = " + lookup.getExpression());
                }
                else if (lookup.getProperty() == null || lookup.getProperty().trim().isEmpty()) {
                    continue;
                }
                else {
                    linker.linkProperty("Entity." + lookup.getProperty(), "lookup.entity", "", "", "", "");
                    linker.update("entity", "lookup");
                }
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Desenha os labels dos campos adicionados neste formulario
        for (Component c : getComponents()) {
            
            if (c instanceof Field) {
                Field field = (Field) c;
                g.setFont(field.getLabel().getFont());
                FontMetrics fm = g.getFontMetrics();
                String label = field.getLabel().getText();
                int fontWidth = fm.stringWidth(label);
                int fontHeight = fm.getHeight();
                field.getLabel().setSize(fontWidth, field.getText().getHeight());
                Graphics g2 = g.create(c.getBounds().x - fontWidth - 5, c.getBounds().y, getWidth(), getHeight());
                field.getLabel().paint(g2);
                
                // Desenha o required
                if (field.isRequired()) {
                    g.setColor(new Color(255, 100, 100));
                    g.drawString("*", c.getBounds().x + c.getBounds().width + 3, c.getBounds().y + (fontHeight / 2));
                }
                
                if (mode == Mode.EMPTY) {
                    field.getText().setEditable(false);
                    // field.getText().setText("");
                }
                else if (mode == Mode.READ_ONLY) {
                    field.getText().setEditable(false);
                }
                else if (mode == Mode.UPDATE) {
                    field.getText().setEditable(field.isEditableOnUpdate());
                }
                else if (mode == Mode.INSERT) {
                    field.getText().setEditable(field.isEditableOnInsert());
                }
                else {
                    field.getText().setEditable(true);
                }
            }
            else if (c instanceof Lookup) {
                Lookup lookup = (Lookup) c;
                if (mode == Mode.EMPTY) {
                    lookup.getText().setEditable(false);
                    // field.getText().setText("");
                }
                else if (mode == Mode.READ_ONLY) {
                    lookup.getText().setEditable(false);
                }
                else if (mode == Mode.UPDATE) {
                    lookup.getText().setEditable(lookup.isEditableOnUpdate());
                }
                else if (mode == Mode.INSERT) {
                    lookup.getText().setEditable(lookup.isEditableOnInsert());
                }
                else {
                    lookup.getText().setEditable(true);
                }
                
            }
        }        
    }

    
}
