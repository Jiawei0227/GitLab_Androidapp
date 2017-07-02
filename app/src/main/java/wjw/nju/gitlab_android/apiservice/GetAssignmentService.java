package wjw.nju.gitlab_android.apiservice;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.util.List;

import wjw.nju.gitlab_android.apiservice.apiVO.CourseVO;
import wjw.nju.gitlab_android.util.JsonUtil;

/**
 * Created by wangjiawei on 2017/6/28.
 */

public class GetAssignmentService  extends AsyncTask<String, String,String> {

    private String token;

    private Handler handler;


    public GetAssignmentService(String token, Handler handler) {
        this.token = token;
        this.handler = handler;
    }

    @Override
    protected String doInBackground(String... params) {
        String re = "[{\"id\":\"38\",\"name\":\"android测试1\"},{\"id\":\"93\",\"name\":\"android测试2\"},{\"id\":\"98\",\"name\":\"android测试3\"}]";

        return re;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Message msg = handler.obtainMessage();
        try {
            List<CourseVO> courseVOs = JsonUtil.stringToList(result,CourseVO.class);
            msg.obj = courseVOs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.sendMessage(msg);
    }
}
