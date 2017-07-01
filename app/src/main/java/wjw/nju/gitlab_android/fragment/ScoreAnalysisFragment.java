package wjw.nju.gitlab_android.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.util.AnalysisDataVO;

import static android.R.attr.label;
import static android.R.id.list;


public class ScoreAnalysisFragment extends Fragment {

    AnalysisDataVO analysisDataVO;

    public ScoreAnalysisFragment() {

    }


    public static ScoreAnalysisFragment newInstance(AnalysisDataVO p) {
        ScoreAnalysisFragment fragment = new ScoreAnalysisFragment();
        Bundle args = new Bundle();
        args.putSerializable("analysisDataVO",p);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            analysisDataVO = (AnalysisDataVO) getArguments().getSerializable("analysisDataVO");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_score_analysis, container, false);

        initComponent(view);
        setChart(view);
        return view;
    }

    public void initComponent(View view){
        Collections.sort(analysisDataVO.scores, (a, b)-> a.compareTo(b) );

        TextView title = (TextView) view.findViewById(R.id.analysis_total);
        title.setText(analysisDataVO.noScore + analysisDataVO.scores.size()+"");

        TextView title2 = (TextView) view.findViewById(R.id.analysis_average);
        title2.setText(analysisDataVO.total/analysisDataVO.scores.size()+"");

        TextView title3 = (TextView) view.findViewById(R.id.analysis_high);
        title3.setText(analysisDataVO.scores.get(analysisDataVO.scores.size()-1)+"");

        TextView title4 = (TextView) view.findViewById(R.id.analysis_mid);
        title4.setText(analysisDataVO.scores.get(analysisDataVO.scores.size()/2)+"");

        TextView title5 = (TextView) view.findViewById(R.id.analysis_noscore);
        title5.setText(analysisDataVO.noScore+"");
    }

    public void setChart(View view){
        int[] nums = new int[6];
        for(int i: analysisDataVO.scores){
            if (i<60)
                nums[0]+=1;
            else if(i>=60 && i<70)
                nums[1]+=1;
            else if(i>=70 && i<80)
                nums[2]+=1;
            else if(i>=80 && i<90)
                nums[3]+=1;
            else if(i>=90 && i<99)
                nums[4]+=1;
            else if(i == 100)
                nums[5]+=1;

        }
        ColumnChartView chart = (ColumnChartView) view.findViewById(R.id.chart);
        int numSubcolumns = 1;//设置每个柱状图显示的颜色数量(每个柱状图显示多少块)
        int numColumns = 6;//柱状图的数量

        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                SubcolumnValue value = new SubcolumnValue(nums[i],R.color.accent);//第一个值是数值(值>0 方向朝上，值<0，方向朝下)，第二个值是颜色
                values.add(value);
            }
            Column column = new Column(values);//一个柱状图的实例
            column.setHasLabels(true);//设置是否显示每个柱状图的高度，
            //column.setHasLabelsOnlyForSelected(hasLabelForSelected);//点击的时候是否显示柱状图的高度，和setHasLabels()和用的时候，setHasLabels()失效
            columns.add(column);
        }
        ColumnChartData mColumnChartData = new ColumnChartData(columns);//表格的数据实例

        Axis axisX = new Axis();
        AxisValue value1 = new AxisValue(0f);//值是在哪显示 0 是指 第0个柱状图
        value1.setLabel("[60以下]");//设置显示的文本，默认为柱状图的位置
        AxisValue value2 = new AxisValue(1.0f);
        value2.setLabel("[60-70]");
        AxisValue value3 = new AxisValue(2.0f);
        value3.setLabel("[70-80]");
        AxisValue value4 = new AxisValue(3.0f);
        value4.setLabel("[80-90]");
        AxisValue value5 = new AxisValue(4.0f);
        value5.setLabel("[90-99]");
        AxisValue value6 = new AxisValue(5.0f);
        value6.setLabel("[100]");

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        axisValues.add(value1);
        axisValues.add(value2);
        axisValues.add(value3);
        axisValues.add(value4);
        axisValues.add(value5);
        axisValues.add(value6);

        axisX.setValues(axisValues);
        Axis axisY = new Axis().setHasLines(true);

        axisX.setName("分数段");//设置X轴的注释
        axisY.setName("人数");//设置Y轴的注释

        mColumnChartData.setAxisXBottom(axisX);//设置X轴显示的位置
        mColumnChartData.setAxisYLeft(axisY);//设置Y轴显示的位置

        chart.setColumnChartData(mColumnChartData);//为View设置数据
        chart.setZoomEnabled(false);//设置是否支持缩放
    }



}
