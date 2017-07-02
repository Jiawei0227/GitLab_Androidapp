package wjw.nju.gitlab_android.adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import static android.R.id.list;

/**
 * 这里使用多个fragment进行切换，好处是每个fragment 都有自己独立的代码
 * <p>
 * Created by cg on 2015/10/21.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> list_fragment;                         //fragment列表

    private List<String> list_Title;                              //tab名的列表

    private FragmentManager fm;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_Title) {

        super(fm);
        this.fm = fm;
        this.list_fragment = list_fragment;
        this.list_Title = list_Title;
        //setFragments(list_fragment);

    }


    @Override

    public Fragment getItem(int i) {

        return list_fragment.get(i);

    }


    @Override

    public int getCount() {

        return list_fragment.size();

    }

    public void setFragments(List<Fragment> fragments) {
        if ((list_fragment == null) && (fm == null) || (fragments == null)) {
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        for (int i = 0; i < list_fragment.size(); i++) {
            Fragment fragment = (Fragment) list_fragment.get(i);
            ft.remove(fragment);
        }
        ft.commit();
        ft = null;
        fm.executePendingTransactions();

        this.list_fragment.addAll(fragments);
        notifyDataSetChanged();
    }

    /**
     * 此方法是给tablayout中的tab赋值的，就是显示名称
     *
     * @param position
     * @return
     */

    @Override

    public CharSequence getPageTitle(int position) {

        return list_Title.get(position % list_Title.size());

    }

}