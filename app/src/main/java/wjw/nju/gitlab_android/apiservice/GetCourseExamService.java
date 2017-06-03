package wjw.nju.gitlab_android.apiservice;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import wjw.nju.gitlab_android.apiservice.apiVO.PublishTaskVO;
import wjw.nju.gitlab_android.apiservice.apiVO.StudentVO;
import wjw.nju.gitlab_android.util.APIHttpRequestUtil;
import wjw.nju.gitlab_android.util.JsonUtil;

/**
 * Created by wangjiawei on 2017/6/3.
 */

public class GetCourseExamService extends AsyncTask<String, String ,String>{

    private String token;

    private String courseId;

    private Handler handler;

    private GET_COURSE_TYPE type;

    public enum GET_COURSE_TYPE{
        EXAM, HOMEWORK, EXERCISE
    }

    public GetCourseExamService(String courseId,String token,Handler handler,GET_COURSE_TYPE type) {
        this.token = token;
        this.courseId = courseId;
        this.handler = handler;
        this.type = type;
    }

    @Override
    protected String doInBackground(String... params) {
        String re = null;
        try {
            JSONObject js = new JSONObject();
            switch (type) {
                case EXAM:
                    re = APIHttpRequestUtil.getJSON(APIConfig.GET_COURSE_EXAM_BY_COURSE_SERVICE(courseId), js, token); break;
                case HOMEWORK:
                    re = APIHttpRequestUtil.getJSON(APIConfig.GET_COURSE_HOMEWORK_BY_COURSE_SERVICE(courseId), js, token); break;
                case EXERCISE:
                    re = APIHttpRequestUtil.getJSON(APIConfig.GET_COURSE_EXERCISE_BY_COURSE_SERVICE(courseId), js, token); break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Message msg = handler.obtainMessage();
        try {
            List<PublishTaskVO> publishTaskVOs = JsonUtil.stringToList(result,PublishTaskVO.class);
            msg.obj = publishTaskVOs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.sendMessage(msg);
    }
}
