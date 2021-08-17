package com.longfor.fsscreportdb.Thread.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.csvreader.CsvReader;
import com.longfor.fsscreportdb.ods.nc.entity.Auxiliarybalance;
import com.longfor.fsscreportdb.ods.nc.entity.Auxiliarybalancedetails;
import com.longfor.fsscreportdb.ods.nc.service.IAuxiliarybalanceService;
import com.longfor.fsscreportdb.ods.nc.service.IAuxiliarybalancedetailsService;


@Component("NcThread3")
@Scope("prototype")
public class NcThread3 extends Thread{
	
	
	private String AuxiliaryBalance;
	private String AuxiliaryBalanceDetails;
	
	
	public String getAuxiliaryBalance() {
		return AuxiliaryBalance;
	}


	public void setAuxiliaryBalance(String auxiliaryBalance) {
		AuxiliaryBalance = auxiliaryBalance;
	}


	public String getAuxiliaryBalanceDetails() {
		return AuxiliaryBalanceDetails;
	}


	public void setAuxiliaryBalanceDetails(String auxiliaryBalanceDetails) {
		AuxiliaryBalanceDetails = auxiliaryBalanceDetails;
	}


	@Autowired
	private IAuxiliarybalanceService auxiliarybalanceService;
	
	@Autowired
	private IAuxiliarybalancedetailsService auxiliarybalanceDetailsService;
	

	@Override
    public void run() {
		saveCsvByAuxiliaryBalance();
    	saveCsvByAuxiliaryBalanceDetails();
    }
	
	void saveCsvByAuxiliaryBalance() {
		Auxiliarybalance auxiliarybalance = new  Auxiliarybalance();
		CsvReader reader = null;
		List<Auxiliarybalance> list = new ArrayList<Auxiliarybalance>();
        
        try {
        	
             // reader = new CsvReader("E:/log/123123785721761781_AuxiliaryBalance.csv");
             reader = new CsvReader(AuxiliaryBalance);

             // 读取表头
             reader.readHeaders();
             // 逐条读取记录，直至读完
             while (reader.readRecord()) {
             	
             	System.err.println("------------Auxiliarybalance表开始写入cvs文件-----------");
             	auxiliarybalance.setAccassitemcode(reader.get("ACCASSITEMCODE"));
             	auxiliarybalance.setAccountcode(reader.get("ACCOUNTCODE"));
             	auxiliarybalance.setAccountcodes(reader.get("ACCOUNTCODES"));
             	auxiliarybalance.setAccountingbookcode(reader.get("ACCOUNTINGBOOKCODE"));
             	auxiliarybalance.setAccountingbookcodes(reader.get("ACCOUNTINGBOOKCODES"));
             	auxiliarybalance.setAccountingbookname(reader.get("ACCOUNTINGBOOKNAME"));
             	auxiliarybalance.setAccountname(reader.get("ACCOUNTNAME"));
             	auxiliarybalance.setBatchernumber(reader.get("BATCHERNUMBER"));
             	auxiliarybalance.setBeginmonth(reader.get("BEGINMONTH"));
             	auxiliarybalance.setBeginyear(reader.get("BEGINYEAR"));
             	auxiliarybalance.setChecktypecode1(reader.get("CHECKTYPECODE1"));
             	auxiliarybalance.setChecktypecode2(reader.get("CHECKTYPECODE2"));
             	auxiliarybalance.setChecktypecode3(reader.get("CHECKTYPECODE3"));
             	auxiliarybalance.setChecktypecode4(reader.get("CHECKTYPECODE4"));
             	auxiliarybalance.setChecktypecode5(reader.get("CHECKTYPECODE5"));
             	auxiliarybalance.setChecktypecode6(reader.get("CHECKTYPECODE6"));
             	auxiliarybalance.setChecktypecode7(reader.get("CHECKTYPECODE7"));
             	auxiliarybalance.setChecktypecode8(reader.get("CHECKTYPECODE8"));
             	auxiliarybalance.setChecktypecode9(reader.get("CHECKTYPECODE9"));
             	auxiliarybalance.setChecktypename1(reader.get("CHECKTYPENAME1"));
             	auxiliarybalance.setChecktypename2(reader.get("CHECKTYPENAME2"));
             	auxiliarybalance.setChecktypename3(reader.get("CHECKTYPENAME3"));
             	auxiliarybalance.setChecktypename4(reader.get("CHECKTYPENAME4"));
             	auxiliarybalance.setChecktypename5(reader.get("CHECKTYPENAME5"));
             	auxiliarybalance.setChecktypename6(reader.get("CHECKTYPENAME6"));
             	auxiliarybalance.setChecktypename7(reader.get("CHECKTYPENAME7"));
             	auxiliarybalance.setChecktypename8(reader.get("CHECKTYPENAME8"));
             	auxiliarybalance.setChecktypename9(reader.get("CHECKTYPENAME9"));
             	auxiliarybalance.setCheckvaluecode1(reader.get("CHECKVALUECODE1"));
             	auxiliarybalance.setCheckvaluecode2(reader.get("CHECKVALUECODE2"));
             	auxiliarybalance.setCheckvaluecode3(reader.get("CHECKVALUECODE3"));
             	auxiliarybalance.setCheckvaluecode4(reader.get("CHECKVALUECODE4"));
             	auxiliarybalance.setCheckvaluecode5(reader.get("CHECKVALUECODE5"));
             	auxiliarybalance.setCheckvaluecode6(reader.get("CHECKVALUECODE6"));
             	auxiliarybalance.setCheckvaluecode7(reader.get("CHECKVALUECODE7"));
             	auxiliarybalance.setCheckvaluecode8(reader.get("CHECKVALUECODE8"));
             	auxiliarybalance.setCheckvaluecode9(reader.get("CHECKVALUECODE9"));
             	auxiliarybalance.setCheckvaluename1(reader.get("CHECKVALUENAME1"));
             	auxiliarybalance.setCheckvaluename2(reader.get("CHECKVALUENAME2"));
             	auxiliarybalance.setCheckvaluename3(reader.get("CHECKVALUENAME3"));
             	auxiliarybalance.setCheckvaluename4(reader.get("CHECKVALUENAME4"));
             	auxiliarybalance.setCheckvaluename5(reader.get("CHECKVALUENAME5"));
             	auxiliarybalance.setCheckvaluename6(reader.get("CHECKVALUENAME6"));
             	auxiliarybalance.setCheckvaluename7(reader.get("CHECKVALUENAME7"));
             	auxiliarybalance.setCheckvaluename8(reader.get("CHECKVALUENAME8"));
             	auxiliarybalance.setCheckvaluename9(reader.get("CHECKVALUENAME9"));
             	auxiliarybalance.setCreditaccumamount(reader.get("CREDITACCUMAMOUNT"));
             	auxiliarybalance.setCreditamount(reader.get("CREDITAMOUNT"));
             	auxiliarybalance.setDebitaccumamount(reader.get("DEBITACCUMAMOUNT"));
             	auxiliarybalance.setDebitamount(reader.get("DEBITAMOUNT"));
             	auxiliarybalance.setDr(reader.get("DR"));
             	auxiliarybalance.setEndmonth(reader.get("ENDMONTH"));
             	auxiliarybalance.setEndyear(reader.get("ENDYEAR"));
             	auxiliarybalance.setId(reader.get("ID"));
             	auxiliarybalance.setOrgcode(reader.get("ORGCODE"));
             	auxiliarybalance.setQcamount(reader.get("QCAMOUNT"));
             	auxiliarybalance.setQcfx(reader.get("QCFX"));
             	auxiliarybalance.setQmamount(reader.get("QMAMOUNT"));
             	auxiliarybalance.setQmfx(reader.get("QMFX"));
             	auxiliarybalance.setTs(reader.get("TS"));
             	System.err.println(auxiliarybalance.toString());
             	list.add(auxiliarybalance);
             	
             	if(list.size()==1000) {
             		boolean saveByCvs = auxiliarybalanceService.saveByCvs(list);
             		System.err.println("Auxiliarybalance表cvs保存状态："+saveByCvs);
                     list.clear();
             	} 
            }
            if(list.size()>0){
         		boolean saveByCvs = auxiliarybalanceService.saveByCvs(list);
         		System.err.println("Auxiliarybalance表cvs保存状态："+saveByCvs);
         	}
            System.err.println("Auxiliarybalance表cvs文件读取完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
	}
	
	void saveCsvByAuxiliaryBalanceDetails(){
		
		Auxiliarybalancedetails details = new Auxiliarybalancedetails();
		CsvReader reader = null;
		List<Auxiliarybalancedetails> list = new ArrayList<Auxiliarybalancedetails>();
        
        try {
        	
             // reader = new CsvReader("E:/log/123123785721761781_AuxiliaryBalance.csv");
             reader = new CsvReader(AuxiliaryBalanceDetails);

             // 读取表头
             reader.readHeaders();

             // 逐条读取记录，直至读完
             while (reader.readRecord()) {
             	
             	System.err.println("------------Auxiliarybalancedetails表开始写入cvs文件-----------");
             	details.setDr(reader.get("DR")); 
        		details.setTs(reader.get("TS")); 
        		details.setId(reader.get("ID")); 
        		details.setYear(reader.get("YEAR")); 
        		details.setMonth(reader.get("MONTH")); 
        		details.setDay(reader.get("DAY")); 
        		details.setAccountname(reader.get("ACCOUNTNAME")); 
        		details.setAccountcode(reader.get("ACCOUNTCODE")); 
        		details.setVouchernum(reader.get("VOUCHERNUM")); 
        		details.setNote(reader.get("NOTE")); 
        		details.setAdverseaccountname(reader.get("ADVERSEACCOUNTNAME")); 
        		details.setAdverseaccountcode(reader.get("ADVERSEACCOUNTCODE")); 
        		details.setDebitamount(reader.get("DEBITAMOUNT")); 
        		details.setCreditamount(reader.get("CREDITAMOUNT")); 
        		details.setDirection(reader.get("DIRECTION")); 
        		details.setAmount(reader.get("AMOUNT")); 
        		details.setOrgcode(reader.get("ORGCODE")); 
        		details.setBeginyear(reader.get("BEGINYEAR")); 
        		details.setBeginmonth(reader.get("BEGINMONTH")); 
        		details.setEndyear(reader.get("ENDYEAR")); 
        		details.setEndmonth(reader.get("ENDMONTH")); 
        		details.setChecktypecode1(reader.get("CHECKTYPECODE1")); 
        		details.setChecktypename1(reader.get("CHECKTYPENAME1")); 
        		details.setCheckvaluecode1(reader.get("CHECKVALUECODE1")); 
        		details.setCheckvaluename1(reader.get("CHECKVALUENAME1")); 
        		details.setChecktypecode2(reader.get("CHECKTYPECODE2")); 
        		details.setChecktypename2(reader.get("CHECKTYPENAME2")); 
        		details.setCheckvaluecode2(reader.get("CHECKVALUECODE2")); 
        		details.setCheckvaluename2(reader.get("CHECKVALUENAME2")); 
        		details.setChecktypecode3(reader.get("CHECKTYPECODE3")); 
        		details.setChecktypename3(reader.get("CHECKTYPENAME3")); 
        		details.setCheckvaluecode3(reader.get("CHECKVALUECODE3")); 
        		details.setCheckvaluename3(reader.get("CHECKVALUENAME3")); 
        		details.setChecktypecode4(reader.get("CHECKTYPECODE4")); 
        		details.setChecktypename4(reader.get("CHECKTYPENAME4")); 
        		details.setCheckvaluecode4(reader.get("CHECKVALUECODE4")); 
        		details.setCheckvaluename4(reader.get("CHECKVALUENAME4")); 
        		details.setChecktypecode5(reader.get("CHECKTYPECODE5")); 
        		details.setChecktypename5(reader.get("CHECKTYPENAME5")); 
        		details.setCheckvaluecode5(reader.get("CHECKVALUECODE5")); 
        		details.setCheckvaluename5(reader.get("CHECKVALUENAME5")); 
        		details.setChecktypecode6(reader.get("CHECKTYPECODE6")); 
        		details.setChecktypename6(reader.get("CHECKTYPENAME6")); 
        		details.setCheckvaluecode6(reader.get("CHECKVALUECODE6")); 
        		details.setCheckvaluename6(reader.get("CHECKVALUENAME6")); 
        		details.setChecktypecode7(reader.get("CHECKTYPECODE7")); 
        		details.setChecktypename7(reader.get("CHECKTYPENAME7")); 
        		details.setCheckvaluecode7(reader.get("CHECKVALUECODE7")); 
        		details.setCheckvaluename7(reader.get("CHECKVALUENAME7")); 
        		details.setChecktypecode8(reader.get("CHECKTYPECODE8")); 
        		details.setChecktypename8(reader.get("CHECKTYPENAME8")); 
        		details.setCheckvaluecode8(reader.get("CHECKVALUECODE8")); 
        		details.setCheckvaluename8(reader.get("CHECKVALUENAME8")); 
        		details.setChecktypecode9(reader.get("CHECKTYPECODE9")); 
        		details.setChecktypename9(reader.get("CHECKTYPENAME9")); 
        		details.setCheckvaluecode9(reader.get("CHECKVALUECODE9")); 
        		details.setCheckvaluename9(reader.get("checkvaluename9")); 
        		details.setBatchernumber(reader.get("CHECKVALUENAME9")); 
        		details.setFid(reader.get("FID"));
             	System.err.println(details.toString());
             	list.add(details);
             	
             	if(list.size()==1000) {
             		boolean saveByCvs = auxiliarybalanceDetailsService.saveByCvs(list);
             		System.err.println("Auxiliarybalancedetails表cvs保存状态："+saveByCvs);
                     list.clear();
             	} 
            }
            if(list.size()>0){
         		boolean saveByCvs = auxiliarybalanceDetailsService.saveByCvs(list);
         		System.err.println("Auxiliarybalancedetails表cvs保存状态："+saveByCvs);
         	}
            System.err.println("Auxiliarybalancedetails表cvs文件读取完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
		
	}
	
}
