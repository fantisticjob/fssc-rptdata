package com.longfor.fsscreportdb.oa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

@TableName("DIN_DIM")
public class DinDim {
    private static final long serialVersionUID = 1L;

    /**
     *   维度编码
     */
    @TableId("DIM_ID")
    private String dimId;

    /**
     *   维度名称
     */
    @TableField("DIM_VALUE")
    private String dimValue;

    /**
     *   备注
     */
    @TableField("REMARKS")
    private String remarks;

    /**
     *   创建日期
     */
    @TableField("CREATE_DATE")
    private String createDate;

    /**
     *    联查表 din_dim_value
     */
    private List<DinDimValue> dinDimValueList;

    public String getDimId() {
        return dimId;
    }

    public void setDimId(String dimId) {
        this.dimId = dimId;
    }

    public String getDimValue() {
        return dimValue;
    }

    public void setDimValue(String dimValue) {
        this.dimValue = dimValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<DinDimValue> getDinDimValueList() {
        return dinDimValueList;
    }

    public void setDinDimValueList(List<DinDimValue> dinDimValueList) {
        this.dinDimValueList = dinDimValueList;
    }
}
