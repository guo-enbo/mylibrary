package com.guoenbo.library.util;

import com.qiji.fingertipfinancial.R;

import java.util.LinkedHashMap;
import java.util.Map;


public class FaceUtil {

	public static int FACE_NUM_PAGE = 5;// 总共有多少页
	public static final int NUM_PAGE = 20;// 每页20个表情,还有最后一个删除button
	public static int TOTAL;
	public static final int WIDTH = 70;
	public static final int HEIGHT = 70;
	private static Map<String, Integer> mFaceMap = new LinkedHashMap<String, Integer>();
	
	public static Map<String, Integer> getFaceMap() {
		if (mFaceMap.isEmpty()){
			initFaceMap();
			TOTAL =  mFaceMap.size();
			FACE_NUM_PAGE = (int) Math.ceil((float)TOTAL/(float)NUM_PAGE);
		}
		return mFaceMap;
	}
	
	private static void initFaceMap() {
		mFaceMap.put("[微笑]", R.mipmap.f_static_001);
		mFaceMap.put("[憋嘴]", R.mipmap.f_static_002);
		mFaceMap.put("[色]", R.mipmap.f_static_003);
		mFaceMap.put("[发呆]", R.mipmap.f_static_004);
		mFaceMap.put("[得意]", R.mipmap.f_static_005);
		mFaceMap.put("[流泪]", R.mipmap.f_static_006);
		mFaceMap.put("[害羞]", R.mipmap.f_static_007);
		mFaceMap.put("[闭嘴]", R.mipmap.f_static_008);
		mFaceMap.put("[睡]", R.mipmap.f_static_009);
		mFaceMap.put("[大哭]", R.mipmap.f_static_010);
		mFaceMap.put("[尴尬]", R.mipmap.f_static_011);
		mFaceMap.put("[发怒]", R.mipmap.f_static_012);
		mFaceMap.put("[调皮]", R.mipmap.f_static_013);
		mFaceMap.put("[呲牙]", R.mipmap.f_static_014);
		mFaceMap.put("[惊讶]", R.mipmap.f_static_015);
		mFaceMap.put("[难过]", R.mipmap.f_static_016);
		mFaceMap.put("[酷]", R.mipmap.f_static_017);
		mFaceMap.put("[冷汗]", R.mipmap.f_static_018);
		mFaceMap.put("[抓狂]", R.mipmap.f_static_019);
		mFaceMap.put("[吐]", R.mipmap.f_static_020);
		mFaceMap.put("[偷笑]", R.mipmap.f_static_021);
		mFaceMap.put("[愉快]", R.mipmap.f_static_022);
		mFaceMap.put("[白眼]", R.mipmap.f_static_023);
		mFaceMap.put("[傲慢]", R.mipmap.f_static_024);
		mFaceMap.put("[饥饿]", R.mipmap.f_static_025);
		mFaceMap.put("[困]", R.mipmap.f_static_026);
		mFaceMap.put("[惊恐]", R.mipmap.f_static_027);
		mFaceMap.put("[流汗]", R.mipmap.f_static_028);
		mFaceMap.put("[憨笑]", R.mipmap.f_static_029);
		mFaceMap.put("[悠闲]", R.mipmap.f_static_030);
		mFaceMap.put("[奋斗]", R.mipmap.f_static_031);
		mFaceMap.put("[咒骂]", R.mipmap.f_static_032);
		mFaceMap.put("[疑问]", R.mipmap.f_static_033);
		mFaceMap.put("[嘘]", R.mipmap.f_static_034);
		mFaceMap.put("[晕]", R.mipmap.f_static_035);
		mFaceMap.put("[疯了]", R.mipmap.f_static_036);
		mFaceMap.put("[衰]", R.mipmap.f_static_037);
		mFaceMap.put("[骷髅]", R.mipmap.f_static_038);
		mFaceMap.put("[敲打]", R.mipmap.f_static_039);
		mFaceMap.put("[再见]", R.mipmap.f_static_040);
		mFaceMap.put("[擦汗]", R.mipmap.f_static_041);
		mFaceMap.put("[抠鼻]", R.mipmap.f_static_042);
		mFaceMap.put("[鼓掌]", R.mipmap.f_static_043);
		mFaceMap.put("[糗大了]", R.mipmap.f_static_044);
		mFaceMap.put("[坏笑]", R.mipmap.f_static_045);
		mFaceMap.put("[左哼哼]", R.mipmap.f_static_046);
		mFaceMap.put("[右哼哼]", R.mipmap.f_static_047);
		mFaceMap.put("[哈欠]", R.mipmap.f_static_048);
		mFaceMap.put("[鄙视]", R.mipmap.f_static_049);
		mFaceMap.put("[委屈]", R.mipmap.f_static_050);
		mFaceMap.put("[快哭了]", R.mipmap.f_static_051);
		mFaceMap.put("[阴险]", R.mipmap.f_static_052);
		mFaceMap.put("[亲亲]", R.mipmap.f_static_053);
		mFaceMap.put("[吓]", R.mipmap.f_static_054);
		mFaceMap.put("[可怜]", R.mipmap.f_static_055);
		mFaceMap.put("[菜刀]", R.mipmap.f_static_056);
		mFaceMap.put("[西瓜]", R.mipmap.f_static_057);
		mFaceMap.put("[啤酒]", R.mipmap.f_static_058);
		mFaceMap.put("[篮球]", R.mipmap.f_static_059);
		mFaceMap.put("[乒乓]", R.mipmap.f_static_060);
	}
	
}
