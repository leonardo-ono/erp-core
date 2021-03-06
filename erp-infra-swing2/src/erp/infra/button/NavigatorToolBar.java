package erp.infra.button;

import javax.swing.JToolBar;

/**
 * NavigatorToolBar class.
 *
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (07/02/2013 09:39)
 */
public class NavigatorToolBar extends JToolBar {

    /**
     * Creates new form CrudToolBar
     */
    public NavigatorToolBar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reloadGridButton1 = new erp.infra.button.ReloadGridButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        firstlButton1 = new erp.infra.button.FirstButton();
        previousButton1 = new erp.infra.button.PreviousButton();
        nextButton1 = new erp.infra.button.NextButton();
        lastButton1 = new erp.infra.button.LastButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        reloadGridButton1.setText("reloadGridButton1");
        reloadGridButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reloadGridButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(reloadGridButton1);
        add(jSeparator1);

        firstlButton1.setText("firstlButton1");
        firstlButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        firstlButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(firstlButton1);

        previousButton1.setText("previousButton1");
        previousButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        previousButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(previousButton1);

        nextButton1.setText("nextButton1");
        nextButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nextButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(nextButton1);

        lastButton1.setText("lastButton1");
        lastButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lastButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(lastButton1);
        add(jSeparator2);

        jLabel5.setText("   ");
        add(jLabel5);

        jLabel1.setText("Tamanho da p�g.:");
        add(jLabel1);

        jTextField1.setMaximumSize(new java.awt.Dimension(60, 25));
        jTextField1.setMinimumSize(new java.awt.Dimension(60, 25));
        add(jTextField1);

        jLabel4.setText("   ");
        add(jLabel4);
        add(jSeparator3);

        jLabel3.setText("   ");
        add(jLabel3);

        jLabel2.setText("Total de linhas: 127      P�gina: 1 de 23");
        add(jLabel2);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private erp.infra.button.FirstButton firstlButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JTextField jTextField1;
    private erp.infra.button.LastButton lastButton1;
    private erp.infra.button.NextButton nextButton1;
    private erp.infra.button.PreviousButton previousButton1;
    private erp.infra.button.ReloadGridButton reloadGridButton1;
    // End of variables declaration//GEN-END:variables
}
