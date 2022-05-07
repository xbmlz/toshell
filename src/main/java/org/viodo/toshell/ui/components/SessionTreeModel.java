package org.viodo.toshell.ui.components;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.viodo.toshell.bean.SessionInfo;
import org.viodo.toshell.conf.AppSettings;
import org.viodo.toshell.ui.UIConstants;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author chenxc
 */
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
        AppSettings.SESSION_LIST = JSONUtil.toList(objects, SessionInfo.class);
        if (AppSettings.SESSION_LIST.size() > 0) {
            initTree(AppSettings.SESSION_LIST, UIConstants.SESSION_TYPE_GROUP_ROOT_ID, rootNode);
        }
        nodeStructureChanged(rootNode);
    }

    private static void initTree(List<SessionInfo> list, String pid, DefaultMutableTreeNode parent) {
        for (SessionInfo info : list) {
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
