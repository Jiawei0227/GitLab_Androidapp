package wjw.nju.gitlab_android.apiservice;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.HashMap;

import wjw.nju.gitlab_android.activity.MainActivity;
import wjw.nju.gitlab_android.util.HttpRequestUtil;

/**
 * Created by wangjiawei on 2017/5/31.
 */

public class LoginService extends AsyncTask<String, String ,JSONArray>{

    Handler mhandler = new Handler();
    String username;
    String password;

    public LoginService(Handler handler,String username,String password){
        this.mhandler = handler;
        this.username = username;
        this.password = password;

    }


    @Override
    protected JSONArray doInBackground(String... params) {
        JSONArray jsonArray = null;
        try {
            HashMap<String,String> map = new HashMap<>();
            map.put("username",username);
            map.put("password",password);
            jsonArray = HttpRequestUtil.sendPostRequest("http://115.29.184.56:8090/api/user/auth",map,null);
            Log.i("",jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        super.onPostExecute(result);
        Message msg = mhandler.obtainMessage();
        msg.what = 1;
        msg.obj = result.toString();
        mhandler.sendMessage(msg);
    }

}
