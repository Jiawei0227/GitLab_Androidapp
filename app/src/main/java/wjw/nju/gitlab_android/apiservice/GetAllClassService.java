package wjw.nju.gitlab_android.apiservice;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import wjw.nju.gitlab_android.apiservice.apiVO.CourseVO;
import wjw.nju.gitlab_android.util.APIHttpRequestUtil;

/**
 * Created by wangjiawei on 2017/6/1.
 */

public class GetAllClassService extends AsyncTask<String,String,String> {

    private String token;
    Handler mhandler;

    public GetAllClassService(Handler handler, String token){
        mhandler = handler;
        this.token = token;
    }

    @Override
    protected String doInBackground(String... params) {
        String re = null;
        try {
            JSONObject js = new JSONObject();
            re = APIHttpRequestUtil.getJSON(APIConfig.GET_ALL_CLASS_INFO_SERVICE,js,token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Message msg = mhandler.obtainMessage();
        try {
            JSONArray jsonArray = new JSONArray(result);
            List<CourseVO> courseVOList = new ArrayList<>();
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                courseVOList.add(new CourseVO(object.getInt("id")+"" , object.getString("name")));
            }
            msg.obj = courseVOList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mhandler.sendMessage(msg);
    }
}
