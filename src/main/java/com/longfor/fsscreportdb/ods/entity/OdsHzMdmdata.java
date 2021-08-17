package com.longfor.fsscreportdb.ods.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * 项目经营情况主数据数据接口
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-07
 */
@TableName("ODS_HZ_MDMDATA")
@KeySequence("ODS_HZMDMDATA")
public class OdsHzMdmdata implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 公司名
     */
    @TableField("company_nm")
    private String company_nm;

    /**
     * 公司code
     */
    @TableField("company_code")
    private String company_code;

    /**
     * 项目名
     */
    @TableField("project_nm")
    private String project_nm;

    /**
     * 项目code
     */
    @TableField("project_code")
    private String project_code;

    /**
     * 分期名
     */
    @TableField("phase_nm")
    private String phase_nm;

    /**
     * 分期code
     */
    @TableField("phase_code")
    private String phase_code;

    /**
     * 组团名
     */
    @TableField("phase_group_nm")
    private String phase_group_nm;

    /**
     * 组团code
     */
    @TableField("phase_group_code")
    private String phase_group_code;

    /**
     * 业态code
     */
    @TableField("mdm_format_code")
    private String mdm_format_code;

    /**
     * 业态名
     */
    @TableField("mdm_format_nm")
    private String mdm_format_nm;

    /**
     * 指标-建筑面积
     */
    @TableField("mdm_area")
    private BigDecimal mdm_area;

    /**
     * 指标-建筑套数
     */
    @TableField("mdm_sets")
    private BigDecimal mdm_sets;

    /**
     * 龙头-地块编号
     */
    @TableField("sc_id")
    private String sc_id;

    /**
     * 龙头-容积
     */
    @TableField("sc_plot_r")
    private String sc_plot_r;

    /**
     * 龙头-总占地面积率
     */
    @TableField("lp_area_t")
    private String lp_area_t;

	public String getCompany_nm() {
		return company_nm;
	}

	public void setCompany_nm(String company_nm) {
		this.company_nm = company_nm;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getProject_nm() {
		return project_nm;
	}

	public void setProject_nm(String project_nm) {
		this.project_nm = project_nm;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getPhase_nm() {
		return phase_nm;
	}

	public void setPhase_nm(String phase_nm) {
		this.phase_nm = phase_nm;
	}

	public String getPhase_code() {
		return phase_code;
	}

	public void setPhase_code(String phase_code) {
		this.phase_code = phase_code;
	}

	public String getPhase_group_nm() {
		return phase_group_nm;
	}

	public void setPhase_group_nm(String phase_group_nm) {
		this.phase_group_nm = phase_group_nm;
	}

	public String getPhase_group_code() {
		return phase_group_code;
	}

	public void setPhase_group_code(String phase_group_code) {
		this.phase_group_code = phase_group_code;
	}

	public String getMdm_format_code() {
		return mdm_format_code;
	}

	public void setMdm_format_code(String mdm_format_code) {
		this.mdm_format_code = mdm_format_code;
	}

	public String getMdm_format_nm() {
		return mdm_format_nm;
	}

	public void setMdm_format_nm(String mdm_format_nm) {
		this.mdm_format_nm = mdm_format_nm;
	}

	public BigDecimal getMdm_area() {
		return mdm_area;
	}

	public void setMdm_area(BigDecimal mdm_area) {
		this.mdm_area = StringUtil.getTwoDecimal(mdm_area);
	}

	public BigDecimal getMdm_sets() {
		return mdm_sets;
	}

	public void setMdm_sets(BigDecimal mdm_sets) {
		this.mdm_sets = StringUtil.getTwoDecimal(mdm_sets);
	}

	public String getSc_id() {
		return sc_id;
	}

	public void setSc_id(String sc_id) {
		this.sc_id = sc_id;
	}

	public String getSc_plot_r() {
		return sc_plot_r;
	}

	public void setSc_plot_r(String sc_plot_r) {
		this.sc_plot_r = sc_plot_r;
	}

	public String getLp_area_t() {
		return lp_area_t;
	}

	public void setLp_area_t(String lp_area_t) {
		this.lp_area_t = lp_area_t;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

}
