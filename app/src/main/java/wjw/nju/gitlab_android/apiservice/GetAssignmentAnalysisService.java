package wjw.nju.gitlab_android.apiservice;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import org.json.JSONObject;

import wjw.nju.gitlab_android.apiservice.apiVO.AssignmentAnalysisVO;
import wjw.nju.gitlab_android.apiservice.apiVO.AssignmentScoreVO;
import wjw.nju.gitlab_android.util.APIHttpRequestUtil;
import wjw.nju.gitlab_android.util.JsonUtil;

/**
 * Created by wangjiawei on 2017/7/1.
 */

public class GetAssignmentAnalysisService extends AsyncTask<String,String,String>{

    private String token;

    private String assignmentId;

    private String studentId;

    private Handler handler;


    public GetAssignmentAnalysisService(String assignmentId,String studentId, String token, Handler handler) {
        this.token = token;
        this.assignmentId = assignmentId;
        this.handler = handler;
    }

    @Override
    protected String doInBackground(String... params) {
        String re = null;
        try {
            System.out.println(assignmentId + " "+ token);
            re = APIHttpRequestUtil.getJSON(APIConfig.GET_ASSIGNMENT_ANALYSIS(assignmentId,studentId), new JSONObject(), token);
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
            AssignmentAnalysisVO publishTaskVOs = (AssignmentAnalysisVO) JsonUtil.stringToObject(result,AssignmentAnalysisVO.class);
            msg.obj = publishTaskVOs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.sendMessage(msg);
    }

}
