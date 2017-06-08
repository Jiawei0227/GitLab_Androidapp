package wjw.nju.gitlab_android.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import wjw.nju.gitlab_android.R;
import wjw.nju.gitlab_android.adapter.MainDrawerAdapter;
import wjw.nju.gitlab_android.adapter.Item.MainDrawerMenu;


public class Tool_NavigationDrawerFragment extends Fragment {

    private ListView lv_main_drawer_leftmenu;
    //定义菜单的listView
    private List<MainDrawerMenu> list_menu;


    /**
     *  设置菜单点击接口，以方便外部Activity调用
     */
    public interface menuClickListener
    {
        void menuClick(String menuName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_drawer,container,false);

        initleftMenuContral(view);

        return view;
    }

    /**
     * 初始化左侧菜单列表listView,并为菜单，设置点击事件
     * @param view
     */
    private void initleftMenuContral(View view) {
        lv_main_drawer_leftmenu = (ListView)view.findViewById(R.id.lv_main_drawer_leftmenu);
        list_menu = getMenuItem();
        lv_main_drawer_leftmenu.setAdapter(new MainDrawerAdapter(getActivity(),list_menu));
        lv_main_drawer_leftmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(getActivity() instanceof menuClickListener)
                {
                    ((menuClickListener)getActivity()).menuClick(list_menu.get(position).getMainDrawer_menuName());
                }
            }
        });
    }

    /**
     * 从arrays.xml中取出数据，装入list<T>中
     * @return
     */
    private List<MainDrawerMenu> getMenuItem()
    {
        List<MainDrawerMenu> list_menu = new ArrayList<MainDrawerMenu>();

        String[] itemTitle = getResources().getStringArray(R.array.item_title);
        TypedArray itemIconRes = getResources().obtainTypedArray(R.array.item_icon_res);

        for(int i=0;i<itemTitle.length;i++)
        {

            MainDrawerMenu lmi = new MainDrawerMenu();
            lmi.setMainDrawer_icon(itemIconRes.getResourceId(i,0));
            lmi.setMainDrawer_menuName(itemTitle[i]);
            list_menu.add(lmi);
        }

        return list_menu;
    }
}
