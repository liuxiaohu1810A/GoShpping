package com.example.app2.goshpping.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.app2.R;
import com.example.app2.goshpping.DBUtils.DBUtils;
import com.example.app2.goshpping.bean.DataBean;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<DataBean> list;

    public MyAdapter(Context context, List<DataBean> list) {
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
        if (DBUtils.query(dataBean)!=null){
            dataBean.setIsChex(true);
        }else{
            dataBean.setIsChex(false);
        }
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.cb.isChecked()){
                    if (DBUtils.query(dataBean)==null){
                        dataBean.setIsChex(true);
                        DBUtils.insert(dataBean);
                        Toast.makeText(context, "加入购物车", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "已添加到购物车", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    dataBean.setIsChex(false);
                    DBUtils.delete(dataBean);
                    Toast.makeText(context, "从购物车中删除商品", Toast.LENGTH_SHORT).show();
                }
                a.onClick(v,i);
            }
        });

        viewHolder.cb.setChecked(dataBean.getIsChex());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView tu;
        private final TextView title;
        private final TextView price;
        private final TextView name;
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
    private Lxh a;

    public void setA(Lxh a) {
        this.a = a;
    }

    public interface Lxh{
        void onClick(View v, int position);
    }
}
