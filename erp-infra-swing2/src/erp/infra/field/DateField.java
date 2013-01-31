package erp.infra.field;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 * DateField class.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (31/01/2013 14:00)
 */
public class DateField extends TextField implements DatePicker.ModelListener {

    private JPopupMenu popup = new JPopupMenu();
    private DatePicker datePicker = new DatePicker();
    private ShowPopupAction showPopupAction = new ShowPopupAction();
    
    public DateField() {
        initComponents();
        popup.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        popup.setLayout(new BorderLayout());
        popup.add(datePicker, BorderLayout.CENTER);
        popup.setPreferredSize(new Dimension(250, 150));
        datePicker.setListener(this);
        init(Date.class);
        button.addActionListener(showPopupAction);
        text.registerKeyboardAction(showPopupAction
                , KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0)
                , JComponent.WHEN_FOCUSED); 
        
        add(button, BorderLayout.EAST);
    }

    @Override
    public void dateChanged() {
        setValue(datePicker.getDate());
        popup.setVisible(false);
    }
    
    private class ShowPopupAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            text.requestFocus();
            datePicker.setDate((Date) DateField.this.getValue());
            popup.show(text, 0, text.getHeight());
            
            // Garante que o foco fique em datePicker
            // necessario para que o usuario possa selecionar
            // a data atraves do teclado
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    datePicker.requestFocus();
                }
            });
        }
    }

    @Override
    public void setEditable(boolean editable) {
        super.setEditable(editable);
        button.setEnabled(editable);
        
        button.removeActionListener(showPopupAction);
        text.unregisterKeyboardAction(
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
        
        if (editable) {
            button.addActionListener(showPopupAction);
            text.registerKeyboardAction(showPopupAction
                    , KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0)
                    , JComponent.WHEN_FOCUSED); 
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

        button = new javax.swing.JButton();

        button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/infra/images/datePicker.gif"))); // NOI18N
        button.setFocusable(false);
        button.setPreferredSize(new java.awt.Dimension(32, 25));

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button;
    // End of variables declaration//GEN-END:variables
}
