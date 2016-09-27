package test.com.demo.adapter;

import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class StaggeredAdapter extends RecyclerViewAdapter{
    private List<Integer> mHeight;



    public StaggeredAdapter(ArrayList<String> datas) {
        super(datas);
        mHeight=new ArrayList<Integer>();

        for (int i=0;i<mDataList.size();i++){
            mHeight.add((int) (100+Math.random()*300));
        }

    }


    /**
     * 绑定数据ViewHolder里面的数据
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(DemoViewHolder holder, int position) {
       ViewGroup.LayoutParams lp= holder.itemView.getLayoutParams();
        lp.height=mHeight.get(position);
        holder.itemView.setLayoutParams(lp);

        holder.mTextView.setText(mDataList.get(position));
        setUpItemEvent(holder);

    }


}

