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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.adapter.ClassInfoAdapter;
import wjw.nju.gitlab_android.adapter.CourseListAdapter;
import wjw.nju.gitlab_android.adapter.Item.CourseInfoItem;
import wjw.nju.gitlab_android.adapter.Item.IndexInfoItem;
import wjw.nju.gitlab_android.apiservice.GetAssignmentScoreService;
import wjw.nju.gitlab_android.apiservice.apiVO.AssignmentAnalysisVO;
import wjw.nju.gitlab_android.apiservice.apiVO.AssignmentScoreVO;
import wjw.nju.gitlab_android.apiservice.apiVO.CourseVO;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.util.Base64EncodeUtil;

public class AssignmentDetailFragment extends Fragment {

    private LoginVO loginVO;
    private String assignmentId;
    private AssignmentScoreVO assignmentScoreVO;


    public static AssignmentDetailFragment getInstance(String assignmentId,LoginVO loginVO) {
        AssignmentDetailFragment fragment = new AssignmentDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("loginVO", loginVO);
        args.putString("assignmentId", assignmentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            loginVO = (LoginVO) getArguments().getSerializable("loginVO");
            assignmentId = getArguments().getString("assignmentId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_assignment_detail, container, false);
        initVO(view);
        return view;
    }

    public void initVO(View view){

        Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg){
                assignmentScoreVO = (AssignmentScoreVO) msg.obj;
                initComponent(view);
            }
        };
        GetAssignmentScoreService getAssignmentScoreService = new GetAssignmentScoreService(assignmentId, Base64EncodeUtil.getToken(loginVO.getUsername(),loginVO.getPassword()),h);
        getAssignmentScoreService.execute();
    }

    public void initComponent(View view){
        TextView title = (TextView) view.findViewById(R.id.textview_assignment_detail_title);
        title.setText("Assignment: "+assignmentScoreVO.getAssignmentId());

        List<CourseInfoItem> lists = new ArrayList<>();
        for(AssignmentScoreVO.QuestionsBean q:assignmentScoreVO.getQuestions()){
            CourseInfoItem courseInfoItem = new CourseInfoItem(q.getQuestionInfo().getId()+"",q.getQuestionInfo().getTitle());
            courseInfoItem.setListener(e->{
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_main,QuestionInfoDetailFragment.newInstance(loginVO,q)).addToBackStack(null).commit();
            });

            lists.add(courseInfoItem);
        }

        CourseListAdapter courseListAdapter = new CourseListAdapter(lists,this.getActivity(),R.mipmap.assignments);
        ListView listView = (ListView) view.findViewById(R.id.listview_assignment_detail);
        listView.setAdapter(courseListAdapter);

    }
}
