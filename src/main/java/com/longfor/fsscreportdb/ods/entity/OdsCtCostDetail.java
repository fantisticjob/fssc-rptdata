package com.longfor.fsscreportdb.ods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * ods层成本明细
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-08
 */
@TableName("ODS_CT_COST_DETAIL")
@KeySequence(value = "ODS_CBTZ")
public class OdsCtCostDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 年
     */
    private String yearv;

    /**
     * 月
     */
    private String periodv;

    /**
     * 科目
     */
    private String subjcode;

    /**
     * 科目名称
     */
    private String subjname;

    /**
     * 单据编号
     */
    private String djbh;

    /**
     * 摘要内容
     */
    private String explanation;

    /**
     * 来源
     */
    private String laiyuan;

    /**
     * 借方累计发生
     */
    private BigDecimal debitamount;

    /**
     * 分期id
     */
    private String fqguid;

    /**
     * 分期名称
     */
    private String fqname;

    /**
     * 土地费用
     */
    private BigDecimal localdebitamount_td;

    /**
     * 前期工程费
     */
    private BigDecimal localdebitamount_qq;

    /**
     * 建安工程费
     */
    private BigDecimal localdebitamount_ja;

    /**
     * 基础设施费
     */
    private BigDecimal localdebitamount_jc;

    /**
     * 配套设施费
     */
    private BigDecimal localdebitamount_pt;

    /**
     * 环境景观工程费
     */
    private BigDecimal localdebitamount_hj;

    /**
     * 工程相关费
     */
    private BigDecimal localdebitamount_gcxg;

    /**
     * 工程预估成本
     */
    private BigDecimal localdebitamount_gcyg;

    /**
     * 工程后续成本
     */
    private BigDecimal localdebitamount_gchx;

    /**
     * 开发间接费转入
     */
    private BigDecimal localdebitamount_kfjj;

    /**
     * nc取票金额（不含税）
     */
    private BigDecimal qp_amount;

    /**
     * nc取票金额（可抵扣税额）
     */
    private BigDecimal qp_amount_hs;

    /**
     * 凭证
     */
    private String nov;

    /**
     * 核销凭证
     */
    private String hx_nov;

    /**
     * 辅助核算编码
     */
    private String fuzhucode;

    /**
     * 辅助核算名称
     */
    private String fuzhuname;

    /**
     * 贷方累计发生
     */
    private BigDecimal creditamount;

    /**
     * 申请单号
     */
    private String applycode;

    /**
     * 合同类别
     */
    private String httypename;

    /**
     * 合同名称
     */
    private String contractname;

    /**
     * 对方单位
     */
    private String x_dfprovidernames;

    /**
     * 结算状态
     */
    private String jsstate;

    /**
     * 合同范围
     */
    private String x_contractscope;

    /**
     * 合同最新金额（不含税）
     */
    private BigDecimal x_totalamount_nontax;

    /**
     * 成本取票金额（含税）
     */
    private BigDecimal x_amount;

    /**
     * 成本取票金额（不含税）
     */
    private BigDecimal x_amount_nontax;

    /**
     * 成本取票金额（可抵扣税额）
     */
    private BigDecimal x_inputtax;

    /**
     * 成本付款金额
     */
    private BigDecimal applyamount_bz;

    /**
     * 合计
     */
    private BigDecimal x_paymentamount;

    /**
     * 现金
     */
    private BigDecimal x_paymentamount_xj;

    /**
     * 商票
     */
    private BigDecimal x_paymentamount_sp;

    /**
     * 保理
     */
    private BigDecimal x_paymentamount_bl;

    /**
     * 扣罚款
     */
    private BigDecimal x_offsetamount_bz;

    /**
     * op_time
     */
    private Date op_time;

    /**
     * 执行批次
     */
    private String execution_id;

    /**
     * 年月
     */
    private String datekey;

    /**
     * etl时间
     */
    private Date etl_time;

    /**
     * 合同编码
     */
    private String contractcode;

    /**
     * 合同最新金额（含税）
     */
    private BigDecimal x_totalamount_inputtax;
    
    /**
     * 
     */
    private BigDecimal nc_fkamount;
    
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSubjcode() {
		return subjcode;
	}

	public void setSubjcode(String subjcode) {
		this.subjcode = subjcode;
	}

	public String getSubjname() {
		return subjname;
	}

	public void setSubjname(String subjname) {
		this.subjname = subjname;
	}

	public String getDjbh() {
		return djbh;
	}

	public void setDjbh(String djbh) {
		this.djbh = djbh;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getLaiyuan() {
		return laiyuan;
	}

	public void setLaiyuan(String laiyuan) {
		this.laiyuan = laiyuan;
	}

	public BigDecimal getDebitamount() {
		return debitamount;
	}

	public void setDebitamount(BigDecimal debitamount) {
		
		
		this.debitamount = StringUtil.getTwoDecimal(debitamount);
	}

	public String getFqguid() {
		return fqguid;
	}

	public void setFqguid(String fqguid) {
		this.fqguid = fqguid;
	}

	public String getFqname() {
		return fqname;
	}

	public void setFqname(String fqname) {
		this.fqname = fqname;
	}

	public BigDecimal getLocaldebitamount_td() {
		return localdebitamount_td;
	}

	public void setLocaldebitamount_td(BigDecimal localdebitamount_td) {
		
		this.localdebitamount_td = StringUtil.getTwoDecimal(localdebitamount_td);
	}

	public BigDecimal getLocaldebitamount_qq() {
		return localdebitamount_qq;
	}

	public void setLocaldebitamount_qq(BigDecimal localdebitamount_qq) {
		
		this.localdebitamount_qq = StringUtil.getTwoDecimal(localdebitamount_qq);
	}

	public BigDecimal getLocaldebitamount_ja() {
		return localdebitamount_ja;
	}

	public void setLocaldebitamount_ja(BigDecimal localdebitamount_ja) {
		
		this.localdebitamount_ja =StringUtil.getTwoDecimal(localdebitamount_ja);
		
	}

	public BigDecimal getLocaldebitamount_jc() {
		return localdebitamount_jc;
	}

	public void setLocaldebitamount_jc(BigDecimal localdebitamount_jc) {
		
		this.localdebitamount_jc = StringUtil.getTwoDecimal(localdebitamount_jc);
	}

	public BigDecimal getLocaldebitamount_pt() {
		return localdebitamount_pt;
	}

	public void setLocaldebitamount_pt(BigDecimal localdebitamount_pt) {
		
		this.localdebitamount_pt = StringUtil.getTwoDecimal(localdebitamount_pt);
	}

	public BigDecimal getLocaldebitamount_hj() {
		return localdebitamount_hj;
	}

	public void setLocaldebitamount_hj(BigDecimal localdebitamount_hj) {
		
		this.localdebitamount_hj = StringUtil.getTwoDecimal(localdebitamount_hj);
	}

	public BigDecimal getLocaldebitamount_gcxg() {
		return localdebitamount_gcxg;
	}

	public void setLocaldebitamount_gcxg(BigDecimal localdebitamount_gcxg) {
		
		this.localdebitamount_gcxg = StringUtil.getTwoDecimal(localdebitamount_gcxg);
	}

	public BigDecimal getLocaldebitamount_gcyg() {
		return localdebitamount_gcyg;
	}

	public void setLocaldebitamount_gcyg(BigDecimal localdebitamount_gcyg) {
		
		this.localdebitamount_gcyg = StringUtil.getTwoDecimal(localdebitamount_gcyg);
	}

	public BigDecimal getLocaldebitamount_gchx() {
		return localdebitamount_gchx;
	}

	public void setLocaldebitamount_gchx(BigDecimal localdebitamount_gchx) {
		
		this.localdebitamount_gchx = StringUtil.getTwoDecimal(localdebitamount_gchx);
	}

	public BigDecimal getLocaldebitamount_kfjj() {
		return localdebitamount_kfjj;
	}

	public void setLocaldebitamount_kfjj(BigDecimal localdebitamount_kfjj) {
		
		
		this.localdebitamount_kfjj = StringUtil.getTwoDecimal(localdebitamount_kfjj);
	}

	public BigDecimal getQp_amount() {
		return qp_amount;
	}

	public void setQp_amount(BigDecimal qp_amount) {

		
		this.qp_amount = StringUtil.getTwoDecimal(qp_amount);
	}

	public BigDecimal getQp_amount_hs() {
		return qp_amount_hs;
	}

	public void setQp_amount_hs(BigDecimal qp_amount_hs) {
		
		
		this.qp_amount_hs = StringUtil.getTwoDecimal(qp_amount_hs);
	}

	public String getNov() {
		return nov;
	}

	public void setNov(String nov) {
		this.nov = nov;
	}

	public String getHx_nov() {
		return hx_nov;
	}

	public void setHx_nov(String hx_nov) {
		this.hx_nov = hx_nov;
	}

	public String getFuzhucode() {
		return fuzhucode;
	}

	public void setFuzhucode(String fuzhucode) {
		this.fuzhucode = fuzhucode;
	}

	public String getFuzhuname() {
		return fuzhuname;
	}

	public void setFuzhuname(String fuzhuname) {
		this.fuzhuname = fuzhuname;
	}

	public BigDecimal getCreditamount() {
		return creditamount;
	}

	public void setCreditamount(BigDecimal creditamount) {
		
		
		this.creditamount = StringUtil.getTwoDecimal(creditamount);
	}

	public String getApplycode() {
		return applycode;
	}

	public void setApplycode(String applycode) {
		this.applycode = applycode;
	}

	public String getHttypename() {
		return httypename;
	}

	public void setHttypename(String httypename) {
		this.httypename = httypename;
	}

	public String getContractname() {
		return contractname;
	}

	public void setContractname(String contractname) {
		this.contractname = contractname;
	}

	public String getX_dfprovidernames() {
		return x_dfprovidernames;
	}

	public void setX_dfprovidernames(String x_dfprovidernames) {
		this.x_dfprovidernames = x_dfprovidernames;
	}

	public String getJsstate() {
		return jsstate;
	}

	public void setJsstate(String jsstate) {
		this.jsstate = jsstate;
	}

	public String getX_contractscope() {
		return x_contractscope;
	}

	public void setX_contractscope(String x_contractscope) {
		this.x_contractscope = x_contractscope;
	}

	public BigDecimal getX_totalamount_nontax() {
		
		return x_totalamount_nontax;
	}

	public void setX_totalamount_nontax(BigDecimal x_totalamount_nontax) {
		
		
		this.x_totalamount_nontax =StringUtil.getTwoDecimal(x_totalamount_nontax);
	}

	public BigDecimal getX_amount() {
		return x_amount;
	}

	public void setX_amount(BigDecimal x_amount) {
		
		
		this.x_amount = StringUtil.getTwoDecimal(x_amount);
	}

	public BigDecimal getX_amount_nontax() {
		return x_amount_nontax;
	}

	public void setX_amount_nontax(BigDecimal x_amount_nontax) {
		
		
		this.x_amount_nontax = StringUtil.getTwoDecimal(x_amount_nontax);
	}

	public BigDecimal getX_inputtax() {
		return x_inputtax;
	}

	public void setX_inputtax(BigDecimal x_inputtax) {
		
		this.x_inputtax = StringUtil.getTwoDecimal(x_inputtax);
	}

	public BigDecimal getApplyamount_bz() {
		return applyamount_bz;
	}

	public void setApplyamount_bz(BigDecimal applyamount_bz) {
		
		this.applyamount_bz = StringUtil.getTwoDecimal(applyamount_bz);
	}

	public BigDecimal getX_paymentamount() {
		return x_paymentamount;
	}

	public void setX_paymentamount(BigDecimal x_paymentamount) {
		
		
		this.x_paymentamount = StringUtil.getTwoDecimal(x_paymentamount);
	}

	public BigDecimal getX_paymentamount_xj() {
		return x_paymentamount_xj;
	}

	public void setX_paymentamount_xj(BigDecimal x_paymentamount_xj) {
		
		
		this.x_paymentamount_xj = StringUtil.getTwoDecimal(x_paymentamount_xj);
	}

	public BigDecimal getX_paymentamount_sp() {
		return x_paymentamount_sp;
	}

	public void setX_paymentamount_sp(BigDecimal x_paymentamount_sp) {
		
		
		this.x_paymentamount_sp =StringUtil.getTwoDecimal(x_paymentamount_sp);
	}

	public BigDecimal getX_paymentamount_bl() {
		return x_paymentamount_bl;
	}

	public void setX_paymentamount_bl(BigDecimal x_paymentamount_bl) {
		
		this.x_paymentamount_bl = StringUtil.getTwoDecimal(x_paymentamount_bl);
	}

	public BigDecimal getX_offsetamount_bz() {
		return x_offsetamount_bz;
	}

	public void setX_offsetamount_bz(BigDecimal x_offsetamount_bz) {
		
		
		this.x_offsetamount_bz = StringUtil.getTwoDecimal(x_offsetamount_bz);
	}

	public Date getOp_time() {
		return op_time;
	}

	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}

	public String getExecution_id() {
		return execution_id;
	}

	public void setExecution_id(String execution_id) {
		this.execution_id = execution_id;
	}

	public String getDatekey() {
		return datekey;
	}

	public void setDatekey(String datekey) {
		this.datekey = datekey;
	}

	public Date getEtl_time() {
		return etl_time;
	}

	public void setEtl_time(Date etl_time) {
		this.etl_time = etl_time;
	}

	public String getContractcode() {
		return contractcode;
	}

	public void setContractcode(String contractcode) {
		this.contractcode = contractcode;
	}

	public BigDecimal getX_totalamount_inputtax() {
		return x_totalamount_inputtax;
	}

	public void setX_totalamount_inputtax(BigDecimal x_totalamount_inputtax) {
		
		this.x_totalamount_inputtax = StringUtil.getTwoDecimal(x_totalamount_inputtax);
	}

	public BigDecimal getNc_fkamount() {
		return nc_fkamount;
	}

	public void setNc_fkamount(BigDecimal nc_fkamount) {
		this.nc_fkamount = StringUtil.getTwoDecimal(nc_fkamount);
	}
}
