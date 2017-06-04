package wjw.nju.gitlab_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.activity.TeacherMenu;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;


public class IndexFragment extends Fragment {

    private LoginVO loginVO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index,container,false);
        initLoginVO();
        initComponent(view);

        return view;
    }

    private void initComponent(View view){


        TextView index_name = (TextView) view.findViewById(R.id.index_name);
        index_name.setText(loginVO.getName());

        TextView index_username = (TextView) view.findViewById(R.id.index_username);
        index_username.setText(loginVO.getUsername());

        TextView index_email = (TextView) view.findViewById(R.id.index_email);
        index_email.setText(loginVO.getEmail());

        String gender = "";
        switch (loginVO.getGender()){
            case male: gender = "男"; break;
            case female: gender = "女"; break;
        }
        TextView index_gender = (TextView) view.findViewById(R.id.index_gender);
        index_gender.setText(gender);

        String type = "";
        switch (loginVO.getType()){
            case student: type = getResources().getString(R.string.student);; break;
            case teacher: type = getResources().getString(R.string.teacher);; break;
            case admin: type = getResources().getString(R.string.admin); break;
        }
        TextView index_type = (TextView) view.findViewById(R.id.index_type);
        index_type.setText(type);

        TextView index_gitid = (TextView) view.findViewById(R.id.index_gitid);
        index_gitid.setText(loginVO.getS_git_id()==null?"暂无":loginVO.getS_git_id());

        TextView index_number = (TextView) view.findViewById(R.id.index_number);
        index_number.setText(loginVO.getS_number()==null?"暂无":loginVO.getS_number());


    }

    private void initLoginVO(){
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            loginVO = (LoginVO) bundle.getSerializable(TeacherMenu.LOGIN_VO);
        }
    }

}
