package wjw.nju.gitlab_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.apiservice.apiVO.LoginVO;
import wjw.nju.gitlab_android.fragment.ClassInfoFragment;
import wjw.nju.gitlab_android.fragment.IndexFragment;
import wjw.nju.gitlab_android.fragment.Tool_NavigationDrawerFragment;
import wjw.nju.gitlab_android.util.TopColorUtil;

public class TeacherMenu extends AppCompatActivity implements Tool_NavigationDrawerFragment.menuClickListener{

    private Toolbar toolbar;                             //定义toolbar
    private ActionBarDrawerToggle mDrawerToggle;         //定义toolbar左上角的弹出左侧菜单按钮
    private DrawerLayout drawer_main;                    //定义左侧滑动布局，其实就是主布局

    private TextView username;
    private TextView usertype;

    private IndexFragment iFragment;                     //定义首页fragment
    private ClassInfoFragment classInfoFragment;        //定义全部课

    //private FindFragment fFragment;                      //定义发现fragment
    //private AttentionFragment aFragment;                 //定义关注fragment
    //private CollectionFragment cFragment;                //定义收藏fragment
    //private DraftFragment dFragment;                     //定义草稿fragment

    private Fragment isFragment;                         //记录当前正在使用的fragment

    public final static String LOGIN_VO = "wjw.nju.gitlab_android.LoginVO";
    private LoginVO loginVO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_menu);
        initToolbar();
        TopColorUtil.setWindowStatusBarColor(this,R.color.primary_dark);

        initComponent();

        Intent intent = this.getIntent();
        loginVO = (LoginVO) intent.getExtras().getSerializable(MainActivity.LOGIN_KEY);
        setValue(loginVO);

        initFragment(savedInstanceState);

    }

    public void initComponent(){
        username = (TextView) findViewById(R.id.txt_main_drawer_UserNick);
        usertype = (TextView) findViewById(R.id.txt_main_drawer_UserProfile);
    }

    public void setValue(LoginVO loginVO){
        username.setText(loginVO.getName());
        switch (loginVO.getType()) {
            case student: usertype.setText(R.string.student); break;
            case teacher: usertype.setText(R.string.teacher); break;
            case admin: usertype.setText(R.string.admin); break;
        }
    }

    /**
     * 初始化Toolbar，并设置Toolbar中的菜单与标题，并与DrawerLayout.DrawerListener相关联，设置动态图标
     */
    public void initToolbar()
    {
        toolbar = (Toolbar)this.findViewById(R.id.teacher_toolbar);
        toolbar.setTitle("首页");                     // 标题的文字需在setSupportActionBar之前，不然会无效
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.primary));
        //为了生成，工具栏左上角的动态图标，要使用下面的方法
        drawer_main = (DrawerLayout) findViewById(R.id.drawer_main);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer_main, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        drawer_main.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id. main_toolbar_shuffle) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void menuClick(String menuName) {
        getSupportActionBar().setTitle(menuName);

        if(menuName.equals(getResources().getString(R.string.tea_index))){
            if(iFragment == null) {
                iFragment = new IndexFragment();
            }
            switchContent(isFragment,iFragment);
        }else if(menuName.equals(getResources().getString(R.string.tea_class))){
            if(classInfoFragment == null){
                classInfoFragment = new ClassInfoFragment();
            }
            switchContent(isFragment,classInfoFragment);
        }
        invalidateOptionsMenu();
        drawer_main.closeDrawers();


    }

    /**
     * 当fragment进行切换时，采用隐藏与显示的方法加载fragment以防止数据的重复加载
     * @param from
     * @param to
     */
    public void switchContent(Fragment from, Fragment to) {
        if (isFragment != to) {
            isFragment = to;
            FragmentManager fm = getSupportFragmentManager();
            //添加渐隐渐现的动画
            FragmentTransaction ft = fm.beginTransaction();

//            if (!to.isAdded()) {    // 先判断是否被add过
//                ft.hide(from).add(R.id.frame_main, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
//            } else {
//                ft.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
//            }
            Bundle bundle = new Bundle();
            bundle.putSerializable(LOGIN_VO, loginVO);
            isFragment.setArguments(bundle);

            ft.replace(R.id.frame_main,to).commit();
        }
    }

    /**
     * 为页面加载初始状态的fragment
     */
    public void initFragment(Bundle savedInstanceState)
    {
        //判断activity是否重建，如果不是，则不需要重新建立fragment.
        if(savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            if(iFragment == null) {
                iFragment = new IndexFragment();
            }
            isFragment = iFragment;

            Bundle bundle = new Bundle();
            bundle.putSerializable(LOGIN_VO, loginVO);
            isFragment.setArguments(bundle);

            ft.add(R.id.frame_main, iFragment).commit();
        }
    }
}
