package wjw.nju.gitlab_android.apiservice;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import org.json.JSONObject;

import java.util.List;

import wjw.nju.gitlab_android.apiservice.apiVO.CourseVO;
import wjw.nju.gitlab_android.apiservice.apiVO.ReadmeVO;
import wjw.nju.gitlab_android.util.APIHttpRequestUtil;
import wjw.nju.gitlab_android.util.JsonUtil;

/**
 * Created by wangjiawei on 2017/6/28.
 */

public class GetCoursesService extends AsyncTask<String, String,String> {

    private String token;

    private Handler handler;


    public GetCoursesService(String token, Handler handler) {
        this.token = token;
        this.handler = handler;
    }

    @Override
    protected String doInBackground(String... params) {
        String re = "[{\"id\":\"2\",\"name\":\"软件工程\"}]";

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
