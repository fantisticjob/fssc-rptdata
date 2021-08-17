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
 * NC科目数据
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-11
 */
@TableName("ODS_ZD_NCGLSUBJECTBALANCE")
@KeySequence("ODS_ZDNCGLSUBJECTBALANCE")
public class OdsZdNcglsubjectbalance implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 公司名称
     */
    @TableField("gsmc")
    private String gsmc;

    /**
     * 年
     */
    @TableField("yearv")
    private String yearv;

    /**
     * 月
     */
    @TableField("periodv")
    private String periodv;

    /**
     * 科目等级
     */
    @TableField("km_level")
    private BigDecimal km_level;

    /**
     * 贷方
     */
    @TableField("fsd")
    private BigDecimal fsd;

    /**
     * 借方
     */
    @TableField("fsj")
    private BigDecimal fsj;

    /**
     * 累计贷
     */
    @TableField("ljd")
    private BigDecimal ljd;

    /**
     * 累计借
     */
    @TableField("ljj")
    private BigDecimal ljj;

    /**
     * 期末余额
     */
    @TableField("qmye")
    private BigDecimal qmye;

    /**
     * 科目名称
     */
    @TableField("km_name")
    private String km_name;

    /**
     * 科目代码
     */
    @TableField("km_code")
    private String km_code;

    /**
     * 科目全名称
     */
    @TableField("km_dispname")
    private String km_dispname;

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

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getGsmc() {
		return gsmc;
	}

	public void setGsmc(String gsmc) {
		this.gsmc = gsmc;
	}

	public String getYearv() {
		return yearv;
	}

	public void setYearv(String yearv) {
		this.yearv = yearv;
	}

	public String getPeriodv() {
		return periodv;
	}

	public void setPeriodv(String periodv) {
		this.periodv = periodv;
	}

	public BigDecimal getKm_level() {
		return km_level;
	}

	public void setKm_level(BigDecimal km_level) {
		this.km_level =  StringUtil.getTwoDecimal(km_level);
	}

	public BigDecimal getFsd() {
		return fsd;
	}

	public void setFsd(BigDecimal fsd) {
		this.fsd =  StringUtil.getTwoDecimal(fsd);
	}

	public BigDecimal getFsj() {
		return fsj;
	}

	public void setFsj(BigDecimal fsj) {
		this.fsj =  StringUtil.getTwoDecimal(fsj);
	}

	public BigDecimal getLjd() {
		return ljd;
	}

	public void setLjd(BigDecimal ljd) {
		this.ljd =  StringUtil.getTwoDecimal(ljd);
	}

	public BigDecimal getLjj() {
		return ljj;
	}

	public void setLjj(BigDecimal ljj) {
		this.ljj =  StringUtil.getTwoDecimal(ljj);
	}

	public BigDecimal getQmye() {
		return qmye;
	}

	public void setQmye(BigDecimal qmye) {
		this.qmye =  StringUtil.getTwoDecimal(qmye);
	}

	public String getKm_name() {
		return km_name;
	}

	public void setKm_name(String km_name) {
		this.km_name = km_name;
	}

	public String getKm_code() {
		return km_code;
	}

	public void setKm_code(String km_code) {
		this.km_code = km_code;
	}

	public String getKm_dispname() {
		return km_dispname;
	}

	public void setKm_dispname(String km_dispname) {
		this.km_dispname = km_dispname;
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
