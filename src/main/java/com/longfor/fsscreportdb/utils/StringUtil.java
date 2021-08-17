package com.longfor.fsscreportdb.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * 修改审批状态为汉字
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-10
 */
public class StringUtil {


    /**
     * 获取上个月的年月
     *
     * @return
     */
    public static String getYestMonth() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return dateFormat.format(calendar.getTime());
    }


    /**
     * 获取带单引号的字符串
     * 例：'1','2'
     *
     * @return
     */
    public static String getListStringIds(String id) {
        StringBuilder ids = new StringBuilder();
        List<String> list = getStringList(id);
        for (int i = 0; i < list.size(); i++) {

            if (i == list.size() - 1) {

                ids.append("'" + list.get(i) + "'");
            } else {

                ids.append("'" + list.get(i) + "',");
            }
        }
        return ids.toString();
    }

    public static List<String> getStringList(String id) {

        List<String> accounts = new ArrayList<String>();
        if (id != null && !"".equals(id)) {
            String all = id.replaceAll("'", "");
            String[] split = all.split(",");
            for (int i = 0; i < split.length; i++) {

                accounts.add(split[i]);
            }
        }
        return accounts;
    }


    public static BigDecimal getTwoDecimal(BigDecimal big) {
        DecimalFormat df = new DecimalFormat("#.00");
        return new BigDecimal(df.format(big));
    }

    /**
     * 如果是1号，则取上个月的数据，不为1号则取参数时间到1号之间数据
     *
     * @return
     * @throws ParseException
     */
    public static Map<String, Date> getBetweenEnd() throws ParseException {

        Map<String, Date> map = new HashMap<String, Date>();

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);


        if (day > 1) {
            Date endTime = calendar.getTime();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date startTime = calendar.getTime();
            map.put("startTime", startTime);
            map.put("endTime", endTime);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            Date endTime = calendar.getTime();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date startTime = calendar.getTime();
            map.put("startTime", startTime);
            map.put("endTime", endTime);
        }

        return map;
    }

    /**
     * @param args
     */
    public static Map<String, String> getBeforeThree() {

        Map<String, String> returnMap = new HashMap<String, String>();

        Calendar a = Calendar.getInstance();
        a.add(Calendar.MONTH, -3);

        String beginYear = String.valueOf(a.get(Calendar.YEAR));
        int beginMonth = a.get(Calendar.MONTH);
        beginMonth = beginMonth + 1;
        String strBegingMonth = String.valueOf(beginMonth);
        if (beginMonth < 10) {
            strBegingMonth = "0" + strBegingMonth;
        }
        returnMap.put("beginYear", beginYear);
        returnMap.put("beginMonth", strBegingMonth);

        a = Calendar.getInstance();
        a.add(Calendar.MONTH, -1);

        String endYear = String.valueOf(a.get(Calendar.YEAR));
        int endMonth = a.get(Calendar.MONTH);
        endMonth = endMonth + 1;
        String strendMonth = String.valueOf(endMonth);
        if (endMonth < 10) {
            strendMonth = "0" + strendMonth;
        }

        returnMap.put("endYear", endYear);
        returnMap.put("endMonth", strendMonth);

        return returnMap;
    }


    /**
     * 月度锁板
     **/
    public static boolean isSesionLockMonth() {

        Calendar a = Calendar.getInstance();
        int day = a.get(Calendar.DAY_OF_MONTH);
        if (day == 1) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * 每年01-01、04-01、07-01、10-01对上季度数据锁板
     **/
    public static boolean isSesionLock() {

        Calendar a = Calendar.getInstance();
        int month = a.get(Calendar.MONTH);
        int day = a.get(Calendar.DAY_OF_MONTH);

        if (0 == month || 3 == month || 6 == month || 9 == month) {
            if (day == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String getYMDstr() {
        StringBuilder sb = new StringBuilder();
        Calendar a = Calendar.getInstance();
        a.add(Calendar.DAY_OF_MONTH, -1);
        int year = a.get(Calendar.YEAR);
        int month = a.get(Calendar.MONTH);
        month = month + 1;
        int day = a.get(Calendar.DAY_OF_MONTH);

        sb.append(year);
        sb.append("-");
        if (month < 10) {
            sb.append("0");
            sb.append(month);
        } else {
            sb.append(month);
        }
        sb.append("-");
        if (day < 10) {
            sb.append("0");
            sb.append(day);
        } else {
            sb.append(day);
        }

        System.out.println(sb.toString());
        return sb.toString();

    }

    /**
     * 当前月份和
     * 当前月份之前所有月份取得
     */
    public static List<String> getBeforeMonth() {
        List<String> resultList = new ArrayList<String>();
        Calendar a = Calendar.getInstance();
        int month = a.get(Calendar.MONTH);

        for (int i = 1; i <= month; i++) {
            String temp = "";
            if (i < 10) {
                temp = temp + "0";
                temp = temp + i;
            } else {
                temp = temp + i;
            }
            resultList.add(temp);
        }
        return resultList;
    }


    public static String getYesYear() {
        Calendar a = Calendar.getInstance();
        a.add(Calendar.MONTH, -1);
        int year = a.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    public static String getYesMonth() {
        Calendar a = Calendar.getInstance();
        a.add(Calendar.MONTH, -1);
        int month = a.get(Calendar.MONTH);
        month = month + 1;
        String temp = "";

        if (month < 10) {
            temp = temp + "0";
            temp = temp + month;
        } else {
            temp = temp + month;
        }
        return temp;
    }

    public static String getDay() {

        Calendar a = Calendar.getInstance();
        int day = a.get(Calendar.DAY_OF_MONTH);

        return String.valueOf(day);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static List<String> getListId(String id) {

        List<String> accounts = new ArrayList<String>();
        if (id != null && !"".equals(id)) {
            String all = id.replaceAll("'", "");
            String[] split = all.split(",");
            for (int i = 0; i < split.length; i++) {
                accounts.add(split[i]);
            }
        }
        return accounts;
    }

    public static List<String> getAreas(String area) {

        List<String> accounts = new ArrayList<String>();
        if (area != null && !"".equals(area)) {
            String all = area.replaceAll("'", "");
            String[] split = all.split(",");
            for (int i = 0; i < split.length; i++) {
                accounts.add("'" + split[i] + "'");
            }
        }
        return accounts;
    }

    /**
     * 当前日期取得年月   yyyy mm  如果是1号，则取上个月
     *
     * @return
     */
    public static JSONObject getNewDate() {

        JSONObject json = new JSONObject();
        String newMonth = "";

        Calendar a = Calendar.getInstance();
        int day = a.get(Calendar.DATE);
        int month = a.get(Calendar.MONTH);

        if (day == 1) {
            a.add(Calendar.MONTH, -1);
        }

        int year = a.get(Calendar.YEAR);
        month = a.get(Calendar.MONTH) + 1;

        if (month < 10) {

            newMonth = "0" + String.valueOf(month);
        } else {
            newMonth = String.valueOf(month);
        }

        json.put("month", newMonth);
        json.put("year", year);
        return json;
    }

    /**
     * 获取昨天的日期
     *
     * @return
     */
    public static String getYesterday() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, -24);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 获取今天的日期
     *
     * @return
     */
    public static String getToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 获取半年前的日期
     *
     * @return
     */
    public static String getHalfAYearAgo() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);// 月份减6
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 日期格式字符串转日期 时分秒
     *
     * @return
     * @throws ParseException
     */
    public static Date getDateConversionSfm(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse(date);
        return parse;
    }

    /**
     * 日期格式字符串转日期
     *
     * @return
     * @throws ParseException
     */
    public static Date getDateConversion(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(date);
        return parse;
    }

    /**
     * 日期格式字符串转日期2
     *
     * @return
     * @throws ParseException
     */
    public static Date getDateConversion2(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date parse = sdf.parse(date);
        return parse;
    }

    /**
     * 获得当年的时间
     *
     * @return
     */
    public static String getYear() {
        Calendar a = Calendar.getInstance();
        a.add(Calendar.MONTH, -1);
        int year = a.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    /**
     * 获得上个月的数据
     *
     * @return
     */
    public static String getLastMonth() {
        Calendar a = Calendar.getInstance();
        a.add(Calendar.MONTH, -1);
        int month = a.get(Calendar.MONTH);
        month = month + 1;
        String temp = "";

        if (month < 10) {
            temp = temp + "0";
            temp = temp + month;
        } else {
            temp = temp + month;
        }
        return temp;
    }

    /**
     * 获取当年的第一天
     *
     * @param year
     * @return
     */
    public static String getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        currCal.clear();
        currCal.set(Calendar.YEAR, currentYear);
        Date currYearFirst = currCal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(currYearFirst);
    }


    public static Map<String, List<String>> getBeforeThreeYear() {
        Map<String, List<String>> result = new HashMap<String, List<String>>();

        Calendar currCal = Calendar.getInstance();
        int day = currCal.get(Calendar.DAY_OF_MONTH);
        int month = currCal.get(Calendar.MONTH);
        if (day < 8) {
            month = month - 1;
        }
        currCal.add(Calendar.MONTH, -36);
        int year = currCal.get(Calendar.YEAR);

        List<String> yearList = new ArrayList<String>();
        List<String> monthList = new ArrayList<String>();

        year = year - 1;
        for (int i = 0; i < 4; i++) {
            year = year + 1;

            for (int j = 0; j < 12; j++) {
                if (i == 0 && j < month) {
                    continue;
                }
                if (i == 3 && j >= month) {
                    break;
                } else {
                    if (j + 1 < 10) {
                        monthList.add("0" + String.valueOf(j + 1));
                    } else {
                        monthList.add(String.valueOf(j + 1));
                    }
                }
                yearList.add(String.valueOf(year));
            }
        }

        result.put("yearList", yearList);
        result.put("monthList", monthList);

        return result;
    }

    public static Map<String, List<String>> getBefore() {
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        Calendar currCal = Calendar.getInstance();
        int day = currCal.get(Calendar.DAY_OF_MONTH);
        int month = currCal.get(Calendar.MONTH);
        if (day < 8) {
            month = month - 1;
        }
        int year = currCal.get(Calendar.YEAR);
        List<String> yearList = new ArrayList<String>();
        List<String> monthList = new ArrayList<String>();
        yearList.add(String.valueOf(year));

        if (month < 10) {
            monthList.add("0" + String.valueOf(month));
        } else {
            monthList.add(String.valueOf(month));
        }
        result.put("yearList", yearList);
        result.put("monthList", monthList);

        return result;
    }

    /**
     * 得到上一年至上个月的年月
     *
     * @return
     */
    public static Map<String, List<String>> getOldYearMonth() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
        List<String> years = new ArrayList<String>();
        List<String> months = new ArrayList<String>();

        String format = df.format(new Date());
        String[] split = format.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);

        for (int i = 1; i <= 12; i++) {
            years.add(year - 1 + "");
            if (i < 10) {
                months.add("0" + i);
            } else {
                months.add(i + "");
            }
        }

        for (int i = 1; i < month; i++) {
            years.add(year + "");
            if (i < 10) {
                months.add("0" + i);
            } else {
                months.add(i + "");
            }
        }
        map.put("year", years);
        map.put("month", months);
        return map;
    }

	/**
	 * 获取上一个月的最后一天
	 *
	 * @return
	 */
    public static String getLastDayOfLastMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH,0);
		String lastDay = format.format(cale.getTime());
		return lastDay;
	}

}
