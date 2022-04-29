package org.viodo.toshell.ui.components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author chenxc
 */
public class LookAndFeelsComboBox extends JComboBox<UIManager.LookAndFeelInfo> {
    private final PropertyChangeListener lafListener = this::lafChanged;

    @SuppressWarnings("unchecked")
    public LookAndFeelsComboBox() {
        setRenderer(new BasicComboBoxRenderer() {
            @Override
            @SuppressWarnings("rawtypes")
            public Component getListCellRendererComponent(JList list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                value = (value != null)
                        ? ((UIManager.LookAndFeelInfo) value).getName()
                        : UIManager.getLookAndFeel().getName();
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
    }

    public void addLookAndFeel(String name, String className) {
        getMutableModel().addElement(new UIManager.LookAndFeelInfo(name, className));
    }

    public String getSelectedLookAndFeel() {
        Object sel = getSelectedItem();
        return (sel instanceof UIManager.LookAndFeelInfo) ? ((UIManager.LookAndFeelInfo) sel).getClassName() : null;
    }

    public void setSelectedLookAndFeel(String className) {
        setSelectedIndex(getIndexOfLookAndFeel(className));
    }

    public void selectedCurrentLookAndFeel() {
        setSelectedLookAndFeel(UIManager.getLookAndFeel().getClass().getName());
    }

    public void removeLookAndFeel(String className) {
        int index = getIndexOfLookAndFeel(className);
        if (index >= 0) {
            getMutableModel().removeElementAt(index);
        }
    }

    public int getIndexOfLookAndFeel(String className) {
        ComboBoxModel<UIManager.LookAndFeelInfo> model = getModel();
        int size = model.getSize();
        for (int i = 0; i < size; i++) {
            if (className.equals(model.getElementAt(i).getClassName())) {
                return i;
            }
        }
        return -1;
    }

    private MutableComboBoxModel<UIManager.LookAndFeelInfo> getMutableModel() {
        return (MutableComboBoxModel<UIManager.LookAndFeelInfo>) getModel();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        selectedCurrentLookAndFeel();
        UIManager.addPropertyChangeListener(lafListener);
    }

    @Override
    public void removeNotify() {
        super.removeNotify();

        UIManager.removePropertyChangeListener(lafListener);
    }

    void lafChanged(PropertyChangeEvent e) {
        if ("lookAndFeel".equals(e.getPropertyName())) {
            selectedCurrentLookAndFeel();
        }
    }
}
