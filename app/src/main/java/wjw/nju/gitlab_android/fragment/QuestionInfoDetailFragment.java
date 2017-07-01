package wjw.nju.gitlab_android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.adapter.Item.StudentScoreItem;
import wjw.nju.gitlab_android.adapter.StudentScoreAdapter;
import wjw.nju.gitlab_android.apiservice.apiVO.AssignmentScoreVO;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.util.AnalysisDataVO;

public class QuestionInfoDetailFragment extends Fragment {

    LoginVO loginVO;
    AssignmentScoreVO.QuestionsBean questionsBean;


    public static QuestionInfoDetailFragment newInstance(LoginVO param1, AssignmentScoreVO.QuestionsBean param2) {
        QuestionInfoDetailFragment fragment = new QuestionInfoDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("loginVO",param1);
        args.putSerializable("questionBean",param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            loginVO = (LoginVO)getArguments().getSerializable("loginVO");
            questionsBean = (AssignmentScoreVO.QuestionsBean)getArguments().getSerializable("questionBean");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_info_detail, container, false);

        initComponent(view);

        return view;
    }

    public void initComponent(View view){
        TextView title = (TextView) view.findViewById(R.id.textview_question_info_title);
        title.setText(questionsBean.getQuestionInfo().getTitle());

        TextView title2 = (TextView) view.findViewById(R.id.textview_question_info_description);
        title2.setText(questionsBean.getQuestionInfo().getDescription());

        TextView title3 = (TextView) view.findViewById(R.id.textview_question_info_type);
        title3.setText(questionsBean.getQuestionInfo().getType());


        AnalysisDataVO analysisDataVO = new AnalysisDataVO();
        analysisDataVO.noScore = 0;
        analysisDataVO.total = 0;
        analysisDataVO.scores = new ArrayList();

        List<StudentScoreItem> lists = new ArrayList<>();
        for (AssignmentScoreVO.QuestionsBean.StudentsBean s:questionsBean.getStudents()){
            StudentScoreItem studentScoreItem = new StudentScoreItem();
            studentScoreItem.name = s.getStudentName();
            studentScoreItem.number = s.getStudentNumber();
            if(s.isScored()) {
                studentScoreItem.score = s.getScore() + "";
                analysisDataVO.scores.add(s.getScore());
                analysisDataVO.total += s.getScore();
            }else {
                studentScoreItem.score = "未得分";
                analysisDataVO.noScore += 1;
            }

            lists.add(studentScoreItem);
        }
        StudentScoreAdapter studentScoreAdapter = new StudentScoreAdapter(lists,this.getActivity());
        ListView listView = (ListView)view.findViewById(R.id.listView_question_info_student);
        listView.setAdapter(studentScoreAdapter);


        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_analysis_data);
        imageView.setOnClickListener(e->{
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_main,ScoreAnalysisFragment.newInstance(analysisDataVO)).addToBackStack(null).commit();
        });
    }


}
