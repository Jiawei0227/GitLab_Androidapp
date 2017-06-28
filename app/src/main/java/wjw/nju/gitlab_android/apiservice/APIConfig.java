package wjw.nju.gitlab_android.apiservice;

/**
 * Created by wangjiawei on 2017/6/2.
 */

public class APIConfig {

    public static final String BASEURL= "http://115.29.184.56:8090/api/";

    public static final String LOGIN_SERVICE = BASEURL + "user/auth";

    public static final String GET_ALL_CLASS_INFO_SERVICE = BASEURL + "group" ;

    public static String GET_ASSIGNMENT_SCORE(String assignmentId){
      return BASEURL + "assignment/"+ assignmentId +"/score";
    }

    public static String GET_README(String assignmentId, String studentId){
        return BASEURL + "/assignment/"+assignmentId+"/student/"+studentId+"/analysis";
    }

    public static String GET_STUDENT_BY_GROUP_SERVICE(String groupId) {
        return BASEURL + "group/"+groupId+"/students";
    }

    public static String GET_COURSE_EXAM_BY_COURSE_SERVICE(String courseId) {
        return BASEURL + "course/"+courseId+"/exam";
    }

    public static String GET_COURSE_HOMEWORK_BY_COURSE_SERVICE(String courseId) {
        return BASEURL + "course/"+courseId+"/homework";
    }

    public static String GET_COURSE_EXERCISE_BY_COURSE_SERVICE(String courseId) {
        return BASEURL + "course/"+courseId+"/exercise";
    }
}
