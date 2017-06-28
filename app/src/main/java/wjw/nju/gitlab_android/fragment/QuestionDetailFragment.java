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

/**
 * Created by wangjiawei on 2017/6/28.
 */

public class QuestionDetailFragment extends Fragment
{

    private PublishTaskVO.QuestionsBean questionsBean;
    private ListView elistView;


    public static QuestionDetailFragment newInstance(PublishTaskVO.QuestionsBean questionsBean) {
        QuestionDetailFragment fragment = new QuestionDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("questionsBean" ,questionsBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.questionsBean = (PublishTaskVO.QuestionsBean) getArguments().getSerializable("questionsBean");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_course_list, container, false);
        initVO(view);
        return view;
    }


    private void initVO(View view){
        List<IndexInfoItem> lists = new ArrayList<>();
        IndexInfoItem item1 = new IndexInfoItem("Title",questionsBean.getTitle());
        IndexInfoItem item2 = new IndexInfoItem("Description",questionsBean.getDescription());
        IndexInfoItem item3 = new IndexInfoItem("Difficulty",questionsBean.getDifficulty());
        IndexInfoItem item4 = new IndexInfoItem("GitUrl",questionsBean.getGitUrl());
        IndexInfoItem item5 = new IndexInfoItem("Type",questionsBean.getType());
        IndexInfoItem item6 = new IndexInfoItem("Creator",questionsBean.getCreator().getName());
        IndexInfoItem item7 = new IndexInfoItem("Duration",questionsBean.getDuration()+"");
        IndexInfoItem item8 = new IndexInfoItem("link",questionsBean.getLink()==-1?"暂无相关链接":questionsBean.getLink()+"");
        IndexInfoItem item9 = new IndexInfoItem("knowledgeVos",questionsBean.getKnowledgeVos()==null?"暂无":questionsBean.getKnowledgeVos().toString());


        lists.add(item1);
        lists.add(item2);
        lists.add(item3);
        lists.add(item4);
        lists.add(item5);
        lists.add(item6);
        lists.add(item7);
        lists.add(item8);
        lists.add(item9);

        ExamDetailAdapter e = new ExamDetailAdapter(lists,this.getActivity());


        this.elistView = (ListView)view.findViewById(R.id.Listview_courseList);
        elistView.setAdapter(e);


    }
}
