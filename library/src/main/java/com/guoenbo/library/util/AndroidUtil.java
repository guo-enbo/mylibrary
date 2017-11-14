package com.guoenbo.library.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableRow;
import android.widget.TextView;

import com.qiji.fingertipfinancial.bean.photo.FilePhotoBean;
import com.qiji.fingertipfinancial.common.MainApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;


/**
 * @author leeandy007
 * @Desc: 封装android公用工具类
 * @version :
 *
 */
@SuppressWarnings("deprecation")
public class AndroidUtil {

	public static String getActOrFragName(Context context){
		String mActivityName = getClassName(context.getClass());
		return mActivityName;
	}
	/**
	 * 根据Class获取类名
	 * @param cls
	 * @return
	 */
	public static String getClassName(Class<?> cls){
		String clsName = null;
		if(null != cls){
			String fullClsName = cls.getName();
			if(!StringUtil.isEmpty(fullClsName)){
				int postion = fullClsName.lastIndexOf(".");
				clsName = fullClsName.substring(postion+1);
			}
		}

		return clsName;
	}

	/**
	 * 裁剪照片窗口
	 *
	 * @param data
	 * @return
	 */
	public Intent getCropImageIntent(Bitmap data) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setType("image/*");
		intent.putExtra("data", data);
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 128);
		intent.putExtra("outputY", 128);
		intent.putExtra("return-data", true);
		return intent;
	}

	/**
	 *
	 * Item选择的状态图标切换 选中item后，变成删除图标
	 *
	 * @param resId
	 * @param status
	 *            true:删除图标 false:其他图标
	 * @param tv
	 *            图标textview
	 * @param context
	 */
	public static void setIconState(int resId, boolean status, TextView tv, Context context) {
		tv.setClickable(status);
		Drawable dr = context.getResources().getDrawable(resId);
		dr.setBounds(0, 0, dr.getMinimumWidth(), dr.getMinimumHeight());
		tv.setCompoundDrawables(null, null, dr, null);
	}

	/**
	 * 创建一个PopupWindow对象
	 *
	 * @param context
	 * @param view
	 * @return
	 */
	public static PopupWindow getPopupWindow(Context context, View view, int ilayout) {
		View layoutView = LayoutInflater.from(context).inflate(ilayout, null);
		PopupWindow popu = new PopupWindow(layoutView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
		popu.setBackgroundDrawable(new BitmapDrawable());
		return popu;
	}

	/**
	 * 返回主界面底部按钮
	 *
	 * @return
	 */
	public static Button getMainButton(Context context, Object obj) {
		Button button = new Button(context);
		TableRow.LayoutParams lp = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.FILL_PARENT, 1.0f);
		button.setLayoutParams(lp);
		button.setGravity(Gravity.CENTER);
		button.setTextSize(12);
		button.setPadding(5, 5, 5, 5);
		button.setBackgroundColor(Color.TRANSPARENT);
		button.setTextColor(Color.WHITE);
		button.setTag(obj);
		return button;

		// textView.setCompoundDrawablesWithIntrinsicBounds();
		// textView.setText();
	}

	/**
	 * DIP -> PX 转换
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(float dipValue) {
		final float scale = MainApplication.getContext().getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * PX -> DIP 转换
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(float pxValue) {
		final float scale = MainApplication.getContext().getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static Drawable getRoundedBitmap(Context context, float radius, int ResId){
		Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(), ResId);
		return doRoundedBitmap(context, radius, mBitmap);
	}

	public static Drawable getRoundedBitmap(Context context, float radius, Drawable mDrawable){
		Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
		return doRoundedBitmap(context, radius, mBitmap);
	}

	/**
	 * 画圆角图片
	 * @param context
	 * @param radius 圆角半径
	 * @param mBitmap
	 * @return
	 */
	public static Drawable doRoundedBitmap(Context context, float radius, Bitmap mBitmap){
		Bitmap bgBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Config.ARGB_8888);
		Canvas mCanvas = new Canvas(bgBitmap);

		Paint mPaint = new Paint();
		Rect mRect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
		RectF mRectF = new RectF(mRect);

		mPaint.setAntiAlias(true);
		//先绘制圆角矩形
		mCanvas.drawRoundRect(mRectF, radius, radius, mPaint);

		//设置图像的叠加模式 
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		//绘制图像  
		mCanvas.drawBitmap(mBitmap, mRect, mRect, mPaint);
		mBitmap.recycle();

		return new BitmapDrawable(bgBitmap);
	}

	/**
	 * 压缩图片 注重分辨率
	 * @param image
	 * @return
	 */
	public static Bitmap comp(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 65, baos);
		/*if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出	
			baos.reset();//重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
		}*/
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		//现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 1080f;//这里设置高度为800f
		float ww = 720f;//这里设置宽度为480f
		//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;//be=1表示不缩放
		if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置缩放比例
		//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return bitmap;//压缩好比例大小后再进行质量压缩
	}

	/**
	 * 压缩图片 注重质量
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while ( baos.toByteArray().length / 1024>300) {	//循环判断如果压缩后图片是否大于400kb,大于继续压缩	
			baos.reset();//重置baos即清空baos
			image.compress(CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;//每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	/**
	 * 获取视频的缩略图
	 * 先通过ThumbnailUtils来创建一个视频的缩略图，然后再利用ThumbnailUtils来生成指定大小的缩略图。
	 * 如果想要的缩略图的宽和高都小于MICRO_KIND，则类型要使用MICRO_KIND作为kind的值，这样会节省内存。
	 * @param videoPath 视频的路径
	 * @param width 指定输出视频缩略图的宽度
	 * @param height 指定输出视频缩略图的高度度
	 * @param kind 参照MediaStore.Images.Thumbnails类中的常量MINI_KIND和MICRO_KIND。
	 *            其中，MINI_KIND: 512 x 384，MICRO_KIND: 96 x 96
	 * @return 指定大小的视频缩略图
	 */
	public static Bitmap getVideoThumbnail(String videoPath, int width, int height,
                                           int kind) {
		Bitmap bitmap = null;
		// 获取视频的缩略图
		bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}

	/**
	 * 获取系统所有的照片
	 */
	public static HashMap<String, ArrayList<FilePhotoBean>> listAlldir(Context context){
		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		Uri uri = intent.getData();
		HashMap<String, ArrayList<FilePhotoBean>> map=new HashMap<String, ArrayList<FilePhotoBean>>();
		String[] proj ={MediaStore.Images.Media.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media._ID};
		Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
		while(cursor.moveToNext()){
			String path = cursor.getString(0);
			String fileName = cursor.getString(1);
			String id = cursor.getString(2);
			if(map.containsKey(fileName)) {//如果文件夹存在
				if(map.get(fileName)!=null) {
					FilePhotoBean bean = new FilePhotoBean();
					bean.setName(fileName);
					bean.setPath(path);
					bean.setId(id);
					map.get(fileName).add(bean);
				}
			} else {
				int index=path.lastIndexOf("gif");
				if(index == -1) {
					ArrayList<FilePhotoBean> list = new ArrayList<FilePhotoBean>();
					FilePhotoBean bean = new FilePhotoBean();
					bean.setName(fileName);
					bean.setPath(path);
					bean.setId(id);
					list.add(bean);
					map.put(fileName, list);
				}
			}
		}

		return map;
	}

	/**
	 *判断当前应用程序处于前台还是后台
	 * @param context
	 * @return
	 */
	public static boolean isApplicationBroughtToBackground(final Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	public static void setMinHeapSize(float fsize) {
		try {
			Class<?> cls = Class.forName("dalvik.system.VMRuntime");
			Method getRuntime = cls.getMethod("getRuntime");
			Object obj = getRuntime.invoke(null);// obj就是Runtime
			if (obj == null) {
				//System.err.println("obj is null");
			} else {
				//System.out.println(obj.getClass().getName());
				Class<?> runtimeClass = obj.getClass();
				/*Method setMinimumHeapSize = runtimeClass.getMethod(
						"setMinimumHeapSize", long.class);*/
				Method setTargetHeapUtilization = runtimeClass.getMethod(
						"setTargetHeapUtilization", float.class);
				//setMinimumHeapSize.invoke(obj, size);
				setTargetHeapUtilization.invoke(obj, fsize);
			}

		} catch (Exception e) {
		}
	}

	/**
	 * 判断当前网络是否是3G网络
	 *
	 * @param context
	 * @return boolean
	 */
	public static boolean is3G(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}

	/**
	 * 将bitmap保存层文件
	 * */
	public static File saveBitmap2file(Bitmap bmp, String filePath,
                                       String filename) {
		File file = null;
		File fileDir = new File(filePath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		CompressFormat format = CompressFormat.JPEG;
		OutputStream stream = null;
		try {
			stream = new FileOutputStream(filePath + filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (bmp.compress(format, 100, stream)) {
			file = new File(filePath + filename);
		}
		return file;
	}

	/**
	 * MD5单向加密，32位，用于加密密码，因为明文密码在信道中传输不安全，明文保存在本地也不安全
	 *
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i] & 0xff) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static void LaunchApp(Context context, String packageName) {
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
			Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
			resolveIntent.setPackage(pi.packageName);
			List<ResolveInfo> apps = pm.queryIntentActivities(resolveIntent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
			for (ResolveInfo ri: apps) {
				String className = ri.activityInfo.name;
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_LAUNCHER);
				ComponentName cn = new ComponentName(packageName, className);
				intent.setComponent(cn);
				context.startActivity(intent);
			}
		} catch (PackageManager.NameNotFoundException e) {
			ToastUtil.getInstance().Short("未安装该应用");
		}
	}

	public static List<ResolveInfo> getShareApps(Context context){
		List<ResolveInfo> mApps = new ArrayList<ResolveInfo>();
		Intent intent=new Intent(Intent.ACTION_SEND,null);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setType("text/plain");
		PackageManager pm = context.getPackageManager();
		mApps = pm.queryIntentActivities(intent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
		return mApps;
	}

	public static List<PackageInfo> getAllApps(Context context) {
		List<PackageInfo> apps = new ArrayList<>();
		PackageManager pm = context.getPackageManager();
		//获取手机内所有应用
		List<PackageInfo> list = pm.getInstalledPackages(PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
		for (PackageInfo pi : list) {
			//判断是否为非系统预装的应用程序
			if ((pi.applicationInfo.flags & pi.applicationInfo.FLAG_SYSTEM) <= 0) {
				// customs applications
				apps.add(pi);
			}
		}
		return apps;
	}

	/**
	 * 获取本机IP地址 IPv4
	 * @return
	 */
	public static String getIP(){
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
				{
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address))//(inetAddress instanceof Inet4Address)去掉在4.0以上系统是IPv6
					{
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		}
		catch (SocketException ex){
			ex.printStackTrace();
		}
		return null;
	}

	
}
