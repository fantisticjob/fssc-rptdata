package com.longfor.fsscreportdb.ods.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 共享所有人员接口
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-18
 */
@TableName("ODS_PT_GXUSERS")
@KeySequence("ODS_PTGXUSERS")
public class OdsPtGxusers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;
    /**
     * 平台
     */
    private String platform;

    /**
     * 地区
     */
    private String area;

    /**
     * 用户
     */
    private String usr_caption;

    /**
     * 账号
     */
    private String usr_description;

    /**
     * 部门
     */
    private String f_title;

    /**
     * 是否离职
     */
    private String usr_disable;

    /**
     * 是否信用员工 10   和15 的 就是享受信用付款的员工
     */
    private String xyyg;

    /**
     * 信用等级 a是高信用  b是低信用
     */
    private String f_xydj;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getUsr_caption() {
		return usr_caption;
	}

	public void setUsr_caption(String usr_caption) {
		this.usr_caption = usr_caption;
	}

	public String getUsr_description() {
		return usr_description;
	}

	public void setUsr_description(String usr_description) {
		this.usr_description = usr_description;
	}

	public String getF_title() {
		return f_title;
	}

	public void setF_title(String f_title) {
		this.f_title = f_title;
	}

	public String getUsr_disable() {
		return usr_disable;
	}

	public void setUsr_disable(String usr_disable) {
		this.usr_disable = usr_disable;
	}

	public String getXyyg() {
		return xyyg;
	}

	public void setXyyg(String xyyg) {
		this.xyyg = xyyg;
	}

	public String getF_xydj() {
		return f_xydj;
	}

	public void setF_xydj(String f_xydj) {
		this.f_xydj = f_xydj;
	}

}
