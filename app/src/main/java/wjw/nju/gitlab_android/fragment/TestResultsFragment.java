package wjw.nju.gitlab_android.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.adapter.FragmentAdapter;
import wjw.nju.gitlab_android.apiservice.GetAllClassService;
import wjw.nju.gitlab_android.apiservice.GetAssignmentAnalysisService;
import wjw.nju.gitlab_android.apiservice.apiVO.AssignmentAnalysisVO;
import wjw.nju.gitlab_android.apiservice.apiVO.CourseVO;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.util.Base64EncodeUtil;

public class TestResultsFragment extends Fragment {

    private AssignmentAnalysisVO assignmentAnalysisVO;
    private String studentId;
    private String assignmentId;
    private LoginVO loginVO;
    private int[] tabImg;
    private String questionId;
    private TabLayout tab_title;
    private ViewPager vp_pager;

    private List<String> list_title;                                      //tab名称列表
    private List<Fragment> list_fragment;
    private FragmentAdapter fAdapter;

    private MetricDataResultFragment nFragment;
    private TestResultFragment sFragment;
    private ScoreResultFragment fFragment;
    private ReadmeFragment readmeFragment;



    public static TestResultsFragment newInstance(LoginVO loginVO,String studentId,String assignmentId ,String questionId) {
        TestResultsFragment fragment = new TestResultsFragment();
        Bundle args = new Bundle();
        args.putSerializable("loginVO",loginVO);
        args.putString("studentId",studentId);
        args.putString("assignmentId",assignmentId);
        args.putString("questionId",questionId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            studentId = getArguments().getString("studentId");
            assignmentId = getArguments().getString("assignmentId");
            questionId = getArguments().getString("questionId");
            loginVO = (LoginVO)getArguments().getSerializable("loginVO");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_results, container, false);
        initControls(view);
        return view;
    }

    /**
     * 初始化控件
     */
    private void initControls(View view)
    {
        tab_title = (TabLayout)view.findViewById(R.id.tab_title);
        vp_pager = (ViewPager)view.findViewById(R.id.vp_pager);

        //为tabLayout上的图标赋值
        tabImg = new int[]{R.mipmap.analysis,R.mipmap.analysis,R.mipmap.analysis};

        Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg){
                assignmentAnalysisVO = (AssignmentAnalysisVO) msg.obj;
                viewChanage();
            }
        };

        GetAssignmentAnalysisService getAllClassService = new GetAssignmentAnalysisService(assignmentId,studentId, Base64EncodeUtil.getToken(loginVO.getUsername(),loginVO.getPassword()),h);
        getAllClassService.execute();
    }

    /**
     * 采用在viewpager中切换 view 的方式,并添加了icon的功能
     */
    private void viewChanage()
    {
        list_fragment = new ArrayList<>();

        AssignmentAnalysisVO.QuestionResultsBean questionResultsBean = null;
        for (AssignmentAnalysisVO.QuestionResultsBean q:assignmentAnalysisVO.getQuestionResults()){
            if(q.getQuestionId()==Integer.parseInt(questionId)) {
                questionResultsBean = q;
                break;
            }
        }

        nFragment = MetricDataResultFragment.newInstance(questionResultsBean.getMetricData());
        sFragment = TestResultFragment.newInstance(questionResultsBean.getTestResult());
        fFragment = ScoreResultFragment.newInstance(questionResultsBean.getScoreResult().getGit_url(),questionResultsBean.getScoreResult().getScore()+"",questionResultsBean.getScoreResult().isScored()+"");
        readmeFragment = ReadmeFragment.newInstance(studentId,assignmentId,questionId,loginVO);

        list_fragment.add(readmeFragment);
        list_fragment.add(nFragment);
        list_fragment.add(sFragment);
        list_fragment.add(fFragment);

        list_title = new ArrayList<>();
        list_title.add("README.md");
        list_title.add("MetricData");
        list_title.add("TestResult");
        list_title.add("ScoreResult");
        fAdapter = new FragmentAdapter(getFragmentManager(),list_fragment,list_title);
        vp_pager.setAdapter(fAdapter);

        //将tabLayout与viewpager连起来
        tab_title.setupWithViewPager(vp_pager);
    }


}
