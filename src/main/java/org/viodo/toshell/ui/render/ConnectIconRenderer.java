package org.viodo.toshell.ui.render;

import com.formdev.flatlaf.icons.FlatFileViewDirectoryIcon;
import org.viodo.toshell.model.SessionModel;

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
        System.out.println(node.getUserObject());
        if (node.getUserObject() instanceof SessionModel sessionModel) {
            if (sessionModel.getType().equals("group")){
                this.setIcon(new FlatFileViewDirectoryIcon());
            }
        }
        return this;
    }
}
