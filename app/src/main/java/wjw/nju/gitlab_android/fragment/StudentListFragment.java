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
import wjw.nju.gitlab_android.activity.TeacherMenu;
import wjw.nju.gitlab_android.adapter.ClassInfoAdapter;
import wjw.nju.gitlab_android.adapter.Item.CourseInfoItem;
import wjw.nju.gitlab_android.adapter.Item.StudentInfoItem;
import wjw.nju.gitlab_android.adapter.StudentInfoListAdapter;
import wjw.nju.gitlab_android.apiservice.GetAllClassService;
import wjw.nju.gitlab_android.apiservice.GetStudentListByGroupService;
import wjw.nju.gitlab_android.apiservice.apiVO.CourseVO;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.apiservice.apiVO.StudentVO;
import wjw.nju.gitlab_android.util.Base64EncodeUtil;

public class StudentListFragment extends Fragment {

    private LoginVO loginVO;
    private String groupId;
    private List<StudentVO> studentVOs;
    private ListView studentList;


    public static StudentListFragment getInstatnce(String groupId, LoginVO loginVo){
        StudentListFragment fragment = new StudentListFragment();
        Bundle args = new Bundle();
        args.putString("groupId", groupId);
        args.putSerializable("loginVO",loginVo);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_student_list, container, false);

        initVO(view);

        return view;
    }

    private void initVO(View view){
        if (getArguments() != null) {
            groupId = getArguments().getString("groupId");
            loginVO = (LoginVO) getArguments().getSerializable("loginVO");
        }

        Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg){
                studentVOs = (List<StudentVO>) msg.obj;
                initComponent(view);
            }
        };

        GetStudentListByGroupService getStudentListByGroupService = new GetStudentListByGroupService(groupId, Base64EncodeUtil.getToken(loginVO.getUsername(),loginVO.getPassword()), h);
        getStudentListByGroupService.execute();
    }

    private void initComponent(View view){
        List<StudentInfoItem> lists = new ArrayList<>();
        for (StudentVO c: studentVOs) {
            StudentInfoItem studentInfoItem = new StudentInfoItem();
            studentInfoItem.clickListener = e->{
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_main,StudentInfoDetailFragment.getInstatnce(c)).commit();
            };
            studentInfoItem.studentVO = c;
            lists.add(studentInfoItem);
        }

        studentList = (ListView) view.findViewById(R.id.student_view_list);

        StudentInfoListAdapter c = new StudentInfoListAdapter(lists,this.getActivity());
        studentList.setAdapter(c);

    }



}
