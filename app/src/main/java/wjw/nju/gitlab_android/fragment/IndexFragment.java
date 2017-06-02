package wjw.nju.gitlab_android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.activity.TeacherMenu;
import wjw.nju.gitlab_android.apiservice.apistate.LoginVO;


public class IndexFragment extends Fragment {

    private LoginVO loginVO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index,container,false);
        initLoginVO();

        return view;
    }

    private void initLoginVO(){
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            loginVO = (LoginVO) bundle.getSerializable(TeacherMenu.LOGIN_VO);
        }
    }

}
