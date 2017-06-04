package wjw.nju.gitlab_android.apiservice;

import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import wjw.nju.gitlab_android.apiservice.apiVO.StudentVO;
import wjw.nju.gitlab_android.util.APIHttpRequestUtil;

/**
 * Created by wangjiawei on 2017/6/2.
 */

public class GetStudentListByGroupService extends AsyncTask<String, String,String>{

    private String groupId;
    private String token;
    Handler mhandler;

    public GetStudentListByGroupService(String groupId,String token,Handler mhandler){
        this.groupId = groupId;
        this.token = token;
        this.mhandler = mhandler;
    }

    @Override
    protected String doInBackground(String... params) {
        String re = null;
        try {
            JSONObject js = new JSONObject();
            re = APIHttpRequestUtil.getJSON(APIConfig.GET_STUDENT_BY_GROUP_SERVICE(groupId),js,token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Message msg = mhandler.obtainMessage();
        try {
            JSONArray jsonArray = new JSONArray(result);
            List<StudentVO> studentVOList = new ArrayList<>();
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);

                StudentVO studentVO = new StudentVO();
                studentVO.setId(object.getInt("id")+"");
                studentVO.setUsername(object.getString("username"));
                studentVO.setName(object.getString("name"));
                studentVO.setType(object.getString("type"));
                studentVO.setAvatar(object.getString("avatar"));
                studentVO.setGender(object.getString("gender"));
                studentVO.setEmail(object.getString("email"));
                studentVO.setSchoolId(object.getInt("schoolId")+"");
                studentVO.setGitId(object.getInt("gitId")+"");
                studentVO.setNumber(object.getInt("number")+"");
                studentVO.setGroupId(object.getInt("groupId")+"");
                studentVO.setGitUsername(object.getString("gitUsername"));

                studentVOList.add(studentVO);
            }
            msg.obj = studentVOList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        mhandler.sendMessage(msg);
    }
}
