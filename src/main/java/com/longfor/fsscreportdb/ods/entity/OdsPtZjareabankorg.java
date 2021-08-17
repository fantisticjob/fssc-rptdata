package com.longfor.fsscreportdb.ods.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 资金银行账户-地区-组织对照关系
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-13
 */
@TableName("ODS_PT_ZJAREABANKORG")
@KeySequence("ODS_PTZJAREABANKORG")
public class OdsPtZjareabankorg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 组织code
     */
    private String org_code;

    /**
     * 组织名称
     */
    private String org_name;

    /**
     * 地区code
     */
    private String area_code;

    /**
     * 地区
     */
    private String area;
    
    /**
     * 地区
     */
    private String accountname;

    /**
     * 银行账号
     */
    private String bank_account;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

}
