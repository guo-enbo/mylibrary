package com.guoenbo.library.util;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.czt.mp3recorder.MP3Recorder;
import com.qiji.fingertipfinancial.common.Constants;

import java.io.File;
import java.io.IOException;

public class AudioRecorder {
	
	private static boolean tag = false;
	private static boolean playState = false; // 播放状态
	private static MediaPlayer mediaPlayer;
	
	private MP3Recorder mRecorder;
	private String fileUrl, dirPath;

	public AudioRecorder(String path, String fileName) {
		dirPath = Constants.SDPATH + Constants.PATH_VOICE + path + File.separator;
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();// 创建文件夹
		}
		mRecorder = new MP3Recorder(new File(dirPath, fileName + ".mp3"));
		fileUrl = dirPath + fileName + ".mp3";
	}
	
	public void start() throws IOException {
		mRecorder.start();
	}
	
	public void stop() throws IOException {
		mRecorder.stop();
	}

	public String getFilePath() {
		File file = new File(fileUrl);
		return file.getAbsolutePath();
	}
	
	public double getAmplitude() {
		if (mRecorder != null) {
			return (mRecorder.getRealVolume());
		} 
		return 0;
	}
	
//	private static int SAMPLE_RATE_IN_HZ = 8000;
//	private MediaRecorder recorder = new MediaRecorder();
//	private String path;
	
//	public AudioRecorder(String path, String fileName) {
//		this.path = sanitizePath(path, fileName);
//		
//	}
	
//	private String sanitizePath(String path, String fileName) {
//		if (!fileName.startsWith("/")) {
//			fileName = "/" + fileName;
//		}
//		if (!fileName.contains(".")) {
//			fileName += ".amr";
//		}
//		return Constants.SDPATH + Constants.PATH_VOICE + path + fileName;
//	}

	// 获取文件手机路径
//	public String getFilePath() {
//		File file = new File(path);
//		return file.getAbsolutePath();
//	}

//	public void start() throws IOException {
//		String state = android.os.Environment.getExternalStorageState();
//		if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
//			throw new IOException("SD Card is not mounted,It is  " + state
//					+ ".");
//		}
//		File directory = new File(path).getParentFile();
//		if (!directory.exists() && !directory.mkdirs()) {
//			throw new IOException("Path to file could not be created");
//		}
//		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//		recorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
//		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//		recorder.setAudioSamplingRate(SAMPLE_RATE_IN_HZ);
//		recorder.setOutputFile(path);
//		recorder.prepare();
//		recorder.start();
//	}

//	public void stop() throws IOException {
//		recorder.stop();
//		recorder.release();
//	}

//	public double getAmplitude() {
//		if (recorder != null) {
//			return (recorder.getMaxAmplitude());
//		} else
//			return 0;
//	}
	
	public static void play(String voiceURL) {
		if (!playState) {
			mediaPlayer = new MediaPlayer();
			try {
				mediaPlayer.setDataSource(voiceURL);
				mediaPlayer.prepare();
				mediaPlayer.start();
				playState = true;
				// 设置播放结束时监听
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						if (playState) {
							playState = false;
						}
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
				playState = false;
			} else {
				playState = false;
			}
		}
	}
	
	public static void playVoice(final String voiceURL){
		if (CustomUtil.sdCardExist()) {//SD卡存在
			final String voicePath = Constants.SDPATH + Constants.PATH_VOICE;
			int index = voiceURL.lastIndexOf(File.separator);
			final String fileName = voiceURL.substring(index + 1);
			String localFilePathString = voicePath + fileName;
			if(!FileUtil.checkFileExists(localFilePathString)){
				//本地SD卡路径下的语音文件不存在
				new Thread(new Runnable() {
					@Override
					public void run() {
							if(FileUtil.existNetFile(voiceURL)){//当前网络文件存在
								if (HttpDownLoadUtil.DownLoadFile(voiceURL, voicePath, fileName)) {//把网络语音文件下载到本地SD卡上指定的目录下
									AudioRecorder.play(voicePath + fileName);//播放下载好的语音文件
								}
							}else{//当前网络文件不存在
								tag = true;
							}
						}
					}).start();
				
			}else{//本地SD卡路径下的语音文件存在就播放本地语音文件
				AudioRecorder.play(localFilePathString);
			}
		} else {//SD卡不存在，直接播放网络
			if(FileUtil.existNetFile(voiceURL)){//当前网络文件存在
				AudioRecorder.play(voiceURL);
			}else{//当前网络文件不存在
				tag = true;
			}
		}
		if(tag){
			ToastUtil.getInstance().Short("当前语音消息已过期");
		}
	}
	
	public static void playStop(){
		if(null != mediaPlayer){
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
				playState = false;
			} 
		}
	}
	
}