package wjw.nju.gitlab_android.adapter.Item;

/**
 * Created by wangjiawei on 2017/6/8.
 */

public class IndexInfoItem {

    private String title;

    private String subtitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public IndexInfoItem(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }
}
