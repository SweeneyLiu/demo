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

    private ArrayList<String> mArrayList;

    public RecyclerViewAdapter(ArrayList<String> arrayList) {
        this.mArrayList = arrayList;
    }

    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
        DemoViewHolder demoViewHolder = new DemoViewHolder(view);
        return demoViewHolder;
    }

    @Override
    public void onBindViewHolder(DemoViewHolder holder, int position) {
        holder.mTextView.setText(mArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public static class DemoViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;
        public DemoViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
