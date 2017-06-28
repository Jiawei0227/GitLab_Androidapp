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
import wjw.nju.gitlab_android.activity.MenuActivity;
import wjw.nju.gitlab_android.adapter.CourseListAdapter;
import wjw.nju.gitlab_android.adapter.Item.CourseInfoItem;
import wjw.nju.gitlab_android.apiservice.GetAllClassService;
import wjw.nju.gitlab_android.apiservice.GetCoursesService;
import wjw.nju.gitlab_android.apiservice.apiVO.CourseVO;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.apiservice.apiVO.PublishTaskVO;
import wjw.nju.gitlab_android.util.Base64EncodeUtil;

public class CourseListFragment extends Fragment {

    private String fragmentType ;
    private ListView listView;
    private List<CourseVO> courseVOs;
    private LoginVO loginVO;

    public CourseListFragment() {
        // Required empty public constructor
    }

    public static CourseListFragment newInstance(String param1, String param2) {
        CourseListFragment fragment = new CourseListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);
        initVO(view);
        return view;
    }

    private void initVO(View view){
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            loginVO = (LoginVO) bundle.getSerializable(MenuActivity.LOGIN_VO);
            fragmentType = bundle.getString("fragmentType");
        }

        Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg){
                courseVOs = (List<CourseVO>) msg.obj;
                initComponent(view);
            }
        };

        GetCoursesService getAllClassService = new GetCoursesService( Base64EncodeUtil.getToken(loginVO.getUsername(),loginVO.getPassword()),h);
        getAllClassService.execute();
    }

    private void initComponent(View view){
        List<CourseInfoItem> lists = new ArrayList<>();
        for (CourseVO c: courseVOs) {
            CourseInfoItem studentInfoItem = new CourseInfoItem(c.getId(),c.getName());
            studentInfoItem.setListener( e->{
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_main,ExamListFragment.newInstance(c.getId(),loginVO,fragmentType)).addToBackStack(null).commit();
            });
            lists.add(studentInfoItem);
        }

        listView = (ListView) view.findViewById(R.id.Listview_courseList);
        CourseListAdapter c = new CourseListAdapter(lists,this.getActivity());
        listView.setAdapter(c);

    }


}
