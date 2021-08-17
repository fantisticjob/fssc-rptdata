package com.longfor.fsscreportdb.reconciliation.entity;
import java.io.Serializable;


/**
 * <p>
 * 存储过程实体类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-11
 */

public class StoredProcedure implements Serializable {

    private static final long serialVersionUID = 1L;


    private String p_account;
    
    private String p_accounts;

    private String p_date;
    
    private String v_results;
    
    private String p_company;
    
    private String p_flag;
    
    
	public String getP_flag() {
		return p_flag;
	}

	public void setP_flag(String p_flag) {
		this.p_flag = p_flag;
	}

	public String getP_company() {
		return p_company;
	}

	public void setP_company(String p_company) {
		this.p_company = p_company;
	}

	public String getV_results() {
		return v_results;
	}

	public void setV_results(String v_results) {
		this.v_results = v_results;
	}

	public String getP_account() {
		return p_account;
	}

	public void setP_account(String p_account) {
		this.p_account = p_account;
	}

	public String getP_date() {
		return p_date;
	}

	public void setP_date(String p_date) {
		this.p_date = p_date;
	}

	public String getP_accounts() {
		return p_accounts;
	}

	public void setP_accounts(String p_accounts) {
		this.p_accounts = p_accounts;
	}
	
}
