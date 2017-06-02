package wjw.nju.gitlab_android.apiservice.apistate;

/**
 * Created by wangjiawei on 2017/6/2.
 */

public class CourseVO {

    private String name;

    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseVO(String id, String name) {
        this.name = name;
        this.id = id;
    }
}
