package org.viodo.toshell.ui.components;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.viodo.toshell.model.SessionModel;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SessionTreeModel extends DefaultTreeModel {

    DefaultMutableTreeNode rootNode;

    public SessionTreeModel() {
        super(new DefaultMutableTreeNode("root"));
        rootNode = (DefaultMutableTreeNode) getRoot();
        init();
    }

    public void init() {
        rootNode.removeAllChildren();
        JSONArray objects = JSONUtil.readJSONArray(new ClassPathResource("connect.json").getFile(), StandardCharsets.UTF_8);
        List<SessionModel> sessionModelList = JSONUtil.toList(objects, SessionModel.class);
        initTree(sessionModelList, "0", rootNode);
        nodeStructureChanged(rootNode);
    }

    private static void initTree(List<SessionModel> list, String pid, DefaultMutableTreeNode parent) {
        for (SessionModel info : list) {
            if (info.getParentId().equals(pid)) {
                DefaultMutableTreeNode other = new DefaultMutableTreeNode(info);
                initTree(list, info.getId(), other);
                parent.add(other);
            }
        }
    }

    public TreeNode getRootNode() {
        return rootNode;
    }
}
