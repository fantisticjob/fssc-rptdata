package com.longfor.fsscreportdb.ods.nc.entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;



@XmlAccessorType(XmlAccessType.FIELD)
public class AuxiliaryBalanceBackVoArray {


    @XmlElement(name = "nc.itf.hk.report.auxiliarybalancebackvo")
    private OdsNcAuxiliaryBalance auxiliaryBalance;

	public OdsNcAuxiliaryBalance getAuxiliaryBalance() {
		return auxiliaryBalance;
	}

	public void setAuxiliaryBalance(OdsNcAuxiliaryBalance auxiliaryBalance) {
		this.auxiliaryBalance = auxiliaryBalance;
	}
}
