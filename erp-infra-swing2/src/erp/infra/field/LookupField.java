package erp.infra.field;

import erp.infra.annotation.Form;
import erp.infra.entity.EntityDao;
import erp.infra.entity.EntityModel;
import erp.infra.entity.EntityModelListener;
import erp.infra.entity.GenericJpaDao;
import erp.infra.filter.ConditionContainer;
import erp.infra.filter.Filter;
import erp.infra.filter.LikeOperation;
import erp.infra.form.FormUtils;
import erp.infra.grid.Grid;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

/**
 * LookupField class.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (01/02/2013 09:29)
 */
public class LookupField extends Field {

    private Class entityClass;
    
    private JPopupMenu popup = new JPopupMenu();
    private JList popupList = new JList(new PopupListModel());
    private JScrollPane popupScrollPane = new JScrollPane(popupList);
    private JLabel popupListItemRenderComponent = new JLabel();
    
    private EnterKeyAction enterKeyAction = new EnterKeyAction();
    private UpKeyAction upKeyAction = new UpKeyAction();
    private DownKeyAction downKeyAction = new DownKeyAction();
    
    private Model model;
    private String labelExpression = "''";
    private ModelListener modelListener = new ModelListenerImpl();

    // --- Popup that allow user to select a field to lookup ---
    
    private JPopupMenu popupFields = new JPopupMenu();
    private JList popupFieldsList = new JList(new PopupFieldsListModel());
    private JScrollPane popupFieldsScrollPane = new JScrollPane(popupFieldsList);

    public LookupField() {
        initComponents();
        setComponent(text);
        setModel(new Model());

        popupListItemRenderComponent.setFont(label.getFont());
        popupListItemRenderComponent.setOpaque(true);
                
        popupListItemRenderComponent.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)
                , BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        
        text.setText("");
        label.setText("");
        popupScrollPane.setBorder(null);
        popupList.addMouseListener(new ListMouseClicked());
        popupList.setBorder(null);
        popupList.setFocusable(false);
        popupList.setCellRenderer(new ListItemRenderer());
        popup.setBackground(popupList.getBackground());
        popup.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        popup.setLayout(new BorderLayout());
        popup.add(popupScrollPane, BorderLayout.CENTER);
        popup.setPreferredSize(new Dimension(250, 150));
        popup.setFocusable(false);
        
        text.registerKeyboardAction(enterKeyAction
                , KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0)
                , JComponent.WHEN_FOCUSED); 
        
        text.registerKeyboardAction(upKeyAction
                , KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0)
                , JComponent.WHEN_FOCUSED); 
        
        text.registerKeyboardAction(downKeyAction
                , KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0)
                , JComponent.WHEN_FOCUSED); 
        
        // --- Popup that allow user to select a field to lookup ---
        popupFieldsScrollPane.setBorder(null);
        popupFieldsList.addMouseListener(new ListFieldsMouseClicked());
        popupFieldsList.setBorder(null);
        popupFieldsList.setFocusable(true);
        popupFields.setBackground(popupList.getBackground());
        popupFields.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        popupFields.setLayout(new BorderLayout());
        popupFields.add(popupFieldsScrollPane, BorderLayout.CENTER);
        popupFields.setPreferredSize(new Dimension(250, 150));
        popupFields.setFocusable(false);
    }
    
    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
        setModel(createModel(entityClass));
        getModel().setEntityDao(createGenericDao(entityClass));
    }
    
    private <T> Model<T> createModel(Class<T> entityClass) {
        return new Model<T>();
    }
    
    private <T> GenericJpaDao<T> createGenericDao(final Class<T> entityClass) {
        return new GenericJpaDao<T>() {
            @Override
            public Class getEntityClass() throws Exception {
                return entityClass;
            }
            
        };
    }
    
    public JLabel getPopupListItemRenderComponent() {
        return popupListItemRenderComponent;
    }

    public void setPopupListItemRenderComponent(
            JLabel popupListItemRenderComponent) {
        
        this.popupListItemRenderComponent = popupListItemRenderComponent;
    }

    public Model getModel() {
        return model;
    }

    public final void setModel(Model model) {
        this.model = model;
        model.view = this;
        model.addListener(modelListener);
        model.getEntityModel().addListener(new EntityModelListenerImpl());
    }

    public String getLabelExpression() {
        return labelExpression;
    }

    public void setLabelExpression(String labelExpression) {
        this.labelExpression = labelExpression;
    }

    public JButton getButton() {
        return button;
    }

    public JSplitPane getSplitPane() {
        return splitPane;
    }

    public JTextField getText() {
        return text;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        text = new javax.swing.JTextField();
        panel = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        button = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        splitPane.setBorder(null);
        splitPane.setDividerLocation(100);

        text.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        text.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                textFocusGained(evt);
            }
        });
        text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textKeyReleased(evt);
            }
        });
        splitPane.setLeftComponent(text);

        panel.setLayout(new java.awt.BorderLayout());

        label.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label.setText("description");
        label.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        panel.add(label, java.awt.BorderLayout.CENTER);

        button.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        button.setText("...");
        button.setFocusable(false);
        button.setPreferredSize(new java.awt.Dimension(32, 23));
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
        panel.add(button, java.awt.BorderLayout.LINE_START);

        splitPane.setRightComponent(panel);

        add(splitPane);
    }// </editor-fold>//GEN-END:initComponents

    private void textFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFocusLost
        updateView();
        popup.setVisible(false);
    }//GEN-LAST:event_textFocusLost

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        Component obj = getParent();
        while (!(obj instanceof JFrame)) {
            obj = obj.getParent();
        }
        JDialog gridDialog = new GridDialog((JFrame) obj);
    }//GEN-LAST:event_buttonActionPerformed

    private void textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textKeyReleased
        if (!model.isShowPopupListOnKeypress() && !popup.isVisible()) {
            return;
        }
        if (evt.getKeyCode() == 27) {
            popup.setVisible(false);
            return;
        }
        if (!Character.isDefined(evt.getKeyChar()) 
                || evt.getKeyCode() == 10 || evt.getKeyCode() == 13) {
            
            return;
        }
        try {
            model.initUpdateList(text.getText());
        } catch (Exception ex) {
            Logger.getLogger(LookupField.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_textKeyReleased

    private void textFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFocusGained
        text.selectAll();
    }//GEN-LAST:event_textFocusGained

    public void showPopupFields() {
        popupFields.show(this, 0, 0);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                popupFieldsList.requestFocus();
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button;
    private javax.swing.JLabel label;
    private javax.swing.JPanel panel;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JTextField text;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean isAcceptableType(Class type) {
        Form formAnnotation = (Form) type.getAnnotation(Form.class);
        return (formAnnotation != null);
    }

    @Override
    public void init(Class type) {
        if (!isAcceptableType(type)) {
            throw new RuntimeException("Class " + type.getName() 
                    + " is not a acceptable type for LookupField !");
        }
    }

    @Override
    public boolean isEditable() {
        return text.isEditable();
    }

    @Override
    public void setEditable(boolean editable) {
        text.setEditable(editable);
        button.setEnabled(editable);
    }
    
    @Override
    public boolean isEnabled() {
        return text.isEnabled();
    }

    @Override
    public void setEnabled(boolean enabled) {
        text.setEnabled(enabled);
        button.setEnabled(enabled);
    }
    
    @Override
    public void setValue(Object value) {
        model.getEntityModel().setEntity(value);
    }

    @Override
    public Object getValue() {
        return model.getEntityModel().getEntity();
    }

    public void updateView() {
        try {
            if (model.getEntityModel().getEntity() == null) {
                text.setText("");
                label.setText("");
                return;
            }
            ScriptEngine se = new ScriptEngineManager()
                    .getEngineByName("JavaScript");
            
            se.put("entity", model.getEntityModel().getEntity());
            Object ret = se.eval("entity." + model.getLookupProperty());
            if (ret == null) {
                ret = "";
            }
            text.setText(ret.toString());
            label.setText(se.eval(labelExpression).toString());
        } catch (ScriptException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void updateList() {
        if (!popup.isVisible() && model.getList().size() > 0) {
            showPopupList();
        }
        if (popup.isVisible() && model.getList().isEmpty()) {
            popup.setVisible(false);
        }
        if (popup.isVisible()) {
            popupList.setSelectedIndex(0);
            popupList.updateUI();
        }
    }
    
    private void showPopupList() {
        popup.setPreferredSize(model.getPopupListSize());
        popup.show(text, 0, text.getHeight());
    }
    
    // --- Model ---
    
    public static class Model<T> {
        protected LookupField view;
        private EntityModel<T> entityModel = new EntityModel<T>();
        private EntityDao entityDao;
        protected String lookupProperty;
        private List<ModelListener> listeners 
                = new ArrayList<ModelListener>();
        
        private List<T> list = new ArrayList<T>();

        public LookupField getView() {
            return view;
        }

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

        public EntityDao getEntityDao() {
            return entityDao;
        }

        public void setEntityDao(EntityDao entityDao) {
            this.entityDao = entityDao;
        }

        public String getLookupProperty() {
            return lookupProperty;
        }

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
            fireListChanged();
        }

        public void setLookupProperty(String lookupProperty) {
            this.lookupProperty = lookupProperty;
        }
        
        private void initUpdateList(String value) throws Exception {
            if (entityDao != null) {
                Filter filter = new Filter(entityDao.getEntityClass());
                ConditionContainer condition = filter.createCondition(view.getModel().getLookupProperty(), new LikeOperation());
                condition.getField().setValue("%" + value.trim() + "%");
                filter.getContainers().add(condition);
                List<T> returnList = entityDao.executeQuery(filter);
                setList(returnList);
            }
        }

        public Dimension getPopupListSize() {
            return new Dimension(view.getBounds().width
                    , view.getBounds().height * 5);
        }
    
        public String getPopupListItemExpression() {
            String selectedText = view.text.getText();
            return "'<html><body>' + entity." 
                    + getLookupProperty() + ".toString().replace('" + selectedText 
                    + "', '<strong>" + selectedText + "</strong>') + "
                    + view.getLabelExpression() + " + '</body></html>'";
        }
        
        private boolean showPopupListOnKeypress = false;
        
        public boolean isShowPopupListOnKeypress() {
            return showPopupListOnKeypress;
        }

        public void setShowPopupListOnKeypress(boolean show) {
            this.showPopupListOnKeypress = show;
        }

        // --- Listener ---
        
        public void addListener(ModelListener listener) {
            listeners.add(listener);
        }
        
        public void removeListener(ModelListener listener) {
            listeners.remove(listener);
        }
        
        private void fireEntityModelChanged() {
            for (ModelListener listener : listeners) {
                listener.entityModelChanged();
            }
        }

        private void fireListChanged() {
            for (ModelListener listener : listeners) {
                listener.listChanged();
            }
        }
        
    }

    // --- ModelListener ---
    
    public static interface ModelListener {
        void entityModelChanged();
        void listChanged();
    }

    // --- EntityModelListener implementation ---
    
    private class EntityModelListenerImpl implements EntityModelListener {
        @Override
        public void entityChanged() {
            updateView();
        }
    }
    
    // --- ModelListener implementation for this view ---
    
    private class ModelListenerImpl implements ModelListener {
        @Override
        public void entityModelChanged() {
            model.getEntityModel().addListener(new EntityModelListenerImpl());
        }

        @Override
        public void listChanged() {
            updateList();
        }
    }
    
    // --- PopupListModel implementation ---
    
    private class PopupListModel extends AbstractListModel {

        @Override
        public int getSize() {
            return model.getList().size();
        }

        @Override
        public Object getElementAt(int index) {
            return model.getList().get(index);
        }
        
    }
    
    // --- PopupFieldsListModel implementation ---
    
    private class PopupFieldsListModel extends AbstractListModel {

        @Override
        public int getSize() {
            return FormUtils.getAllFieldsAnnotation(entityClass).size();
        }

        @Override
        public Object getElementAt(int index) {
            return FormUtils.getAllFieldsAnnotation(entityClass).get(index).label();
        }
        
    }

    // --- Allow user to select a lookup property by double clicking on a item ---
    
    private class ListFieldsMouseClicked extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            erp.infra.annotation.Field field = FormUtils.getAllFieldsAnnotation(entityClass).get(popupFieldsList.getSelectedIndex());
            System.out.println("mouseClicked " + FormUtils.getPropertyById(field.id(), entityClass));
            model.setLookupProperty(FormUtils.getPropertyById(field.id(), entityClass));
            popupFields.setVisible(false);
            LookupField.this.getParent().repaint();
        }
    }

    // --- Alow user to select list item through keyboard ---
    
    private class EnterKeyAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (popup.isVisible()) {
                model.getEntityModel().setEntity(popupList.getSelectedValue());
                popup.setVisible(false);
            }
            else {
                // se o texto for vazio, considera entity nulo
                if (text.getText().trim().length() == 0) {
                    model.getEntityModel().setEntity(null);
                    FocusManager.getCurrentManager().focusNextComponent();
                    return;
                }
                
                // Se text esta com valor preenchido e o model.getSelectedItem 
                // nao eh nulo, e se o valor texto corresponde ao valor 
                // do model.getSelectedItem, passa apenas para o proximo campo
                if (text.getText().trim().length() > 0 && model.getEntityModel().getEntity() != null) {
                    try {
                        ScriptEngine se = new ScriptEngineManager()
                                .getEngineByName("JavaScript");

                        se.put("entity", model.getEntityModel().getEntity());
                        Object ret = se.eval("entity." + model.getLookupProperty());
                        if (ret == null) {
                            ret = "";
                        }
                        if (text.getText().equals(ret)) {
                            FocusManager.getCurrentManager().focusNextComponent();
                            return;
                        }
                    } catch (ScriptException ex) {
                        Logger.getLogger(LookupField.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                try {
                    List list = new ArrayList();
                    if (model.getEntityDao() != null) {
                        Filter filter = new Filter(getModel().getEntityDao().getEntityClass());
                        ConditionContainer condition = filter.createCondition(getModel().getLookupProperty(), new LikeOperation());
                        condition.getField().setValue("%" + text.getText().trim() + "%");
                        filter.getContainers().add(condition);
                        list = model.getEntityDao().executeQuery(filter);
                    }
                    if (list.isEmpty()) {
                        throw new Exception("Entity not found !");
                    }
                    else if (list.size() == 1) {
                        model.getEntityModel().setEntity(list.get(0));
                        FocusManager.getCurrentManager().focusNextComponent();
                    }
                    else {
                        model.setList(list);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LookupField.class.getName())
                            .log(Level.SEVERE, null, ex);
                    
                    JOptionPane.showMessageDialog(getParent()
                            , ex.getMessage(), ":: Aten��o:"
                            , JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private class UpKeyAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (popup.isVisible() && popupList.getSelectedIndex()==0) {
                popup.setVisible(false);
                model.setShowPopupListOnKeypress(false);
            }
            else if (popup.isVisible()) {
                int si = popupList.getSelectedIndex();
                popupList.setSelectedIndex(--si);
                popupList.ensureIndexIsVisible(si);
            }
        }
    }

    private class DownKeyAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!popup.isVisible() && !model.isShowPopupListOnKeypress()) {
                try {
                    model.initUpdateList(text.getText());
                    model.setShowPopupListOnKeypress(true);
                } catch (Exception ex) {
                    Logger.getLogger(LookupField.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (!popup.isVisible() && model.getList().size() > 0) {
                showPopupList();
                popupList.setSelectedIndex(0);
                popupList.ensureIndexIsVisible(0);
            }
            else {
                int si = popupList.getSelectedIndex();
                popupList.setSelectedIndex(++si);
                popupList.ensureIndexIsVisible(si);
            }
        }
    }
    
    // --- Allow user to select double clicking on a item ---
    
    private class ListMouseClicked extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() != 2) {
                return ;
            }
            model.getEntityModel().setEntity(popupList.getSelectedValue());
            popup.setVisible(false);
        }
    }

    // --- ListItemRenderer ---
    
    private class ListItemRenderer implements ListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList jlist, Object entity, int i
                , boolean bln, boolean bln1) {

            try {
                ScriptEngine se = new ScriptEngineManager()
                        .getEngineByName("JavaScript");
                
                se.put("entity", entity);
                popupListItemRenderComponent
                        .setText(se.eval(
                        model.getPopupListItemExpression()).toString());
                
                if (bln) {
                    popupListItemRenderComponent.setForeground(jlist.getSelectionForeground());
                    popupListItemRenderComponent.setBackground(jlist.getSelectionBackground());
                }
                else {
                    popupListItemRenderComponent.setForeground(jlist.getForeground());
                    popupListItemRenderComponent.setBackground(jlist.getBackground());
                }
                return popupListItemRenderComponent;
            } catch (ScriptException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // --- Paint implementation ---

    @Override
    public void paintLabel(Graphics g) {
        super.paintLabel(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);        
        if (popupFieldsList.getSelectedValue() != null) {
            g2d.setFont(new Font("arial", Font.PLAIN, 8));
            g2d.drawString(popupFieldsList.getSelectedValue().toString().toUpperCase() 
                    + getLabelSeparator(), getBounds().x + 10, getBounds().y - 2);
        }
        Polygon p = new Polygon();
        p.addPoint(getBounds().x, getBounds().y - 8);
        p.addPoint(getBounds().x + 7, getBounds().y - 8);
        p.addPoint(getBounds().x + 3, getBounds().y - 3);
        g.setColor(Color.GRAY);
        g.fillPolygon(p);
    }
    
    // --- Grid dialog ---
    
    private class GridDialog extends JDialog {
        
        private erp.infra.form.Form form = new erp.infra.form.Form();
        private Grid grid = new Grid();
        private JScrollPane scrollPane = new JScrollPane(grid);
        private GridDoubleClickAction gridDoubleClickAction = new GridDoubleClickAction();
        
        public GridDialog(Frame frame) {
            super(frame, true);
            
            setLayout(new BorderLayout());
            setSize(600, 400);
            setLocationRelativeTo(frame);

            form.setEntityClass(entityClass);
            
            grid.addMouseListener(gridDoubleClickAction);
            grid.setFormModel(form);
            grid.reload();
            
            add(scrollPane, BorderLayout.CENTER);
            setVisible(true);
        }
        
        private class GridDoubleClickAction extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() != 2) {
                    return;
                }
                model.getEntityModel().setEntity(grid.getGridModel().getEntities().get(grid.getSelectedRow()));
                GridDialog.this.dispose();
            }
        }

    }
    
}
