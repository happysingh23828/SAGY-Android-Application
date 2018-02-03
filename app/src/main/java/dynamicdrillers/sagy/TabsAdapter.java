package dynamicdrillers.sagy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mayank on 2/2/18.
 */

public class TabsAdapter extends FragmentPagerAdapter {

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 2:
                return fragment = new ComplaintFragment();
            case 0:
                return fragment = new DashboardFragment();
            case 1:
                return fragment = new NotificationFragment();
            case 3:
                return fragment = new RecentActivitiesFragment();
            default:
                return null;
        }
    }

        @Override
        public int getCount() {
            return 4;
        }

        public CharSequence getPageTitle(int Position)
        {
            switch (Position)
            {
                case 1:
                    return "Complaint";
                case 0:
                    return "Dashbord";
                case 2:
                    return "Notification";
                case 3:
                    return "Recent Activity";
                default:
                    return null;
            }
        }
}
