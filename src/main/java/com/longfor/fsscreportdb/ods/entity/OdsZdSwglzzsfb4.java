package com.longfor.fsscreportdb.ods.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 增值税纳税申报表附列资料（四）
 * </p>
 *
 * @author chenziyao
 * @since 2021-01-14
 */
@TableName("ODS_ZD_SWGLZZSFB4")
@KeySequence("ODS_ZDSWGLZZSFB4")
public class OdsZdSwglzzsfb4 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
     * 纳税人识别号
     */
    @TableField("NSRSBH")
    private String nsrsbh;

    /**
     * 纳税人名称
     */
    @TableField("NSRMC")
    private String nsrmc;

    /**
     * 法定代表人姓名
     */
    @TableField("FDDBR")
    private String fddbr;

    /**
     * 注册地址
     */
    @TableField("ZCDZ")
    private String zcdz;

    /**
     * 生产经营地址
     */
    @TableField("SCJYDZ")
    private String scjydz;

    /**
     * 开户银行
     */
    @TableField("KHYH")
    private String khyh;

    /**
     * 开户账号
     */
    @TableField("KHZH")
    private String khzh;

    /**
     * 电话号码
     */
    @TableField("DHHM")
    private String dhhm;

    /**
     * 所属行业-代码
     */
    @TableField("SSHY_DM")
    private String sshy_dm;

    /**
     * 所属行业-名称
     */
    @TableField("SSHY_MC")
    private String sshy_mc;

    /**
     * 登记注册类型-代码
     */
    @TableField("DJZCLX_DM")
    private String djzclx_dm;

    /**
     * 登记注册类型-名称
     */
    @TableField("DJZCLX_MC")
    private String djzclx_mc;

    /**
     * 公共信息ID
     */
    @TableField("GGXX_ID")
    private String ggxx_id;

    /**
     * 行次
     */
    @TableField("HC")
    private BigDecimal hc;

    /**
     * 项目
     */
    @TableField("XM")
    private String xm;

    /**
     * 期初余额
     */
    @TableField("L1")
    private BigDecimal l1;

    /**
     * 本期发生额
     */
    @TableField("L2")
    private BigDecimal l2;

    /**
     * 本期应抵减税额
     */
    @TableField("L3")
    private BigDecimal l3;

    /**
     * 本期实际抵减税额
     */
    @TableField("L4")
    private BigDecimal l4;

    /**
     * 期末余额
     */
    @TableField("L5")
    private BigDecimal l5;

    /**
     * 录入时间
     */
    @TableField("LR_SJ")
    private String lr_sj;

    /**
     * op_time
     */
    @TableField("OP_TIME")
    private String op_time;

    /**
     * 执行批次
     */
    @TableField("EXECUTION_ID")
    private String execution_id;

    /**
     * 入库时间
     */
    @TableField("LOAD_DATE")
    private String load_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
		this.hc = hc;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public BigDecimal getL1() {
		return l1;
	}

	public void setL1(BigDecimal l1) {
		this.l1 = l1;
	}

	public BigDecimal getL2() {
		return l2;
	}

	public void setL2(BigDecimal l2) {
		this.l2 = l2;
	}

	public BigDecimal getL3() {
		return l3;
	}

	public void setL3(BigDecimal l3) {
		this.l3 = l3;
	}

	public BigDecimal getL4() {
		return l4;
	}

	public void setL4(BigDecimal l4) {
		this.l4 = l4;
	}

	public BigDecimal getL5() {
		return l5;
	}

	public void setL5(BigDecimal l5) {
		this.l5 = l5;
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
