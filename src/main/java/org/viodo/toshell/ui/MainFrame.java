package org.viodo.toshell.ui;

import org.viodo.toshell.ui.UIConstants;

import javax.swing.*;
import java.io.Serial;

/**
 * 主框架代码
 *
 * @author chenxc
 */
public class MainFrame extends JFrame {

    @Serial
    private static final long serialVersionUID = -332963894416012132L;

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {
        setName(UIConstants.APP_NAME);
        setTitle(UIConstants.APP_NAME);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
