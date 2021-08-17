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
 * 统计报表NC科目表
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-06
 */
@TableName("ODS_HZ_TJBBNCKM")
@KeySequence("ODS_HZTJBBNCKM")
public class OdsHzTjbbnckm implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 公司名称
     */
    @TableField("gsmc")
    private String gsmc;

    /**
     * 科目代码
     */
    @TableField("km_code")
    private String km_code;

    /**
     * 科目名称
     */
    @TableField("km_name")
    private String km_name;

    /**
     * 年
     */
    @TableField("yearv")
    private String yearv;

    /**
     * 月
     */
    @TableField("periodv")
    private String periodv;

    /**
     * 上年末结余资金
     */
    @TableField("balance_last_y")
    private BigDecimal balance_last_y;

    /**
     * 银行贷款
     */
    @TableField("bank_loan")
    private BigDecimal bank_loan;

    /**
     * 本年各项应付款合计
     */
    @TableField("payments_y")
    private BigDecimal payments_y;

    /**
     * 上年末结余资金_其中：工程款
     */
    @TableField("payments_y_gc")
    private BigDecimal payments_y_gc;

    /**
     * 本年土地成交价款
     */
    @TableField("land_transaction_price")
    private BigDecimal land_transaction_price;

    /**
     * 在岗职工
     */
    @TableField("worker_employees")
    private BigDecimal worker_employees;

    /**
     * 劳务派遣人员
     */
    @TableField("dispatch_personnel")
    private BigDecimal dispatch_personnel;

    /**
     * 年初存货
     */
    @TableField("inventory")
    private BigDecimal inventory;

    /**
     * 资产合计
     */
    @TableField("assets_total")
    private BigDecimal assets_total;

    /**
     * 负债合计
     */
    @TableField("total_liabilities")
    private BigDecimal total_liabilities;

    /**
     * 流动资产合计
     */
    @TableField("total_current_assets")
    private BigDecimal total_current_assets;

    /**
     * 货币资金
     */
    @TableField("monetary_fund")
    private BigDecimal monetary_fund;

    /**
     * 应收账款
     */
    @TableField("accounts_receivable")
    private BigDecimal accounts_receivable;

    /**
     * 存货
     */
    @TableField("inventory_y")
    private BigDecimal inventory_y;

    /**
     * 可供出售金融资产
     */
    @TableField("financial_assets_available")
    private BigDecimal financial_assets_available;

    /**
     * 持有至到期投资
     */
    @TableField("maturity_investments")
    private BigDecimal maturity_investments;

    /**
     * 长期股权投资
     */
    @TableField("equity_investment")
    private BigDecimal equity_investment;

    /**
     * 固定资产原价
     */
    @TableField("original_cost")
    private BigDecimal original_cost;

    /**
     * 固定资产原价_房屋和构筑物
     */
    @TableField("original_cost_fw")
    private BigDecimal original_cost_fw;

    /**
     * 固定资产原价_机器设备
     */
    @TableField("original_cost_jq")
    private BigDecimal original_cost_jq;

    /**
     * 固定资产原价_运输工具
     */
    @TableField("original_cost_ys")
    private BigDecimal original_cost_ys;

    /**
     * 固定资产原价_电子设备
     */
    @TableField("original_cost_dz")
    private BigDecimal original_cost_dz;

    /**
     * 累计折旧
     */
    @TableField("accumulated_depreciation")
    private BigDecimal accumulated_depreciation;

    /**
     * 本年折旧
     */
    @TableField("accumulated_depreciation_y")
    private BigDecimal accumulated_depreciation_y;

    /**
     * 固定资产净值
     */
    @TableField("net_fixed_assets")
    private BigDecimal net_fixed_assets;

    /**
     * 在建工程
     */
    @TableField("projects_under_construction")
    private BigDecimal projects_under_construction;

    /**
     * 无形资产
     */
    @TableField("intangible_assets")
    private BigDecimal intangible_assets;

    /**
     * 无形资产_土地使用权
     */
    @TableField("intangible_assets_td")
    private BigDecimal intangible_assets_td;

    /**
     * 无形资产_软件使用权
     */
    @TableField("intangible_assets_rj")
    private BigDecimal intangible_assets_rj;

    /**
     * 流动负债
     */
    @TableField("current_liabilities")
    private BigDecimal current_liabilities;

    /**
     * 应付账款
     */
    @TableField("accounts_payable")
    private BigDecimal accounts_payable;

    /**
     * 非流动负债
     */
    @TableField("no_current_liabilities")
    private BigDecimal no_current_liabilities;

    /**
     * 所有者权益合计
     */
    @TableField("total_owner_equity")
    private BigDecimal total_owner_equity;

    /**
     * 所有者权益合计_实收资本
     */
    @TableField("total_owner_equity_ss")
    private BigDecimal total_owner_equity_ss;

    /**
     * 营业收入
     */
    @TableField("operating_income")
    private BigDecimal operating_income;

    /**
     * 主营业务收入
     */
    @TableField("operating_income_zy")
    private BigDecimal operating_income_zy;

    /**
     * 其他收入
     */
    @TableField("operating_income_qt")
    private BigDecimal operating_income_qt;

    /**
     * 营业成本
     */
    @TableField("operating_cost")
    private BigDecimal operating_cost;

    /**
     * 主营业务成本
     */
    @TableField("operating_cost_zy")
    private BigDecimal operating_cost_zy;

    /**
     * 税金及附加
     */
    @TableField("taxes_surcharges")
    private BigDecimal taxes_surcharges;

    /**
     * 销售费用
     */
    @TableField("cost_sales")
    private BigDecimal cost_sales;

    /**
     * 管理费用
     */
    @TableField("management_fees")
    private BigDecimal management_fees;

    /**
     * 财务费用
     */
    @TableField("finance_charges")
    private BigDecimal finance_charges;

    /**
     * 财务费用利息收入
     */
    @TableField("finance_charges_lxsr")
    private BigDecimal finance_charges_lxsr;

    /**
     * 财务费用利息支出
     */
    @TableField("finance_charges_lxzc")
    private BigDecimal finance_charges_lxzc;

    /**
     * 公允价值变动收益
     */
    @TableField("income_from_changes")
    private BigDecimal income_from_changes;

    /**
     * 投资收益
     */
    @TableField("income_from_investment")
    private BigDecimal income_from_investment;

    /**
     * 资产处置收益
     */
    @TableField("income_from_asset")
    private BigDecimal income_from_asset;

    /**
     * 其他收益
     */
    @TableField("other_income")
    private BigDecimal other_income;

    /**
     * 营业利润
     */
    @TableField("operating_profit")
    private BigDecimal operating_profit;

    /**
     * 营业外收入
     */
    @TableField("nonbusiness_income")
    private BigDecimal nonbusiness_income;

    /**
     * 营业外支出
     */
    @TableField("nonbusiness_expenditure")
    private BigDecimal nonbusiness_expenditure;

    /**
     * 利润总额
     */
    @TableField("profit_total")
    private BigDecimal profit_total;

    /**
     * 所得税费用
     */
    @TableField("income_tax_expense")
    private BigDecimal income_tax_expense;

    /**
     * 应付职工薪酬
     */
    @TableField("payroll_payable")
    private BigDecimal payroll_payable;

    /**
     * 应交增值税
     */
    @TableField("vat_payable")
    private BigDecimal vat_payable;

    @TableField("op_time")
    private String op_time;

    /**
     * 执行批次
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

	public String getGsmc() {
		return gsmc;
	}

	public void setGsmc(String gsmc) {
		this.gsmc = gsmc;
	}

	public String getKm_code() {
		return km_code;
	}

	public void setKm_code(String km_code) {
		this.km_code = km_code;
	}

	public String getKm_name() {
		return km_name;
	}

	public void setKm_name(String km_name) {
		this.km_name = km_name;
	}

	public String getYearv() {
		return yearv;
	}

	public void setYearv(String yearv) {
		this.yearv = yearv;
	}

	public String getPeriodv() {
		return periodv;
	}

	public void setPeriodv(String periodv) {
		this.periodv = periodv;
	}

	public BigDecimal getBalance_last_y() {
		return balance_last_y;
	}

	public void setBalance_last_y(BigDecimal balance_last_y) {
		this.balance_last_y =   StringUtil.getTwoDecimal(balance_last_y);
	}

	public BigDecimal getBank_loan() {
		return bank_loan;
	}

	public void setBank_loan(BigDecimal bank_loan) {
		this.bank_loan =   StringUtil.getTwoDecimal(bank_loan);
	}

	public BigDecimal getPayments_y() {
		return payments_y;
	}

	public void setPayments_y(BigDecimal payments_y) {
		this.payments_y =   StringUtil.getTwoDecimal(payments_y);
	}

	public BigDecimal getPayments_y_gc() {
		return payments_y_gc;
	}

	public void setPayments_y_gc(BigDecimal payments_y_gc) {
		this.payments_y_gc =   StringUtil.getTwoDecimal(payments_y_gc);
	}

	public BigDecimal getLand_transaction_price() {
		return land_transaction_price;
	}

	public void setLand_transaction_price(BigDecimal land_transaction_price) {
		this.land_transaction_price =   StringUtil.getTwoDecimal(land_transaction_price);
	}

	public BigDecimal getWorker_employees() {
		return worker_employees;
	}

	public void setWorker_employees(BigDecimal worker_employees) {
		this.worker_employees =   StringUtil.getTwoDecimal(worker_employees);
	}

	public BigDecimal getDispatch_personnel() {
		return dispatch_personnel;
	}

	public void setDispatch_personnel(BigDecimal dispatch_personnel) {
		this.dispatch_personnel =   StringUtil.getTwoDecimal(dispatch_personnel);
	}

	public BigDecimal getInventory() {
		return inventory;
	}

	public void setInventory(BigDecimal inventory) {
		this.inventory =   StringUtil.getTwoDecimal(inventory);
	}

	public BigDecimal getAssets_total() {
		return assets_total;
	}

	public void setAssets_total(BigDecimal assets_total) {
		this.assets_total =   StringUtil.getTwoDecimal(assets_total);
	}

	public BigDecimal getTotal_liabilities() {
		return total_liabilities;
	}

	public void setTotal_liabilities(BigDecimal total_liabilities) {
		this.total_liabilities =   StringUtil.getTwoDecimal(total_liabilities);
	}

	public BigDecimal getTotal_current_assets() {
		return total_current_assets;
	}

	public void setTotal_current_assets(BigDecimal total_current_assets) {
		this.total_current_assets =   StringUtil.getTwoDecimal(total_current_assets);
	}

	public BigDecimal getMonetary_fund() {
		return monetary_fund;
	}

	public void setMonetary_fund(BigDecimal monetary_fund) {
		this.monetary_fund =   StringUtil.getTwoDecimal(monetary_fund);
	}

	public BigDecimal getAccounts_receivable() {
		return accounts_receivable;
	}

	public void setAccounts_receivable(BigDecimal accounts_receivable) {
		this.accounts_receivable =   StringUtil.getTwoDecimal(accounts_receivable);
	}

	public BigDecimal getInventory_y() {
		return inventory_y;
	}

	public void setInventory_y(BigDecimal inventory_y) {
		this.inventory_y =   StringUtil.getTwoDecimal(inventory_y);
	}

	public BigDecimal getFinancial_assets_available() {
		return financial_assets_available;
	}

	public void setFinancial_assets_available(BigDecimal financial_assets_available) {
		this.financial_assets_available =   StringUtil.getTwoDecimal(financial_assets_available);
	}

	public BigDecimal getMaturity_investments() {
		return maturity_investments;
	}

	public void setMaturity_investments(BigDecimal maturity_investments) {
		this.maturity_investments =   StringUtil.getTwoDecimal(maturity_investments);
	}

	public BigDecimal getEquity_investment() {
		return equity_investment;
	}

	public void setEquity_investment(BigDecimal equity_investment) {
		this.equity_investment =   StringUtil.getTwoDecimal(equity_investment);
	}

	public BigDecimal getOriginal_cost() {
		return original_cost;
	}

	public void setOriginal_cost(BigDecimal original_cost) {
		this.original_cost =   StringUtil.getTwoDecimal(original_cost);
	}

	public BigDecimal getOriginal_cost_fw() {
		return original_cost_fw;
	}

	public void setOriginal_cost_fw(BigDecimal original_cost_fw) {
		this.original_cost_fw =   StringUtil.getTwoDecimal(original_cost_fw);
	}

	public BigDecimal getOriginal_cost_jq() {
		return original_cost_jq;
	}

	public void setOriginal_cost_jq(BigDecimal original_cost_jq) {
		this.original_cost_jq =   StringUtil.getTwoDecimal(original_cost_jq);
	}

	public BigDecimal getOriginal_cost_ys() {
		return original_cost_ys;
	}

	public void setOriginal_cost_ys(BigDecimal original_cost_ys) {
		this.original_cost_ys =   StringUtil.getTwoDecimal(original_cost_ys);
	}

	public BigDecimal getOriginal_cost_dz() {
		return original_cost_dz;
	}

	public void setOriginal_cost_dz(BigDecimal original_cost_dz) {
		this.original_cost_dz =   StringUtil.getTwoDecimal(original_cost_dz);
	}

	public BigDecimal getAccumulated_depreciation() {
		return accumulated_depreciation;
	}

	public void setAccumulated_depreciation(BigDecimal accumulated_depreciation) {
		this.accumulated_depreciation =   StringUtil.getTwoDecimal(accumulated_depreciation);
	}

	public BigDecimal getAccumulated_depreciation_y() {
		return accumulated_depreciation_y;
	}

	public void setAccumulated_depreciation_y(BigDecimal accumulated_depreciation_y) {
		this.accumulated_depreciation_y =   StringUtil.getTwoDecimal(accumulated_depreciation_y);
	}

	public BigDecimal getNet_fixed_assets() {
		return net_fixed_assets;
	}

	public void setNet_fixed_assets(BigDecimal net_fixed_assets) {
		this.net_fixed_assets =   StringUtil.getTwoDecimal(net_fixed_assets);
	}

	public BigDecimal getProjects_under_construction() {
		return projects_under_construction;
	}

	public void setProjects_under_construction(BigDecimal projects_under_construction) {
		this.projects_under_construction =   StringUtil.getTwoDecimal(projects_under_construction);
	}

	public BigDecimal getIntangible_assets() {
		return intangible_assets;
	}

	public void setIntangible_assets(BigDecimal intangible_assets) {
		this.intangible_assets =   StringUtil.getTwoDecimal(intangible_assets);
	}

	public BigDecimal getIntangible_assets_td() {
		return intangible_assets_td;
	}

	public void setIntangible_assets_td(BigDecimal intangible_assets_td) {
		this.intangible_assets_td =   StringUtil.getTwoDecimal(intangible_assets_td);
	}

	public BigDecimal getIntangible_assets_rj() {
		return intangible_assets_rj;
	}

	public void setIntangible_assets_rj(BigDecimal intangible_assets_rj) {
		this.intangible_assets_rj =   StringUtil.getTwoDecimal(intangible_assets_rj);
	}

	public BigDecimal getCurrent_liabilities() {
		return current_liabilities;
	}

	public void setCurrent_liabilities(BigDecimal current_liabilities) {
		this.current_liabilities =   StringUtil.getTwoDecimal(current_liabilities);
	}

	public BigDecimal getAccounts_payable() {
		return accounts_payable;
	}

	public void setAccounts_payable(BigDecimal accounts_payable) {
		this.accounts_payable =   StringUtil.getTwoDecimal(accounts_payable);
	}

	public BigDecimal getNo_current_liabilities() {
		return no_current_liabilities;
	}

	public void setNo_current_liabilities(BigDecimal no_current_liabilities) {
		this.no_current_liabilities =   StringUtil.getTwoDecimal(no_current_liabilities);
	}

	public BigDecimal getTotal_owner_equity() {
		return total_owner_equity;
	}

	public void setTotal_owner_equity(BigDecimal total_owner_equity) {
		this.total_owner_equity =   StringUtil.getTwoDecimal(total_owner_equity);
	}

	public BigDecimal getTotal_owner_equity_ss() {
		return total_owner_equity_ss;
	}

	public void setTotal_owner_equity_ss(BigDecimal total_owner_equity_ss) {
		this.total_owner_equity_ss =   StringUtil.getTwoDecimal(total_owner_equity_ss);
	}

	public BigDecimal getOperating_income() {
		return operating_income;
	}

	public void setOperating_income(BigDecimal operating_income) {
		this.operating_income =   StringUtil.getTwoDecimal(operating_income);
	}

	public BigDecimal getOperating_income_zy() {
		return operating_income_zy;
	}

	public void setOperating_income_zy(BigDecimal operating_income_zy) {
		this.operating_income_zy =   StringUtil.getTwoDecimal(operating_income_zy);
	}

	public BigDecimal getOperating_income_qt() {
		return operating_income_qt;
	}

	public void setOperating_income_qt(BigDecimal operating_income_qt) {
		this.operating_income_qt =   StringUtil.getTwoDecimal(operating_income_qt);
	}

	public BigDecimal getOperating_cost() {
		return operating_cost;
	}

	public void setOperating_cost(BigDecimal operating_cost) {
		this.operating_cost =   StringUtil.getTwoDecimal(operating_cost);
	}

	public BigDecimal getOperating_cost_zy() {
		return operating_cost_zy;
	}

	public void setOperating_cost_zy(BigDecimal operating_cost_zy) {
		this.operating_cost_zy =   StringUtil.getTwoDecimal(operating_cost_zy);
	}

	public BigDecimal getTaxes_surcharges() {
		return taxes_surcharges;
	}

	public void setTaxes_surcharges(BigDecimal taxes_surcharges) {
		this.taxes_surcharges =   StringUtil.getTwoDecimal(taxes_surcharges);
	}

	public BigDecimal getCost_sales() {
		return cost_sales;
	}

	public void setCost_sales(BigDecimal cost_sales) {
		this.cost_sales =   StringUtil.getTwoDecimal(cost_sales);
	}

	public BigDecimal getManagement_fees() {
		return management_fees;
	}

	public void setManagement_fees(BigDecimal management_fees) {
		this.management_fees =   StringUtil.getTwoDecimal(management_fees);
	}

	public BigDecimal getFinance_charges() {
		return finance_charges;
	}

	public void setFinance_charges(BigDecimal finance_charges) {
		this.finance_charges =   StringUtil.getTwoDecimal(finance_charges);
	}

	public BigDecimal getFinance_charges_lxsr() {
		return finance_charges_lxsr;
	}

	public void setFinance_charges_lxsr(BigDecimal finance_charges_lxsr) {
		this.finance_charges_lxsr =   StringUtil.getTwoDecimal(finance_charges_lxsr);
	}

	public BigDecimal getFinance_charges_lxzc() {
		return finance_charges_lxzc;
	}

	public void setFinance_charges_lxzc(BigDecimal finance_charges_lxzc) {
		this.finance_charges_lxzc =   StringUtil.getTwoDecimal(finance_charges_lxzc);
	}

	public BigDecimal getIncome_from_changes() {
		return income_from_changes;
	}

	public void setIncome_from_changes(BigDecimal income_from_changes) {
		this.income_from_changes =   StringUtil.getTwoDecimal(income_from_changes);
	}

	public BigDecimal getIncome_from_investment() {
		return income_from_investment;
	}

	public void setIncome_from_investment(BigDecimal income_from_investment) {
		this.income_from_investment =   StringUtil.getTwoDecimal(income_from_investment);
	}

	public BigDecimal getIncome_from_asset() {
		return income_from_asset;
	}

	public void setIncome_from_asset(BigDecimal income_from_asset) {
		this.income_from_asset =   StringUtil.getTwoDecimal(income_from_asset);
	}

	public BigDecimal getOther_income() {
		return other_income;
	}

	public void setOther_income(BigDecimal other_income) {
		this.other_income =   StringUtil.getTwoDecimal(other_income);
	}

	public BigDecimal getOperating_profit() {
		return operating_profit;
	}

	public void setOperating_profit(BigDecimal operating_profit) {
		this.operating_profit =   StringUtil.getTwoDecimal(operating_profit);
	}

	public BigDecimal getNonbusiness_income() {
		return nonbusiness_income;
	}

	public void setNonbusiness_income(BigDecimal nonbusiness_income) {
		this.nonbusiness_income =   StringUtil.getTwoDecimal(nonbusiness_income);
	}

	public BigDecimal getNonbusiness_expenditure() {
		return nonbusiness_expenditure;
	}

	public void setNonbusiness_expenditure(BigDecimal nonbusiness_expenditure) {
		this.nonbusiness_expenditure =   StringUtil.getTwoDecimal(nonbusiness_expenditure);
	}

	public BigDecimal getProfit_total() {
		return profit_total;
	}

	public void setProfit_total(BigDecimal profit_total) {
		this.profit_total =   StringUtil.getTwoDecimal(profit_total);
	}

	public BigDecimal getIncome_tax_expense() {
		return income_tax_expense;
	}

	public void setIncome_tax_expense(BigDecimal income_tax_expense) {
		this.income_tax_expense =   StringUtil.getTwoDecimal(income_tax_expense);
	}

	public BigDecimal getPayroll_payable() {
		return payroll_payable;
	}

	public void setPayroll_payable(BigDecimal payroll_payable) {
		this.payroll_payable =   StringUtil.getTwoDecimal(payroll_payable);
	}

	public BigDecimal getVat_payable() {
		return vat_payable;
	}

	public void setVat_payable(BigDecimal vat_payable) {
		this.vat_payable =   StringUtil.getTwoDecimal(vat_payable);
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
