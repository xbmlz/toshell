package org.viodo.toshell.model;

import cn.hutool.core.util.IdUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * connect model
 *
 * @author chenxc
 */
@Setter
@Getter
public class SessionModel {

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

    @Override
    public String toString() {
        return name;
    }

    public static SessionModel createGroup(String groupName) {
        SessionModel info = new SessionModel();
        info.setId(IdUtil.simpleUUID());
        info.setParentId("0");
        info.setType("group");
        info.setName(groupName);
        return info;
    }
}
