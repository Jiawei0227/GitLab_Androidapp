package wjw.nju.gitlab_android;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import wjw.nju.gitlab_android.apiservice.APIConfig;
import wjw.nju.gitlab_android.apiservice.GetAllClassService;
import wjw.nju.gitlab_android.apiservice.GetAssignmentScoreService;
import wjw.nju.gitlab_android.apiservice.GetCourseAssignmentService;
import wjw.nju.gitlab_android.apiservice.GetStudentListByGroupService;
import wjw.nju.gitlab_android.apiservice.apiVO.PublishTaskVO;
import wjw.nju.gitlab_android.apiservice.apiVO.ScoreVO;
import wjw.nju.gitlab_android.util.APIHttpRequestUtil;
import wjw.nju.gitlab_android.util.Base64EncodeUtil;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class APITest {

    @Test
    public void test1(){
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

    @Test
    public void test2(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        Handler h = new Handler(appContext.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                System.out.println(msg.obj);
            }
        };
        GetStudentListByGroupService getAllClassService = new GetStudentListByGroupService("1",Base64EncodeUtil.getToken("liuqin","123"),h);
        getAllClassService.execute();
    }
    @Test
    public void test3(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        Handler h = new Handler(appContext.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                List<PublishTaskVO> re = (List<PublishTaskVO>)msg.obj;
                System.out.println(re.get(0).getCurrentTime());
            }
        };
        GetCourseAssignmentService getAllClassService = new GetCourseAssignmentService("1",Base64EncodeUtil.getToken("141250143","141250143"),h, GetCourseAssignmentService.GET_COURSE_TYPE.EXAM);
        getAllClassService.execute();
    }

    @Test
    public void test4(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        //APIHttpRequestUtil.getJSON(APIConfig.GET_ASSIGNMENT_SCORE("38"),new JSONObject(),Base64EncodeUtil.getToken("liuqin","123"));
        Handler h = new Handler(appContext.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                List<ScoreVO> re = (List<ScoreVO>)msg.obj;
                System.out.println(re.get(0).getAssignmentId());
            }
        };
        GetAssignmentScoreService getAllClassService = new GetAssignmentScoreService("38",Base64EncodeUtil.getToken("liuqin","123"),h);
        getAllClassService.execute();

    }
}
