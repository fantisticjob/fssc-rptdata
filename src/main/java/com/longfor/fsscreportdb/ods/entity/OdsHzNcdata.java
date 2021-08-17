package com.longfor.fsscreportdb.ods.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 项目经营情况NC数据接口
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-06
 */
@TableName("ODS_HZ_NCDATA")
@KeySequence("ODS_HZNCDATA")
public class OdsHzNcdata implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

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
     * 帐套主键
     */
    @TableField("pk_accountingbook")
    private String pk_accountingbook;

    /**
     * 币种名称
     */
    @TableField("bz_name")
    private String bz_name;

    /**
     * 帐套编码
     */
    @TableField("zt_code")
    private String zt_code;

    /**
     * 帐套名称
     */
    @TableField("zt_name")
    private String ztname;

    /**
     * 科目编码
     */
    @TableField("km_code")
    private String km_code;

    /**
     * 科目级别
     */
    @TableField("km_level")
    private String km_level;

    /**
     * 科目名称
     */
    @TableField("km_name")
    private String km_name;

    /**
     * 科目全名称
     */
    @TableField("km_dispname")
    private String km_dispname;

    /**
     * 贷方发生
     */
    @TableField("fsd")
    private String fsd;

    /**
     * 借方发生
     */
    @TableField("fsj")
    private String fsj;

    /**
     * 累计贷方
     */
    @TableField("ljd")
    private String ljd;

    /**
     * 累计借方
     */
    @TableField("ljj")
    private String ljj;

    /**
     * 期末余额
     */
    @TableField("qmye")
    private String qmye;

    /**
     * 期末方向
     */
    @TableField("qmfx")
    private String qmfx;

    /**
     * 期初余额
     */
    @TableField("qcye")
    private String qcye;

    /**
     * 期初方向
     */
    @TableField("qcfx")
    private String qcfx;

    /**
     * 执行时间
     */
    @TableField("op_time")
    private String op_time;

    /**
     * 批次号
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

	public String getPk_accountingbook() {
		return pk_accountingbook;
	}

	public void setPk_accountingbook(String pk_accountingbook) {
		this.pk_accountingbook = pk_accountingbook;
	}

	public String getBz_name() {
		return bz_name;
	}

	public void setBz_name(String bz_name) {
		this.bz_name = bz_name;
	}

	public String getZt_code() {
		return zt_code;
	}

	public void setZt_code(String zt_code) {
		this.zt_code = zt_code;
	}

	public String getZtname() {
		return ztname;
	}

	public void setZtname(String ztname) {
		this.ztname = ztname;
	}

	public String getKm_code() {
		return km_code;
	}

	public void setKm_code(String km_code) {
		this.km_code = km_code;
	}

	public String getKm_level() {
		return km_level;
	}

	public void setKm_level(String km_level) {
		this.km_level = km_level;
	}

	public String getKm_name() {
		return km_name;
	}

	public void setKm_name(String km_name) {
		this.km_name = km_name;
	}

	public String getKm_dispname() {
		return km_dispname;
	}

	public void setKm_dispname(String km_dispname) {
		this.km_dispname = km_dispname;
	}

	public String getFsd() {
		return fsd;
	}

	public void setFsd(String fsd) {
		this.fsd = fsd;
	}

	public String getFsj() {
		return fsj;
	}

	public void setFsj(String fsj) {
		this.fsj = fsj;
	}

	public String getLjd() {
		return ljd;
	}

	public void setLjd(String ljd) {
		this.ljd = ljd;
	}

	public String getLjj() {
		return ljj;
	}

	public void setLjj(String ljj) {
		this.ljj = ljj;
	}

	public String getQmye() {
		return qmye;
	}

	public void setQmye(String qmye) {
		this.qmye = qmye;
	}

	public String getQmfx() {
		return qmfx;
	}

	public void setQmfx(String qmfx) {
		this.qmfx = qmfx;
	}

	public String getQcye() {
		return qcye;
	}

	public void setQcye(String qcye) {
		this.qcye = qcye;
	}

	public String getQcfx() {
		return qcfx;
	}

	public void setQcfx(String qcfx) {
		this.qcfx = qcfx;
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
