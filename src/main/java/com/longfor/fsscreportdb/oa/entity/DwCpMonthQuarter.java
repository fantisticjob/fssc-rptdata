package com.longfor.fsscreportdb.oa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("DW_CP_MONTH_QUARTER")
public class DwCpMonthQuarter {
    private static final long serialVersionUID = 1L;

    /**
     *   年月(2020-10)
     */
    @TableId("DATA_DATE")
    private String dataDate;

    /**
     *   季度(2020Q4)
     */
    @TableField("QUARTER")
    private String quarter;

    /**
     *   季度数据锁板标识(Y-锁板 N-未锁板)
     */
    @TableField("FLAG")
    private String flag;

    /**
     *   季度序号
     */
    @TableField("SEQ")
    private String seq;

    /**
     *   界面下拉框选项控制
     */
    @TableField("SEL_FLAG")
    private String selFlag;

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getSelFlag() {
        return selFlag;
    }

    public void setSelFlag(String selFlag) {
        this.selFlag = selFlag;
    }
}
