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
 * 收款机明细接口
 * </p>
 *
 * @author chenziyao
 * @since 2020-09-23
 */
@TableName("ODS_SKJ_SKJINFO")
@KeySequence("ODS_SKJ_SKJINFO_S")
public class OdsSkjSkjinfo implements Serializable {

    private static final long serialVersionUID = 1L;

   
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 交易时间
     */
    private String datetime;

    /**
     * 交易日期
     */
    private String trandate;

    /**
     * 交易金额
     */
    private BigDecimal tranmoney;

    /**
     * 客户名称
     */
    private String businessname;

    /**
     * 项目名称
     */
    private String projectname;

    /**
     * 组织名称
     */
    private String orgname;

    /**
     * 终端号
     */
    private String termno;

    /**
     * 终端类型
     */
    private String inststyle;

    /**
     * 设备在线离线状态
     */
    private String state;

    /**
     * 运行状态
     */
    private String runstate;

    /**
     * 收款通道类型
     */
    private String paytype;

    /**
     * 收款通道类型名称
     */
    private String dictname;

    /**
     * 机具
     */
    private String isjj;

    /**
     * 执行时间
     */
    private String op_time;

    /**
     * 批次号
     */
    private String execution_id;

    /**
     * 入库时间
     */
    private String load_date;
    
    private String avg_work_time;

	public String getAvg_work_time() {
		return avg_work_time;
	}

	public void setAvg_work_time(String avg_work_time) {
		this.avg_work_time = avg_work_time;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getTrandate() {
		return trandate;
	}

	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}

	public BigDecimal getTranmoney() {
		return tranmoney;
	}

	public void setTranmoney(BigDecimal tranmoney) {
		this.tranmoney = StringUtil.getTwoDecimal(tranmoney);
	}

	public String getBusinessname() {
		return businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getTermno() {
		return termno;
	}

	public void setTermno(String termno) {
		this.termno = termno;
	}

	public String getInststyle() {
		return inststyle;
	}

	public void setInststyle(String inststyle) {
		this.inststyle = inststyle;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRunstate() {
		return runstate;
	}

	public void setRunstate(String runstate) {
		this.runstate = runstate;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getDictname() {
		return dictname;
	}

	public void setDictname(String dictname) {
		this.dictname = dictname;
	}

	public String getIsjj() {
		return isjj;
	}

	public void setIsjj(String isjj) {
		this.isjj = isjj;
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
