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
import wjw.nju.gitlab_android.adapter.IndexInfoListAdapter;
import wjw.nju.gitlab_android.adapter.Item.IndexInfoItem;
import wjw.nju.gitlab_android.adapter.Item.StudentInfoItem;
import wjw.nju.gitlab_android.adapter.StudentInfoListAdapter;
import wjw.nju.gitlab_android.apiservice.GetStudentListByGroupService;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.apiservice.apiVO.StudentVO;
import wjw.nju.gitlab_android.util.Base64EncodeUtil;


public class StudentInfoDetailFragment extends Fragment {

    private StudentVO studentVO;
    private ListView studentList;
    private List<IndexInfoItem> indexInfoItemList;


    public static StudentInfoDetailFragment getInstatnce( StudentVO studentVO){
        StudentInfoDetailFragment fragment = new StudentInfoDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("studentVO", studentVO);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_student_info_detail, container, false);

        initVO(view);
        initComponent(view);
        return view;
    }

    private void initVO(View view){
        if (getArguments() != null) {
            studentVO = (StudentVO) getArguments().getSerializable("studentVO");
        }

    }

    private void initComponent(View view){
        indexInfoItemList = new ArrayList<>();
        indexInfoItemList.add(new IndexInfoItem("姓名",studentVO.getName()));
        indexInfoItemList.add(new IndexInfoItem("用户名",studentVO.getUsername()));
        String type = "";
        switch (studentVO.getType()){
            case "student": type = getResources().getString(R.string.student); break;
            case "teacher": type = getResources().getString(R.string.teacher); break;
            case "admin": type = getResources().getString(R.string.admin); break;
        }
        indexInfoItemList.add(new IndexInfoItem("类型",type));
        String gender = "";
        switch (studentVO.getGender()){
            case "male": gender = "男"; break;
            case "female": gender = "女"; break;
        }
        indexInfoItemList.add(new IndexInfoItem("性别",gender));
        indexInfoItemList.add(new IndexInfoItem("GitID",studentVO.getGitId()==null?"暂无":studentVO.getGitId()));
        indexInfoItemList.add(new IndexInfoItem("学号",studentVO.getNumber()==null?"暂无":studentVO.getNumber()));


        studentList = (ListView)view.findViewById(R.id.student_detail_info);
        studentList.setAdapter(new IndexInfoListAdapter(indexInfoItemList, this.getActivity()));

    }
}
