package com.guoenbo.library.common;

import android.graphics.Canvas;
import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/6/13.
 */
public class Constants {
    /**
     * 记录播放位置
     */
    public static int playPosition=-1;

    private static Canvas canvas;

    public static Canvas getCanvas() {
        return canvas;
    }

    public static void setCanvas(Canvas canvas) {
        Constants.canvas = canvas;
    }

    public static final String KEY_TITLE = "title";
    public static final String KEY_TYPE = "type";
    public static final String KEY_TAG = "tag";
    public static final int PLAYER = 10000;

    public static final String NO_SD = "未检测到SD卡";
    public static final String MSG_EMPTY = "暂无数据";
    public static final String MSG_LOADING_OVER = "加载完毕";
    public static final String EXIT_PROCESS = "再按一次退出程序";
    public static final String KEY_PAGE = "page";
    public static final String KEY_LIMIT = "limit";
    public static final int SLEEP = 3000;
    public static final int OVERLAY_PERMISSION_REQ_CODE = 7;
    public static final int BUILD_INIT_CACHE_CODE = 1;
    public static final int BUILD_INIT_UPDATE_CODE = 2;
    public static final int VALUE_LIMIT = 20;
    public static final int CHOOSE_LIMIT = 9;
    public final static String MSG_CLICK_REFRESH = "您的网络不给力~";
    public final static String MSG_CLICK_SHARE = "分享失败";
    public final static String IN_QQ = "您的设备未安装手机QQ";
    public final static String MSG_UPDATE_APP_NAME = "环裕金服";
    // 下载安装apk路径 getExternalStorageDirectory/getFilesDir
    public final static String APK_NAME = "fingertipfinancial.apk";
    public final static String APP_NAME = "fingertipfinancial";
    // 日志保存路径
    public final static String PATH_LOG = File.separator + APP_NAME
            + File.separator + "log" + File.separator;
    public final static String SDPATHS = Environment
            .getExternalStorageDirectory().getAbsolutePath();
    /*public final static String PATH_IMAGE = SDPATH + File.separator + APP_NAME
            + File.separator + "image" + File.separator;*/
    // sd卡的绝对路径
    public final static String SDPATH = Environment
            .getExternalStorageDirectory().getAbsolutePath();
    // 设置头像文件保存路径
    public final static String PATH_HEAD = SDPATH + File.separator + APP_NAME
            + File.separator + "head" + File.separator;
    public final static String PATH_IMAGE = SDPATH + File.separator + APP_NAME
            + File.separator + "image" + File.separator;
    public final static String IMAGELOAD = PATH_IMAGE + "cache" + File.separator;
    public final static String PATH_VOICE = File.separator + APP_NAME
            + File.separator + "chat" + File.separator + "voice"
            + File.separator;

    /***************************缓存Key***************************/
    //登录key
    public static final String KEY_USER_CACHE = "mLoginBean";

    /***************************SharedPreferences-Key***************************/

    public static final String KEY_SP_FILE = "knowledge";

    public static final String KEY_SP_IsRemember = "IsRemember";

    public static final String KEY_SP_LoginName = "LoginName";

    public static final String KEY_SP_Password = "Password";
    public static final String MINUTESURL="http://test/";
    public static final String DETAILURL="test/";

    public static final String APATCH_PATH = "/out.apatch";
    public static final String EXTERNALPATH  = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String HTTP_BASE = "http://7xrnuc.com1.z0.glb.clouddn.com/";/*http://f5.market.xiaomi.com/download/AppStore/";*/
    public final static String APPDIR = "weread";
    public final static String APPPATH = SDPATH + APPDIR;
    public final static String PATH_MEDIA = SDPATH + APPDIR;
    public final static String HEADER_ICON_FILE = SDPATH + "/header.jpg";
    public static final String KEY_SHARE_SDK_SMS = "577c217d41f6";
    public static final String SECRECT_SHARE_SDK_SMS = "effdc6b4f88086b7dc7c86373fa9ff76";
    public static final String VERSON_MSG = "当前为最新版本";

    public final static String SUFFIX_IMAGE_GIF = ".gif";

    public static final String FLAG_JUMP_2_ARTICLE_LIST_TYPE = "flag_jump_2_article_list_type";

    public static final int FETCH_DATA_PAGE_NUM = 10;

    public static final int TYPE_DIALOG_LOGIN = 4;
    public static final int TYPE_DIALOG_REGISTER = 5;

    public static final String NO_ACCOUNT_NOTICE = "还没有账号？<font color=red>赶快注册一个!</font>";
    public static final String HAS_ACCOUNT_NOTICE = "已经有账号了，<font color=red>直接登陆</font>";

    public static final String CACHE_PREF_NAME = "cache_pref_name";
    public static final String CATEGORY_CACHE_ARTICLE_LIST = "category_cache_article_list_";
    public static final String CATEGORY_CACHE_NOTE_LIST = "category_cache_note_list";
    public static final String CATEGORY_CACHE_FAVORITE_LIST = "category_cache_favorite_list";
    public static final String CATEGORY_CACHE_NOTIFICATION_LIST = "category_cache_notification_list";

    public static final int MSG_FIRST_REFRESH = 100;
    public static final int MSG_FIRST_REFRESH_TIME_UP = 101;
    public static final int MSG_PROGRESS_BAR_LOADING = 102;
    public static final int TIME_SPACE_PB_LOADING = 100;
    public static final int TIME_OUT_NETWORK_REQUEST = 3 * 1000;

    public static final int USER_TYPE_TOURIST = 1;
    public static final int USER_TYPE_PLAT = 3;
    public static final int USER_TYPE_NORMAL = 2;
    public static final int USER_TYPE_NONE = 4;

    public static final String USER_TYPE_PLAT_REGISTER = "register";

    public static final int EVENT_TYPE_USER_NORMAL_LOGIN = 100;
    public static final int EVENT_TYPE_USER_NORMAL_REGISTER = 101;
    public static final int EVENT_TYPE_USER_PLAT_LOGIN = 102;

    public static final int ACCOUNT_MAX_LENGTH = 24;
    public static final int ACCOUNT_MIN_LENGTH = 3;

    public static final int PASSWORD_MAX_LENGTH = 20;
    public static final int PASSWORD_MIN_LENGTH = 6;
    public final static String SHARE_TITLE_CONTENT = " 来自@单读";

    public final static float DELTA_MIN_SLIDE_MENU_DRAW_SHDOW = 0.01F;
    public final static float MIN_VALID_X_VALUE_WHEN_DRAW_SHDOW = 0.01F;
    public static final int LIMIT_CELL_PHONE_NUMBER = 11;

    public static final String INTENT_PHOTO_PATH = "intent_photo_path";
    public static final String INTENT_CROPPED_IMAGE_BYTE_ARR = "intent_cropped_image_byte_arr";
    public static final int MAX_SIZE_HEADER_ICON_KB = 100;

    public static final String KEY_ACTION_INTENT = "KEY_ACTION_INTENT";
    public static final int ACTION_INTENT_PREPARE_LOGIN_REGISER = 100;

    public static final int ACTION_INTENT_NONE = 0;
    public static final int ACTION_INTENT_ADD_FAVORITE = 1;
    public static final int ACTION_INTENT_COMMENT_DIAN_ZAN = 2;
    public static final int ACTION_INTENT_COMMENT = 3;
    public static final int ACTION_INTENT_COMMENT_REPLAY = 4;
    public static final int ACTION_INTENT_JUST_LOGIN_REGISTER = 5;
    public static final int ACTION_INTENT_FAVORITE_ARTICLE = 6;
    public static final int ACTION_INTENT_MY_COMMENT = 7;
    public static final int ACTION_INTENT_CHANGE_NICKNAME = 8;
    public static final int ACTION_INTENT_SHARE = 11;


    public static final int ACTION_INTENT_FROM_PERSONAL_PAGE = 1000;

    public static final String TYPE_HOME = "0";
    public static final String TYPE_WORDS = "1";
    public static final String TYPE_VIDEO = "2";
    public static final String TYPE_VOICE = "3";
    public static final String TYPE_CALENDAR = "4";
    public static final String TYPE_LEFT_MENU = "100";
    public static final String TYPE_RIGHT_MENU = "101";

    public static final String TYPE_COMMENT = "20";
    public static final String TYPE_FAVORITE = "21";

    public static final String KEY_FRAGMENT_TYPE = "KEY_FRAGMENT_TYPE";

    public static final int MSG_SET_VIDEO_VIEW_TRANSPARENT = 500;
    public static final int MSG_DISMISS_VIDEO_CONTROL_BAR = 501;
    public static final int MSG_CANCEL_VIEW_SELECTED_STATE = 502;
    public static final int DELAY_MSG_DISMISS_VIDEO_CONTROL_BAR = 4000;
    public static final int DELAY_MSG_CANCEL_VIEW_SELECTED_STATE = 500;
    public static final int DELAY_MSG_SET_VIDEO_VIEW_TRANSPARENT = 1000;

    public static final String STATUS_SHOW_VIDEO_NORMAL = "0";
    public static final String STATUS_SHOW_VIDEO_HIDE = "1";

    public static final String VALUE_TYPE_MSG_COMMENT_MEPO= "1";
    public static final String VALUE_TYPE_MSG_LIKE_MEPO = "2";
    public static final String VALUE_TYPE_MSG_COMMENT_ARTICLE = "3";
    public static final String VALUE_TYPE_MSG_LIKE_ARTICLE = "4";
    public static final String VALUE_TYPE_MSG_LIKE_COMMENT = "5";
    public static final String VALUE_TYPE_REPLAY_COMMENT = "6";
    public static final String VALUE_TYPE_SYSTEM_MSG = "7";




    public final static String PATH_SDCARD_APK = File.separator + APP_NAME
            + File.separator + "install" + File.separator;
    public final static String PATH_SYS_APK = File.separator + APP_NAME
            + File.separator;

    // 版本信息
    public static final String BUNDLE_KEY_TAndroidVersion = "BUNDLE_KEY_TAndroidVersion";
    public final static int TH_VERSION_SUCC = 10200; // 请求版本更新成功
    public final static int TH_VERSION_FAILD = 10201; // 请求版本更新失败
    public final static int TH_NO_VERSION = 10202; // 暂无新版本
    public final static int TH_DOWN_SUCC = 10203; // 下载apk成功
    public final static int TH_DOWN_FAILD = 10204; // 下载apk失败
    public static final String BUNDLE_KEY_VER_APKURL = "BUNDLE_KEY_VER_APKURL";
    public static final String BUNDLE_KEY_VER_SAVEPATH = "BUNDLE_KEY_VER_SAVEPATH";

    public final static String MSG_UPDATE_APP_FAILD = "下载失败";
    public final static String MSG_UPDATE_APP_START = "开始下载";

    public static final String PATH_VIDEO_SCREENSHOT = APPPATH + "/video_screenshot.jpg";
    public static final String DownLoadFile = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
            "HYFileDownloader" ;


    /*********************************************接口（action）********************************/



    /*****************************************第二域名接口***************************************/

}
