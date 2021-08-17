package com.longfor.fsscreportdb.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 角色用户权限表
 * </p>
 *
 * @author chenziyao
 * @since 2021-01-13
 */
@TableName("ROLE_USER_RIGHTS")
public class RoleUserRights implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private BigDecimal id;

    /**
     * 账户
     */
    @TableField("ACCOUNT")
    private String account;

    /**
     * 姓名
     */
    @TableField("NAME")
    private String name;
    
    /**
     * 地区
     */
    @TableField("area")
    private String area;
    
    /**
     * 职位
     */
    @TableField("POSITION")
    private String position;

    /**
     * 权限
     */
    @TableField("RIGHTS")
    private String rights;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
