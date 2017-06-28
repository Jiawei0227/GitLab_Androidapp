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
import wjw.nju.gitlab_android.adapter.Item.PublishTaskItem;

/**
 * Created by wangjiawei on 2017/6/28.
 */

public class AssignmentListAdapter extends BaseAdapter{

    private List<PublishTaskItem> courseInfos;
    private Context context;
    private LayoutInflater layoutInflater;

    public AssignmentListAdapter(List<PublishTaskItem> indexInfoItems, Context context ){
        this.courseInfos = indexInfoItems;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return courseInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return courseInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.assignment_item,null);
        }

        TextView title = (TextView)convertView.findViewById(R.id.assignment_title);
        title.setText(courseInfos.get(position).title+":"+courseInfos.get(position).state);


        ImageView arrow = (ImageView) convertView.findViewById(R.id.assignment_detail);
        arrow.setOnClickListener(courseInfos.get(position).clickListener);

        return convertView;
    }

}
