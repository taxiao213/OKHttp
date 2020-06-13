package com.plug.okhttp_module;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.ColorMatrixColorFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;

/**
 * 数据库操作
 * Created by A35 on 2020/6/13
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase writableDatabase;

    public MySqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public MySqliteOpenHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 使用 beginTransaction 开启一个事务，程序执行到 endTransaction 方法时会检查事务的标志是否为成功，如果程序执行到endTransaction之前调用了
     * setTransactionSuccessful 方法设置事务的标志为成功，则提交事务；如果没有调用
     * setTransactionSuccessful 方法则回滚事务
     */
    public void init(Context context) {
        // 初始化
        MySqliteOpenHelper mySqliteOpenHelper = new MySqliteOpenHelper(context, "db_name", null, 1);
        SQLiteDatabase writableDatabase = mySqliteOpenHelper.writableDatabase;
        // 执行语句
        writableDatabase.execSQL("");
        // 开启一个事务
        writableDatabase.beginTransaction();
        // 置成功点
        writableDatabase.setTransactionSuccessful();
        // 结束事务，包括提交和回滚
        writableDatabase.endTransaction();
        // 关流
        writableDatabase.close();
        SurfaceView surfaceView = new SurfaceView(context);
        SurfaceHolder holder = surfaceView.getHolder();

        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setDisplay(holder);


    }

}
