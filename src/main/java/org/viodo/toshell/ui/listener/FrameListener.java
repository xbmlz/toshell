package org.viodo.toshell.ui.listener;

import org.viodo.toshell.App;
import org.viodo.toshell.ui.form.MainForm;
import org.viodo.toshell.ui.form.SessionForm;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * <pre>
 * 主框架监听器
 * </pre>
 *
 * @author chenxc
 * @since 2022年4月28日
 */
public class FrameListener {

    public static void addListener() {
        App.mainFrame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                // set split pane divider location
                MainForm.getInstance().getLrSplitPane().setDividerLocation(0.2);
                SessionForm.getInstance().getLrSplitPane().setDividerLocation(0.8);
                SessionForm.getInstance().getTbSplitPane().setDividerLocation(0.8);
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }
}
