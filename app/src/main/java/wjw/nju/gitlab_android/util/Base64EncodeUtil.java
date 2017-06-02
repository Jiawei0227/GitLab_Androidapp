package wjw.nju.gitlab_android.util;

import android.util.Base64;

/**
 * Created by wangjiawei on 2017/6/2.
 */

public class Base64EncodeUtil {

    public static String getToken(String username, String password){
        String base = username + ":" + password;
        return Base64.encodeToString(base.getBytes(),Base64.DEFAULT);
    }

}
