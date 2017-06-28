package wjw.nju.gitlab_android.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.apiservice.LoginService;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.util.TopColorUtil;

public class MainActivity extends AppCompatActivity implements OnClickListener{


    public  final static String LOGIN_KEY = "apiservice.apiVO.LoginVO";

    private EditText et_name, et_pass;
    private Button bt_username_clear;
    private Button bt_password_clear;
    private Button bt_pwd_eye;
    private TextWatcher username_watcher;
    private TextWatcher password_watcher;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TopColorUtil.setWindowStatusBarColor(this,R.color.primary_dark);
        initComponent();

        bt_username_clear.setOnClickListener(this);
        bt_password_clear.setOnClickListener(this);
        bt_pwd_eye.setOnClickListener(this);
        initWatcher();

        et_name.addTextChangedListener(username_watcher);
        et_pass.addTextChangedListener(password_watcher);
        mLoginButton.setOnClickListener(this);

    }

    public void initComponent(){
        et_name = (EditText) findViewById(R.id.username);
        et_pass = (EditText) findViewById(R.id.password);
        et_name.setTextColor(getResources().getColor(R.color.black));
        et_pass.setTextColor(getResources().getColor(R.color.black));
        bt_username_clear = (Button) findViewById(R.id.bt_username_clear);
        bt_password_clear = (Button) findViewById(R.id.bt_pwd_clear);
        bt_pwd_eye = (Button) findViewById(R.id.bt_pwd_eye);
        mLoginButton = (Button) findViewById(R.id.login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:  //登陆
                login();
                break;
            case R.id.bt_username_clear:
                et_name.setText("");
                et_pass.setText("");
                break;
            case R.id.bt_pwd_clear:
                et_pass.setText("");
                break;
            case R.id.bt_pwd_eye:
                if(et_pass.getInputType() == (InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD)){
                    bt_pwd_eye.setBackgroundResource(R.mipmap.ic_eye_open);
                    et_pass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
                }else{
                    bt_pwd_eye.setBackgroundResource(R.mipmap.ic_eye);
                    et_pass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                et_pass.setSelection(et_pass.getText().toString().length());
                break;
        }
    }

    private void initWatcher() {
        username_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            public void afterTextChanged(Editable s) {
                et_pass.setText("");
                if(s.toString().length()>0){
                    bt_username_clear.setVisibility(View.VISIBLE);
                }else{
                    bt_username_clear.setVisibility(View.INVISIBLE);
                }
            }
        };

        password_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    bt_password_clear.setVisibility(View.VISIBLE);
                }else{
                    bt_password_clear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    private void login(){
        String username = et_name.getText().toString();
        String password = et_pass.getText().toString();
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                LoginVO loginVO = (LoginVO) msg.obj;
                if(loginVO.getLogin_state().equals(LoginVO.LoginState.LOGIN_FAILURE))
                    Toast.makeText(MainActivity.this, R.string.login_fail, Toast.LENGTH_SHORT).show();
                else{
                    Intent intent = null;
                    if(loginVO.getType().equals(LoginVO.LoginType.teacher)) {
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                    }else if(loginVO.getType().equals(LoginVO.LoginType.student)){
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                    }
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(LOGIN_KEY,loginVO);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }
            }
        };
        LoginService loginService = new LoginService(handler,username,password);
        loginService.execute();


    }
}
