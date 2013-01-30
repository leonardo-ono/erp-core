package erp.infra.field;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

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
        new ObjectTypeConfig();
        new TextTypeConfig();
        new CharacterTypeConfig();
        new BooleanTypeConfig();
        new NumericTypeConfig();
        new DateTypeConfig();
    }
    
    @Override
    public boolean isAcceptableType(Class type) {
        return typeConfigs.containsKey(type);
    }
    
    @Override
    public Set<Class> acceptableTypes() {
        return typeConfigs.keySet();
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
    public void setValue(Object value) {
        typeConfig.setValue(value);
    }

    @Override
    public Object getValue() {
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
        add(text, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private erp.infra.field.RegexText text;
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

    // --- Default type configs ---

    private class ObjectTypeConfig extends TypeConfig<Object> {
        private Object obj;
        
        ObjectTypeConfig() {
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
            obj = value;
            if (obj == null) {
                text.setText("");
            }
            else {
                text.setText(obj.toString());
            }
        }
    }
    
    private class TextTypeConfig extends TypeConfig<String> {
        TextTypeConfig() {
            setTypeConfig();
        }
        
        private void setTypeConfig() {
            typeConfigs.put(String.class, this);
        }
        
        @Override
        public void config() {
            text.setRegex(".*");
        }

        @Override
        public String getValue() {
            return text.getText();
        }

        @Override
        public void setValue(String value) {
            text.setText(value);
        }
    }

    private class CharacterTypeConfig extends TypeConfig<Character> {
        CharacterTypeConfig() {
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
            text.setText(value.toString());
        }
    }
    
    private class BooleanTypeConfig extends TypeConfig<Boolean> {
        BooleanTypeConfig() {
            setAllTypeConfigs();
        }
        
        private void setAllTypeConfigs() {
            typeConfigs.put(Boolean.class, this);
            typeConfigs.put(boolean.class, this);
        }
        
        @Override
        public void config() {
            // TODO internationalization
            text.setRegex("SIM|NAO");
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
            if (value) {
                text.setText("SIM");
            }
            else {
                text.setText("NAO");
            }
        }
    }
    
    private class NumericTypeConfig extends TypeConfig {
        NumericTypeConfig() {
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
            text.setText(value.toString());
        }
    }
 
    private class DateTypeConfig extends TypeConfig<Date> {
        private DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        DateTypeConfig() {
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
            text.setText(sdf.format(value));
        }
    }
    
}
