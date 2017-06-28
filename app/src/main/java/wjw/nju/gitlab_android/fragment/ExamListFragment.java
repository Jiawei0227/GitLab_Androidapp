package wjw.nju.gitlab_android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.adapter.AssignmentListAdapter;
import wjw.nju.gitlab_android.adapter.Item.IndexInfoItem;
import wjw.nju.gitlab_android.adapter.Item.PublishTaskItem;
import wjw.nju.gitlab_android.apiservice.GetCourseAssignmentService;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.apiservice.apiVO.PublishTaskVO;
import wjw.nju.gitlab_android.util.Base64EncodeUtil;

public class ExamListFragment extends Fragment {
    private LoginVO loginVO;
    private String courseId;
    private List<PublishTaskVO> studentVOs;
    private String fragmentType;
    private ListView studentList;

    public ExamListFragment() {
        // Required empty public constructor
    }


    public static ExamListFragment newInstance(String courseId, LoginVO loginVo, String fragmentType) {
        ExamListFragment fragment = new ExamListFragment();
        Bundle args = new Bundle();
        args.putString("fragmentType" ,fragmentType);
        args.putString("courseId", courseId);
        args.putSerializable("loginVO",loginVo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exam_list, container, false);
        initVO(view);
        return view;
    }

    private void initVO(View view){
        if (getArguments() != null) {
            loginVO = (LoginVO) getArguments().getSerializable("loginVO");
            fragmentType = getArguments().getString("fragmentType");
            courseId = getArguments().getString("courseId");
        }
        Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg){
                studentVOs = (List<PublishTaskVO>) msg.obj;
                initComponent(view);
            }
        };
        if(fragmentType.equals("StudentExam")) {
            GetCourseAssignmentService getCourseAssignmentService = new GetCourseAssignmentService(courseId, Base64EncodeUtil.getToken(loginVO.getUsername(), loginVO.getPassword()), h, GetCourseAssignmentService.GET_COURSE_TYPE.EXAM);
            getCourseAssignmentService.execute();
        }else if(fragmentType.equals("StudentExercise")) {
            GetCourseAssignmentService getCourseAssignmentService = new GetCourseAssignmentService(courseId, Base64EncodeUtil.getToken(loginVO.getUsername(), loginVO.getPassword()), h, GetCourseAssignmentService.GET_COURSE_TYPE.EXERCISE);
            getCourseAssignmentService.execute();
        }else if(fragmentType.equals("StudentHomework")) {
            GetCourseAssignmentService getCourseAssignmentService = new GetCourseAssignmentService(courseId, Base64EncodeUtil.getToken(loginVO.getUsername(), loginVO.getPassword()), h, GetCourseAssignmentService.GET_COURSE_TYPE.HOMEWORK);
            getCourseAssignmentService.execute();
        }


    }

    private void initComponent(View view){
        List<PublishTaskItem> lists = new ArrayList<>();
        for (PublishTaskVO c: studentVOs) {
            PublishTaskItem studentInfoItem = new PublishTaskItem();
            studentInfoItem.clickListener = e->{
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_main,ExamDetailFragment.newInstance(loginVO,c));
                ft.addToBackStack(null);
                ft.commit();
            };
            studentInfoItem.title = c.getTitle();
            studentInfoItem.state = c.getStatus();
            lists.add(studentInfoItem);
        }

        studentList = (ListView) view.findViewById(R.id.listview_examList);
        AssignmentListAdapter assignmentListAdapter = new AssignmentListAdapter(lists,this.getActivity());
        studentList.setAdapter(assignmentListAdapter);

    }


}
