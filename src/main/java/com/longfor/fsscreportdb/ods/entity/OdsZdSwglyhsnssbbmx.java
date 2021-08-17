package com.longfor.fsscreportdb.ods.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.longfor.fsscreportdb.utils.StringUtil;

import java.io.Serializable;

/**
 * <p>
 * 印花税
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-12
 */
@TableName("ODS_ZD_SWGLYHSNSSBBMX")
@KeySequence("ODS_ZDSWGLYHSNSSBBMX")
public class OdsZdSwglyhsnssbbmx implements Serializable {

    private static final long serialVersionUID = 1L;

    
    /**
     * 主键id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 纳税人识别号
     */
    private String nsrsbh;

    /**
     * 纳税人识别号
     */
    private String nsrmc;

    /**
     * 1、单位（默认）；2、个人
     */
    private String nsrlx;

    /**
     * 登记注册类型-代码
     */
    private String djzclx_dm;

    /**
     * 登记注册类型-名称
     */
    private String djzclx_mc;

    /**
     * 所属行业-代码
     */
    private String sshy_dm;

    /**
     * 所属行业-名称	
     */
    private String sshy_mc;

    /**
     * 身份证件类型
     */
    private String sfzjlx;

    /**
     * 身份证件号码
     */
    private String sfzjhm;

    /**
     * 联系方式(联系电话)
     */
    private String lxfs;

    /**
     * 业务场景id
     */
    private String ywcj_id;

    /**
     * 应税凭证
     */
    private String sm_mc;

    /**
     * 行次
     */
    private BigDecimal hc;

    /**
     * 计税金额或件数
     */
    private BigDecimal jsje;

    /**
     * 适用税率（印花税税目税率）
     */
    private BigDecimal sm_sl;

    /**
     * 核定征收（核定依据）
     */
    private BigDecimal hdyj;

    /**
     * 核定征收（核定比例）
     */
    private BigDecimal hdbl;

    /**
     * 本期应纳税额
     */
    private BigDecimal bqynse;

    /**
     * 本期已缴税额
     */
    private BigDecimal bqyjse;

    /**
     * 本期减免税额（减免性质代码）
     */
    private BigDecimal jmxzdm;

    /**
     * 本期减免税额（减免额）
     */
    private BigDecimal jme;

    /**
     * 本期应补（退）税额
     */
    private BigDecimal bqybtse;

    /**
     * 录入时间
     */
    private String lr_sj;

    private String op_time;

    /**
     * 执行批次
     */
    private String execution_id;

    /**
     * 入库时间
     */
    private String load_date;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getNsrlx() {
		return nsrlx;
	}

	public void setNsrlx(String nsrlx) {
		this.nsrlx = nsrlx;
	}

	public String getDjzclx_dm() {
		return djzclx_dm;
	}

	public void setDjzclx_dm(String djzclx_dm) {
		this.djzclx_dm = djzclx_dm;
	}

	public String getDjzclx_mc() {
		return djzclx_mc;
	}

	public void setDjzclx_mc(String djzclx_mc) {
		this.djzclx_mc = djzclx_mc;
	}

	public String getSshy_dm() {
		return sshy_dm;
	}

	public void setSshy_dm(String sshy_dm) {
		this.sshy_dm = sshy_dm;
	}

	public String getSshy_mc() {
		return sshy_mc;
	}

	public void setSshy_mc(String sshy_mc) {
		this.sshy_mc = sshy_mc;
	}

	public String getSfzjlx() {
		return sfzjlx;
	}

	public void setSfzjlx(String sfzjlx) {
		this.sfzjlx = sfzjlx;
	}

	public String getSfzjhm() {
		return sfzjhm;
	}

	public void setSfzjhm(String sfzjhm) {
		this.sfzjhm = sfzjhm;
	}

	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getYwcj_id() {
		return ywcj_id;
	}

	public void setYwcj_id(String ywcj_id) {
		this.ywcj_id = ywcj_id;
	}

	public String getSm_mc() {
		return sm_mc;
	}

	public void setSm_mc(String sm_mc) {
		this.sm_mc = sm_mc;
	}

	public BigDecimal getHc() {
		return hc;
	}

	public void setHc(BigDecimal hc) {
		this.hc = StringUtil.getTwoDecimal(hc);
	}

	public BigDecimal getJsje() {
		return jsje;
	}

	public void setJsje(BigDecimal jsje) {
		this.jsje = StringUtil.getTwoDecimal(jsje);
	}

	public BigDecimal getSm_sl() {
		return sm_sl;
	}

	public void setSm_sl(BigDecimal sm_sl) {
		this.sm_sl = StringUtil.getTwoDecimal(sm_sl);
	}

	public BigDecimal getHdyj() {
		return hdyj;
	}

	public void setHdyj(BigDecimal hdyj) {
		this.hdyj = StringUtil.getTwoDecimal(hdyj);
	}

	public BigDecimal getHdbl() {
		return hdbl;
	}

	public void setHdbl(BigDecimal hdbl) {
		this.hdbl = StringUtil.getTwoDecimal(hdbl);
	}

	public BigDecimal getBqynse() {
		return bqynse;
	}

	public void setBqynse(BigDecimal bqynse) {
		this.bqynse = StringUtil.getTwoDecimal(bqynse);
	}

	public BigDecimal getBqyjse() {
		return bqyjse;
	}

	public void setBqyjse(BigDecimal bqyjse) {
		this.bqyjse = StringUtil.getTwoDecimal(bqyjse);
	}

	public BigDecimal getJmxzdm() {
		return jmxzdm;
	}

	public void setJmxzdm(BigDecimal jmxzdm) {
		this.jmxzdm = StringUtil.getTwoDecimal(jmxzdm);
	}

	public BigDecimal getJme() {
		return jme;
	}

	public void setJme(BigDecimal jme) {
		this.jme = StringUtil.getTwoDecimal(jme);
	}

	public BigDecimal getBqybtse() {
		return bqybtse;
	}

	public void setBqybtse(BigDecimal bqybtse) {
		this.bqybtse = StringUtil.getTwoDecimal(bqybtse);
	}

	public String getLr_sj() {
		return lr_sj;
	}

	public void setLr_sj(String lr_sj) {
		this.lr_sj = lr_sj;
	}

	public String getOp_time() {
		return op_time;
	}

	public void setOp_time(String op_time) {
		this.op_time = op_time;
	}

	public String getExecution_id() {
		return execution_id;
	}

	public void setExecution_id(String execution_id) {
		this.execution_id = execution_id;
	}

	public String getLoad_date() {
		return load_date;
	}

	public void setLoad_date(String load_date) {
		this.load_date = load_date;
	}
    
}
