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
 * 资金计划口径现金流量表
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-11
 */
@TableName("ODS_ZD_NCVIUFOLHGX")
@KeySequence("ODS_ZDNCVIUFOLHGX")
public class OdsZdNcviufolhgx implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 期间
     */
    @TableField("accountantperiod")
    private String accountantperiod;

    @TableField("unitcode")
    private String unitcode;

    @TableField("unitname")
    private String unitname;

    @TableField("indexcode")
    private String indexcode;

    @TableField("indexname")
    private String indexname;

    @TableField("monthmoney")
    private BigDecimal monthmoney;

    /**
     * 入库时间
     */
    @TableField("op_time")
    private String optime;

    /**
     * 执行批次
     */
    @TableField("execution_id")
    private String executionid;

    @TableField("load_date")
    private String loaddate;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getAccountantperiod() {
		return accountantperiod;
	}

	public void setAccountantperiod(String accountantperiod) {
		this.accountantperiod = accountantperiod;
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getIndexcode() {
		return indexcode;
	}

	public void setIndexcode(String indexcode) {
		this.indexcode = indexcode;
	}

	public String getIndexname() {
		return indexname;
	}

	public void setIndexname(String indexname) {
		this.indexname = indexname;
	}

	public BigDecimal getMonthmoney() {
		return monthmoney;
	}

	public void setMonthmoney(BigDecimal monthmoney) {
		this.monthmoney = StringUtil.getTwoDecimal(monthmoney);
	}

	public String getOptime() {
		return optime;
	}

	public void setOptime(String optime) {
		this.optime = optime;
	}

	public String getExecutionid() {
		return executionid;
	}

	public void setExecutionid(String executionid) {
		this.executionid = executionid;
	}

	public String getLoaddate() {
		return loaddate;
	}

	public void setLoaddate(String loaddate) {
		this.loaddate = loaddate;
	}

}
