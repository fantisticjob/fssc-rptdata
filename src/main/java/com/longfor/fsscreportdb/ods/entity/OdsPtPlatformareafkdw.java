package com.longfor.fsscreportdb.ods.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 平台地区付款单位
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-13
 */
@TableName("ODS_PT_PLATFORMAREAFKDW")
@KeySequence("ODS_PTPLATFORMAREAFKDW")
public class OdsPtPlatformareafkdw implements Serializable {

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
     * 付款单位
     */
    private String f_fkdw;

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

	public String getF_fkdw() {
		return f_fkdw;
	}

	public void setF_fkdw(String f_fkdw) {
		this.f_fkdw = f_fkdw;
	}

}
