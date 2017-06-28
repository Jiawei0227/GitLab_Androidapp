package wjw.nju.gitlab_android.apiservice;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;


import org.json.JSONObject;

import java.util.List;

import wjw.nju.gitlab_android.apiservice.apiVO.ScoreVO;
import wjw.nju.gitlab_android.util.APIHttpRequestUtil;
import wjw.nju.gitlab_android.util.JsonUtil;

/**
 * Created by wangjiawei on 2017/6/26.
 */
public class GetAssignmentScoreService extends AsyncTask<String,String,String> {
    private String token;

    private String assignmentId;

    private Handler handler;


    public GetAssignmentScoreService(String assignmentId, String token, Handler handler) {
        this.token = token;
        this.assignmentId = assignmentId;
        this.handler = handler;
    }

    @Override
    protected String doInBackground(String... params) {
        String re = null;
        try {
            System.out.println(assignmentId + " "+ token);
            re = APIHttpRequestUtil.getJSON(APIConfig.GET_ASSIGNMENT_SCORE(assignmentId), new JSONObject(), token);
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
            List<ScoreVO> publishTaskVOs = JsonUtil.stringToList(result,ScoreVO.class);
            msg.obj = publishTaskVOs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.sendMessage(msg);
    }
}
