package wjw.nju.gitlab_android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.adapter.TestCaseAdapter;
import wjw.nju.gitlab_android.apiservice.apiVO.AssignmentAnalysisVO;

public class TestResultFragment extends Fragment {


    AssignmentAnalysisVO.QuestionResultsBean.TestResultBean testResultBean;


    public static TestResultFragment newInstance(AssignmentAnalysisVO.QuestionResultsBean.TestResultBean testResultBean) {
        TestResultFragment fragment = new TestResultFragment();
        Bundle args = new Bundle();
        args.putSerializable("testResultBean",testResultBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            testResultBean = (AssignmentAnalysisVO.QuestionResultsBean.TestResultBean)getArguments().getSerializable("testResultBean");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_test_result, container, false);
        initVO(view);
        return view;
    }

    public void initVO(View view){

        TextView t = (TextView) view.findViewById(R.id.test_giturl);
        t.setText(testResultBean.getGit_url());

        TextView t2 = (TextView) view.findViewById(R.id.test_compile);
        t2.setText(testResultBean.isCompile_succeeded()+"");

        TextView t3 = (TextView) view.findViewById(R.id.test_tested);
        t3.setText(testResultBean.isTested()+"");

        if(testResultBean.getTestcases() == null)
            return;
        ListView listView = (ListView)view.findViewById(R.id.listview_testcase);
        TestCaseAdapter testCaseAdapter = new TestCaseAdapter(testResultBean.getTestcases(),this.getActivity());
        listView.setAdapter(testCaseAdapter);
    }


}
