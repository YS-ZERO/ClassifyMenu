package pri.ys.example;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import pri.ys.classifymenu.view.ui.ClassifySelectView;
import pri.ys.classifymenu.view.vo.ItemListVO;

public class MainActivity extends Activity implements ClassifySelectView.onSelectListener {

    private List<ItemListVO> mItemList;
    private ClassifySelectView csv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mItemList = new ArrayList<ItemListVO>();
        /**初始化 生成 所有 临时 数据*/
        for (int i = 0; i < tempData0.length; i++) {
            ItemListVO vo = new ItemListVO();
            vo.setItemName(tempData0[i]);
            vo.setSonItems(tempDatas[i]);
            mItemList.add(vo);
        }


        /**默认显示的view*/
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View defView = inflater.inflate(R.layout.def_view, null);
        Button tempBtn = (Button) defView.findViewById(R.id.def_view_btn);
        tempBtn.setText(mItemList.get(0).getSonItems()[0]);

        csv = (ClassifySelectView) findViewById(R.id.classify_select_view);
        csv.setOnSelectListener(this);
        csv.initViewData(mItemList, defView);

    }


    private String[] tempData0 = {"菜品", "工艺", "菜系", "人群", "功能"};

    /*按菜品选择菜谱
    按工艺选择菜谱
    按菜系选择菜谱
    按人群选择菜谱
    按功能选择菜谱*/
    private String[][] tempDatas = {
            {"荤菜", "素菜", "汤粥", "西点", "主食", "饮品", "便当", "小吃"},
            {"红烧", "炒", "煎", "炸", "焖", "炖", "蒸", "烩", "熏", "腌", "煮", "炝", "卤", "拌", "烤"},
            {"鲁菜", "川菜", "粤菜", "闽菜", "浙菜", "湘菜", "上海菜", "徽菜", "京菜", "东北菜", "西北菜", "客家菜", "台湾美食", "泰国菜", "日本料理", "韩国料理", "西餐"},
            {"孕妇食谱", "婴幼食谱", "儿童食谱", "懒人食谱", "宵夜", "素食", "产妇食谱", "二人世界", "下午茶"},
            {"减肥", "便秘", "养胃", "滋阴", "补阳", "月经不调", "美容", "养生", "贫血", "润肺"}
    };


    //点击后标签后，需要显示在界面上的内容，需要自定义
    @Override
    public void onItemClick(int first, int second) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View newView = inflater.inflate(R.layout.def_view, null);
        Button tempBtn = (Button) newView.findViewById(R.id.def_view_btn);
        tempBtn.setText(mItemList.get(csv.mFirstLevel).getSonItems()[csv.mSecondLevel]);

        csv.setChangeView(newView);
    }


}
