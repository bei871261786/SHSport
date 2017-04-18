package shlottery.gov.cn.lotterycenter.utils;

public class TimeUtil {
	public static String formatTime(int duration){
		StringBuffer sb = new StringBuffer();
		if(duration<60){//小于60秒，单位则为秒
			sb.append(duration).append("秒");
		}else{
			if(duration<3600){//小于1个小时，单位为分钟
				sb.append(duration/60).append("分钟");
			}else{ //大于1小时，单位为M小时N分钟
				int hour=duration/3600;
				int minute=(duration%3600)/60;
				sb.append(hour).append("小时");
				if(minute!=0){
					sb.append(minute).append("分钟");
				}
			}
		}
		return sb.toString();
	}
}
