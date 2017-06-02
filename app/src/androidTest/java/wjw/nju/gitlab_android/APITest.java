package wjw.nju.gitlab_android;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import wjw.nju.gitlab_android.apiservice.GetAllClassService;
import wjw.nju.gitlab_android.util.Base64EncodeUtil;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class APITest {

    @Test
    public void test(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        Handler h = new Handler(appContext.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                System.out.println(msg.obj);
            }
        };
        GetAllClassService getAllClassService = new GetAllClassService(h, Base64EncodeUtil.getToken("liuqin","123"));
        getAllClassService.execute();
    }
}
