package erp.infra.form;

import erp.infra.form.Form;
import java.awt.Color;
import java.awt.Graphics;

/**
 * FormDesignPanel class.
 *
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (07/02/2013 15:48)
 */
public class FormDesignPanel extends Form {
    
    private int gridSize = 10;
    
    public FormDesignPanel() {
        initComponents();
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        if (gridSize < 5) {
            gridSize = 5;
        }
        this.gridSize = gridSize;
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        for (int y=0; y<=getHeight(); y+=gridSize) {
            for (int x=0; x<=getWidth(); x+=gridSize) {
                g.drawLine(x, y, x, y);
            }
        }
    }
    
}