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
 * 企业所得税
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-11
 */
@TableName("ODS_ZD_SWGLQS")
@KeySequence("ODS_ZDSWGLQS")
public class OdsZdSwglqs implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 纳税人名称
     */
    @TableField("name")
    private String name;

    /**
     * 业务场景id
     */
    @TableField("ywcj_id")
    private String ywcj_id;

    /**
     * 所属期
     */
    @TableField("ssq_dm")
    private String ssq_dm;

    /**
     * 营业收入
     */
    @TableField("yysr")
    private BigDecimal yysr;

    /**
     * 营业成本
     */
    @TableField("yycb")
    private BigDecimal yycb;

    /**
     * 利润总额
     */
    @TableField("lrze")
    private BigDecimal lrze;

    /**
     * 加:特定业务计算的应纳税所得额
     */
    @TableField("tdywjsdynssde")
    private BigDecimal tdywjsdynssde;

    /**
     * 减:不征税收入
     */
    @TableField("bzssr")
    private BigDecimal bzssr;

    /**
     * 减：免税收入、减计收入、所得减免等优惠金额
     */
    @TableField("mssr")
    private BigDecimal mssr;

    /**
     * 减:固定资产加速折旧（扣除）调减额
     */
    @TableField("gdzcjszjtze")
    private BigDecimal gdzcjszjtze;

    /**
     * 减:弥补以前年度亏损
     */
    @TableField("mbyqndks")
    private BigDecimal mbyqndks;

    /**
     * 实际利润额按照上一纳税年度应纳税所得额平均额确定的应纳税所得额
     */
    @TableField("sjlre")
    private BigDecimal sjlre;

    /**
     * 应纳所得税额（88=87*25%）
     */
    @TableField("ynsdse")
    private BigDecimal ynsdse;

    /**
     * 减:减免所得税额
     */
    @TableField("jmsdse")
    private BigDecimal jmsdse;

    /**
     * 减:实际已缴纳所得税额
     */
    @TableField("sjyjnsdse")
    private BigDecimal sjyjnsdse;

    /**
     * 减:特定业务预缴（征）所得税额
     */
    @TableField("tdywyjsdse")
    private BigDecimal tdywyjsdse;

    /**
     * 本期应补（退）所得税额(92=88-89-90-91)税务机关确定的本期应纳所得税额
     */
    @TableField("ybtsdse")
    private BigDecimal ybtsdse;

    /**
     * 总机构填写：本期分摊应补（退）所得税额（93=94+95+96）
     */
    @TableField("zjgbqftybtsdse")
    private BigDecimal zjgbqftybtsdse;

    /**
     * 其中：总机构分摊应补（退）所得税额
     */
    @TableField("zjgftybtsdse")
    private BigDecimal zjgftybtsdse;

    /**
     * 财政集中分配应补（退）所得税额
     */
    @TableField("czjzfpybtsdse")
    private BigDecimal czjzfpybtsdse;

    /**
     * 总机构具有主体生产经营职能的部门分摊所得税额
     */
    @TableField("zjgznbmftsdse")
    private BigDecimal zjgznbmftsdse;

    /**
     * 分支机构填写：本期分摊比例
     */
    @TableField("fzjgbqftbl")
    private BigDecimal fzjgbqftbl;

    /**
     * 本期分摊应补（退）所得税额
     */
    @TableField("fzjgbqftybtsdse")
    private BigDecimal fzjgbqftybtsdse;

    /**
     * 录入时间
     */
    @TableField("lr_sj")
    private String lr_sj;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYwcj_id() {
		return ywcj_id;
	}

	public void setYwcj_id(String ywcj_id) {
		this.ywcj_id = ywcj_id;
	}

	public String getSsq_dm() {
		return ssq_dm;
	}

	public void setSsq_dm(String ssq_dm) {
		this.ssq_dm = ssq_dm;
	}

	public BigDecimal getYysr() {
		return yysr;
	}

	public void setYysr(BigDecimal yysr) {
		this.yysr = StringUtil.getTwoDecimal(yysr);
	}

	public BigDecimal getYycb() {
		return yycb;
	}

	public void setYycb(BigDecimal yycb) {
		this.yycb =  StringUtil.getTwoDecimal(yycb);
	}

	public BigDecimal getLrze() {
		return lrze;
	}

	public void setLrze(BigDecimal lrze) {
		this.lrze = StringUtil.getTwoDecimal(lrze);
	}

	public BigDecimal getTdywjsdynssde() {
		return tdywjsdynssde;
	}

	public void setTdywjsdynssde(BigDecimal tdywjsdynssde) {
		this.tdywjsdynssde = StringUtil.getTwoDecimal(tdywjsdynssde);
	}

	public BigDecimal getBzssr() {
		return bzssr;
	}

	public void setBzssr(BigDecimal bzssr) {
		this.bzssr = StringUtil.getTwoDecimal(bzssr);
	}

	public BigDecimal getMssr() {
		return mssr;
	}

	public void setMssr(BigDecimal mssr) {
		this.mssr = StringUtil.getTwoDecimal(mssr);
	}

	public BigDecimal getGdzcjszjtze() {
		return gdzcjszjtze;
	}

	public void setGdzcjszjtze(BigDecimal gdzcjszjtze) {
		this.gdzcjszjtze = StringUtil.getTwoDecimal(gdzcjszjtze);
	}

	public BigDecimal getMbyqndks() {
		return mbyqndks;
	}

	public void setMbyqndks(BigDecimal mbyqndks) {
		this.mbyqndks = StringUtil.getTwoDecimal(mbyqndks);
	}

	public BigDecimal getSjlre() {
		return sjlre;
	}

	public void setSjlre(BigDecimal sjlre) {
		this.sjlre = StringUtil.getTwoDecimal(sjlre);
	}

	public BigDecimal getYnsdse() {
		return ynsdse;
	}

	public void setYnsdse(BigDecimal ynsdse) {
		this.ynsdse = StringUtil.getTwoDecimal(ynsdse);
	}

	public BigDecimal getJmsdse() {
		return jmsdse;
	}

	public void setJmsdse(BigDecimal jmsdse) {
		this.jmsdse = StringUtil.getTwoDecimal(jmsdse);
	}

	public BigDecimal getSjyjnsdse() {
		return sjyjnsdse;
	}

	public void setSjyjnsdse(BigDecimal sjyjnsdse) {
		this.sjyjnsdse = StringUtil.getTwoDecimal(sjyjnsdse);
	}

	public BigDecimal getTdywyjsdse() {
		return tdywyjsdse;
	}

	public void setTdywyjsdse(BigDecimal tdywyjsdse) {
		this.tdywyjsdse = StringUtil.getTwoDecimal(tdywyjsdse);
	}

	public BigDecimal getYbtsdse() {
		return ybtsdse;
	}

	public void setYbtsdse(BigDecimal ybtsdse) {
		this.ybtsdse = StringUtil.getTwoDecimal(ybtsdse);
	}

	public BigDecimal getZjgbqftybtsdse() {
		return zjgbqftybtsdse;
	}

	public void setZjgbqftybtsdse(BigDecimal zjgbqftybtsdse) {
		this.zjgbqftybtsdse = StringUtil.getTwoDecimal(zjgbqftybtsdse);
	}

	public BigDecimal getZjgftybtsdse() {
		return zjgftybtsdse;
	}

	public void setZjgftybtsdse(BigDecimal zjgftybtsdse) {
		this.zjgftybtsdse = StringUtil.getTwoDecimal(zjgftybtsdse);
	}

	public BigDecimal getCzjzfpybtsdse() {
		return czjzfpybtsdse;
	}

	public void setCzjzfpybtsdse(BigDecimal czjzfpybtsdse) {
		this.czjzfpybtsdse = StringUtil.getTwoDecimal(czjzfpybtsdse);
	}

	public BigDecimal getZjgznbmftsdse() {
		return zjgznbmftsdse;
	}

	public void setZjgznbmftsdse(BigDecimal zjgznbmftsdse) {
		this.zjgznbmftsdse = StringUtil.getTwoDecimal(zjgznbmftsdse);
	}

	public BigDecimal getFzjgbqftbl() {
		return fzjgbqftbl;
	}

	public void setFzjgbqftbl(BigDecimal fzjgbqftbl) {
		this.fzjgbqftbl = StringUtil.getTwoDecimal(fzjgbqftbl);
	}

	public BigDecimal getFzjgbqftybtsdse() {
		return fzjgbqftybtsdse;
	}

	public void setFzjgbqftybtsdse(BigDecimal fzjgbqftybtsdse) {
		this.fzjgbqftybtsdse = StringUtil.getTwoDecimal(fzjgbqftybtsdse);
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
