package test.com.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import test.com.demo.R;

/**
 * Created by liushuwei on 2016/9/26.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DemoViewHolder>{

    protected ArrayList<String> mDataList;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewAdapter(ArrayList<String> arrayList) {
        this.mDataList = arrayList;
    }

    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
        DemoViewHolder demoViewHolder = new DemoViewHolder(view);
        return demoViewHolder;
    }

    @Override
    public void onBindViewHolder(DemoViewHolder holder, int position) {
        holder.mTextView.setText(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class DemoViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;
        public DemoViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }


    /**
     * 点击事件的接口
     */
    public interface  OnItemClickListener{
        void onItemClickListener(View view,int position);//点击
        void onItemLongClickListener(View view,int position);//长按
    }


    /**
     * 增加一行
     * @param position
     */
    public void add(int position){
        mDataList.add(position,"添加一项");
        notifyItemInserted(position);
    }

    /**
     * 删除一行
     * @param position
     */
    public void delete(int position){
        mDataList.remove(position);
        notifyItemRemoved(position);
        if(position != mDataList.size()){ // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, mDataList.size() - position);
        }
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 实现瀑布流布局中的相关事件
     * @param holder
     */
    protected void setUpItemEvent(final DemoViewHolder holder) {
        if (mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClickListener(holder.itemView,layoutPosition);
                }

            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition=holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClickListener(holder.itemView,layoutPosition);
                    return false;
                }
            });
        }
    }

}
