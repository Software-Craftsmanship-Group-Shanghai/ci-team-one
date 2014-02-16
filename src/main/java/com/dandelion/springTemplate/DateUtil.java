package com.dandelion.springTemplate;

import java.util.Calendar;
import java.util.Date;
/**
 * 时间工具类
 * @author liuzhenguo
 *
 */
public class DateUtil {
	
	public static final int MILLISECOND = 1000;
	public static final int SECOND = 60;
	public static final int MINUTE = 60;
	
	/**
	 * 计算传入的时间和当前时间的间隔，单位为小时
	 * @param time
	 * @return
	 */
	public static float subTime(Date time){
		
		Date currentDate = new Date();
		float interval = currentDate.getTime() - time.getTime();
		float intervalHour = interval/(MILLISECOND * SECOND * MINUTE);
		
		return intervalHour;
		
	}

	/**
	 * 获取当前的date类型的时间
	 * @return
	 */
	public static Date getDate(){
		
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
		
	}
}
