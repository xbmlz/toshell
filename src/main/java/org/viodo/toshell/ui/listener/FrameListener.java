package org.viodo.toshell.ui.listener;

import org.viodo.toshell.App;
import org.viodo.toshell.ui.form.MainForm;

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
                MainForm.getInstance().getLrSplitPane().setDividerLocation(0.7);
                MainForm.getInstance().getTbSplitPane().setDividerLocation(0.7);
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
