package com.longfor.fsscreportdb.ods.nc.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.Converter;


/**
 * <p>
 * NC外系统查询辅助余额表
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-12
 */
@TableName("ODS_NC_AUXILIARY_BALANCE_BD")
@KeySequence("ODS_NC_AUXILIARY_BALANCE_BD_S")
@XmlAccessorType(XmlAccessType.FIELD)
public class OdsNcAuxiliaryBalancebd implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;
    /**
     * 账套code
     */
    private String accountingbookcode;

    /**
     * 凭证主键
     */
    private String accountcode;

    /**
     * 制单人
     */
    private String accountname;

    /**
     * 期初方向
     */
    private String qcfx;

    /**
     * 期初余额
     */
    private String qcamount;

    /**
     * 借方发生
     */
    private String debitamount;

    /**
     * 贷方发生
     */
    private String creditamount;

    /**
     * 借方累计
     */
    private String debitaccumamount;

    /**
     * 贷方累计
     */
    private String creditaccumamount;
    
    /**
     * 期末方向
     */
    private String qmfx;

    /**
     * 期末余额
     */
    private String qmamount;

    /**
     * 账套名称
     */
    private String accountingbookname;

    /**
     * 客商code
     */
    private String kscode;

    /**
     * 客商名称
     */
    private String ksname;

    /**
     * 项目code
     */
    private String xmcode;

    /**
     * 项目名称
     */
    private String xmname;

    /**
     * 人员code
     */
    private String rycode;

    /**
     * 人员姓名
     */
    private String ryname;

    /**
     * 单据code
     */
    private String djhcode;

    /**
     * 单据name
     */
    private String djhname;
    
    /**
     * 组织code
     */
    private String orgcode;
    
    /**
     * 开始年
     */
    private String beginyear;
    
    /**
     * 开始月
     */
    private String beginmonth;
    
    /**
     * 结束年
     */
    private String endyear;
    
    /**
     * 结束月
     */
    private String endmonth;

    public String getBeginyear() {
		return beginyear;
	}

	public void setBeginyear(String beginyear) {
		this.beginyear = beginyear;
	}

	public String getBeginmonth() {
		return beginmonth;
	}

	public void setBeginmonth(String beginmonth) {
		this.beginmonth = beginmonth;
	}

	public String getEndyear() {
		return endyear;
	}

	public void setEndyear(String endyear) {
		this.endyear = endyear;
	}

	public String getEndmonth() {
		return endmonth;
	}

	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}



	public String getAccountingbookcode() {
		return accountingbookcode;
	}

	public void setAccountingbookcode(String accountingbookcode) {
		this.accountingbookcode = accountingbookcode;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getQcfx() {
		return qcfx;
	}

	public void setQcfx(String qcfx) {
		this.qcfx = qcfx;
	}

	public String getQcamount() {
		return qcamount;
	}

	public void setQcamount(String qcamount) {
		this.qcamount = qcamount;
	}

	public String getDebitamount() {
		return debitamount;
	}

	public void setDebitamount(String debitamount) {
		this.debitamount = debitamount;
	}

	public String getCreditamount() {
		return creditamount;
	}

	public void setCreditamount(String creditamount) {
		this.creditamount = creditamount;
	}

	public String getDebitaccumamount() {
		return debitaccumamount;
	}

	public void setDebitaccumamount(String debitaccumamount) {
		this.debitaccumamount = debitaccumamount;
	}

	public String getCreditaccumamount() {
		return creditaccumamount;
	}

	public void setCreditaccumamount(String creditaccumamount) {
		this.creditaccumamount = creditaccumamount;
	}

	public String getQmfx() {
		return qmfx;
	}

	public void setQmfx(String qmfx) {
		this.qmfx = qmfx;
	}

	public String getQmamount() {
		return qmamount;
	}

	public void setQmamount(String qmamount) {
		this.qmamount = qmamount;
	}

	public String getAccountingbookname() {
		return accountingbookname;
	}

	public void setAccountingbookname(String accountingbookname) {
		this.accountingbookname = accountingbookname;
	}

	public String getKscode() {
		return kscode;
	}

	public void setKscode(String kscode) {
		this.kscode = kscode;
	}

	public String getKsname() {
		return ksname;
	}

	public void setKsname(String ksname) {
		this.ksname = ksname;
	}

	public String getXmcode() {
		return xmcode;
	}

	public void setXmcode(String xmcode) {
		this.xmcode = xmcode;
	}

	public String getXmname() {
		return xmname;
	}

	public void setXmname(String xmname) {
		this.xmname = xmname;
	}

	public String getRycode() {
		return rycode;
	}

	public void setRycode(String rycode) {
		this.rycode = rycode;
	}

	public String getRyname() {
		return ryname;
	}

	public void setRyname(String ryname) {
		this.ryname = ryname;
	}

	public String getDjhcode() {
		return djhcode;
	}

	public void setDjhcode(String djhcode) {
		this.djhcode = djhcode;
	}

	public String getDjhname() {
		return djhname;
	}

	public void setDjhname(String djhname) {
		this.djhname = djhname;
	}
	
	public Auxiliarybalance convertTo() {
        OrderConverter convert = new OrderConverter();
        return convert.convert(this);
	}

    public OdsNcAuxiliaryBalancebd convertFrom(Auxiliarybalance order) {
        OrderConverter convert = new OrderConverter();
        return convert.reverse().convert(order);
    }
    private static class OrderConverter extends Converter<OdsNcAuxiliaryBalancebd, Auxiliarybalance> {

        @Override
        protected Auxiliarybalance doForward(OdsNcAuxiliaryBalancebd dto) {

            return null;
        }
        //具体的转换内容
        @Override
        protected OdsNcAuxiliaryBalancebd doBackward(Auxiliarybalance aux) {
        	OdsNcAuxiliaryBalancebd dto = new OdsNcAuxiliaryBalancebd();
            dto.setAccountcode(aux.getAccountcode());
            dto.setAccountingbookcode(aux.getAccountingbookcode());
            dto.setAccountingbookname(aux.getAccountingbookname());
            dto.setAccountingbookname(aux.getAccountingbookname());
            dto.setAccountname(aux.getAccountname());
            dto.setBeginmonth(aux.getBeginmonth());
            dto.setBeginyear(aux.getBeginyear());
            dto.setCreditaccumamount(aux.getCreditaccumamount());
            dto.setCreditamount(aux.getCreditamount());
            dto.setDebitaccumamount(aux.getDebitaccumamount());
            dto.setDebitamount(aux.getDebitamount());
            dto.setEndmonth(aux.getEndmonth());
            dto.setEndyear(aux.getEndyear());
            dto.setOrgcode(aux.getOrgcode());
            dto.setQcamount(aux.getQcamount());
            dto.setQcfx(aux.getQcfx());
            dto.setQmamount(aux.getQmamount());
            dto.setQmfx(aux.getQmfx());
            /**
            dto.setDjhcode();
            dto.setDjhname();
            dto.setKscode();
            dto.setKsname();
            dto.setRycode();
            dto.setRyname();
            dto.setXmcode();
            dto.setXmname();*/
            return dto;
        }
    }
	
}
