package erp.infra.field;

import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TextField class.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (30/01/2013 09:18)
 */
public class TextField extends Field implements FocusListener {

    // --- TextField specific properties ---

    private String regex = "";
    private boolean selectAllOnFocus = true;
    private boolean editablePropertyModificable = true;

    public TextField() {
        initComponents();
        text.addFocusListener(this);
        component = text;
        
        // Set acceptable types for this field
        new ObjectTypeImplementation();
        new TextTypeImplementation();
        new CharacterTypeImplementation();
        new BooleanTypeImplementation();
        new NumericTypeImplementation();
        new DateTypeImplementation();
        new BigDecimalTypeImplementation();
    }
    
    @Override
    public boolean isAcceptableType(Class type) {
        return typeConfigs.containsKey(type);
    }
    
    /**
     * Inicia as configuracoes padroes para cada tipo aceitavel.
     */
    @Override
    public void init(Class type) {
        typeConfig = typeConfigs.get(type);
        if (typeConfig == null) {
            throw new UnsupportedOperationException("Type " 
                    + type.getName() + " not supported !");
        }
        this.type = type;
        typeConfig.config();
    }
    
    @Override
    public void setEditable(boolean editable) {
        if (editablePropertyModificable) {
            text.setEditable(editable);
        }
    }
    
    @Override
    public boolean isEditable() {
        return text.isEditable();
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        text.setEnabled(enabled);
    }
    
    @Override
    public boolean isEnabled() {
        return text.isEnabled();
    }
    
    @Override
    public void setValue(Object value) {
        if (typeConfig == null) {
            return;
        }
        typeConfig.setValue(value);
    }

    @Override
    public Object getValue() {
        if (typeConfig == null) {
            return null;
        }
        return typeConfig.getValue();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        text = new erp.infra.field.RegexText();

        setLayout(new java.awt.BorderLayout());

        text.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textActionPerformed(evt);
            }
        });
        text.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textFocusLost(evt);
            }
        });
        add(text, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textActionPerformed
        KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
    }//GEN-LAST:event_textActionPerformed

    private void textFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFocusLost
        // TODO permitir usuario definir se valida o valor agora ou nao ?
        setValue(getValue());
    }//GEN-LAST:event_textFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected erp.infra.field.RegexText text;
    // End of variables declaration//GEN-END:variables

    // --- TextField specific properties ---
    
    public void setFieldText(String fieldText) {
        this.text.setText(fieldText);
    }
    
    public String getFieldText() {
        return text.getText();
    }

    public boolean isSelectAllOnFocus() {
        return selectAllOnFocus;
    }

    public void setSelectAllOnFocus(boolean selectAllOnFocus) {
        this.selectAllOnFocus = selectAllOnFocus;
    }
    
    // --- FocusListener implementation ---

    @Override
    public void focusGained(FocusEvent e) {
        if (selectAllOnFocus) {
            text.selectAll();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    // --- Default type implementation ---

    private class ObjectTypeImplementation extends TypeImplementation<Object> {
        private Object obj;
        
        ObjectTypeImplementation() {
            setTypeConfig();
        }
        
        private void setTypeConfig() {
            typeConfigs.put(Object.class, this);
        }
        
        @Override
        public void config() {
            setEditable(false);
            editablePropertyModificable = false;
        }

        @Override
        Object getValue() {
            return obj;
        }

        @Override
        void setValue(Object value) {
            this.obj = value;
            if (value == null) {
                text.setText("");
            }
            else {
                text.setText(value.toString());
            }
        }
    }
    
    private class TextTypeImplementation extends TypeImplementation<String> {
        TextTypeImplementation() {
            setTypeConfig();
        }
        
        private void setTypeConfig() {
            typeConfigs.put(String.class, this);
        }
        
        @Override
        public void config() {
            // text.setRegex(".*");
        }

        @Override
        public String getValue() {
            return text.getText();
        }

        @Override
        public void setValue(String value) {
            if (value == null) {
                text.setText("");
            }
            else {
                text.setText(value);
            }
        }
    }

    private class CharacterTypeImplementation extends TypeImplementation<Character> {
        CharacterTypeImplementation() {
            setAllTypeConfigs();
        }
        
        private void setAllTypeConfigs() {
            typeConfigs.put(Character.class, this);
            typeConfigs.put(char.class, this);
        }
        
        @Override
        public void config() {
            text.setRegex(".{1}");
        }

        @Override
        Character getValue() {
            Character c = null;
            try {
                c = text.getText().charAt(0);
            }
            catch (Exception e) { }
            return c;
        }

        @Override
        void setValue(Character value) {
            if (value == null) {
                text.setText("");
            }
            else {
                text.setText(value.toString());
            }
        }
    }
    
    private class BooleanTypeImplementation extends TypeImplementation<Boolean> {
        BooleanTypeImplementation() {
            setAllTypeConfigs();
        }
        
        private void setAllTypeConfigs() {
            typeConfigs.put(Boolean.class, this);
            typeConfigs.put(boolean.class, this);
        }
        
        @Override
        public void config() {
            // TODO internationalization
            text.setRegex("S|N");
        }

        @Override
        Boolean getValue() {
            Boolean ret = false;
            if (text.getText().startsWith("S")) {
                ret = true;
            }
            return ret;
        }

        @Override
        void setValue(Boolean value) {
            if (value != null && value) {
                text.setText("S");
            }
            else {
                text.setText("N");
            }
        }
    }
    
    private class NumericTypeImplementation extends TypeImplementation {
        NumericTypeImplementation() {
            setAllTypeConfigs();
        }
        
        private void setAllTypeConfigs() {
            typeConfigs.put(byte.class, this);
            typeConfigs.put(Byte.class, this);
            typeConfigs.put(short.class, this);
            typeConfigs.put(Short.class, this);
            typeConfigs.put(int.class, this);
            typeConfigs.put(Integer.class, this);
            typeConfigs.put(Long.class, this);
            typeConfigs.put(long.class, this);
        }
        
        @Override
        public void config() {
            text.setRegex("[0-9]*");
        }

        @Override
        Object getValue() {
            Object ret = null;
            try {
                Constructor c = type.getConstructor(String.class);
                ret = c.newInstance(text.getText());
            } catch (Exception ex) {
            }
            return ret;
        }

        @Override
        void setValue(Object value) {
            if (value == null) {
                text.setText("");
            }
            else {
                text.setText(value.toString());
            }
        }
    }
 
    private class DateTypeImplementation extends TypeImplementation<Date> {
        private DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        DateTypeImplementation() {
            setTypeConfig();
        }
        
        private void setTypeConfig() {
            typeConfigs.put(java.util.Date.class, this);
        }
        
        @Override
        public void config() {
            // TODO internationalization
            text.setRegex("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        }

        @Override
        Date getValue() {
            Date ret = null;
            try {
                ret = sdf.parse(text.getText());
            } catch (ParseException ex) {
            }
            return ret;
        }

        @Override
        void setValue(Date value) {
            try {
                text.setText(sdf.format(value));
            }
            catch (Exception e) {
                text.setText("");
            }
        }
    }
    
    private class BigDecimalTypeImplementation extends TypeImplementation<BigDecimal> {
        
        BigDecimalTypeImplementation() {
            setTypeConfig();
        }
        
        private void setTypeConfig() {
            typeConfigs.put(BigDecimal.class, this);
        }
        
        @Override
        public void config() {
            // TODO internationalization
            text.setRegex("[0-9,]*");
        }

        @Override
        BigDecimal getValue() {
            BigDecimal ret = null;
            try {
                String stringValue = text.getText();
                stringValue = stringValue.replace(".", "");
                stringValue = stringValue.replace(",", ".");
                ret = new BigDecimal(stringValue);
                System.out.println("TextField getValue() BigDecimal=" + ret);
            } catch (Exception ex) {
            }
            return ret;
        }

        @Override
        void setValue(BigDecimal value) {
            try {
                String stringValue = value.toString();
                stringValue = stringValue.replace(".", ",");
                text.setText(stringValue);
            }
            catch (Exception e) {
                text.setText("");
            }
        }
    }
    
}
