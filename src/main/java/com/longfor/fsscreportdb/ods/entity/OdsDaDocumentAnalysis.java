package com.longfor.fsscreportdb.ods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * 单据退回分析明细
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-10
 */
@TableName("ODS_DA_DOCUMENT_ANALYSIS")
@KeySequence("ODS_DJTH")
public class OdsDaDocumentAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 年
     */
    @TableField("f_op_year")
    private String f_op_year;

    /**
     * 月
     */
    @TableField("f_op_month")
    private String f_op_month;

    /**
     * 地区
     */
    @TableField("f_dqmc")
    private String f_dqmc;

    /**
     * 单据号
     */
    @TableField("f_bill_id")
    private String f_bill_id;

    /**
     * 发起人账号
     */
    @TableField("usr_description")
    private String usr_description;

    /**
     * 发起人姓名
     */
    @TableField("usr_caption")
    private String usr_caption;

    /**
     * 部门
     */
    @TableField("f_title")
    private String f_title;

    /**
     * 退回时间
     */
    @TableField("f_op_time")
    private String f_op_time;

    /**
     * 退回原因
     */
    @TableField("f_zbmc")
    private String f_zbmc;

    /**
     * 执行时间
     */
    @TableField("op_time")
    private Date op_time;

    /**
     * 执行批次
     */
    @TableField("execution_id")
    private String execution_id;

    /**
     * 补充说明
     */
    @TableField("f_bcsm_note")
    private String f_bcsm_note;

    /**
     * etl时间
     */
    @TableField("etl_time")
    private Date etl_time;

    /**
     * 全部单据数量
     */
    @TableField("f_allnum")
    private BigDecimal f_allnum;
    
    @TableField("f_op_info")
    private String f_op_info;

    private String f_fvep_uid;
    
    private String f_fvep_grp;
    
    private String f_erp_hszt;
    
    private String f_djzt_gx;
    
    private String f_fkzt;
    
    /**
     * （全部单据数量(地区+部门维度)
     */
    private String f_bu_allnum;
    
    
	public String getF_bu_allnum() {
		return f_bu_allnum;
	}

	public void setF_bu_allnum(String f_bu_allnum) {
		this.f_bu_allnum = f_bu_allnum;
	}

	public String getF_fvep_uid() {
		return f_fvep_uid;
	}

	public void setF_fvep_uid(String f_fvep_uid) {
		this.f_fvep_uid = f_fvep_uid;
	}

	public String getF_fvep_grp() {
		return f_fvep_grp;
	}

	public void setF_fvep_grp(String f_fvep_grp) {
		this.f_fvep_grp = f_fvep_grp;
	}

	public String getF_erp_hszt() {
		return f_erp_hszt;
	}

	public void setF_erp_hszt(String f_erp_hszt) {
		this.f_erp_hszt = f_erp_hszt;
	}

	public String getF_djzt_gx() {
		return f_djzt_gx;
	}

	public void setF_djzt_gx(String f_djzt_gx) {
		this.f_djzt_gx = f_djzt_gx;
	}

	public String getF_fkzt() {
		return f_fkzt;
	}

	public void setF_fkzt(String f_fkzt) {
		this.f_fkzt = f_fkzt;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getF_op_year() {
		return f_op_year;
	}

	public void setF_op_year(String f_op_year) {
		this.f_op_year = f_op_year;
	}

	public String getF_op_month() {
		return f_op_month;
	}

	public void setF_op_month(String f_op_month) {
		this.f_op_month = f_op_month;
	}

	public String getF_dqmc() {
		return f_dqmc;
	}

	public void setF_dqmc(String f_dqmc) {
		this.f_dqmc = f_dqmc;
	}

	public String getF_bill_id() {
		return f_bill_id;
	}

	public void setF_bill_id(String f_bill_id) {
		this.f_bill_id = f_bill_id;
	}

	public String getUsr_description() {
		return usr_description;
	}

	public void setUsr_description(String usr_description) {
		this.usr_description = usr_description;
	}

	public String getUsr_caption() {
		return usr_caption;
	}

	public void setUsr_caption(String usr_caption) {
		this.usr_caption = usr_caption;
	}

	public String getF_title() {
		return f_title;
	}

	public void setF_title(String f_title) {
		this.f_title = f_title;
	}

	public String getF_op_time() {
		return f_op_time;
	}

	public void setF_op_time(String f_op_time) {
		this.f_op_time = f_op_time;
	}

	public String getF_zbmc() {
		return f_zbmc;
	}

	public void setF_zbmc(String f_zbmc) {
		this.f_zbmc = f_zbmc;
	}

	public Date getOp_time() {
		return op_time;
	}

	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}

	public String getExecution_id() {
		return execution_id;
	}

	public void setExecution_id(String execution_id) {
		this.execution_id = execution_id;
	}

	public String getF_bcsm_note() {
		return f_bcsm_note;
	}

	public void setF_bcsm_note(String f_bcsm_note) {
		this.f_bcsm_note = f_bcsm_note;
	}

	public Date getEtl_time() {
		return etl_time;
	}

	public void setEtl_time(Date etl_time) {
		this.etl_time = etl_time;
	}

	public BigDecimal getF_allnum() {
		return f_allnum;
	}

	public void setF_allnum(BigDecimal f_allnum) {
		this.f_allnum = StringUtil.getTwoDecimal(f_allnum);
	}

	public String getF_op_info() {
		return f_op_info;
	}

	public void setF_op_info(String f_op_info) {
		this.f_op_info = f_op_info;
	}
}
