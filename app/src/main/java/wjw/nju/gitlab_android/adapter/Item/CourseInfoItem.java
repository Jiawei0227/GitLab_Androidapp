package wjw.nju.gitlab_android.adapter.Item;

import android.view.View;

import wjw.nju.gitlab_android.apiservice.apiVO.CourseVO;

/**
 * Created by wangjiawei on 2017/6/8.
 */

public class CourseInfoItem extends CourseVO {

    private View.OnClickListener listener;

    public CourseInfoItem(String id, String name) {
        super(id, name);
    }

    public CourseInfoItem(String id, String name,  View.OnClickListener listener){
        super(id,name);
        this.listener = listener;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }
}
