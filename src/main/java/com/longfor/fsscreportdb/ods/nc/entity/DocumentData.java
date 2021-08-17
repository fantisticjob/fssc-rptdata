package com.longfor.fsscreportdb.ods.nc.entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentData {

    @XmlElement(name = "nc.itf.hk.report.auxiliarybalancebackvo-array")
    private AuxiliaryBalanceBackVoArray auxiliaryBalanceBackVoArray;

	public AuxiliaryBalanceBackVoArray getAuxiliaryBalanceBackVoArray() {
		return auxiliaryBalanceBackVoArray;
	}

	public void setAuxiliaryBalanceBackVoArray(AuxiliaryBalanceBackVoArray auxiliaryBalanceBackVoArray) {
		this.auxiliaryBalanceBackVoArray = auxiliaryBalanceBackVoArray;
	}
    
}
