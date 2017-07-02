package wjw.nju.gitlab_android.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.apiservice.GetReadmeService;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.apiservice.apiVO.ReadmeVO;
import wjw.nju.gitlab_android.util.Base64EncodeUtil;

public class ReadmeFragment extends Fragment {
    String studentId;
    String assignmentId;
    String questionId;
    ReadmeVO readmeVO;
    LoginVO loginVO;


    public static ReadmeFragment newInstance(String studentId, String assignmentId,String questionId,LoginVO loginVO) {
        ReadmeFragment fragment = new ReadmeFragment();
        Bundle args = new Bundle();
        args.putString("studentId", studentId);
        args.putString("assignmentId", assignmentId);
        args.putString("questionId", questionId);
        args.putSerializable("loginVO",loginVO);
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
            loginVO = (LoginVO) getArguments().getSerializable("loginVO");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_readme, container, false);
        getReadMe(view);
        return view;
    }

    public void getReadMe(View view){
        Handler h = new Handler(){
            @Override
            public void handleMessage(Message m){
                readmeVO = (ReadmeVO)m.obj;
                initVO(view);
            }
        };
        GetReadmeService getReadmeService = new GetReadmeService(assignmentId,studentId,questionId, Base64EncodeUtil.getToken(loginVO.getUsername(),loginVO.getPassword()),h);
        getReadmeService.execute();
    }

    public void initVO(View view){
        TextView textView = (TextView) view.findViewById(R.id.textview_readme);
        if(readmeVO == null || readmeVO.getContent()==null)
            textView.setText("暂无");
        else
            textView.setText(readmeVO.getContent());
    }
}
