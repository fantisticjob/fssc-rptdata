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
 * 信用超期明细
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-08
 */
@TableName("ODS_CR_CREDIT_PAY")
@KeySequence(value = "ODSCRCREDITPAY_S")
public class OdsCrCreditPay implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 编码
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 平台
     */
    @TableField("f_area")
    private String f_area;

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
    @TableField("usr_caption")
    private String usr_caption;

    /**
     * 发起人姓名
     */
    @TableField("usr_description")
    private String usr_description;

    /**
     * 部门
     */
    @TableField("f_title")
    private String f_title;

    /**
     * 付款时间
     */
    @TableField("f_pay_time")
    private Date f_pay_time;

    /**
     * 超期天数
     */
    @TableField("f_otd_num")
    private BigDecimal f_otd_num;

    /**
     * 金额
     */
    @TableField("f_pay_amt")
    private BigDecimal f_pay_amt;

    /**
     * 年份
     */
    @TableField("f_year")
    private String f_year;

    /**
     * 月份
     */
    @TableField("f_month")
    private String f_month;

    /**
     * 单据类型
     */
    @TableField("f_djlx")
    private String f_djlx;

    /**
     * 单据来源性质
     */
    @TableField("f_income_type")
    private String f_income_type;

    /**
     * 信用等级
     */
    @TableField("f_crdt_grd")
    private String f_crdt_grd;

    /**
     * 共享单据状态
     */
    @TableField("f_djzt_gx")
    private String f_djzt_gx;

    /**
     * 付款状态
     */
    @TableField("f_fkzt")
    private String f_fkzt;

    /**
     * 是否超期
     */
    @TableField("f_sfcq")
    private String f_sfcq;

    /**
     * 是否先支付后审核
     */
    @TableField("f_sfxzf")
    private String f_sfxzf;

    /**
     * 初审状态
     */
    @TableField("f_sta_fvep")
    private String f_sta_fvep;

    /**
     * 实体状态
     */
    @TableField("f_stzt")
    private String f_stzt;

    /**
     * 进入共享池时间
     */
    @TableField("jrgx_time")
    private String jrgx_time;

    /**
     * 单据提交时间
     */
    @TableField("f_djtjsj")
    private String f_djtjsj;

    /**
     * etl时间
     */
    @TableField("etl_time")
    private Date etl_time;

    /**
     * 10，15享受信用付款的员工
     */
    @TableField("usr_t02")
    private String usr_t02;
    
    
    /**
     * 进入共享池时间
     */
    @TableField("F_ENTSC_TIME")
    private String f_entsc_time;
    
    /**
     *付款单位 
     */
    private String f_fkdw;
    
    /**
     * 核实主体
     */
    private String f_erp_hszt;
    
    /**
     * 初审人
     */
    private String f_fvep_uid;
    
    /**
     * 初审组
     */
    private String f_fvep_grp;
    
    /**
     * 支付状态
     */
    private String f_sta_pay;
    
    public String getF_fkdw() {
		return f_fkdw;
	}

	public void setF_fkdw(String f_fkdw) {
		this.f_fkdw = f_fkdw;
	}

	public String getF_erp_hszt() {
		return f_erp_hszt;
	}

	public void setF_erp_hszt(String f_erp_hszt) {
		this.f_erp_hszt = f_erp_hszt;
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

	public String getF_sta_pay() {
		return f_sta_pay;
	}

	public void setF_sta_pay(String f_sta_pay) {
		this.f_sta_pay = f_sta_pay;
	}

	public String getF_entsc_time() {
		return f_entsc_time;
	}

	public void setF_entsc_time(String f_entsc_time) {
		this.f_entsc_time = f_entsc_time;
	}

	/**
     * 信用分 a高信用，b低信用 为空的可以认为a
     */
    @TableField("f_xydj")
    private String f_xydj;
    /**
     * 信用分 a高信用，b低信用 为空的可以认为a
     */
    @TableField("sys_source")
    private String sys_source;

	public String getSys_source() {
		return sys_source;
	}

	public void setSys_source(String sys_source) {
		this.sys_source = sys_source;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getF_area() {
		return f_area;
	}

	public void setF_area(String f_area) {
		this.f_area = f_area;
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

	public Date getF_pay_time() {
		return f_pay_time;
	}

	public void setF_pay_time(Date f_pay_time) {
		this.f_pay_time = f_pay_time;
	}

	public BigDecimal getF_otd_num() {
		return f_otd_num;
	}

	public void setF_otd_num(BigDecimal f_otd_num) {
		this.f_otd_num = f_otd_num;
	}

	public BigDecimal getF_pay_amt() {
		return f_pay_amt;
	}

	public void setF_pay_amt(BigDecimal f_pay_amt) {
		this.f_pay_amt = StringUtil.getTwoDecimal(f_pay_amt);
	}

	public String getF_year() {
		return f_year;
	}

	public void setF_year(String f_year) {
		this.f_year = f_year;
	}

	public String getF_month() {
		return f_month;
	}

	public void setF_month(String f_month) {
		this.f_month = f_month;
	}

	public String getF_djlx() {
		return f_djlx;
	}

	public void setF_djlx(String f_djlx) {
		this.f_djlx = f_djlx;
	}

	public String getF_income_type() {
		return f_income_type;
	}

	public void setF_income_type(String f_income_type) {
		this.f_income_type = f_income_type;
	}

	public String getF_crdt_grd() {
		return f_crdt_grd;
	}

	public void setF_crdt_grd(String f_crdt_grd) {
		this.f_crdt_grd = f_crdt_grd;
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

	public String getF_sfcq() {
		return f_sfcq;
	}

	public void setF_sfcq(String f_sfcq) {
		this.f_sfcq = f_sfcq;
	}

	public String getF_sfxzf() {
		return f_sfxzf;
	}

	public void setF_sfxzf(String f_sfxzf) {
		this.f_sfxzf = f_sfxzf;
	}

	public String getF_sta_fvep() {
		return f_sta_fvep;
	}

	public void setF_sta_fvep(String f_sta_fvep) {
		this.f_sta_fvep = f_sta_fvep;
	}

	public String getF_stzt() {
		return f_stzt;
	}

	public void setF_stzt(String f_stzt) {
		this.f_stzt = f_stzt;
	}

	public String getJrgx_time() {
		return jrgx_time;
	}

	public void setJrgx_time(String jrgx_time) {
		this.jrgx_time = jrgx_time;
	}

	public String getF_djtjsj() {
		return f_djtjsj;
	}

	public void setF_djtjsj(String f_djtjsj) {
		this.f_djtjsj = f_djtjsj;
	}

	public Date getEtl_time() {
		return etl_time;
	}

	public void setEtl_time(Date etl_time) {
		this.etl_time = etl_time;
	}

	public String getUsr_t02() {
		return usr_t02;
	}

	public void setUsr_t02(String usr_t02) {
		this.usr_t02 = usr_t02;
	}

	public String getF_xydj() {
		return f_xydj;
	}

	public void setF_xydj(String f_xydj) {
		this.f_xydj = f_xydj;
	}
}
