package wjw.nju.gitlab_android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wjw.nju.gitlab_android.R;


public class ScoreResultFragment extends Fragment {

    private String gitUrl;
    private String score;
    private String scored;

    public static ScoreResultFragment newInstance(String gitUrl,String score,String scored) {
        ScoreResultFragment fragment = new ScoreResultFragment();
        Bundle args = new Bundle();
        args.putString("gitUrl",gitUrl);
        args.putString("score",score);
        args.putString("scored",scored);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            gitUrl = getArguments().getString("gitUrl");
            score = getArguments().getString("score");
            scored = getArguments().getString("scored");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_score_result, container, false);

        initComponent(view);

        return view;
    }

    public void initComponent(View view){
        TextView t = (TextView) view.findViewById(R.id.score_result_git);
        t.setText(gitUrl);

        TextView t2 = (TextView) view.findViewById(R.id.score_result_score);
        t2.setText(score);

        TextView t3 = (TextView) view.findViewById(R.id.score_result_scored);
        t3.setText(scored);
    }



}
