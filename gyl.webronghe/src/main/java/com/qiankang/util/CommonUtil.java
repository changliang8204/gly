package com.qiankang.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class CommonUtil {
	 /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.replaceAll("-", ""); 
    } 
	/**
	 * 获取昨日日期字符串 yyyyMMdd
	 * @return
	 */
	public static String getTommorow(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = null;
		Calendar cd = Calendar.getInstance();
		cd.setTime(new Date());
		cd.add(Calendar.DAY_OF_MONTH, -1);
		date = sdf.format(cd.getTime());
		sdf = null;
		return date;
	}
	/**
	 * 获取前日日期字符串 yyyyMMdd
	 * @return
	 */
	public static String getTommorowBefore(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = null;
		Calendar cd = Calendar.getInstance();
		cd.setTime(new Date());
		cd.add(Calendar.DAY_OF_MONTH, -2);
		date = sdf.format(cd.getTime());
		sdf = null;
		return date;
	}
	/**
	 * 获取当日日期字符串 yyyyMMdd
	 * @return
	 */
	public static String getToday(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = null;
		date = sdf.format(new Date());
		sdf = null;
		return date;
	}

	/**
	 * 将时间格式转化
	 * @param source_style 原时间格式
	 * @param time 要转化的时间串
	 * @param target_style 目标时间格式
	 * @return
	 */
	public static String changeTimeStyle(final String source_style, final String time, final String target_style) throws ParseException{
		String db_time = "";
		SimpleDateFormat df = new SimpleDateFormat(source_style);
		Date date = null;
		try{
			date = df.parse(time);
		}catch(ParseException e){
			throw e;
		}
		df = new SimpleDateFormat(target_style);
		db_time = df.format(date);
		return db_time;
	}
	/**
	 * 获取Double四舍五入后的整型数据
	 * @param num
	 * @return
	 */
	public static int getRoundNum(Double num){
		BigDecimal b=new BigDecimal(num.toString());   
        double data = b.setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue();   
        return (int) data;
	}
	/**
	 * 获取四舍五入后的精度数据
	 * @param num
	 * @param scale
	 * @return
	 */
	public static double getScalNum(double num, int scale){
		BigDecimal b = null;
		if(Double.isInfinite(num)){
			b = new BigDecimal(Double.MAX_VALUE);
		}else if(Double.isNaN(num)){
			b = new BigDecimal(0);
		}else{
			b = new BigDecimal(Double.toString(num));
		}
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 向上取整
	 * @param num
	 * @param scale
	 * @return
	 */
	public static int getUpNum(double num){
		BigDecimal b = null;
		if(Double.isInfinite(num)){
			b = new BigDecimal(Double.MAX_VALUE);
		}else if(Double.isNaN(num)){
			b = new BigDecimal(0);
		}else{
			b = new BigDecimal(Double.toString(num));
		}
		return b.setScale(0, RoundingMode.UP).intValue();
	}
	/**
	 * 向下取整
	 * @param num
	 * @param scale
	 * @return
	 */
	public static int getDownNum(double num){
		BigDecimal b = null;
		if(Double.isInfinite(num)){
			b = new BigDecimal(Double.MAX_VALUE);
		}else if(Double.isNaN(num)){
			b = new BigDecimal(0);
		}else{
			b = new BigDecimal(Double.toString(num));
		}
		return b.setScale(0, RoundingMode.DOWN).intValue();
	}
	/**
	 * 计算两个日期值间的间隔天数,包含两头的时间
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	public static int countDaySpace(final Date startDate, final Date endDate){
		int result = 0;
		result = (int)((endDate.getTime() - startDate.getTime())/(1000 * 60 * 60 * 24)) + 1;
		return result;
	}
	
	/**
	 * 计算两个日期值间的间隔天数,包含两头的时间
	 * 
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	public static int countDaySpace(String startDay, String endDay, String style) {
		final Date startDate = Convert2Date(startDay, style);
		final Date endDate = Convert2Date(endDay, style);
		int result = 0;
		result = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
		return result;
	}
	
	public static Date Convert2Date(String date, String style) {
		SimpleDateFormat formatter = new SimpleDateFormat(style);
		String str = date;
		ParsePosition pos = new ParsePosition(0);
		Date dt = formatter.parse(str, pos);
		if (dt != null) {
			return dt;
		} else {
			return null;
		}
	}
	
	/**
	 * 计算两个日期时间间隔的秒数
	 * @param startTime yyyyMMddHHmmss
	 * @param endTime yyyyMMddHHmmss
	 * @return
	 */
	public static long countTimeSpace(final String startTime, final String endTime) throws ParseException{
		long second = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date startDate = sdf.parse(startTime);
		Date endDate = sdf.parse(endTime);
		second = (endDate.getTime() - startDate.getTime()) / 1000;
		if(second >0){
			second = second +1;
		}
		return second;
	}
	/**
	 * 计算当天两个时点(标准格式)间隔的秒数
	 * @param startTime HHmmss
	 * @param endTime HHmmss
	 * @return
	 */
	public static long countDayTimeSpaceStand(String startTime, String endTime) throws ParseException{
		long second = 0;
		if(startTime == null || startTime.trim().equals(""))
			startTime = "000000";
		if(endTime == null || endTime.trim().equals(""))
			endTime = "235959";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date startDate = sdf.parse("20100101"+startTime);
		Date endDate = sdf.parse("20100101"+endTime);
		second = (endDate.getTime() - startDate.getTime()) / 1000;
		if(second >0){
			second = second +1;
		}
		return second;
	}
	/**
	 * 计算两个日期值间的间隔月数，包含两头的时间
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int countMonthSpace(final Date startDate, final Date endDate) {
		int result = 0;
		Calendar startCd = Calendar.getInstance();
		startCd.setTime(startDate);
		Calendar endCd = Calendar.getInstance();
		endCd.setTime(endDate);
		int sYear = startCd.get(Calendar.YEAR);
		int sMonth = startCd.get(Calendar.MONTH);
		int eYear = endCd.get(Calendar.YEAR);
		int eMonth = endCd.get(Calendar.MONTH);
		result = (eYear - sYear) * 12 + (eMonth - sMonth + 1);
		return result;
	}
	/**
	 * 根据输入的月份字符串，返回该月下的天数
	 * @param month yyyyMM
	 * @return
	 */
	public static int countMonthDays(final String month) throws ParseException{
		int days = 30;
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Date date = df.parse(month);
		cd.setTime(date);
		days = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}
	/**
	 * 根据币种格式化金额
	 * @param currencyCode 币种代码
	 * @param amount 金额
	 * @return
	 */
	public static String formatMoney(String currencyCode, double amount) throws IllegalArgumentException{
		String money = "";
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		if(currencyCode.equals("156")){ //人民币
			nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
		}else if(currencyCode.equals("840")){ //美元
			nf = NumberFormat.getCurrencyInstance(Locale.US);
		}else if(currencyCode.equals("826")){ //英镑
			nf = NumberFormat.getCurrencyInstance(Locale.UK);
		}else if(currencyCode.equals("392")){ //日元
			nf = NumberFormat.getCurrencyInstance(Locale.JAPANESE);
		}else if(currencyCode.equals("910")){ //欧元
			nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		}else{
			return "["+currencyCode+"]"+amount;
		}
		money = nf.format(amount);
		return money;
	}
	/**
	 * 根据币种代码获得币种符号
	 * @param currencyCode 币种代码
	 * @param amount 金额
	 * @return
	 */
	public static String getCurrencySymbol(String currencyCode) throws IllegalArgumentException{
		String sympol = "";
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(0);
		if(currencyCode.equals("156")){ //人民币
			nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
		}else if(currencyCode.equals("840")){ //美元
			nf = NumberFormat.getCurrencyInstance(Locale.US);
		}else if(currencyCode.equals("826")){ //英镑
			nf = NumberFormat.getCurrencyInstance(Locale.UK);
		}else if(currencyCode.equals("392")){ //日元
			nf = NumberFormat.getCurrencyInstance(Locale.JAPANESE);
		}else if(currencyCode.equals("910")){ //欧元
			nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		}else{
			return sympol;
		}
		sympol = nf.getCurrency().getSymbol();
		return sympol;
	}
	/**
	 * 获取文件扩展名
	 * @param filename
	 * @return
	 */
	public static String getFileExtension(String filename) { 
		String defExt = "";
        if ((filename != null) && (filename.length() > 0)) { 
            int i = filename.lastIndexOf('.'); 
            if ((i >-1) && (i < (filename.length() - 1))) { 
            	defExt = filename.substring(i + 1); 
            } 
        } 
        return defExt.toLowerCase(); 
    } 

	/**
	 * 判断指定日期是否是周末
	 * 0-周日
	 * 1-一般工作日
	 * 6-周六
	 * @param cd
	 * @param date
	 * @return
	 */
	public static int isWeekend(Calendar cd, Date date){
		if(cd == null)
			cd = Calendar.getInstance();
		cd.setTime(date);
		int tag = cd.get(Calendar.DAY_OF_WEEK);
		if(tag == 7)
			tag = 6;
		else if(tag == 1)
			tag = 0;
		else
			tag = 1;
		return tag;
	}
	/**
	 * 
	 * 获取某日是当月的第几天
	 * 1号是第一天
	 * 2号是第二天
	 * ....
	 * @param day  yyyyMMdd
	 * @return
	 */
	public static int getDayIndexInMonth(String day) throws ParseException{
		int index = 0;
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try{
			Date date = sdf.parse(day);
			cd.setTime(date);
			index = cd.get(Calendar.DAY_OF_MONTH);
		}catch(ParseException e){
			throw e;
		}
		return index;
	}
	/**
	 * 
	 * 获取给定年份最大天数
	 * @param year  yyyy
	 * @return
	 * @throws ParseException
	 */
	public static int getDayOfYear(String year) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = sdf.parse(year);
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		int dayNum = cd.getActualMaximum(Calendar.DAY_OF_YEAR);
		return dayNum;
	}
}
