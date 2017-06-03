package wjw.nju.gitlab_android.apiservice.apiVO;

import java.io.Serializable;

/**
 * Created by wangjiawei on 2017/6/1.
 */

public class LoginVO implements Serializable {

    private LoginState login_state;

    public enum LoginState{ LOGIN_SUCCESS, LOGIN_FAILURE }

    private String username;

    private String password;

    private String name;

    private LoginType type;

    public enum LoginType {student,teacher,admin}

    private String avatar;

    private Gender gender;

    public enum Gender{male, female}

    private String email;

    private String s_git_id;

    private String s_number;

    private String t_authority;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoginType getType() {
        return type;
    }

    public void setType(LoginType type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getS_git_id() {
        return s_git_id;
    }

    public void setS_git_id(String s_git_id) {
        this.s_git_id = s_git_id;
    }

    public String getT_authority() {
        return t_authority;
    }

    public void setT_authority(String t_authority) {
        this.t_authority = t_authority;
    }

    public LoginState getLogin_state() {
        return login_state;
    }

    public void setLogin_state(LoginState login_state) {
        this.login_state = login_state;
    }

    public LoginVO(LoginState state){
        this.setLogin_state(state);
    }

    public String getS_number() {
        return s_number;
    }

    public void setS_number(String s_number) {
        this.s_number = s_number;
    }
}
