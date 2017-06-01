package wjw.nju.gitlab_android.adapter;

/**
 * Created by wangjiawei on 2017/6/1.
 */

public class MainDrawerMenu {

    private int mainDrawer_icon;                      //菜单的图标
    private String mainDrawer_menuName;               //菜单的名称

    public MainDrawerMenu() {
    }

    public MainDrawerMenu(int mainDrawer_icon, String mainDrawer_menuName) {
        this.mainDrawer_icon = mainDrawer_icon;
        this.mainDrawer_menuName = mainDrawer_menuName;
    }

    /**
     * 得到菜单图标
     * @return
     */
    public int getMainDrawer_icon() {
        return mainDrawer_icon;
    }

    /**
     * 设置菜单图标
     * @param mainDrawer_icon
     */
    public void setMainDrawer_icon(int mainDrawer_icon) {
        this.mainDrawer_icon = mainDrawer_icon;
    }

    /**
     * 得到菜单名称
     * @return
     */
    public String getMainDrawer_menuName() {
        return mainDrawer_menuName;
    }

    /**
     * 设置菜单名称
     * @param mainDrawer_menuName
     */
    public void setMainDrawer_menuName(String mainDrawer_menuName) {
        this.mainDrawer_menuName = mainDrawer_menuName;
    }

}
