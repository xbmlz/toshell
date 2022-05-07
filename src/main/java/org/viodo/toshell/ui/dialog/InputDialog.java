package org.viodo.toshell.ui.dialog;

import javax.swing.*;
import java.awt.*;

/**
 * input dialog
 *
 * @author chenxc
 */
public class InputDialog {

    public static Object show(Component parentComponent, String title, String message) {
        JOptionPane inputOptionPane = new JOptionPane();
        inputOptionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        inputOptionPane.setMessage(message);
        Window window = SwingUtilities.windowForComponent(parentComponent);
        return JOptionPane.showInputDialog(
                window,
                inputOptionPane.getMessage(),
                title,
                inputOptionPane.getMessageType(),
                inputOptionPane.getIcon(),
                null,
                null);

    }
}
