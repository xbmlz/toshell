package org.viodo.toshell.ui.render;

import com.formdev.flatlaf.icons.FlatFileViewDirectoryIcon;
import org.viodo.toshell.bean.SessionInfo;
import org.viodo.toshell.ui.UIConstants;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * 连接图标渲染器
 *
 * @author chenxc
 */
public class ConnectIconRenderer extends DefaultTreeCellRenderer {


    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
                                                  int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        if (node.getUserObject() instanceof SessionInfo sessionInfo) {
            if (sessionInfo.getType().equals(UIConstants.SESSION_TYPE_GROUP)){
                this.setIcon(new FlatFileViewDirectoryIcon());
            }
        }
        return this;
    }
}
