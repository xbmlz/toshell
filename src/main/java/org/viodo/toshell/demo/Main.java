package org.viodo.toshell.demo;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class Main {
    public static void fillTree(Node parent, int level, String label) {
        for (int i = 0; i < 5; i++) {
            Node node = new Node(label + " " + i);
            parent.addNode(node);
            if (level > 0) {
                fillTree(node, level - 1, label);
            }
        }
    }
    public static void main(String[] args) {
        Node root = new Node("Root");
        fillTree(root, 5, "tree label");
        DefaultTreeModel model = new DefaultTreeModel(root);

        JTree tree = new JTree(model);
        ToolTipManager.sharedInstance().registerComponent(tree);
        tree.setCellRenderer(new MyTreeCellRenderer());
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new JScrollPane(tree));
        f.pack();
        f.setVisible(true);
    }
}

class MyTreeCellRenderer extends DefaultTreeCellRenderer {
    int rendering = 0;
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component cell = super.getTreeCellRendererComponent(tree, value, sel,
                expanded, leaf, row, hasFocus);
        if (cell instanceof JComponent) {
            ((JComponent) cell).setToolTipText("Hello " + rendering++);
            if (value instanceof Node && cell instanceof JLabel) {
                ((JLabel) cell).setText(((Node) value).name);
            }
        }
        return cell;
    }
}

class Node implements TreeNode {

    private Node parent;
    private List<Node> children;
    public String name;

    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<Node>();
    }

    public void addNode(Node child) {
        children.add(child);
        child.parent = this;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return children.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return children.size() == 0;
    }

    @Override
    public Enumeration<Node> children() {
        return Collections.enumeration(children);
    }
}
