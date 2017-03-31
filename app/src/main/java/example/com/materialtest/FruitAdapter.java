package example.com.materialtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 国富小哥 on 2017/3/28.
 * 其实recyclerview的适配器很简单
 * 1，一个内部类
 * 2，三个重写方法
 * 3，一个构造方法
 * 4，配置上下文
 * 5，配置数据源
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{
    private Context mContext;//上下文
    private List<Fruit> mFruitList;//数据源

    public FruitAdapter(List<Fruit> mFruitList){
        this.mFruitList=mFruitList;
    }

    /**
     * 内部类
     * */
    static class ViewHolder extends RecyclerView.ViewHolder{
        //拿到item的子项
        CardView mCardView;
        ImageView fruit_image;
        TextView fruit_name;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView= (CardView) itemView;
            fruit_image= (ImageView) mCardView.findViewById(R.id.fruit_image);
            fruit_name= (TextView) mCardView.findViewById(R.id.fruit_name);
        }
    }



    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);

        //给itemView的子项设置点击事件
        viewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                Fruit fruit=mFruitList.get(position);
                Intent intent=new Intent();
                intent.setClass(mContext,FruitActivity.class);
                intent.putExtra("FRUIT_NAME",fruit.getName());
                intent.putExtra("FRUIT_IMAGE_ID",fruit.getImageid());
                mContext.startActivity(intent);
            }
        });


        return viewHolder;
    }



    @Override
    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
        Fruit mfruit=mFruitList.get(position);
        //加载水果的名字
        holder.fruit_name.setText(mfruit.getName());
        //加载水果的图片
        Glide.with(mContext).load(mfruit.getImageid()).into(holder.fruit_image);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}






