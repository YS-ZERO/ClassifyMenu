package pri.ys.classifymenu.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import pri.ys.classifymenu.R;


/**
 * Created by YS-WENJIE on 2016/9/13.
 */
public class ClassifySelectItemAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mArrs;
    private int mIndex;

    public ClassifySelectItemAdapter(Context context, String[] arrs, int index) {
        this.mContext = context;
        this.mArrs = arrs;
        this.mIndex = index;
    }

    @Override
    public int getCount() {
        return mArrs.length;
    }

    @Override
    public Object getItem(int position) {
        return mArrs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adapter_item_classify_select, null);
        TextView tv = (TextView) view.findViewById(R.id.adapter_item_classify_select_tv_item);
        tv.setText(mArrs[position]);
        if (mIndex == position){
            tv.setTextColor(mContext.getResources().getColor(R.color.selected_font_color));
        }

        return view;
    }

}
