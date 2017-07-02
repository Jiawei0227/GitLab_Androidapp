package wjw.nju.gitlab_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.adapter.Item.IndexInfoItem;
import wjw.nju.gitlab_android.apiservice.apiVO.AssignmentAnalysisVO;

/**
 * Created by wangjiawei on 2017/7/3.
 */

public class TestCaseAdapter extends BaseAdapter{

    private List<AssignmentAnalysisVO.QuestionResultsBean.TestResultBean.TestcasesBean> infos;
    private Context context;
    private LayoutInflater layoutInflater;

    public TestCaseAdapter(List<AssignmentAnalysisVO.QuestionResultsBean.TestResultBean.TestcasesBean> infos, Context context ){
        this.infos = infos;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.testcase_item,null);
        }


        TextView title = (TextView)convertView.findViewById(R.id.item_testcase_name);
        title.setText(infos.get(position).getName());

        ImageView arrow = (ImageView) convertView.findViewById(R.id.item_testcase_check);
        if(infos.get(position).isPassed())
            arrow.setImageResource(R.mipmap.right);
        else
            arrow.setImageResource(R.mipmap.wrong);

        return convertView;
    }
}
