package org.viodo.toshell.ui;

import cn.hutool.core.thread.ThreadUtil;
import org.viodo.toshell.ui.form.StatusBar;
import org.viodo.toshell.ui.listener.FrameListener;

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
        initListeners();
    }

    private void initComponents() {
        setName(UIConstants.APP_NAME);
        setTitle(UIConstants.APP_NAME);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initListeners() {
        ThreadUtil.execute(StatusBar::updateMemoryProgressBar);
        ThreadUtil.execute(FrameListener::addListener);
    }
}
