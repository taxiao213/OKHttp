package com.plug.okhttp_module;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 拍照 和 摄像实现
 * Created by A35 on 2020/6/13
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class SurfaceViewActivity extends AppCompatActivity {
    private final String TAG = getClass().getName();
    private Camera camera;
    private SurfaceHolder holder;
    private MediaRecorder recorder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        SurfaceView surfaceView = findViewById(R.id.surface);

        // 保存照片
        findViewById(R.id.take).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        //设置照片保存路径
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + UUID.randomUUID().toString() + ".jpg";
                        try {
                            FileOutputStream outputStream = new FileOutputStream(path);
                            //将照片字节数组写到文件中
                            outputStream.write(data);
                            outputStream.close();
                            //重新开启预览
                            camera.startPreview();
                            Log.e(TAG, "写入照片");
                        } catch (Exception e) {
                        }
                    }
                });
            }
        });

        holder = surfaceView.getHolder();
        camera = Camera.open();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    camera.setPreviewDisplay(holder);
                    camera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                camera.stopPreview();
                camera.release();
            }
        });

        camera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {

            }
        });

        findViewById(R.id.video_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        findViewById(R.id.video_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
    }


    /**
     * 开始录制视频
     */
    public void start() {
        //停止预览
        camera.stopPreview();
        //解锁摄像头
        camera.unlock();
        //初始化一个 MediaRecorder 对象
        recorder = new MediaRecorder();
        //给 recorder 设置摄像头
        recorder.setCamera(camera);
        //设置音频源
        recorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        //设置视频源
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        //设置录像质量等参数
        CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_480P);
        recorder.setProfile(profile);
        //设置录像输出路径
        recorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/text.mp4");
        //设置预览显示对象
        recorder.setPreviewDisplay(holder.getSurface());
        try {
            //准备
            recorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //开始录像
        recorder.start();
        Log.e(TAG,"开始录像");
    }

    /**
     * 停止录像
     */
    public void stop() {
        //停止录像
        recorder.stop();
        //重置录像
        recorder.reset();
        recorder.release();
        //锁定摄像头
        camera.lock();
        //开启预览
        camera.startPreview();
        Log.e(TAG,"停止录像");
    }
}
