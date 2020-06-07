package com.plug.okhttp_module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by A35 on 2020/6/7
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> list;
    private Context context;
    private RecyclerViewActivity.RecyclerviewInterface recyclerviewInterface;
    private StringBuffer stringBuffer = new StringBuffer();

    public MyAdapter(RecyclerViewActivity context, ArrayList<String> list, RecyclerViewActivity.RecyclerviewInterface recyclerviewInterface) {
        this.context = context;
        this.list = list;
        this.recyclerviewInterface = recyclerviewInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemtype) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int positon) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.view.setText("我是第-- " + list.get(positon));
        if (recyclerviewInterface != null) {
            stringBuffer.append("onBind== ").append(positon).append("\r\n");
            recyclerviewInterface.bind(stringBuffer.toString());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clean() {
        if (stringBuffer != null) {
            stringBuffer.setLength(0);
        }
        if (recyclerviewInterface != null) {
            recyclerviewInterface.bind("");
        }
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView view;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.tv);
        }
    }
}
