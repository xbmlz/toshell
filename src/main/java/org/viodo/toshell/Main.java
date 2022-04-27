package org.viodo.toshell;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatInspector;
import com.formdev.flatlaf.extras.FlatUIDefaultsInspector;
import com.formdev.flatlaf.util.SystemInfo;

import javax.swing.*;

/**
 * 程序主启动类
 *
 * @author chenxc
 */
public class Main {

    static final String PREFS_ROOT_PATH = "/ToShell";

    static boolean screenshotsMode = Boolean.parseBoolean(System.getProperty("toshell.screenshotsMode"));

    public static void main(String[] args) {
        // https://github.com/JFormDesigner/FlatLaf/blob/main/flatlaf-demo/src/main/java/com/formdev/flatlaf/demo/FlatLafDemo.java
        // macOS
        if (SystemInfo.isMacOS) {
            // enable screen menu bar
            // (moves menu bar from JFrame window to top of screen)
            System.setProperty("apple.laf.useScreenMenuBar", "true");

            // application name used in screen menu bar
            // (in first menu after the "apple" menu)
            System.setProperty("apple.awt.application.name", "FlatLaf Demo");

            // appearance of window title bars
            // possible values:
            //   - "system": use current macOS appearance (light or dark)
            //   - "NSAppearanceNameAqua": use light appearance
            //   - "NSAppearanceNameDarkAqua": use dark appearance
            System.setProperty("apple.awt.application.appearance", "system");
        }

        // Linux
        if (SystemInfo.isLinux) {
            // enable custom window decorations
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        }

        if (Main.screenshotsMode && !SystemInfo.isJava_9_orLater && System.getProperty("toshell.uiScale") == null) {
            System.setProperty("toshell.uiScale", "2x");
        }

        SwingUtilities.invokeLater(() -> {
            // TODO init

            // application specific UI defaults
            FlatLaf.registerCustomDefaultsSource( "org.viodo.toshell" );

            // TODO set look and feel

            // install inspectors
            FlatInspector.install( "ctrl shift alt X" );
            FlatUIDefaultsInspector.install( "ctrl shift alt Y" );

            // TODO create and show the main frame

            // TODO show frame
        });
    }
}
