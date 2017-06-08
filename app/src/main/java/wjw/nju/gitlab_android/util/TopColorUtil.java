package wjw.nju.gitlab_android.util;

import android.app.Activity;
import android.os.Build;
import android.view.WindowManager;

/**
 * Created by wangjiawei on 2017/6/8.
 */

public class TopColorUtil {

    public static boolean setWindowStatusBarColor(Activity activity, int colorResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(colorResId));
            return true;
        }
        return false;
    }
}
