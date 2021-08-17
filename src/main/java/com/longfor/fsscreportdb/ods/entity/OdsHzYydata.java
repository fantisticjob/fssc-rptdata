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
 * 项目经营情况运营数据接口
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-07
 */
@TableName("ODS_HZ_YYDATA")
@KeySequence("ODS_HZYYDATA")
public class OdsHzYydata implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;
    /**
     * 公司名
     */
    @TableField("company_nm")
    private String company_nm;

    /**
     * 公司code
     */
    @TableField("company_code")
    private String company_code;

    /**
     * 项目名
     */
    @TableField("project_nm")
    private String project_nm;

    /**
     * 项目code
     */
    @TableField("project_code")
    private String project_code;

    /**
     * 分期名
     */
    @TableField("phase_nm")
    private String phase_nm;

    /**
     * 分期code
     */
    @TableField("phase_code")
    private String phase_code;

    /**
     * 组团名
     */
    @TableField("phase_group_nm")
    private String phase_group_nm;

    /**
     * 组团code
     */
    @TableField("phase_group_code")
    private String phase_group_code;

    /**
     * 楼栋名
     */
    @TableField("building_nm")
    private String building_nm;

    /**
     * 楼栋code
     */
    @TableField("building_code")
    private String building_code;

    /**
     * 业态名
     */
    @TableField("format_nm")
    private String format_nm;

    /**
     * 业态code
     */
    @TableField("format_code")
    private String format_code;

    /**
     * 房间guid
     */
    @TableField("roomguid")
    private String roomguid;

    /**
     * 运营交房时间
     */
    @TableField("yyjfdate")
    private String yyjfdate;

    /**
     * 实际交房时间
     */
    @TableField("sjjfdate")
    private String sjjfdate;

    /**
     * 实际竣工验收及备案时间
     */
    @TableField("junbei_ac_date")
    private String junbei_ac_date;

    /**
     * 计划竣工验收及备案时间
     */
    @TableField("junbei_pl_date")
    private String junbei_pl_date;

    /**
     * 实际首批次预售许可证时间
     */
    @TableField("yushou_ac_date")
    private String yushou_ac_date;

    /**
     * 计划首批次预售许可证时间
     */
    @TableField("yushou_pl_date")
    private String yushou_pl_date;

    /**
     * 预售建面
     */
    @TableField("ysbldarea")
    private String ysbldarea;

    /**
     * 预售套内面积
     */
    @TableField("ystnarea")
    private String ystnarea;

    /**
     * 签约时间
     */
    @TableField("qysj")
    private String qysj;

    /**
     * 合同总价
     */
    @TableField("httotal")
    private BigDecimal httotal;

    /**
     * 核定建面
     */
    @TableField("bldarea")
    private BigDecimal bldarea;

    /**
     * 套数
     */
    @TableField("room_num")
    private BigDecimal room_num;

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

	public String getCompany_nm() {
		return company_nm;
	}

	public void setCompany_nm(String company_nm) {
		this.company_nm = company_nm;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getProject_nm() {
		return project_nm;
	}

	public void setProject_nm(String project_nm) {
		this.project_nm = project_nm;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getPhase_nm() {
		return phase_nm;
	}

	public void setPhase_nm(String phase_nm) {
		this.phase_nm = phase_nm;
	}

	public String getPhase_code() {
		return phase_code;
	}

	public void setPhase_code(String phase_code) {
		this.phase_code = phase_code;
	}

	public String getPhase_group_nm() {
		return phase_group_nm;
	}

	public void setPhase_group_nm(String phase_group_nm) {
		this.phase_group_nm = phase_group_nm;
	}

	public String getPhase_group_code() {
		return phase_group_code;
	}

	public void setPhase_group_code(String phase_group_code) {
		this.phase_group_code = phase_group_code;
	}

	public String getBuilding_nm() {
		return building_nm;
	}

	public void setBuilding_nm(String building_nm) {
		this.building_nm = building_nm;
	}

	public String getBuilding_code() {
		return building_code;
	}

	public void setBuilding_code(String building_code) {
		this.building_code = building_code;
	}

	public String getFormat_nm() {
		return format_nm;
	}

	public void setFormat_nm(String format_nm) {
		this.format_nm = format_nm;
	}

	public String getFormat_code() {
		return format_code;
	}

	public void setFormat_code(String format_code) {
		this.format_code = format_code;
	}

	public String getRoomguid() {
		return roomguid;
	}

	public void setRoomguid(String roomguid) {
		this.roomguid = roomguid;
	}

	public String getYyjfdate() {
		return yyjfdate;
	}

	public void setYyjfdate(String yyjfdate) {
		this.yyjfdate = yyjfdate;
	}

	public String getSjjfdate() {
		return sjjfdate;
	}

	public void setSjjfdate(String sjjfdate) {
		this.sjjfdate = sjjfdate;
	}

	public String getJunbei_ac_date() {
		return junbei_ac_date;
	}

	public void setJunbei_ac_date(String junbei_ac_date) {
		this.junbei_ac_date = junbei_ac_date;
	}

	public String getJunbei_pl_date() {
		return junbei_pl_date;
	}

	public void setJunbei_pl_date(String junbei_pl_date) {
		this.junbei_pl_date = junbei_pl_date;
	}

	public String getYushou_ac_date() {
		return yushou_ac_date;
	}

	public void setYushou_ac_date(String yushou_ac_date) {
		this.yushou_ac_date = yushou_ac_date;
	}

	public String getYushou_pl_date() {
		return yushou_pl_date;
	}

	public void setYushou_pl_date(String yushou_pl_date) {
		this.yushou_pl_date = yushou_pl_date;
	}

	public String getYsbldarea() {
		return ysbldarea;
	}

	public void setYsbldarea(String ysbldarea) {
		this.ysbldarea = ysbldarea;
	}

	public String getYstnarea() {
		return ystnarea;
	}

	public void setYstnarea(String ystnarea) {
		this.ystnarea = ystnarea;
	}

	public String getQysj() {
		return qysj;
	}

	public void setQysj(String qysj) {
		this.qysj = qysj;
	}

	public BigDecimal getHttotal() {
		return httotal;
	}

	public void setHttotal(BigDecimal httotal) {
		this.httotal = StringUtil.getTwoDecimal(httotal);
	}

	public BigDecimal getBldarea() {
		return bldarea;
	}

	public void setBldarea(BigDecimal bldarea) {
		this.bldarea = StringUtil.getTwoDecimal(bldarea);
	}

	public BigDecimal getRoom_num() {
		return room_num;
	}

	public void setRoom_num(BigDecimal room_num) {
		this.room_num = StringUtil.getTwoDecimal(room_num);
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
