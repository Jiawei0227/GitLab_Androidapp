package wjw.nju.gitlab_android.apiservice;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import org.json.JSONObject;

import java.util.List;

import wjw.nju.gitlab_android.apiservice.apiVO.ReadmeVO;
import wjw.nju.gitlab_android.apiservice.apiVO.ScoreVO;
import wjw.nju.gitlab_android.util.APIHttpRequestUtil;
import wjw.nju.gitlab_android.util.JsonUtil;

/**
 * Created by wangjiawei on 2017/6/26.
 */

public class GetReadmeService  extends AsyncTask<String, String,String> {

    private String token;

    private String assignmentId;

    private String studentId;

    private Handler handler;


    public GetReadmeService(String assignmentId, String studentId, String token, Handler handler) {
        this.token = token;
        this.studentId = studentId;
        this.assignmentId = assignmentId;
        this.handler = handler;
    }

    @Override
    protected String doInBackground(String... params) {
        String re = null;
        try {
            re = APIHttpRequestUtil.getJSON(APIConfig.GET_README(assignmentId,studentId), new JSONObject(), token);
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
            List<ReadmeVO> publishTaskVOs = JsonUtil.stringToList(result,ReadmeVO.class);
            msg.obj = publishTaskVOs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.sendMessage(msg);
    }

}
