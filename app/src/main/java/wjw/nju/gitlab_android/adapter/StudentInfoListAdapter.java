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
import wjw.nju.gitlab_android.adapter.Item.CourseInfoItem;
import wjw.nju.gitlab_android.adapter.Item.StudentInfoItem;
import wjw.nju.gitlab_android.apiservice.apiVO.StudentVO;

/**
 * Created by wangjiawei on 2017/6/8.
 */

public class StudentInfoListAdapter extends BaseAdapter {

    private List<StudentInfoItem> studentVOs;
    private Context context;
    private LayoutInflater layoutInflater;

    public StudentInfoListAdapter(List<StudentInfoItem> indexInfoItems,Context context ){
        this.studentVOs = indexInfoItems;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return studentVOs.size();
    }

    @Override
    public Object getItem(int position) {
        return studentVOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.student_info_listview_item,null);
        }

        TextView title = (TextView)convertView.findViewById(R.id.student_info_number);
        title.setText(studentVOs.get(position).studentVO.getNumber());

        TextView title1 = (TextView)convertView.findViewById(R.id.student_info_name);
        title1.setText(studentVOs.get(position).studentVO.getName());

        TextView title2 = (TextView)convertView.findViewById(R.id.student_info_gitName);
        title2.setText(studentVOs.get(position).studentVO.getGitUsername());

        ImageView arrow = (ImageView) convertView.findViewById(R.id.student_info_detail);
        arrow.setOnClickListener(studentVOs.get(position).clickListener);

        return convertView;
    }
}
