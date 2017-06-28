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

/**
 * Created by wangjiawei on 2017/6/28.
 */

public class ExamDetailAdapter extends BaseAdapter {

    private List<IndexInfoItem> infos;
    private Context context;
    private LayoutInflater layoutInflater;

    public ExamDetailAdapter(List<IndexInfoItem> infos,Context context ){
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
            convertView = layoutInflater.inflate(R.layout.index_info_listview_item,null);
        }


        TextView title = (TextView)convertView.findViewById(R.id.index_info_title);
        title.setText(infos.get(position).getTitle());

        TextView subtitle = (TextView)convertView.findViewById(R.id.index_info_subtitle);
        subtitle.setText(infos.get(position).getSubtitle());

        ImageView arrow = (ImageView) convertView.findViewById(R.id.index_info_image);

        return convertView;
    }
}
