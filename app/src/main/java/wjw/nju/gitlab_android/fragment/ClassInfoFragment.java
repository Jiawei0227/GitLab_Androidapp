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
import java.util.stream.Collector;
import java.util.stream.Collectors;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.activity.TeacherMenu;
import wjw.nju.gitlab_android.adapter.ClassInfoAdapter;
import wjw.nju.gitlab_android.adapter.Item.CourseInfoItem;
import wjw.nju.gitlab_android.apiservice.GetAllClassService;
import wjw.nju.gitlab_android.apiservice.apiVO.CourseVO;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.util.Base64EncodeUtil;

public class ClassInfoFragment extends Fragment{

    private LoginVO loginVO;
    private List<CourseVO> courseVOs;
    private ListView classInfoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_class_info, container, false);
        initVO(view);

        return view;
    }

    private void initVO(View view){
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            loginVO = (LoginVO) bundle.getSerializable(TeacherMenu.LOGIN_VO);
        }

        Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg){
                courseVOs = (List<CourseVO>) msg.obj;
                initComponent(view);
            }
        };

        GetAllClassService getAllClassService = new GetAllClassService(h, Base64EncodeUtil.getToken(loginVO.getUsername(),loginVO.getPassword()));
        getAllClassService.execute();
    }

    private void initComponent(View view){
        List<CourseInfoItem> lists = new ArrayList<>();
        for (CourseVO c: courseVOs) {
            lists.add(new CourseInfoItem(c.getId(),c.getName(), e->{
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                ft.replace(R.id.frame_main,StudentListFragment.getInstatnce(c.getId(),loginVO)).commit();
            }));
        }

        classInfoView = (ListView) view.findViewById(R.id.class_listview_info);

        ClassInfoAdapter c = new ClassInfoAdapter(lists,this.getActivity());
        classInfoView.setAdapter(c);

    }


}
