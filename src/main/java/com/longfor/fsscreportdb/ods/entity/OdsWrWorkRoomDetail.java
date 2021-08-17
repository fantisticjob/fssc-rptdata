package com.longfor.fsscreportdb.ods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * ods层工抵房明细表
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-08
 */
@TableName("ODS_WR_WORK_ROOM_DETAIL")
@KeySequence(value = "ODS_GDF")
public class OdsWrWorkRoomDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 编码
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 公司
     */
    @TableField("buname")
    private String buname;

    /**
     * 分期名
     */
    @TableField("projname")
    private String projname;

    /**
     * 项目名
     */
    @TableField("parentprojname")
    private String parentprojname;

    /**
     * 楼栋名
     */
    @TableField("bldname")
    private String bldname;

    /**
     * 组团名
     */
    @TableField("ztname")
    private String ztname;

    /**
     * 业态code
     */
    @TableField("x_yfproducttypecode")
    private String x_yfproducttypecode;

    /**
     * 业态名陈
     */
    @TableField("x_yfproducttypename")
    private String x_yfproducttypename;

    /**
     * 房间名
     */
    @TableField("roomcode")
    private String roomcode;

    /**
     * 客户名称
     */
    @TableField("cstallname")
    private String cstallname;

    /**
     * 认购日期
     */
    @TableField("rgdate")
    private Date rgdate;

    /**
     * 签约日期
     */
    @TableField("qsdate")
    private Date qsdate;

    /**
     * 网签日期
     */
    @TableField("netcontractdate")
    private Date netcontractdate;

    /**
     * 是否政府网签
     */
    @TableField("ishavezfwq")
    private String ishavezfwq;

    /**
     * 款项类型
     */
    @TableField("itemtype")
    private String itemtype;

    /**
     * 付款方式名称
     */
    @TableField("payformname")
    private String payformname;

    /**
     * 付款方式类型
     */
    @TableField("payformtype")
    private String payformtype;

    /**
     * 标准总价
     */
    @TableField("roomtotal")
    private BigDecimal roomtotal;

    /**
     * 建筑面积
     */
    @TableField("bldarea")
    private BigDecimal bldarea;

    /**
     * 套内面积
     */
    @TableField("tnarea")
    private BigDecimal tnarea;

    /**
     * 成交总价
     */
    @TableField("cjrmbtotal")
    private BigDecimal cjrmbtotal;

    /**
     * 建筑成交单价
     */
    @TableField("cjbldprice")
    private BigDecimal cjbldprice;

    /**
     * 套内成交单价
     */
    @TableField("cjtnprice")
    private BigDecimal cjtnprice;

    /**
     * 已回款金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 滞纳天数
     */
    @TableField("zydate")
    private BigDecimal zydate;

    /**
     * 欠款
     */
    @TableField("rmbye")
    private BigDecimal rmbye;

    /**
     * 竣备日期
     */
    @TableField("jbdate")
    private Date jbdate;

    /**
     * 合同交房日期
     */
    @TableField("jfdate")
    private Date jfdate;

    /**
     * 运营交房时间
     */
    @TableField("yyjfdate")
    private Date yyjfdate;

    /**
     * 滞纳金
     */
    @TableField("znj")
    private BigDecimal znj;

    /**
     * 账单id
     */
    @TableField("feeguid")
    private String feeguid;

    /**
     * 日期
     */
    @TableField("datekey")
    private Date datekey;

    /**
     * 任务执行时间
     */
    @TableField("op_time")
    private Date op_time;

    /**
     * 任务执行批次
     */
    @TableField("execution_id")
    private BigDecimal execution_id;

    /**
     * etl时间
     */
    @TableField("etl_time")
    private Date etl_time;

    /**
     * 应付日期
     */
    @TableField("due_date")
    private Date lastdate;

    /**
     * 回款日期
     */
    @TableField("payment_date")
    private Date kpdate;

    /**
     * 核定总价
     */
    @TableField("hdzj")
    private BigDecimal hdzj;
    
    @TableField("itemname")
    private String itemname;

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getBuname() {
		return buname;
	}

	public void setBuname(String buname) {
		this.buname = buname;
	}

	public String getProjname() {
		return projname;
	}

	public void setProjname(String projname) {
		this.projname = projname;
	}

	public String getParentprojname() {
		return parentprojname;
	}

	public void setParentprojname(String parentprojname) {
		this.parentprojname = parentprojname;
	}

	public String getBldname() {
		return bldname;
	}

	public void setBldname(String bldname) {
		this.bldname = bldname;
	}

	public String getZtname() {
		return ztname;
	}

	public void setZtname(String ztname) {
		this.ztname = ztname;
	}

	public String getX_yfproducttypecode() {
		return x_yfproducttypecode;
	}

	public void setX_yfproducttypecode(String x_yfproducttypecode) {
		this.x_yfproducttypecode = x_yfproducttypecode;
	}

	public String getX_yfproducttypename() {
		return x_yfproducttypename;
	}

	public void setX_yfproducttypename(String x_yfproducttypename) {
		this.x_yfproducttypename = x_yfproducttypename;
	}

	public String getRoomcode() {
		return roomcode;
	}

	public void setRoomcode(String roomcode) {
		this.roomcode = roomcode;
	}

	public String getCstallname() {
		return cstallname;
	}

	public void setCstallname(String cstallname) {
		this.cstallname = cstallname;
	}

	public Date getRgdate() {
		return rgdate;
	}

	public void setRgdate(Date rgdate) {
		this.rgdate = rgdate;
	}

	public Date getQsdate() {
		return qsdate;
	}

	public void setQsdate(Date qsdate) {
		this.qsdate = qsdate;
	}

	public Date getNetcontractdate() {
		return netcontractdate;
	}

	public void setNetcontractdate(Date netcontractdate) {
		this.netcontractdate = netcontractdate;
	}

	public String getIshavezfwq() {
		return ishavezfwq;
	}

	public void setIshavezfwq(String ishavezfwq) {
		this.ishavezfwq = ishavezfwq;
	}

	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public String getPayformname() {
		return payformname;
	}

	public void setPayformname(String payformname) {
		this.payformname = payformname;
	}

	public String getPayformtype() {
		return payformtype;
	}

	public void setPayformtype(String payformtype) {
		this.payformtype = payformtype;
	}

	public BigDecimal getRoomtotal() {
		return roomtotal;
	}

	public void setRoomtotal(BigDecimal roomtotal) {
		this.roomtotal = StringUtil.getTwoDecimal(roomtotal);
	}

	public BigDecimal getBldarea() {
		return bldarea;
	}

	public void setBldarea(BigDecimal bldarea) {
		this.bldarea = StringUtil.getTwoDecimal(bldarea);
	}

	public BigDecimal getTnarea() {
		return tnarea;
	}

	public void setTnarea(BigDecimal tnarea) {
		this.tnarea = StringUtil.getTwoDecimal(tnarea);
	}

	public BigDecimal getCjrmbtotal() {
		return cjrmbtotal;
	}

	public void setCjrmbtotal(BigDecimal cjrmbtotal) {
		this.cjrmbtotal = StringUtil.getTwoDecimal(cjrmbtotal);
	}

	public BigDecimal getCjbldprice() {
		return cjbldprice;
	}

	public void setCjbldprice(BigDecimal cjbldprice) {
		this.cjbldprice = StringUtil.getTwoDecimal(cjbldprice);
	}

	public BigDecimal getCjtnprice() {
		return cjtnprice;
	}

	public void setCjtnprice(BigDecimal cjtnprice) {
		this.cjtnprice = StringUtil.getTwoDecimal(cjtnprice);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = StringUtil.getTwoDecimal(amount);
	}

	public BigDecimal getZydate() {
		return zydate;
	}

	public void setZydate(BigDecimal zydate) {
		this.zydate = StringUtil.getTwoDecimal(zydate);
	}

	public BigDecimal getRmbye() {
		return rmbye;
	}

	public void setRmbye(BigDecimal rmbye) {
		this.rmbye = StringUtil.getTwoDecimal(rmbye);
	}

	public Date getJbdate() {
		return jbdate;
	}

	public void setJbdate(Date jbdate) {
		this.jbdate = jbdate;
	}

	public Date getJfdate() {
		return jfdate;
	}

	public void setJfdate(Date jfdate) {
		this.jfdate = jfdate;
	}

	public Date getYyjfdate() {
		return yyjfdate;
	}

	public void setYyjfdate(Date yyjfdate) {
		this.yyjfdate = yyjfdate;
	}

	public BigDecimal getZnj() {
		return znj;
	}

	public void setZnj(BigDecimal znj) {
		this.znj = StringUtil.getTwoDecimal(znj);
	}

	public String getFeeguid() {
		return feeguid;
	}

	public void setFeeguid(String feeguid) {
		this.feeguid = feeguid;
	}

	public Date getDatekey() {
		return datekey;
	}

	public void setDatekey(Date datekey) {
		this.datekey = datekey;
	}

	public Date getOp_time() {
		return op_time;
	}

	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}

	public BigDecimal getExecution_id() {
		return execution_id;
	}

	public void setExecution_id(BigDecimal execution_id) {
		this.execution_id = StringUtil.getTwoDecimal(execution_id);
	}

	public Date getEtl_time() {
		return etl_time;
	}

	public void setEtl_time(Date etl_time) {
		this.etl_time = etl_time;
	}

	public Date getLastdate() {
		return lastdate;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}

	public Date getKpdate() {
		return kpdate;
	}

	public void setKpdate(Date kpdate) {
		this.kpdate = kpdate;
	}

	public BigDecimal getHdzj() {
		return hdzj;
	}

	public void setHdzj(BigDecimal hdzj) {
		this.hdzj = StringUtil.getTwoDecimal(hdzj);
	}

   
}
