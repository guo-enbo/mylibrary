package com.guoenbo.library.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by leeandy007 on 16/9/24.
 */

public class PictureUtil {

    /**
     * 进行添加水印图片和文字
     *
     * @param src
     * @param watermark
     * @return
     */
    public static Bitmap createBitmap(Bitmap src, Bitmap watermark, String string) {
        if (src == null) {
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        //需要处理图片太大造成的内存超过的问题,这里我的图片很小所以不写相应代码了
        Bitmap newBitmap= Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas mCanvas = new Canvas(newBitmap);
        mCanvas.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src

        //加入图片
        if (watermark != null) {
            Paint paint=new Paint();
            int ww = watermark.getWidth();
            int wh = watermark.getHeight();
            paint.setAlpha(50);
            mCanvas.drawBitmap(watermark, w - ww + 5, h - wh + 5, paint);// 在src的右下角画入水印
        }
        //加入文字
        if(string!=null) {
            String familyName ="宋体";
            Typeface font = Typeface.create(familyName, Typeface.BOLD);
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(Color.RED);
            textPaint.setTypeface(font);
            textPaint.setTextSize(80);
            textPaint.setAlpha(100);
            //文字就加左上角算了
            mCanvas.drawText(string, w/2+30, h-100, textPaint);
        }
        mCanvas.save(Canvas.ALL_SAVE_FLAG);// 保存
        mCanvas.restore();// 存储
        return newBitmap;
    }


    /**
     * 将bitmap保存成文件
     * */
    public static File BitmapToFile(Bitmap bmp, String filePath, String filename) {
        File file = null;
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
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

    public static BitmapFactory.Options getBitmapOption(int inSampleSize){
        System.gc();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inSampleSize = inSampleSize;
        return options;
    }

}
