package pri.ys.classifymenu.view.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pri.ys.classifymenu.R;
import pri.ys.classifymenu.view.adapter.ClassifySelectItemAdapter;
import pri.ys.classifymenu.view.vo.ItemListVO;


/**
 * Created by YS-WENJIE on 2016/9/13.
 */
public class ClassifySelectView extends LinearLayout implements AdapterView.OnItemClickListener, View.OnClickListener {

    private Context mContext;

    public ClassifySelectView(Context context) {
        this(context, null);
    }

    public ClassifySelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassifySelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;

        //  导入布局，不需要再使用view.findviewbyid.... 直接使用findviewbyid
        LayoutInflater.from(context).inflate(R.layout.classify_select_view, this, true);

        initView();
        //initData();
    }

    private LinearLayout llMainItem;
    private ListView lvVertical;
    private LinearLayout llCustom;

    private void initView() {
        llMainItem = (LinearLayout) findViewById(R.id.classify_select_view_ll_main_item);
        lvVertical = (ListView) findViewById(R.id.classify_select_view_lv_vertical);
        llCustom = (LinearLayout) findViewById(R.id.classify_select_view_ll_custom);

        lvVertical.setOnItemClickListener(this);
    }

    private List<ItemListVO> mItemList = new ArrayList<ItemListVO>();


    private List<TextView> tvList = new ArrayList<TextView>();

    /**
     * 对自定义选择界面进行初始化
     * 返回true，初始化成功，否则，初始化出现异常
     * 注：一般情况，这个方法，一个界面，只调用一次
     */
    public boolean initViewData(List<ItemListVO> itemList, View defView) {
        this.mItemList = itemList;

        if (mItemList == null || mItemList.size() == 0) {
            return false;
        }

        for (int i = 0; i < mItemList.size(); i++) {
            TextView tv = new TextView(mContext);
            tv.setText(mItemList.get(i).getItemName());
            tv.setTextSize(20);

            //第一个参数为宽的设置，第二个参数为高的设置。  （用的时候注意修改LinearLayout前缀,其实很多时候可以不写它的）
            LayoutParams lp = new LayoutParams(
                    150,
                    LayoutParams.MATCH_PARENT
            );
            tv.setGravity(Gravity.CENTER);
            tv.setOnClickListener(this);

            lp.setMargins(10, 10, 10, 10);
            tv.setLayoutParams(lp);

            tvList.add(tv);
            llMainItem.addView(tv);
        }

        /**默认显示的选项列表——第一分类标签*/
        ClassifySelectItemAdapter adapter = new ClassifySelectItemAdapter(mContext, mItemList.get(mFirstLevel).getSonItems(), mSecondLevel);
        lvVertical.setAdapter(adapter);
        /**默认显示的 第二分类标签列表*/
        setChangeView(defView);
        /**设置默认选择项*/
        setChangeSelectLable1(mFirstLevel);
        setChangeSelectLable2(mSecondLevel);

        return true;
    }

    /**
     * 设置一级标签 状态
     */
    private void setChangeSelectLable1(int index) {
        tvList.get(mFirstLevel).setTextColor(mContext.getResources().getColor(R.color.def_font_color));
        tvList.get(index).setTextColor(mContext.getResources().getColor(R.color.selected_font_color));
        mFirstLevel = index;

    }

    /**
     * 设置二级标签 状态
     */
    private void setChangeSelectLable2(int position) {
        mSecondLevel = position;

    }


    /**
     * 从主调方法中 传入View，并显示在内容框中
     *
     * @param view
     */
    public void setChangeView(View view) {
        if (view == null) {
            return;
        }

        llCustom.removeAllViews();
        /*LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.adapter_item_classify_select, null);*/
        llCustom.addView(view);
    }

    public int mFirstLevel = 0;      //第一分类标签位置
    public int mSecondLevel = 0;     //第二分类标签位置

    private onSelectListener mListener;

    public interface onSelectListener {
        public void onItemClick(int first, int second);
    }

    public void setOnSelectListener(onSelectListener listener) {
        this.mListener = listener;
    }

    /**
     * 第二级标签 事件
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        setChangeSelectLable2(position);

        /**点击后把索引 传给 接口*/
        if (mListener != null) {
            mListener.onItemClick(mFirstLevel,mSecondLevel);
        }

        ClassifySelectItemAdapter adapter = new ClassifySelectItemAdapter(mContext, mItemList.get(mFirstLevel).getSonItems(), mSecondLevel);
        lvVertical.setAdapter(adapter);
    }

    /**
     * 第一级标签事件
     */
    @Override
    public void onClick(View v) {
        int index = tvList.indexOf(v);  //得到位置

        setChangeSelectLable1(index);
        mSecondLevel = 0;
        /**点击后把索引 传给 接口*/
        if (mListener != null) {
            mListener.onItemClick(mFirstLevel,mSecondLevel);
        }

        ClassifySelectItemAdapter adapter = new ClassifySelectItemAdapter(mContext, mItemList.get(mFirstLevel).getSonItems(), mSecondLevel);
        lvVertical.setAdapter(adapter);
    }
}
