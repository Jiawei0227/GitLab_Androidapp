package wjw.nju.gitlab_android.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.apiservice.GetAllClassService;
import wjw.nju.gitlab_android.apiservice.apiVO.AssignmentAnalysisVO;
import wjw.nju.gitlab_android.apiservice.apiVO.CourseVO;
import wjw.nju.gitlab_android.util.Base64EncodeUtil;

public class MetricDataResultFragment extends Fragment {


    AssignmentAnalysisVO.QuestionResultsBean.MetricDataBean dataBean;

    public static MetricDataResultFragment newInstance(AssignmentAnalysisVO.QuestionResultsBean.MetricDataBean dataBean) {
        MetricDataResultFragment fragment = new MetricDataResultFragment();
        Bundle args = new Bundle();
        args.putSerializable("dataBean",dataBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dataBean = (AssignmentAnalysisVO.QuestionResultsBean.MetricDataBean)getArguments().getSerializable("dataBean");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_metric_data_result, container, false);
        initVO(view);
        return view;
    }

    public void initVO(View view){
        TextView t = (TextView) view.findViewById(R.id.metric_url);
        t.setText(dataBean.getGit_url());

        TextView t2 = (TextView) view.findViewById(R.id.metric_comment_count);
        t2.setText(dataBean.getComment_line_count()+"");

        TextView t3 = (TextView) view.findViewById(R.id.metric_maxcoc);
        t3.setText(dataBean.getMax_coc()+"");

        TextView t4 = (TextView) view.findViewById(R.id.metric_field_count);
        t4.setText(dataBean.getField_count()+"");

        TextView t5 = (TextView) view.findViewById(R.id.metric_measure);
        t5.setText(dataBean.isMeasured()+"");

        TextView t6 = (TextView) view.findViewById(R.id.metric_method_count);
        t6.setText(dataBean.getMethod_count()+"");

        TextView t7 = (TextView) view.findViewById(R.id.metric_total_count);
        t7.setText(dataBean.getTotal_line_count()+"");


    }


}
