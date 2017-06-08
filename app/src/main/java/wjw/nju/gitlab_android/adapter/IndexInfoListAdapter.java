package wjw.nju.gitlab_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.adapter.Item.IndexInfoItem;

/**
 * Created by wangjiawei on 2017/6/8.
 */

public class IndexInfoListAdapter extends BaseAdapter {

    private List<IndexInfoItem> indexInfoItems;
    private Context context;
    private LayoutInflater layoutInflater;

    public IndexInfoListAdapter(List<IndexInfoItem> indexInfoItems,Context context){
        this.indexInfoItems = indexInfoItems;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return indexInfoItems.size();
    }

    @Override
    public Object getItem(int position) {
        return indexInfoItems.get(position);
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
        title.setText(indexInfoItems.get(position).getTitle());

        TextView subTitle = (TextView)convertView.findViewById(R.id.index_info_subtitle);
        subTitle.setText(indexInfoItems.get(position).getSubtitle());

        return convertView;
    }
}
