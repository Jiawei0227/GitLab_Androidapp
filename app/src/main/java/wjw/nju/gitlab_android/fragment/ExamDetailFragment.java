package wjw.nju.gitlab_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.adapter.ExamDetailAdapter;
import wjw.nju.gitlab_android.adapter.Item.IndexInfoItem;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.apiservice.apiVO.PublishTaskVO;


public class ExamDetailFragment extends Fragment {
    private LoginVO loginVO;
    private String courseId;
    private PublishTaskVO publishTaskVO;
    private ListView elistView;


    public static ExamDetailFragment newInstance(LoginVO loginVo, PublishTaskVO publishTaskVO) {
        ExamDetailFragment fragment = new ExamDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("publishTaskVO" ,publishTaskVO);
        args.putSerializable("loginVO",loginVo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            loginVO = (LoginVO) getArguments().getSerializable("loginVO");
            publishTaskVO = (PublishTaskVO) getArguments().getSerializable("publishTaskVO");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_exam_detail, container, false);
        initVO(view);
        return view;
    }


    private void initVO(View view){
        List<IndexInfoItem> lists = new ArrayList<>();
        IndexInfoItem item1 = new IndexInfoItem("Title",publishTaskVO.getTitle());
        IndexInfoItem item2 = new IndexInfoItem("Description",publishTaskVO.getDescription());
        IndexInfoItem item3 = new IndexInfoItem("StartAt",publishTaskVO.getStartAt());
        IndexInfoItem item4 = new IndexInfoItem("EndAt",publishTaskVO.getEndAt());
        IndexInfoItem item5 = new IndexInfoItem("Status",publishTaskVO.getStatus());

        lists.add(item1);
        lists.add(item2);
        lists.add(item3);
        lists.add(item4);
        lists.add(item5);

        ExamDetailAdapter e = new ExamDetailAdapter(lists,this.getActivity());


        this.elistView = (ListView)view.findViewById(R.id.listview_exam_detail);
        elistView.setAdapter(e);

        Button viewquestion = (Button)view.findViewById(R.id.exam_viewquestion);
        viewquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_main,QuestionListFragment.newInstance(publishTaskVO)).addToBackStack(null).commit();
            }
        });
    }

}
