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
 * 增值税纳税申报表附列资料（一）
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-11
 */
@TableName("ODS_ZD_SWGLZZSFB1")
@KeySequence("ODS_ZDSWGLZZSFB1")
public class OdsZdSwglzzsfb1 implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;
    
  
    /**
     * 纳税人识别号
     */
    @TableField("nsrsbh")
    private String nsrsbh;

    /**
     * 纳税人名称
     */
    @TableField("nsrmc")
    private String nsrmc;

    /**
     * 法定代表人姓名
     */
    @TableField("fddbr")
    private String fddbr;

    /**
     * 注册地址
     */
    @TableField("zcdz")
    private String zcdz;

    /**
     * 生产经营地址
     */
    @TableField("scjydz")
    private String scjydz;

    /**
     * 开户银行
     */
    @TableField("khyh")
    private String khyh;

    /**
     * 开户账号
     */
    @TableField("khzh")
    private String khzh;

    /**
     * 电话号码
     */
    @TableField("dhhm")
    private String dhhm;

    /**
     * 所属行业-代码
     */
    @TableField("sshy_dm")
    private String sshy_dm;

    /**
     * 所属行业-名称
     */
    @TableField("sshy_mc")
    private String sshy_mc;

    /**
     * 登记注册类型-代码
     */
    @TableField("djzclx_dm")
    private String djzclx_dm;

    /**
     * 登记注册类型-名称
     */
    @TableField("djzclx_mc")
    private String djzclx_mc;

    /**
     * 公共信息id
     */
    @TableField("ggxx_id")
    private String ggxx_id;

    /**
     * 行次
     */
    @TableField("hc")
    private BigDecimal hc;

    /**
     * 税率
     */
    @TableField("sl")
    private BigDecimal sl;

    /**
     * 开具增值税专用发票-销售额
     */
    @TableField("l1")
    private BigDecimal l1;

    /**
     * 开具增值税专用发票-销项(应纳)税额
     */
    @TableField("l2")
    private BigDecimal l2;

    /**
     * 开具其他发票-销售额
     */
    @TableField("l3")
    private BigDecimal l3;

    /**
     * 开具其他发票-销项(应纳)税额
     */
    @TableField("l4")
    private BigDecimal l4;

    /**
     * 未开具发票-销售额
     */
    @TableField("l5")
    private BigDecimal l5;

    /**
     * 未开具发票-销项(应纳)税额
     */
    @TableField("l6")
    private BigDecimal l6;

    /**
     * 纳税检查调整-销售额
     */
    @TableField("l7")
    private BigDecimal l7;

    /**
     * 纳税检查调整-销项(应纳)税额
     */
    @TableField("l8")
    private BigDecimal l8;

    /**
     * 合计-销售额
     */
    @TableField("l9")
    private BigDecimal l9;

    /**
     * 合计-销项(应纳)税额
     */
    @TableField("l10")
    private BigDecimal l10;

    /**
     * 合计-价税合计
     */
    @TableField("l11")
    private BigDecimal l11;

    /**
     * 服务、不动产和无形资产扣除项目本期实际扣除金额
     */
    @TableField("l12")
    private BigDecimal l12;

    /**
     * 扣除后-含税(免税)销售额	
     */
    @TableField("l13")
    private BigDecimal l13;

    /**
     * 扣除后-销项(应纳)税额
     */
    @TableField("l14")
    private BigDecimal l14;

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

	public String getFddbr() {
		return fddbr;
	}

	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}

	public String getZcdz() {
		return zcdz;
	}

	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}

	public String getScjydz() {
		return scjydz;
	}

	public void setScjydz(String scjydz) {
		this.scjydz = scjydz;
	}

	public String getKhyh() {
		return khyh;
	}

	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}

	public String getKhzh() {
		return khzh;
	}

	public void setKhzh(String khzh) {
		this.khzh = khzh;
	}

	public String getDhhm() {
		return dhhm;
	}

	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
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

	public String getGgxx_id() {
		return ggxx_id;
	}

	public void setGgxx_id(String ggxx_id) {
		this.ggxx_id = ggxx_id;
	}

	public BigDecimal getHc() {
		return hc;
	}

	public void setHc(BigDecimal hc) {
		this.hc = StringUtil.getTwoDecimal(hc);
	}

	public BigDecimal getSl() {
		return sl;
	}

	public void setSl(BigDecimal sl) {
		this.sl = StringUtil.getTwoDecimal(sl);
	}

	public BigDecimal getL1() {
		return l1;
	}

	public void setL1(BigDecimal l1) {
		this.l1 = StringUtil.getTwoDecimal(l1);
	}

	public BigDecimal getL2() {
		return l2;
	}

	public void setL2(BigDecimal l2) {
		this.l2 = StringUtil.getTwoDecimal(l2);
	}

	public BigDecimal getL3() {
		return l3;
	}

	public void setL3(BigDecimal l3) {
		this.l3 = StringUtil.getTwoDecimal(l3);
	}

	public BigDecimal getL4() {
		return l4;
	}

	public void setL4(BigDecimal l4) {
		this.l4 = StringUtil.getTwoDecimal(l4);
	}

	public BigDecimal getL5() {
		return l5;
	}

	public void setL5(BigDecimal l5) {
		this.l5 = StringUtil.getTwoDecimal(l5);
	}

	public BigDecimal getL6() {
		return l6;
	}

	public void setL6(BigDecimal l6) {
		this.l6 = StringUtil.getTwoDecimal(l6);
	}

	public BigDecimal getL7() {
		return l7;
	}

	public void setL7(BigDecimal l7) {
		this.l7 = StringUtil.getTwoDecimal(l7);
	}

	public BigDecimal getL8() {
		return l8;
	}

	public void setL8(BigDecimal l8) {
		this.l8 = StringUtil.getTwoDecimal(l8);
	}

	public BigDecimal getL9() {
		return l9;
	}

	public void setL9(BigDecimal l9) {
		this.l9 = StringUtil.getTwoDecimal(l9);
	}

	public BigDecimal getL10() {
		return l10;
	}

	public void setL10(BigDecimal l10) {
		this.l10 = StringUtil.getTwoDecimal(l10);
	}

	public BigDecimal getL11() {
		return l11;
	}

	public void setL11(BigDecimal l11) {
		this.l11 = StringUtil.getTwoDecimal(l11);
	}

	public BigDecimal getL12() {
		return l12;
	}

	public void setL12(BigDecimal l12) {
		this.l12 = StringUtil.getTwoDecimal(l12);
	}

	public BigDecimal getL13() {
		return l13;
	}

	public void setL13(BigDecimal l13) {
		this.l13 = StringUtil.getTwoDecimal(l13);
	}

	public BigDecimal getL14() {
		return l14;
	}

	public void setL14(BigDecimal l14) {
		this.l14 = StringUtil.getTwoDecimal(l14);
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
