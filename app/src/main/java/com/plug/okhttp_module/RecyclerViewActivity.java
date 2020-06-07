package com.plug.okhttp_module;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by A35 on 2020/6/7
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private TextView tv_ry_size;
    private TextView tv_ry_bind;
    private RecyclerView ry;
    private ArrayList<String> list;
    private MyAdapter myAdapter;
    private RecyclerviewInterface recyclerviewInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);
        findViewById(R.id.tv_global_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 全局刷新
                list.set(5, "5 全局");
                myAdapter.notifyDataSetChanged();
                recyclerInfo();
            }
        });
        findViewById(R.id.tv_partial_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 局部刷新
                list.set(5, "5 局部");
                myAdapter.notifyItemChanged(5, 1);
                recyclerInfo();
            }
        });
        findViewById(R.id.tv_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 清除信息
                if (myAdapter != null) {
                    myAdapter.clean();
                }
            }
        });
        ry = findViewById(R.id.ry);
        tv_ry_size = findViewById(R.id.tv_ry_size);
        tv_ry_bind = findViewById(R.id.tv_ry_bind);
        list = new ArrayList<>();
        recyclerviewInterface = new RecyclerviewInterface() {
            @Override
            public void bind(String value) {
                tv_ry_bind.setText(value);
                recyclerInfo();
            }

            @Override
            public void size() {

            }
        };

        myAdapter = new MyAdapter(RecyclerViewActivity.this, list, recyclerviewInterface);
        ry.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));
        ry.setAdapter(myAdapter);
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i));
        }
        myAdapter.notifyDataSetChanged();
    }

    /**
     * 反射获取缓存信息
     */
    private void recyclerInfo() {
        try {
            Class<?> name = Class.forName(ry.getClass().getName());
            Field mRecycler = name.getDeclaredField("mRecycler");
            mRecycler.setAccessible(true);
            Object recyclerObject = mRecycler.get(ry);

            // 获取一级缓存AttachedScrap
            Class<?> recyclerClass = Class.forName(recyclerObject.getClass().getName());
            Field mAttachedScrap = recyclerClass.getDeclaredField("mAttachedScrap");
            mAttachedScrap.setAccessible(true);
            List attachedScrap = (List) mAttachedScrap.get(recyclerObject);
            int attachedSize = attachedScrap.size();

            // 获取二级缓存CachedViews
            Field mCachedViews = recyclerClass.getDeclaredField("mCachedViews");
            mCachedViews.setAccessible(true);
            List cachedViews = (List) mCachedViews.get(recyclerObject);
            int cachedViewsSize = cachedViews.size();

            // TODO: 2020/6/7 三级缓存 private ViewCacheExtension mViewCacheExtension; 自定义的无法获取
            // 获取四级缓存RecyclerPool
            Field mRecyclerPool = recyclerClass.getDeclaredField("mRecyclerPool");
            mRecyclerPool.setAccessible(true);
            Object recyclerPoolObject = mRecyclerPool.get(recyclerObject);
            Class<?> recyclerPoolClass = Class.forName(recyclerPoolObject.getClass().getName());
            Field mScrap = recyclerPoolClass.getDeclaredField("mScrap");
            mScrap.setAccessible(true);
            SparseArray scrap = (SparseArray) mScrap.get(recyclerPoolObject);
            int recyclerPoolSize = scrap.size();

            tv_ry_size.setText("mAttachedScrap size == " + attachedSize +
                    "\r\n" + "mCachedViews size == " + cachedViewsSize +
                    "\r\n" + "mRecyclerPool size == " + recyclerPoolSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface RecyclerviewInterface {
        void bind(String value);

        void size();
    }
}
