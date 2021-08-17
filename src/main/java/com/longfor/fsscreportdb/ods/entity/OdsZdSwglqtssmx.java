package com.longfor.fsscreportdb.ods.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.longfor.fsscreportdb.utils.StringUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 税务管理其他税收
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-11
 */
@TableName("ODS_ZD_SWGLQTSSMX")
@KeySequence("ODS_ZDSWGLQTSSMX")
public class OdsZdSwglqtssmx implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;
    

    /**
     * 纳税人名称
     */
    @TableField("nsrmc")
    private String nsrmc;

    /**
     * 行号
     */
    @TableField("hc")
    private String hc;

    /**
     * 业务场景id
     */
    @TableField("ywcj_id")
    private String ywcj_id;

    /**
     * 收入-应税收入
     */
    @TableField("sr_yssr")
    private BigDecimal sr_yssr;

    /**
     * 税款缴纳-本期应缴税额
     */
    @TableField("skjn_bqyjsejs")
    private BigDecimal skjn_bqyjsejs;

    /**
     * 录入时间
     */
    @TableField("lrsj")
    private String lrsj;

    /**
     * 本期应补（退）税（费）额 
     */
    @TableField("bqybtse")
    private BigDecimal bqybtse;

    /**
     * 税种代码 房产税 06   土地使用税 15
     */
    @TableField("szdm")
    private String szdm;

    /**
     * 所属期起
     */
    @TableField("ssq_q")
    private String ssq_q;

    /**
     * 所属期止
     */
    @TableField("ssq_z")
    private String ssq_z;

    /**
     * 土地等级
     */
    @TableField("tddj")
    private String tddj;

    /**
     * 土地总面积
     */
    @TableField("tdzmj")
    private BigDecimal tdzmj;

    /**
     * 本期应补(退)税额
     */
    @TableField("ybse_bq")
    private BigDecimal ybse_bq;

    @TableField("op_time")
    private String op_time;

    /**
     * 执行批次
     */
    @TableField("execution_id")
    private String execution_id;

    /**
     * 入库时间
     */
    @TableField("load_date")
    private String load_date;
	/**
	 * 税额标准
	 */
    private String sebz;
    
    /**
     * 本期应纳税额
     */
    private String bqynse;
    
    /**
     * 本期减免税额
     */
    private String jmse_bq;
    
    /**
     * 本期已缴税额
     */
    private String yjse_bq;
    
    /**
     * 本期增值税小规模纳税人减征额
     */
    private String bqzzsxgmnsr_jze;
    
    /**
     * 尾差调整
     */
    private String wctz;
    
    /**
     * 调整后本期应补(退)税额
     */
    private String tzh_ybse_bq;
    
    /**
     * 备注
     */
    private String bz;
    
    /**
     * 删除标识0未删除1已删除
     */
    private String scbz;
    
    /**
     * 房产编号
     */
    private String fcbh;
    
    /**
     * 房产原值
     */
    private String fcyz;
    
    /**
     * 从租房产原值
     */
    private String czfcyz;
    	
    /**
     * 计税比例
     */
    private String jsbl;
    
    /***
     * 税率
     */
    private String sl;
    
    /**
     * 本期申报租金收入
     */
    private String bqsbzjsr;
    	
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getHc() {
		return hc;
	}

	public void setHc(String hc) {
		this.hc = hc;
	}

	public String getYwcj_id() {
		return ywcj_id;
	}

	public void setYwcj_id(String ywcj_id) {
		this.ywcj_id = ywcj_id;
	}

	public BigDecimal getSr_yssr() {
		return sr_yssr;
	}

	public void setSr_yssr(BigDecimal sr_yssr) {
		this.sr_yssr = StringUtil.getTwoDecimal(sr_yssr);
	}

	public BigDecimal getSkjn_bqyjsejs() {
		return skjn_bqyjsejs;
	}

	public void setSkjn_bqyjsejs(BigDecimal skjn_bqyjsejs) {
		this.skjn_bqyjsejs = StringUtil.getTwoDecimal(skjn_bqyjsejs);
	}

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public BigDecimal getBqybtse() {
		return bqybtse;
	}

	public void setBqybtse(BigDecimal bqybtse) {
		this.bqybtse = StringUtil.getTwoDecimal(bqybtse);
	}

	public String getSzdm() {
		return szdm;
	}

	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}

	public String getSsq_q() {
		return ssq_q;
	}

	public void setSsq_q(String ssq_q) {
		this.ssq_q = ssq_q;
	}

	public String getSsq_z() {
		return ssq_z;
	}

	public void setSsq_z(String ssq_z) {
		this.ssq_z = ssq_z;
	}

	public String getTddj() {
		return tddj;
	}

	public void setTddj(String tddj) {
		this.tddj = tddj;
	}

	public BigDecimal getTdzmj() {
		return tdzmj;
	}

	public void setTdzmj(BigDecimal tdzmj) {
		this.tdzmj = StringUtil.getTwoDecimal(tdzmj);
	}

	public BigDecimal getYbse_bq() {
		return ybse_bq;
	}

	public void setYbse_bq(BigDecimal ybse_bq) {
		this.ybse_bq = StringUtil.getTwoDecimal(ybse_bq);
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

	public String getSebz() {
		return sebz;
	}

	public void setSebz(String sebz) {
		this.sebz = sebz;
	}

	public String getBqynse() {
		return bqynse;
	}

	public void setBqynse(String bqynse) {
		this.bqynse = bqynse;
	}

	public String getJmse_bq() {
		return jmse_bq;
	}

	public void setJmse_bq(String jmse_bq) {
		this.jmse_bq = jmse_bq;
	}

	public String getYjse_bq() {
		return yjse_bq;
	}

	public void setYjse_bq(String yjse_bq) {
		this.yjse_bq = yjse_bq;
	}

	public String getBqzzsxgmnsr_jze() {
		return bqzzsxgmnsr_jze;
	}

	public void setBqzzsxgmnsr_jze(String bqzzsxgmnsr_jze) {
		this.bqzzsxgmnsr_jze = bqzzsxgmnsr_jze;
	}

	public String getWctz() {
		return wctz;
	}

	public void setWctz(String wctz) {
		this.wctz = wctz;
	}

	public String getTzh_ybse_bq() {
		return tzh_ybse_bq;
	}

	public void setTzh_ybse_bq(String tzh_ybse_bq) {
		this.tzh_ybse_bq = tzh_ybse_bq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getScbz() {
		return scbz;
	}

	public void setScbz(String scbz) {
		this.scbz = scbz;
	}

	public String getFcbh() {
		return fcbh;
	}

	public void setFcbh(String fcbh) {
		this.fcbh = fcbh;
	}

	public String getFcyz() {
		return fcyz;
	}

	public void setFcyz(String fcyz) {
		this.fcyz = fcyz;
	}

	public String getCzfcyz() {
		return czfcyz;
	}

	public void setCzfcyz(String czfcyz) {
		this.czfcyz = czfcyz;
	}

	public String getJsbl() {
		return jsbl;
	}

	public void setJsbl(String jsbl) {
		this.jsbl = jsbl;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getBqsbzjsr() {
		return bqsbzjsr;
	}

	public void setBqsbzjsr(String bqsbzjsr) {
		this.bqsbzjsr = bqsbzjsr;
	}
	
}
