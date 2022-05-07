package org.viodo.toshell.bean;

import cn.hutool.core.util.IdUtil;
import lombok.Getter;
import lombok.Setter;
import org.viodo.toshell.ui.UIConstants;

import java.util.Date;


/**
 * connect model
 *
 * @author chenxc
 */
@Setter
@Getter
public class SessionInfo {

    /**
     * uuid
     */
    private String id;

    /**
     * 链接类型
     * ssh or mstsc
     */
    private String type;

    /**
     * parent id
     */
    private String parentId;

    /**
     * 链接名称
     */
    private String name;

    /**
     * 链接描述
     */
    private String description;

    /**
     * 链接地址
     */
    private String host;

    /**
     * 链接端口 default 22
     */
    private int port;

    /**
     * 认证类型
     */
    private String authType;

    /**
     * 链接用户名
     */
    private String username;

    /**
     * 链接密码
     */
    private String password;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    public String toString() {
        return name;
    }

    public static SessionInfo createGroup(String groupName, String parentId) {
        SessionInfo info = new SessionInfo();
        info.setId(IdUtil.simpleUUID());
        info.setParentId(parentId);
        info.setType(UIConstants.SESSION_TYPE_GROUP);
        info.setName(groupName);
        return info;
    }
}
