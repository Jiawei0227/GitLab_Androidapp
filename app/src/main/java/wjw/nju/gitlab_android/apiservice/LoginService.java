package wjw.nju.gitlab_android.apiservice;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.util.APIHttpRequestUtil;

/**
 * Created by wangjiawei on 2017/5/31.
 */

public class LoginService extends AsyncTask<String, String ,String>{

    Handler mhandler;
    String username;
    String password;

    public LoginService(Handler handler,String username,String password){
        this.mhandler = handler;
        this.username = username;
        this.password = password;

    }


    @Override
    protected String doInBackground(String... params) {
        String re = null;
        try {
            JSONObject js = new JSONObject();
            js.put("username",username);
            js.put("password",password);
            re = APIHttpRequestUtil.postJSON(APIConfig.LOGIN_SERVICE,js);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Message msg = mhandler.obtainMessage();
        LoginVO loginVO = null;
        if(result.equals("")){
            loginVO = new LoginVO(LoginVO.LoginState.LOGIN_FAILURE);
        }else{
            try {
                JSONObject jsonObject = new JSONObject(result);
                loginVO = new LoginVO(LoginVO.LoginState.LOGIN_SUCCESS);
                loginVO.setUsername(jsonObject.getString("username"));
                loginVO.setName(jsonObject.getString("name"));
                loginVO.setType(LoginVO.LoginType.valueOf(jsonObject.getString("type")));
                loginVO.setAvatar(jsonObject.getString("avatar"));
                loginVO.setGender(LoginVO.Gender.valueOf(jsonObject.getString("gender")));
                loginVO.setEmail(jsonObject.getString("email"));
                loginVO.setPassword(password);
                if(loginVO.getType().equals(LoginVO.LoginType.student)){
                    loginVO.setS_git_id(jsonObject.getInt("gitId")+"");
                    loginVO.setS_number(jsonObject.getString("number"));
                }else if(loginVO.getType().equals(LoginVO.LoginType.teacher)){
                    loginVO.setT_authority(jsonObject.getString("authority"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        msg.what = 1;
        msg.obj = loginVO;
        mhandler.sendMessage(msg);
    }

}
