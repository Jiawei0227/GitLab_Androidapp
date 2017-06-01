package wjw.nju.gitlab_android.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by wangjiawei on 2017/5/31.
 */

public class GetJsonDataUtil {
    private GetJsonDataUtil(){}

    public JSONObject getjsonData(Map<String,String> map)  {
        JSONObject js=new JSONObject();
        try {
            for(Map.Entry<String, String> entry : map.entrySet()){
                js.put(entry.getKey(),entry.getValue());
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return js;
    }
}
