package test.com.demo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by liushuwei on 2016/8/30.
 */
public class ViewPagerAdapter extends PagerAdapter {
    List<View> arrayList;

    public ViewPagerAdapter(List<View> arrayList) {
        this.arrayList = arrayList;//构造方法，参数是我们的页卡，这样比较方便。
    }

    @Override
    public int getCount() {
        return arrayList.size();//返回页卡的数量
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//实例化页卡
        container.addView(arrayList.get(position), 0);
        return arrayList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(arrayList.get(position));//删除指定页卡
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
