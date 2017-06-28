package wjw.nju.gitlab_android.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.adapter.AssignmentListAdapter;
import wjw.nju.gitlab_android.adapter.Item.PublishTaskItem;
import wjw.nju.gitlab_android.apiservice.GetCourseAssignmentService;
import wjw.nju.gitlab_android.apiservice.apiVO.PublishTaskVO;
import wjw.nju.gitlab_android.util.Base64EncodeUtil;

/**
 * Created by wangjiawei on 2017/6/28.
 */

public class QuestionListFragment extends Fragment{

    private List<PublishTaskVO.QuestionsBean> vos;
    private String fragmentType;
    private ListView studentList;

    public QuestionListFragment() {
        // Required empty public constructor
    }


    public static Fragment newInstance(PublishTaskVO vo) {
        QuestionListFragment fragment = new QuestionListFragment();
        Bundle args = new Bundle();
        args.putSerializable("vo" , vo);
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
            PublishTaskVO p = (PublishTaskVO) getArguments().getSerializable("vo");
            vos = p.getQuestions();
        }
        initComponent(view);

    }

    private void initComponent(View view){
        List<PublishTaskItem> lists = new ArrayList<>();
        for (PublishTaskVO.QuestionsBean c: vos) {
            PublishTaskItem studentInfoItem = new PublishTaskItem();
            studentInfoItem.clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frame_main,QuestionDetailFragment.newInstance(c)).addToBackStack(null).commit();
                }
            };
            studentInfoItem.title = c.getTitle();
            studentInfoItem.state = "难度-"+ (c.getDifficulty()==null?"无":c.getDifficulty());
            lists.add(studentInfoItem);
        }

        studentList = (ListView) view.findViewById(R.id.listview_examList);
        AssignmentListAdapter assignmentListAdapter = new AssignmentListAdapter(lists,this.getActivity());
        studentList.setAdapter(assignmentListAdapter);

    }
}
