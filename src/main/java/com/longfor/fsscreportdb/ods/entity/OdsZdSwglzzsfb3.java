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
 * 增值税纳税申报表附列资料（三）
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-11
 */
@TableName("ODS_ZD_SWGLZZSFB3")
@KeySequence("ODS_ZDSWGLZZSFB3")
public class OdsZdSwglzzsfb3 implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    public BigDecimal getid() {
		return id;
	}

	public void setid(BigDecimal id) {
		this.id = id;
	}
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
    private String sshydm;

    /**
     * 所属行业-名称
     */
    @TableField("sshy_mc")
    private String sshymc;

    /**
     * 登记注册类型-代码
     */
    @TableField("djzclx_dm")
    private String djzclxdm;

    /**
     * 登记注册类型-名称
     */
    @TableField("djzclx_mc")
    private String djzclxmc;

    /**
     * 公共信息id
     */
    @TableField("ggxx_id")
    private String ggxxid;

    /**
     * 行次
     */
    @TableField("hc")
    private BigDecimal hc;

    /**
     * 项目
     */
    @TableField("xm")
    private String xm;

    /**
     * 本期服务、不动产和无形资产价税合计额（免税销售额）
     */
    @TableField("l1")
    private BigDecimal l1;

    /**
     * 期初余额
     */
    @TableField("l2")
    private BigDecimal l2;

    /**
     * 本期发生额
     */
    @TableField("l3")
    private BigDecimal l3;

    /**
     * 本期应扣除金额
     */
    @TableField("l4")
    private BigDecimal l4;

    /**
     * 本期实际扣除金额
     */
    @TableField("l5")
    private BigDecimal l5;

    /**
     * 期末余额
     */
    @TableField("l6")
    private BigDecimal l6;

    /**
     * 录入时间
     */
    @TableField("lr_sj")
    private String lrsj;

    @TableField("op_time")
    private String optime;

    /**
     * 执行批次
     */
    @TableField("execution_id")
    private String executionid;

    /**
     * 入库时间
     */
    @TableField("load_date")
    private String loaddate;

    public String getnsrsbh() {
        return nsrsbh;
    }

    public void setnsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }
    public String getnsrmc() {
        return nsrmc;
    }

    public void setnsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }
    public String getfddbr() {
        return fddbr;
    }

    public void setfddbr(String fddbr) {
        this.fddbr = fddbr;
    }
    public String getzcdz() {
        return zcdz;
    }

    public void setzcdz(String zcdz) {
        this.zcdz = zcdz;
    }
    public String getscjydz() {
        return scjydz;
    }

    public void setscjydz(String scjydz) {
        this.scjydz = scjydz;
    }
    public String getkhyh() {
        return khyh;
    }

    public void setkhyh(String khyh) {
        this.khyh = khyh;
    }
    public String getkhzh() {
        return khzh;
    }

    public void setkhzh(String khzh) {
        this.khzh = khzh;
    }
    public String getdhhm() {
        return dhhm;
    }

    public void setdhhm(String dhhm) {
        this.dhhm = dhhm;
    }
    public String getsshydm() {
        return sshydm;
    }

    public void setsshydm(String sshydm) {
        this.sshydm = sshydm;
    }
    public String getsshymc() {
        return sshymc;
    }

    public void setsshymc(String sshymc) {
        this.sshymc = sshymc;
    }
    public String getdjzclxdm() {
        return djzclxdm;
    }

    public void setdjzclxdm(String djzclxdm) {
        this.djzclxdm = djzclxdm;
    }
    public String getdjzclxmc() {
        return djzclxmc;
    }

    public void setdjzclxmc(String djzclxmc) {
        this.djzclxmc = djzclxmc;
    }
    public String getggxxid() {
        return ggxxid;
    }

    public void setggxxid(String ggxxid) {
        this.ggxxid = ggxxid;
    }
    public BigDecimal gethc() {
        return hc;
    }

    public void sethc(BigDecimal hc) {
        this.hc = StringUtil.getTwoDecimal(hc);
    }
    public String getxm() {
        return xm;
    }

    public void setxm(String xm) {
        this.xm = xm;
    }
    public BigDecimal getl1() {
        return l1;
    }

    public void setl1(BigDecimal l1) {
        this.l1 = StringUtil.getTwoDecimal(l1);
    }
    public BigDecimal getl2() {
        return l2;
    }

    public void setl2(BigDecimal l2) {
        this.l2 = StringUtil.getTwoDecimal(l2);
    }
    public BigDecimal getl3() {
        return l3;
    }

    public void setl3(BigDecimal l3) {
        this.l3 = StringUtil.getTwoDecimal(l3);
    }
    public BigDecimal getl4() {
        return l4;
    }

    public void setl4(BigDecimal l4) {
        this.l4 = StringUtil.getTwoDecimal(l4);
    }
    public BigDecimal getl5() {
        return l5;
    }

    public void setl5(BigDecimal l5) {
        this.l5 = StringUtil.getTwoDecimal(l5);
    }
    public BigDecimal getl6() {
        return l6;
    }

    public void setl6(BigDecimal l6) {
        this.l6 = StringUtil.getTwoDecimal(l6);
    }
    public String getlrsj() {
        return lrsj;
    }

    public void setlrsj(String lrsj) {
        this.lrsj = lrsj;
    }
    public String getoptime() {
        return optime;
    }

    public void setoptime(String optime) {
        this.optime = optime;
    }
    public String getexecutionid() {
        return executionid;
    }

    public void setexecutionid(String executionid) {
        this.executionid = executionid;
    }
    public String getloaddate() {
        return loaddate;
    }

    public void setloaddate(String loaddate) {
        this.loaddate = loaddate;
    }
}
