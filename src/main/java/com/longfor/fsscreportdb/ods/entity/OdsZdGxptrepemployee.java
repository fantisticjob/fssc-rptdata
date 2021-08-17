package com.longfor.fsscreportdb.ods.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 共享平台公司职工数表
 * </p>
 *
 * @author chenziyao
 * @since 2021-03-10
 */
@TableName("ODS_ZD_GXPTREPEMPLOYEE")
@KeySequence("ODS_ZD_GXPTREPEMPLOYEE_S")
public class OdsZdGxptrepemployee implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal id;

    /**
     * 公司code
     */
    private String com_code;

    /**
     * 公司名称
     */
    private String com_name;

    /**
     * 公司员工数
     */
    private BigDecimal com_p_num;

    /**
     * 执行时间
     */
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

	public String getCom_code() {
		return com_code;
	}

	public void setCom_code(String com_code) {
		this.com_code = com_code;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public BigDecimal getCom_p_num() {
		return com_p_num;
	}

	public void setCom_p_num(BigDecimal com_p_num) {
		this.com_p_num = com_p_num;
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
