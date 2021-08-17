package ETLJobHandaler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.oa.entity.DwZdsyRole;
import com.longfor.fsscreportdb.oa.entity.DwZdsyRoleRelation;
import com.longfor.fsscreportdb.oa.service.IDwZdsyRoleRelationService;
import com.longfor.fsscreportdb.oa.service.IDwZdsyRoleService;
import com.longfor.fsscreportdb.ods.service.IOdsZdGxptrepemployeeService;
import com.longfor.fsscreportdb.ods.service.IOdsZdNcglsubjectbalanceService;
import com.longfor.fsscreportdb.ods.service.IOdsZdNcviufolhgxService;
import com.longfor.fsscreportdb.ods.service.IOdsZdSwglqsService;
import com.longfor.fsscreportdb.ods.service.IOdsZdSwglqtssmxService;
import com.longfor.fsscreportdb.ods.service.IOdsZdSwglyhsnssbbmxService;
import com.longfor.fsscreportdb.ods.service.IOdsZdSwglzzsfb1Service;
import com.longfor.fsscreportdb.ods.service.IOdsZdSwglzzsfb2Service;
import com.longfor.fsscreportdb.ods.service.IOdsZdSwglzzsfb3Service;
import com.longfor.fsscreportdb.ods.service.IOdsZdSwglzzsfb4Service;
import com.longfor.fsscreportdb.ods.service.IOdsZdSwglzzszbmxService;
import com.longfor.fsscreportdb.utils.CommandLineUtils;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.longfor.fsscreportdb.utils.PushOAMessage;
import com.longfor.fsscreportdb.utils.StringUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 重点税源定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "DDSYXxlJobHandler")
@Component
@Slf4j
public class DDSYXxlJobHandler extends IJobHandler {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Value("${kettel.host1}")
	String ketelHost1;

	@Value("${kettel.port}")
	String ketelPort;

	@Value("${kettel.job.path}")
	String ketelJobPath;

	
	@Autowired
	private IDwZdsyRoleRelationService dwZdsyRoleRelationService;
	
	@Autowired
	private IDwZdsyRoleService dwZdsyRoleService;
	
	@Autowired
	private IOdsZdNcglsubjectbalanceService odsZdNcglsubjectbalanceService;

	@Autowired
	private IOdsZdNcviufolhgxService odsZdNcviufolhgxService;

	@Autowired
	private IOdsZdSwglqsService odsZdSwglqsService;

	@Autowired
	private IOdsZdSwglqtssmxService odsZdSwglqtssmxService;

	@Autowired
	private IOdsZdSwglyhsnssbbmxService odsZdSwglyhsnssbbmxService;

	@Autowired
	private IOdsZdSwglzzsfb1Service odsZdSwglzzsfb1Service;

	@Autowired
	private IOdsZdSwglzzsfb2Service odsZdSwglzzsfb2Service;

	@Autowired
	private IOdsZdSwglzzsfb3Service odsZdSwglzzsfb3Service;

	@Autowired
	private IOdsZdSwglzzsfb4Service odsZdSwglzzsfb4Service;

	@Autowired
	private IOdsZdSwglzzszbmxService odsZdSwglzzszbmxService;

	//@Autowired
	//private IOdsNcBalanceDetailService odsNcBalanceDetailService;

	@Autowired
	private IOdsZdGxptrepemployeeService odsZdGxptrepemployeeService;

	@Autowired
	private CommonDao dao;

	@Override
	public ReturnT<String> execute(String param) throws Exception {
		JSONObject result = null;

		log.info("重点税源处理开始");

		log.info("删除处理1开始");
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ODS_ZD_SWGLYHSNSSBBMX");
		dao.delete(sb.toString());
		log.info("删除处理1结束");

		log.info("删除处理2开始");
		sb = new StringBuilder();
		sb.append("delete from ODS_ZD_SWGLZZSFB1");
		dao.delete(sb.toString());

		log.info("删除处理3开始");
		sb = new StringBuilder();
		sb.append("delete from ODS_ZD_SWGLZZSFB2");
		dao.delete(sb.toString());

		log.info("删除处理4开始");
		sb = new StringBuilder();
		sb.append("delete from ODS_ZD_SWGLZZSFB3");
		dao.delete(sb.toString());

		log.info("删除处理5开始");
		sb = new StringBuilder();
		sb.append("delete from ODS_ZD_SWGLZZSZBMX");
		dao.delete(sb.toString());

		log.info("删除处理6开始");
		sb = new StringBuilder();
		sb.append("delete from ODS_ZD_SWGLQTSSMX");
		dao.delete(sb.toString());

		log.info("删除处理7开始");
		sb = new StringBuilder();
		sb.append("delete from ODS_ZD_SWGLQS");
		dao.delete(sb.toString());

		log.info("删除处理8开始");
		sb = new StringBuilder();
		sb.append("delete from ODS_ZD_NCVIUFOLHGX");
		dao.delete(sb.toString());

		log.info("删除处理9开始");
		sb = new StringBuilder();
		sb.append("delete from ODS_ZD_NCGLSUBJECTBALANCE");
		dao.delete(sb.toString());

		log.info("删除处理10开始");
		sb = new StringBuilder();
		sb.append("delete from ODS_ZD_SWGLZZSFB4");
		dao.delete(sb.toString());

		// 16 印花税
		XxlJobLogger.log("{}", "印花税接口处理开始");
		result = odsZdSwglyhsnssbbmxService.saveList();
		XxlJobLogger.log("印花税同步处理结束{}", String.valueOf(result.get("status")));

		// 17 增值税纳税申报表附列资料（一）
		XxlJobLogger.log("{}", "增值税纳税申报表附列资料（一）接口处理开始");
		result = odsZdSwglzzsfb1Service.saveList();
		XxlJobLogger.log("增值税纳税申报表附列资料（一）同步处理结束{}", String.valueOf(result.get("status")));

		// 18 增值税纳税申报表附列资料（二）
		XxlJobLogger.log("{}", "增值税纳税申报表附列资料（二）接口处理开始");
		result = odsZdSwglzzsfb2Service.saveList();
		XxlJobLogger.log("增值税纳税申报表附列资料（二）同步处理结束{}", String.valueOf(result.get("status")));

		// 19 增值税纳税申报表附列资料（三）
		XxlJobLogger.log("{}", "增值税纳税申报表附列资料（三）接口处理开始");
		result = odsZdSwglzzsfb3Service.saveList();
		XxlJobLogger.log("增值税纳税申报表附列资料（三）同步处理结束{}", String.valueOf(result.get("status")));

		// 19 增值税纳税申报表附列资料（四）
		XxlJobLogger.log("{}", "增值税纳税申报表附列资料（四）接口处理开始");
		result = odsZdSwglzzsfb4Service.saveList();
		XxlJobLogger.log("增值税纳税申报表附列资料（四）同步处理结束{}", String.valueOf(result.get("status")));

		// 20 增值税纳税申报表主表
		XxlJobLogger.log("{}", "增值税纳税申报表主表接口处理开始");
		result = odsZdSwglzzszbmxService.saveList();
		XxlJobLogger.log("增值税纳税申报表主表同步处理结束{}", String.valueOf(result.get("status")));

		// 12 NC科目数据
		XxlJobLogger.log("{}", "NC科目数据接口处理开始");
		JSONObject date = StringUtil.getNewDate();
		String yesMonth = StringUtil.getYesMonth();
		String yearlast = String.valueOf(date.get("year"));
		yearlast = String.valueOf(Integer.parseInt(yearlast) - 1);

		// 去年同期
		result = odsZdNcglsubjectbalanceService.saveList(yearlast, yesMonth);
		result = odsZdNcglsubjectbalanceService.saveList(null,null);
		XxlJobLogger.log("NC科目数据同步处理结束{}", String.valueOf(result.get("status")));

		// 14 企业所得税
		XxlJobLogger.log("{}", "企业所得税接口处理开始");
		result = odsZdSwglqsService.saveList();
		XxlJobLogger.log("企业所得税同步处理结束{}", String.valueOf(result.get("status")));

		// 13 资金计划口径现金流量表
		XxlJobLogger.log("{}", "资金计划口径现金流量表接口处理开始");
		result = odsZdNcviufolhgxService.saveList();
		XxlJobLogger.log("资金计划口径现金流量表同步处理结束{}", String.valueOf(result.get("status")));

		// 15 税务管理其他税收
		XxlJobLogger.log("{}", "税务管理其他税收接口处理开始");
		result = odsZdSwglqtssmxService.saveList();
		XxlJobLogger.log("税务管理其他税收同步处理结束{}", String.valueOf(result.get("status")));

		log.info("共享平台公司职工数表接口处理开始");
		// 共享平台公司职工数表接口处理开始
		XxlJobLogger.log("{}", "共享平台公司职工数表接口处理开始");
		result = odsZdGxptrepemployeeService.saveList();
		XxlJobLogger.log("共享平台公司职工数表处理结束{}", String.valueOf(result.get("status")));
		log.info("共享平台公司职工数表接口处理结束");

		// ============================1,4,7,10 科目：53104==================begin
		sb = new StringBuilder();
		sb = new StringBuilder();
		sb.append("select count(1) from ODS_NC_BALANCE_DETAIL WHERE ACCOUNTCODE='530104' "); // 删除NC系统科目余额表明细
		//Integer count = dao.selectCount(sb.toString());

		sb = new StringBuilder();
		sb.append(" select * from ODS_ACCOUNTING_ORG_AREA ");

		List<Map<String, Object>> orgMapList = dao.selectMaps(sb.toString());
		List<String> orgCodeList = new ArrayList<String>();
		for (int i = 0; i < orgMapList.size(); i++) {
			Map<String, Object> temp = orgMapList.get(i);
			String ORGCODE = (String) temp.get("ORGCODE");
			orgCodeList.add(ORGCODE);
		}

		//Map<String, List<String>> nianYueMap = StringUtil.getOldYearMonth();
	//	List<String> year = nianYueMap.get("year");
		//List<String> month = nianYueMap.get("month");

		List<String> kemuCodeList = new ArrayList<String>();
		kemuCodeList.add("53104");

		// 初期化(53104====科目余额明细)
		/**if (count <= 0) {

			log.info("53104====科目余额明细 初期化 begin");
			for (int i = 0; i < year.size(); i++) {
				String yeart = year.get(i);
				String montht = month.get(i);
				odsNcBalanceDetailService.saveList(orgCodeList, kemuCodeList, yeart, montht, yeart, montht);
			}
			log.info("53104====科目余额明细 初期化 end");

		} else {

			log.info("53104====科目余额明细  上月数据更新 begin");

			sb = new StringBuilder();
			sb.append(" delete from ODS_NC_BALANCE_DETAIL where year = '" + year.get(year.size() - 1)
					+ "'  and  MONTH='" + month.get(month.size() - 1) + "'");
			dao.delete(sb.toString());

			// 上个月 530104 科目余额明细取得

			odsNcBalanceDetailService.saveList(orgCodeList, kemuCodeList);

			log.info("53104====科目余额明细  上月数据更新 begin");
		}*/
		// ============================1,4,7,10 科目：53104==================end

		// ======================add by zhaoxin====================20210312==begin==
		// 三大报表倒入
		// 三大报表数据取得
		try {
			log.info("三大报表数据取得 begin");
			String thisYear = String.valueOf(date.get("year"));
			sb = new StringBuilder();
			sb.append("java -jar import3BaobiaoOnlineYM.jar ");
			sb.append(thisYear);
			sb.append(" ");
			sb.append(yesMonth);
			CommandLineUtils.exeCmd(sb.toString());
			log.info("三大报表数据取得 end");
		} catch (Exception e) {
			log.error("三大报表数据取得 error", e.toString());
		}
		// ======================add by zhaoxin====================
		// 4 ETL调启
		KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
		kettleUtilRemote.callRemote(ketelHost1, ketelPort, ketelJobPath + "/ZDSY/J_ZDSY_ANALYSE.kjb");

		log.info("重点税源处理完了");

        
        log.info("重点税源oa代办开始推送");
        
        List<DwZdsyRole> dqlist = dwZdsyRoleService.getlistByParameter("1");//标识地区还是平台（1是地区2是平台）
		List<DwZdsyRole> ptlist = dwZdsyRoleService.getlistByParameter("2");//标识地区还是平台（1是地区2是平台）
		
		List<DwZdsyRoleRelation> dwZdsyRoleRelation = new ArrayList<DwZdsyRoleRelation>();
		
		String yearMonth = StringUtil.getYestMonth();
		
		String[] split = yearMonth.split("-");
		
		
		
		yearMonth = split[0]+"年"+split[1]+"月";
		
		String businessType="ZDSY";
		//会计填写平台
		for (int j = 0; j < ptlist.size(); j++) {
			
			
			
			List<String> list = new ArrayList<String>();
			list.add("《企业税收信息表》");
			list.add("《企业财务信息表》");
			Map<String,String> map = new HashMap<String,String>();
			map.put("《企业税收信息表》", "http://fr.longhu.net/webroot/decision/view/report?viewlet=/%E8%B4%A2%E5%8A%A1%E5%85%B1%E4%BA%AB%E5%B9%B3%E5%8F%B0/The_key_tax_sources/Form73.frm");//平台
	        map.put("《企业财务信息表》", "http://fr.longhu.net/webroot/decision/view/report?viewlet=/%E8%B4%A2%E5%8A%A1%E5%85%B1%E4%BA%AB%E5%B9%B3%E5%8F%B0/The_key_tax_sources/Form72.frm");//平台
			
			DwZdsyRole role = ptlist.get(j);
			
			
			for (int i = 0; i < list.size(); i++) {
				StringBuilder title = new StringBuilder("");
				title.append("【待办】请填写【");
				title.append(role.getAccountsName());
				title.append("】");
				title.append(yearMonth);
				title.append(list.get(i));
				String uuid = StringUtil.getUUID();
				
				String url = map.get(list.get(i));
				
				JSONObject json = JSONObject.parseObject(new String(PushOAMessage.doPostJson(role.getOaId(), uuid, title.toString(),businessType, url)));
				
				String msg = json.getString("msg");
				String code = json.getString("code");
				if ("success".equals(msg) && "0".equals(code)) {
					
					DwZdsyRoleRelation relation = new DwZdsyRoleRelation();
					relation.setPlatform(role.getPlatform());
					relation.setOaId(role.getOaId());
					relation.setUuid(uuid);
					dwZdsyRoleRelation.add(relation);
					//log.info("推送平台oa待办消息成功:{}" , role.getOaId());
				} else {

					log.error("推送平台oa代办消息失败:{}" , role.getOaId());
				}
				
			}
			
		}
		boolean saveBatch = dwZdsyRoleRelationService.saveBatch(dwZdsyRoleRelation);
		
		if (saveBatch) {

			log.info("保存平台oa代办消息成功！");
		} else {

			log.error("保存平台oa代办消息失败!");
		}
		
		//经理填写地区
		for (int i = 0; i < dqlist.size(); i++) {
			
			
			List<String> list = new ArrayList<String>();
			
			
			if(split[1].equals("2") || split[1].equals("5") ||split[1].equals("8") ||split[1].equals("11")) {
				
				list.add("《企业景气调查问卷》");//2 5 8 11
			}
			
			list.add("《企业税收信息表》");
			Map<String,String> map = new HashMap<String,String>();
			map.put("《企业景气调查问卷》", "http://fr.longhu.net/webroot/decision/view/report?viewlet=/%E8%B4%A2%E5%8A%A1%E5%85%B1%E4%BA%AB%E5%B9%B3%E5%8F%B0/The_key_tax_sources/Form75.frm");//地区
	        map.put("《企业税收信息表》",  "http://fr.longhu.net/webroot/decision/view/report?viewlet=/%E8%B4%A2%E5%8A%A1%E5%85%B1%E4%BA%AB%E5%B9%B3%E5%8F%B0/The_key_tax_sources/Form76.frm");//地区
			
			
			DwZdsyRole role = dqlist.get(i);
			
			
			for (int j = 0; j < list.size(); j++) {
				
				StringBuilder title = new StringBuilder("");
				title.append("【待办】请填写【");
				title.append(role.getAccountsName());
				title.append("】");
				
				title.append(yearMonth);
				title.append(list.get(j));
				String uuid = StringUtil.getUUID();
				
				String url = map.get(list.get(j));
				
				JSONObject json = JSONObject.parseObject(new String(PushOAMessage.doPostJson(role.getOaId(), uuid, title.toString(),businessType, url)));
				
				String msg = json.getString("msg");
				String code = json.getString("code");
				if ("success".equals(msg) && "0".equals(code)) {
					
					DwZdsyRoleRelation relation = new DwZdsyRoleRelation();
					relation.setArea(role.getArea());
					relation.setOaId(role.getOaId());
					relation.setUuid(uuid);
					dwZdsyRoleRelation.add(relation);
					//log.info("推送地区oa待办消息成功:{}" , role.getOaId());
				} else {

					log.error("推送地区oa待办消息失败:{}" , role.getOaId());
				}
				
			}
			
			
		}
		
		saveBatch = dwZdsyRoleRelationService.saveBatch(dwZdsyRoleRelation);
		
		if (saveBatch) {

			log.info("保存地区oa代办消息成功！");
		} else {

			log.error("保存地区oa代办消息失败!");
		}
		
        return SUCCESS;
    }

}
