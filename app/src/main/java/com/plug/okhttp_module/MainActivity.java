package com.plug.okhttp_module;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Instrumentation;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapRegionDecoder;
import android.hardware.input.InputManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RemoteController;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.PersistableBundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Printer;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewAnimator;


import com.plug.okhttp_module.retrofit.HttpRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    private Handler handler;
    private View viewAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate");
        viewAnimation = findViewById(R.id.animation);
        viewAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initAnimation();

                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "哈哈", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogLoginConfirm(MainActivity.this, "1111");
            }
        });
        findViewById(R.id.ry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
            }
        });
        findViewById(R.id.sv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SurfaceViewActivity.class));
            }
        });

        findViewById(R.id.stop_music).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // 通过模拟耳机按键去控制第三方音乐播放器暂停 播放
                    // 1.input指令去调用
                    // 下一首
//                    String keyCommand = "input keyevent " + KeyEvent.KEYCODE_MEDIA_NEXT;
                    // 上一首
//                    String keyCommand = "input keyevent " + KeyEvent.KEYCODE_MEDIA_PREVIOUS;
                    // Key code constant: Play/Pause media key
//                    String keyCommand = "input keyevent " + KeyEvent.KEYCODE_MEDIA_PLAY;
                    // 暂停播放
//                    String keyCommand = "input keyevent " + KeyEvent.KEYCODE_MEDIA_PAUSE;
                    // 开始播放
                    String keyCommand = "input keyevent " + KeyEvent.KEYCODE_MEDIA_PLAY;
                    Runtime runtime = Runtime.getRuntime();
                    Process proc = runtime.exec(keyCommand);

                    // 2.通过反射调用
                    injectInputEvent();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//        videoPlay();

        request();

        // 有序广播
        Intent intent = new Intent();
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        sendOrderedBroadcast(intent, null, broadcastReceiver, null, Activity.RESULT_OK, "", new Bundle());
        createDebugTracing();

        new Thread() {
            @Override
            public void run() {
                super.run();
                // 子线程开启handler
                Looper.prepare();
                Log.e(TAG, " currentThread " + Thread.currentThread().getName());
                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Toast.makeText(MainActivity.this, "handler", Toast.LENGTH_SHORT).show();
                    }
                };
                Looper.loop();
            }
        }.start();

        // 获取可用的堆内存
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClass = activityManager.getMemoryClass();
        Log.e("TAG", String.valueOf(memoryClass));

        initBroadCast();

        View jump = findViewById(R.id.jump);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
        HandlerThread handlerThread = new HandlerThread("Thread");
        handlerThread.start();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl, new TestFragment());
        fragmentTransaction.commitNowAllowingStateLoss();


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    /**
     * 生成日志记录 app.trace 拖到Android studio分析
     */
    private void createDebugTracing() {
//        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "app.trace");
//        Debug.startMethodTracing(file.getAbsolutePath());
//        init();
//        testB();
//        Debug.stopMethodTracing();
    }

    /**
     * 初始化广播，监听物理按键事件
     */
    private void initBroadCast() {
        IntentFilter mediafilter = new IntentFilter();
        //拦截按键KeyEvent.KEYCODE_MEDIA_NEXT、KeyEvent.KEYCODE_MEDIA_PREVIOUS、KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
        mediafilter.addAction(Intent.ACTION_MEDIA_BUTTON);
        mediafilter.addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        mediafilter.addAction("android.intent.action.HEADSET_PLUG");
        mediafilter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        mediafilter.setPriority(1000);//设置优先级，优先级太低可能被拦截，收不到信息。一般默认优先级为0，通话优先级为1，该优先级的值域是-1000到1000。
        MediaButtonReceiver mediaButtonReceiver = new MediaButtonReceiver();
        registerReceiver(mediaButtonReceiver, mediafilter);
        registerHeadsetReceiver();
        try {
            String keyCommand = "input keyevent " + KeyEvent.KEYCODE_MEDIA_PAUSE;
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec(keyCommand);
        } catch (Exception e) {
        }
    }


    //注册 注销
    public void registerHeadsetReceiver() {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        ComponentName name = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());
        audioManager.registerMediaButtonEventReceiver(name);
    }

    public void unregisterHeadsetReceiver() {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        ComponentName name = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());
        audioManager.unregisterMediaButtonEventReceiver(name);
    }

    /**
     * 播放视频
     */
    private void videoPlay() {
        VideoView video = findViewById(R.id.video);

        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/ScreenRecorder/Screenrecorder-2020-04-11-14-24-54-511.mp4");
        video.setVideoPath(file.getAbsolutePath());
        //控制视频播放
        MediaController mc = new MediaController(MainActivity.this);
        video.setMediaController(mc);//让VideoView与MediaControl关联
        video.requestFocus();//让VideoView获取焦点
        video.start();
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "视频播放完毕", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean SWITCH = false;

    /**
     * 通过反射调用第三方音乐播放器 播放 停止
     */
    private void injectInputEvent() {
        long now = SystemClock.uptimeMillis();
        KeyEvent play = new KeyEvent(now, now, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY, 0);
        KeyEvent pause = new KeyEvent(now, now, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PAUSE, 0);
//        InputManager.getInstance().injectInputEvent(up, InputManager.INJECT_INPUT_EVENT_MODE_ASYNC);
        try {
            Class<?> aClass = Class.forName("android.hardware.input.InputManager");
            Method getInstance = aClass.getDeclaredMethod("getInstance");
            Object invoke = getInstance.invoke(aClass);

//            Field inject_input_event_mode_async = aClass.getDeclaredField("INJECT_INPUT_EVENT_MODE_ASYNC");
//            inject_input_event_mode_async.setAccessible(true);
//            Object ASYNC = inject_input_event_mode_async.getInt(aClass);
            Object ASYNC = 0;
            Method injectInputEvent = aClass.getDeclaredMethod("injectInputEvent", InputEvent.class, int.class);
            if (SWITCH) {
                injectInputEvent.invoke(invoke, play, ASYNC);
            } else {
                injectInputEvent.invoke(invoke, pause, ASYNC);
            }
            SWITCH = !SWITCH;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * 属性动画
     * alpha	透明度	float
     * translationX	X方向的位移	float
     * translationY	Y方向的位移	float
     * scaleX	X方向的缩放倍数	float
     * scaleY	Y方向的缩放倍数	float
     * rotation	以屏幕方向为轴的旋转度数	float
     * rotationX	以X轴为轴的旋转度数	float
     * rotationY	以Y轴为轴的旋转度数	float
     */
    private void initAnimation() {
        // 属性动画
        ObjectAnimator translationX = ObjectAnimator.ofFloat(viewAnimation, "translationX", 10, 100);
        translationX.setDuration(300);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(viewAnimation, "alpha", 100, 50);
        alpha.setDuration(300);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(viewAnimation, "scaleX", 1, 5);
        scaleX.setDuration(500);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(viewAnimation, "scaleY", 1, 5);
        scaleY.setDuration(500);
        scaleY.start();
        // 动画集合
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationX);
        animatorSet.playTogether(alpha);
        animatorSet.playTogether(scaleX);
        animatorSet.playTogether(scaleY);
        animatorSet.start();


        ValueAnimator valueAnimator = ValueAnimator.ofFloat(10, 100);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Log.e(TAG, String.valueOf(animatedValue));
            }
        });
        valueAnimator.start();

        // 载入XML动画
        ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.set_animation);
        animator.setTarget(viewAnimation);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Log.e(TAG, String.valueOf(animatedValue));
            }
        });
        animator.start();

        // 补间动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0f, 1f, viewAnimation.getPivotX(), viewAnimation.getPivotY());
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setRepeatMode(Animation.REVERSE);// 设置旋转模式
        scaleAnimation.setRepeatCount(10);// 设置重复次数
        viewAnimation.startAnimation(scaleAnimation);

        // SharedPreferences 存储值
        SharedPreferences sp_name = getSharedPreferences("sp_name", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp_name.edit();
        // 存入键值
        edit.putString("name", "1234");
        edit.apply();
        // 清除所有数据
        edit.clear();
        // 获取值
        sp_name.getString("name", "0");

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        java.lang.reflect.Proxy.newProxyInstance(getClassLoader(), getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                return null;
            }
        });

        //getMainLooper().myQueue()或者Looper.myQueue()Looper.myQueue().addIdleHandler(new IdleHandler() {

        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                //你要处理的事情
                return false;
            }
        });


    }

    private void testB() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testA();
    }

    private void testA() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void request() {
        try {
            new URL("").openConnection(Proxy.NO_PROXY);

        } catch (IOException e) {
            e.printStackTrace();
        }

        CacheControl cacheControl = new CacheControl.Builder()
                .noCache()// 不使用缓存，全部走网络
                .noStore()// 不使用缓存，也不存储缓存
                .onlyIfCached()// 只使用缓存
                .noTransform()// 禁止转码
                .maxAge(10, TimeUnit.SECONDS)// 设置缓存的响应的最长期限
                .maxStale(10, TimeUnit.SECONDS)// 客户端可以接收超出超时期间的响应消息
                .minFresh(10, TimeUnit.SECONDS)// 设置响应将持续刷新的最小秒数
                .build();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().proxy(Proxy.NO_PROXY).build();
        Request request = new Request.Builder()
                // 设置缓存
                .cacheControl(cacheControl)
                .get()
                .url("https://www.baidu.com")
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.d(TAG, "失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, " response " + response.body().string());
            }
        });

    }

    @SuppressLint("InvalidWakeLockTag")
    public void retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        HashMap<Object, Object> map = new HashMap<>();
        retrofit2.Call<Object> call = retrofit.create(HttpRequest.class).getDate(map);
        call.enqueue(new retrofit2.Callback<Object>() {
            @Override
            public void onResponse(retrofit2.Call<Object> call, retrofit2.Response<Object> response) {

            }

            @Override
            public void onFailure(retrofit2.Call<Object> call, Throwable t) {

            }
        });

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "my_power");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        unregisterHeadsetReceiver();
    }
}
