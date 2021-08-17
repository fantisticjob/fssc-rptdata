package com.longfor.fsscreportdb.oa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("DIN_DIM_VALUE")
public class DinDimValue {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableField("DIM_ID")
    private String dimId;

    /**
     *
     */
    @TableField("DIM_VALUE_ID")
    private String dimValueId;

    /**
     * ssoToken的值
     */
    @TableField("DIM_VALUE_NAME")
    private String dimValueName;

    /**
     *标志位
     */
    @TableField("USE_FLAG")
    private int useFlag;

    /**
     * 创建时间
     */
    @TableField("CREATE_DATE")
    private Date createDate;

    /**
     * 标记
     */
    @TableField("REMARKS")
    private String remarks;

    public String getDimId() {
        return dimId;
    }

    public void setDimId(String dimId) {
        this.dimId = dimId;
    }

    public String getDimValueId() {
        return dimValueId;
    }

    public void setDimValueId(String dimValueId) {
        this.dimValueId = dimValueId;
    }

    public String getDimValueName() {
        return dimValueName;
    }

    public void setDimValueName(String dimValueName) {
        this.dimValueName = dimValueName;
    }

    public int getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(int useFlag) {
        this.useFlag = useFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
