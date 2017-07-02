package wjw.nju.gitlab_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.activity.MenuActivity;
import wjw.nju.gitlab_android.adapter.IndexInfoListAdapter;
import wjw.nju.gitlab_android.adapter.Item.IndexInfoItem;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;


public class IndexFragment extends Fragment {

    private LoginVO loginVO;

    private ListView indexlistInfo;
    private List<IndexInfoItem> indexInfoItemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index,container,false);
        initLoginVO();
        initComponent(view);

        return view;
    }

    private void initComponent(View view){
        indexInfoItemList = new ArrayList<>();
        indexInfoItemList.add(new IndexInfoItem("姓名",loginVO.getName()));
        indexInfoItemList.add(new IndexInfoItem("用户名",loginVO.getUsername()));
        String type = "";
        switch (loginVO.getType()){
            case student: type = getResources().getString(R.string.student); break;
            case teacher: type = getResources().getString(R.string.teacher); break;
            case admin: type = getResources().getString(R.string.admin); break;
        }
        indexInfoItemList.add(new IndexInfoItem("类型",type));
        String gender = "";
        switch (loginVO.getGender()){
            case male: gender = "男"; break;
            case female: gender = "女"; break;
        }
        indexInfoItemList.add(new IndexInfoItem("性别",gender));
        indexInfoItemList.add(new IndexInfoItem("GitID",loginVO.getS_git_id()==null?"暂无":loginVO.getS_git_id()));
        indexInfoItemList.add(new IndexInfoItem("学号",loginVO.getS_number()==null||loginVO.getS_number().equals("null")?"暂无":loginVO.getS_number()));




        indexlistInfo = (ListView)view.findViewById(R.id.index_list_info);
        indexlistInfo.setAdapter(new IndexInfoListAdapter(indexInfoItemList, this.getActivity()));




    }

    private void initLoginVO(){
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            loginVO = (LoginVO) bundle.getSerializable(MenuActivity.LOGIN_VO);
        }
    }


}
