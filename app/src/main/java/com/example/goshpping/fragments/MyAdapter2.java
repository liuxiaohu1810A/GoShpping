package com.example.goshpping.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.goshpping.DBUtils.DBUtils;
import com.example.goshpping.R;
import com.example.goshpping.bean.DataBean;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {
    private Context context;
    private List<DataBean> list;

    public MyAdapter2(Context context, List<DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.fragmenta_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final DataBean dataBean = list.get(i);
        viewHolder.name.setText(dataBean.getTitle());
        viewHolder.title.setText(dataBean.getFood_str());
        viewHolder.price.setText(dataBean.getNum()+"");
        Glide.with(context).load(dataBean.getPic()).into(viewHolder.tu);
        viewHolder.cb.setChecked(true);
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (viewHolder.cb.isChecked()==false){
                    DBUtils.delete(dataBean);
                    list.remove(dataBean);
                    notifyDataSetChanged();
                    Toast.makeText(context, "已从购物车中移除", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView tu;
        private final TextView title;
        private final TextView name;
        private final TextView price;
        private final CheckBox cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            name = itemView.findViewById(R.id.tv_name);
            price = itemView.findViewById(R.id.price);
            tu = itemView.findViewById(R.id.iv_tu);
            cb = itemView.findViewById(R.id.cb);

        }
    }

}
