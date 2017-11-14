package com.guoenbo.library.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author leeandy007
 * @Desc: 自定义工具类
 * @version :
 * 
 */
public class CustomUtil {

	 /**
     * 计算某天星期几
     * 基姆拉尔森计算公式
         W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
         d 天
         m 月
         y 年
       	在公式中d表示日期中的日数，m表示月份数，y表示年数。
		注意：在公式中有个与其他公式不同的地方：
		把一月和二月看成是上一年的十三月和十四月，例：如果是2004-1-10则换算成：2003-13-10来代入公式计算。
     * @param y 年
     * @param m 月
     * @param d 日
     * @return
     */
    public static String getChineseWeek(int y, int m, int d)
    {
         if(m==1) {m=13;y--;}
         if(m==2) {m=14;y--;}
         int week=(d+2*m+3*(m+1)/5+y+y/4-y/100+y/400)%7;
         String weekstr="";
         switch(week)
         {
             case 0: weekstr="星期一"; break;
             case 1: weekstr="星期二"; break;
             case 2: weekstr="星期三"; break;
             case 3: weekstr="星期四"; break;
             case 4: weekstr="星期五"; break;
             case 5: weekstr="星期六"; break;
             case 6: weekstr="星期日"; break;
          }
          return weekstr; 
     }
    
	
	/**
	 * 判断字符串是否数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 返回当前应用程序版本号
	 */
	public static int getAppVersionCode(Context context) {
		int versionCode = 0;
		try {
			PackageManager manage = context.getPackageManager();
			PackageInfo info = manage.getPackageInfo(context.getPackageName(), 0);
			versionCode = info.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionCode;
	}
	
	/**
	 * 返回手机型号
	 * @return
	 * @throws Exception
	 */
	public static String getPhoneModel(){
		String type=null;
		try {
			Class<Build> build_class = Build.class;
			Field field2 = build_class.getField("MODEL");
			if(Build.MODEL==null) {
				type = (String) field2.get(new Build());
			}
			else {
				type= Build.MODEL;
			}
		} catch (Exception e) {
		
		}
		type = type.replace(" ", "");
		return StringUtil.isEmpty(type) ? "未知型号" : type;
	}
	
	/**
	 * 判断sdcard是否存在
	 * @return
	 */
	public static boolean sdCardExist() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	

	
	/**
	 * 缩放照片
	 * @param bitmap
	 * @param minSize
	 * @return
	 */
	public static Bitmap ResizeBitmap(Bitmap bitmap, int minSize) {
	     int width = bitmap.getWidth();
	     int height = bitmap.getHeight();
	     float temp = ((float) height) / ((float) width);
	     
	     float newHeight, newWidth;
	     if(temp<1){
	    	 newHeight = minSize; 
	    	 newWidth = (int) (minSize / temp);
	     }else if(temp>1){
	    	 newWidth = minSize; 
	    	 newHeight = (int) (minSize * temp);
	     }else{
	    	 newHeight = minSize;
	    	 newWidth = minSize;
	     }
	     
	     float scaleWidth = ((float) newWidth) / width;
	     float scaleHeight = ((float) newHeight) / height;
	     
	     Matrix matrix = new Matrix();
	     matrix.postScale(scaleWidth, scaleHeight);
	     Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
	     
	     bitmap.recycle();
	     return resizedBitmap;
	}
	

	
	/**
	 * 删除目录和文件
	 * 
	 * @param fi
	 * @throws IOException
	 */
	public static void deleteDirectory(File fi){
		if ((null != fi) && (fi.isDirectory() || fi.isFile())) {
			// 如果fi是文件直接删除
			if (fi.isFile()) {
				fi.delete();
			} else { // 如果是目录，获取该目录下的所有文件和文件夹
				File[] files = fi.listFiles();
				int sz = files.length;
				for (int i = 0; i < sz; i++) {
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		} 
	}
	
	
	/**
	 * 拆分String,以xx号分隔，并封装成List<Integer>
	 */
	public static List<Integer> getIntegerToList(String split, String value) {
		if (value != null && !value.trim().equals("")) {
			List<Integer> list = new ArrayList<Integer>();
			if (value.split(split) == null) {
				list.add(Integer.parseInt(value));
			} else {
				String[] values = value.split(split);
				for (String s : values) {
					list.add(Integer.parseInt(s));
				}
			}
			return list;
		}
		return null;
	}

	/**
	 * 判断特殊字符
	 * @param pInput
	 * @return
	 */
	public static boolean isSpecialChar(String pInput){
        if(pInput == null){
            return false;
        }
        String regEx = "[a-zA-Z0-9]+";
        return pInput.matches(regEx);
    }
	
	// 将阿拉伯数字转换成中文或英文 status 0 表示中文 1表示英文
	public static String getWeek(int day_of_week, int status) {
		String week = "";
		switch (day_of_week) {
		case 0:
			week = status == 0 ? "星期日" : "Sunday";
			break;
		case 1:
			week = status == 0 ? "星期一" : "Monday";
			break;
		case 2:
			week = status == 0 ? "星期二" : "Tuesday";
			break;
		case 3:
			week = status == 0 ? "星期三" : "Wednesday";
			break;
		case 4:
			week = status == 0 ? "星期四" : "Thursday";
			break;
		case 5:
			week = status == 0 ? "星期五" : "Friday";
			break;
		case 6:
			week = status == 0 ? "星期六" : "Saturday";
			break;
		}
		return week;
	}


	/**
	 * 拆分String,以xx号分隔，并封装成ArrayList 不使用超类List，以减少内存开销
	 */
	public static ArrayList<String> getStringToList(String split, String value) {
		if (value != null && !value.trim().equals("")) {
			ArrayList<String> list = new ArrayList<String>();
			if (value.split(split) == null) {
				list.add(value);
			} else {
				String[] values = value.split(split);
				for (String s : values) {
					list.add(s);
				}
			}
			return list;
		}
		return null;
	}

	/**
	 * 拆分Collection,以xx号分隔，并封装成制定分隔符的String
	 */
	public static String getCollectionToString(String split,
                                               Collection<String> collection) {
		String result = "";
		if (collection != null && !collection.isEmpty()) {
			Iterator<String> it = collection.iterator();
			while (it.hasNext()) {
				String s = it.next();
				result = result + split + s;
			}
			result = result.substring(1);
		}
		return result;
	}
	
	/**
	 * 拆分Collection,以xx号分隔，并封装成制定分隔符的String
	 */
	public static String getCollectionToStringByInteger(String split,
                                                        Collection<Integer> collection) {
		String result = "";
		if (collection != null && !collection.isEmpty()) {
			Iterator<Integer> it = collection.iterator();
			while (it.hasNext()) {
				String s = it.next()+"";
				result = result + split + s;
			}
			result = result.substring(1);
		}
		return result;
	}
	
	
	public static String LeftPad_Tow_Zero(int str) {
		DecimalFormat format = new DecimalFormat("00");
		return format.format(str);

	}

	public static boolean isNotEmpty(String text) {
		if (!TextUtils.isEmpty(text)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断List是否存在值
	 * @param list
	 * @return
	 */
	public static boolean isNotEmptyList(List<?> list){
		if(null != list && !list.isEmpty()){
			return true;
		}
		return false;
	}

	/**
	 * 验证手机格式
	 * 电信号段:133/153/180/181/189/177；              1  3578   01379
	 * 联通号段:130/131/132/155/156/185/186/145/176；  1  34578  01256
	 * 移动号段：134/135/136/137/138/139/150/151/152/157/158/159/182/183/184/187/188/147/178。
	 * <p/>
	 * 13      0123456789
	 * 14       57
	 * 15       012356789
	 * 17       678
	 * 18       0123456789
	 *
	 * @param phoneNum
	 * @return
	 */
	public static boolean isMobilePhone(String phoneNum) {
		if (isNotEmpty(phoneNum)) {
			//11位；
			Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[0,1,2,3,5,6,7,8,9])|(17[0-9])|(18[0-9]))\\d{8}$");
			boolean m = p.matcher(phoneNum).matches();
			return m;
		}
		return false;
	}

	/**
	 * 验证手机格式
	 */
	public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、   159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
		String num = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(number)) {
			return false;
		} else {
			//matches():字符串是否在给定的正则表达式匹配
			return number.matches(num);
		}
	}

	/**
	 * 判断是否为邮箱地址
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {

		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

		Pattern p = Pattern.compile(str);

		Matcher m = p.matcher(email);

		return m.matches();

	}
	
}
