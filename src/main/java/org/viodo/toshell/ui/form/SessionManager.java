package org.viodo.toshell.ui.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.icons.FlatFileViewDirectoryIcon;
import com.formdev.flatlaf.icons.FlatFileViewFileIcon;
import com.formdev.flatlaf.icons.FlatSearchIcon;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.viodo.toshell.bean.SessionInfo;
import org.viodo.toshell.conf.AppSettings;
import org.viodo.toshell.ui.UIConstants;
import org.viodo.toshell.ui.components.SessionTreeModel;
import org.viodo.toshell.ui.dialog.InputDialog;
import org.viodo.toshell.ui.handler.DummyTransferHandler;
import org.viodo.toshell.ui.render.ConnectIconRenderer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author chenxc
 */
@Getter
public class SessionManager {

    private final Logger logger = Logger.getLogger(SessionManager.class.getName());

    private JPanel mainPanel;
    private JPanel sessionPanel;
    private JToolBar sessionToolbar;
    private JTextField searchTextField;
    private JButton addBtn;
    private JTree sessionTree;
    private JPopupMenu addPopupMenu;
    private JMenuItem addSessionMenuItem;
    private JMenuItem addGroupMenuItem;

    private static SessionManager instance;

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public SessionManager() {
        init();
    }


    private void init() {

        searchTextField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        searchTextField.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,
                new FlatSearchIcon());

        // add popup menu
        addPopupMenu = new JPopupMenu();
        addSessionMenuItem = new JMenuItem("New Connection");
        addSessionMenuItem.setIcon(new FlatFileViewFileIcon());
        addSessionMenuItem.addActionListener(e -> newSession());
        addGroupMenuItem = new JMenuItem("New Group");
        addGroupMenuItem.setIcon(new FlatFileViewDirectoryIcon());
        addGroupMenuItem.addActionListener(e -> newSessionGroup());
        addPopupMenu.add(addSessionMenuItem);
        addPopupMenu.add(addGroupMenuItem);

        // session tree
        sessionTree.setModel(new SessionTreeModel());
        sessionTree.setDragEnabled(true);
        sessionTree.setDropMode(DropMode.ON_OR_INSERT);
        sessionTree.putClientProperty("FlatLaf.oldTransferHandler", sessionTree.getTransferHandler());
        sessionTree.setTransferHandler(new DummyTransferHandler());
        sessionTree.setCellRenderer(new ConnectIconRenderer());
        sessionTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        sessionTree.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                TreePath path = sessionTree.getClosestPathForLocation(e.getX(), e.getY());
                if (path != null) {
                    sessionTree.setSelectionPath(path);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    addPopupMenu.show(sessionTree, e.getX(), e.getY());
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // add button
        addBtn.setIcon(new FlatSVGIcon("icons/add.svg"));
        addBtn.putClientProperty("JButton.buttonType", "roundRect");
        addBtn.addActionListener(e -> addPopupMenu.show(addBtn, 0, addBtn.getHeight()));

        sessionPanel.setComponentPopupMenu(addPopupMenu);
        sessionPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Component componentAt = sessionTree.getComponentAt(e.getPoint());
                    if (componentAt == null) {
                        sessionTree.clearSelection();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    private void newSessionGroup() {
        Object input = InputDialog.show(MainForm.getInstance().getMainPanel(), UIConstants.NEW_SESSION_GROUP_TITLE,
                UIConstants.NEW_SESSION_GROUP_MESSAGE);
        if (input != null) {
            // 判断是否有选中的节点
            TreePath path = sessionTree.getSelectionPath();
            DefaultTreeModel treeModel = (DefaultTreeModel) sessionTree.getModel();
            DefaultMutableTreeNode treeRootNode;
            DefaultMutableTreeNode child;
            SessionInfo sessionGroup;
            if (path == null) {
                // 在根节点添加新节点
                treeRootNode = (DefaultMutableTreeNode) sessionTree.getModel().getRoot();
                sessionGroup = SessionInfo.createGroup(String.valueOf(input), UIConstants.SESSION_TYPE_GROUP_ROOT_ID);
            } else {
                // 在其他节点添加新节点
                treeRootNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                SessionInfo sessionInfo = (SessionInfo) treeRootNode.getUserObject();
                if (!Objects.equals(sessionInfo.getType(), UIConstants.SESSION_TYPE_GROUP)) {
                    treeRootNode = (DefaultMutableTreeNode) treeRootNode.getParent();
                    SessionInfo parentSession = (SessionInfo) treeRootNode.getUserObject();
                    sessionGroup = SessionInfo.createGroup(String.valueOf(input), parentSession.getId());
                } else {
                    sessionGroup = SessionInfo.createGroup(String.valueOf(input), sessionInfo.getId());
                }
            }
            AppSettings.SESSION_LIST.add(sessionGroup);
            child = new DefaultMutableTreeNode(sessionGroup);
            treeModel.insertNodeInto(child, treeRootNode, treeRootNode.getChildCount());
            sessionTree.expandPath(new TreePath(treeRootNode.getPath()));
            sessionTree.updateUI();
            logger.info(AppSettings.SESSION_LIST.toString());
        }
    }

    private void newSession() {

    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setBackground(new Color(-855310));
        mainPanel.setFocusable(true);
        mainPanel.setInheritsPopupMenu(false);
        sessionToolbar = new JToolBar();
        mainPanel.add(sessionToolbar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        searchTextField = new JTextField();
        sessionToolbar.add(searchTextField);
        addBtn = new JButton();
        addBtn.setText("");
        sessionToolbar.add(addBtn);
        sessionPanel = new JPanel();
        sessionPanel.setLayout(new BorderLayout(0, 0));
        sessionPanel.setBackground(new Color(-1));
        sessionPanel.setForeground(new Color(-1));
        mainPanel.add(sessionPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        sessionTree = new JTree();
        sessionTree.setDragEnabled(true);
        sessionTree.setEditable(false);
        sessionTree.setFocusCycleRoot(true);
        sessionTree.setFocusTraversalPolicyProvider(true);
        sessionTree.setFocusable(true);
        sessionTree.setRequestFocusEnabled(true);
        sessionTree.setRootVisible(false);
        sessionPanel.add(sessionTree, BorderLayout.NORTH);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
