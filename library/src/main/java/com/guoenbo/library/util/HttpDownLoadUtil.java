package com.guoenbo.library.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by guoenbo on 2017/9/11.
 */

public class HttpDownLoadUtil {

    /**
     * @param urlStr
     *            文件网络链接
     * @param path
     *            保存路径
     * @param fileName
     *            文件名
     * */
    public static boolean DownLoadFile(String urlStr, String path, String fileName) {
        boolean tag = false;
        HttpURLConnection connection = null;
        InputStream is = null;
        FileUtil.checkDirectory(path);
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            int res = connection.getResponseCode();
            if (res == 200) {
                FileOutputStream fos = new FileOutputStream(path + fileName);
                is = connection.getInputStream();
                byte[] buffer = new byte[8192];
                int length = -1;
                while ((length = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, length);
                }
                fos.close(); // 关闭FileOutputStream对象
                is.close();
                tag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            tag = false;
        } finally {
            connection.disconnect();
        }
        return tag;
    }
}
