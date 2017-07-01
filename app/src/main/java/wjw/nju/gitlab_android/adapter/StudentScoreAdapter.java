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
import wjw.nju.gitlab_android.adapter.Item.StudentScoreItem;

/**
 * Created by wangjiawei on 2017/7/1.
 */

public class StudentScoreAdapter extends BaseAdapter{

        private List<StudentScoreItem> courseInfos;
        private Context context;
        private LayoutInflater layoutInflater;

        public StudentScoreAdapter(List<StudentScoreItem> indexInfoItems, Context context ){
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
                convertView = layoutInflater.inflate(R.layout.question_info_student_score_item,null);
            }

            TextView title = (TextView)convertView.findViewById(R.id.item_student_number);
            title.setText(courseInfos.get(position).number);

            TextView title2 = (TextView)convertView.findViewById(R.id.item_student_name);
            title2.setText(courseInfos.get(position).name);

            TextView title3 = (TextView)convertView.findViewById(R.id.item_student_score);
            title3.setText(courseInfos.get(position).score);


            return convertView;
        }



}
