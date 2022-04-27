package org.viodo.toshell.ui.form;

import javax.swing.*;

/**
 * 主界面
 *
 * @author chenxc
 */
public class MainForm {

    private JPanel mainPanel;

    private JSplitPane lrSplitPane;
    private JSplitPane tbSplitPane;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JSplitPane getLrSplitPane() {
        return lrSplitPane;
    }

    public JSplitPane getTbSplitPane() {
        return tbSplitPane;
    }

    private static MainForm instance;

    public MainForm() {
    }

    public static MainForm getInstance() {
        if (instance == null) {
            instance = new MainForm();
        }
        return instance;
    }

    public void init() {
        instance = getInstance();
        instance.getMainPanel().updateUI();
        instance.getLrSplitPane().setRightComponent(new MonitorPanel().getMainPanel());
        instance.getTbSplitPane().setTopComponent(new SessionForm().getMainPanel());
        instance.getTbSplitPane().setBottomComponent(new SftpForm().getMainPanel());
    }
}
