package wjw.nju.gitlab_android.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.adapter.Item.CourseInfoItem;
import wjw.nju.gitlab_android.apiservice.apiVO.CourseVO;
import wjw.nju.gitlab_android.fragment.StudentListFragment;

/**
 * Created by wangjiawei on 2017/6/8.
 */

public class ClassInfoAdapter extends BaseAdapter{

    private List<CourseInfoItem> courseInfos;
    private Context context;
    private LayoutInflater layoutInflater;

    public ClassInfoAdapter(List<CourseInfoItem> indexInfoItems,Context context ){
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
            convertView = layoutInflater.inflate(R.layout.class_info_listview_item,null);
        }

        TextView title = (TextView)convertView.findViewById(R.id.class_className);
        title.setText(courseInfos.get(position).getName());

        ImageView arrow = (ImageView) convertView.findViewById(R.id.class_classInfo_detail);
        arrow.setOnClickListener(courseInfos.get(position).getListener());

        return convertView;
    }

}
