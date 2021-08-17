package com.longfor.fsscreportdb.Thread.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-19
 */
@TableName("ODS_NC_NCTHREAD")
@KeySequence("ODS_NC_NCTHREAD_S")
public class OdsNcNcthread implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    private Date starttime;

    private Date endtime1;
    
    private Date endtime2;
    
    private Date endtime3;
    
    private Date endtime4;
    
    private Date endtime5;


	public Date getEndtime1() {
		return endtime1;
	}

	public void setEndtime1(Date endtime1) {
		this.endtime1 = endtime1;
	}

	public Date getEndtime2() {
		return endtime2;
	}

	public void setEndtime2(Date endtime2) {
		this.endtime2 = endtime2;
	}

	public Date getEndtime3() {
		return endtime3;
	}

	public void setEndtime3(Date endtime3) {
		this.endtime3 = endtime3;
	}

	public Date getEndtime4() {
		return endtime4;
	}

	public void setEndtime4(Date endtime4) {
		this.endtime4 = endtime4;
	}

	public Date getEndtime5() {
		return endtime5;
	}

	public void setEndtime5(Date endtime5) {
		this.endtime5 = endtime5;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

}
