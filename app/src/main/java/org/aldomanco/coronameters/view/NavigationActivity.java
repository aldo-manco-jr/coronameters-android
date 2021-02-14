package org.aldomanco.coronameters.view;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.aldomanco.coronameters.R;
import org.aldomanco.coronameters.view.ui.about.AboutFragment;
import org.aldomanco.coronameters.view.ui.world.WorldStatsFragment;
import org.aldomanco.coronameters.view.ui.italy.ItalyStatsFragment;
import org.aldomanco.coronameters.view.ui.notifications.NotificationsFragment;
import org.aldomanco.coronameters.view.ui.report.ReportFragment;

import java.util.List;

public class NavigationActivity extends AppCompatActivity {

    private ItalyStatsFragment italyStatsFragment;
    private ReportFragment reportFragment;
    private WorldStatsFragment worldStatsFragment;
    private NotificationsFragment notificationsFragment;
    private AboutFragment aboutFragment;

    private static NavigationActivity navigationActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        navigationActivity = this;

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navbarListener);

        changeFragment(italyStatsFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navbarListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int navigationSectionIdentifier = item.getItemId();

                    switch (navigationSectionIdentifier) {
                        case R.id.navigation_italy_stats:
                            italyStatsFragment = (ItalyStatsFragment)
                                    createNewInstanceIfNecessary(italyStatsFragment, FragmentsIdentifier.home);
                            changeFragment(italyStatsFragment);
                            break;
                        case R.id.navigation_world_stats:
                            worldStatsFragment = (WorldStatsFragment)
                                    createNewInstanceIfNecessary(worldStatsFragment, FragmentsIdentifier.dashboard);
                            changeFragment(worldStatsFragment);
                            break;
                        case R.id.navigation_notifications:
                            notificationsFragment = (NotificationsFragment)
                                    createNewInstanceIfNecessary(notificationsFragment, FragmentsIdentifier.notifications);
                            changeFragment(notificationsFragment);
                            break;
                        case R.id.navigation_report:
                            reportFragment = (ReportFragment)
                                    createNewInstanceIfNecessary(reportFragment, FragmentsIdentifier.report);
                            changeFragment(reportFragment);
                            break;
                        case R.id.navigation_about:
                            aboutFragment = (AboutFragment)
                                    createNewInstanceIfNecessary(aboutFragment, FragmentsIdentifier.about);
                            changeFragment(aboutFragment);
                            break;
                    }
                    return true;
                }
            };

    private Fragment createNewInstanceIfNecessary(Fragment fragment, FragmentsIdentifier identifier) {
        if (fragment == null) {
            try {
                if (identifier == FragmentsIdentifier.home) {
                    fragment = ItalyStatsFragment.newInstance();
                } else if (identifier == FragmentsIdentifier.dashboard) {
                    fragment = WorldStatsFragment.newInstance();
                } else if (identifier == FragmentsIdentifier.notifications) {
                    fragment = NotificationsFragment.newInstance();
                } else if (identifier == FragmentsIdentifier.report) {
                    fragment = ReportFragment.newInstance();
                } else if (identifier == FragmentsIdentifier.about) {
                    fragment = AboutFragment.newInstance();
                }
            } catch (Exception ignored) {
            }
        }

        return fragment;
    }

    @Override
    public void onBackPressed() {
        tellFragments();
    }

    private void tellFragments() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof WorldStatsFragment) {
                ((WorldStatsFragment) fragment).onBackPressed();
            } else if (fragment instanceof ItalyStatsFragment) {
                ((ItalyStatsFragment) fragment).onBackPressed();
            } else if (fragment instanceof NotificationsFragment) {
                ((NotificationsFragment) fragment).onBackPressed();
            } else if (fragment instanceof ReportFragment) {
                ((ReportFragment) fragment).onBackPressed();
            } else if (fragment instanceof AboutFragment) {
                ((AboutFragment) fragment).onBackPressed();
            }
        }
    }

    public void changeFragment(Fragment selectedFragment) {

        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transactionsManager = fragmentManager.beginTransaction();
            transactionsManager
                    .replace(R.id.nav_host_fragment, selectedFragment)
                    .commit();
        } catch (Exception ignored) {
        }

        /*try {
            Fragment oldFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

            if (oldFragment != newFragment) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transactionsManager = fragmentManager.beginTransaction();

                if (oldFragment != null) {
                    transactionsManager
                            .replace(R.id.nav_host_fragment, newFragment)
                            //.remove(oldFragment)
                            .commit();
                } else {
                    transactionsManager
                            .replace(R.id.nav_host_fragment, newFragment)
                            .commit();
                }
            }
        } catch (Exception ignored) {}*/
    }

    public static NavigationActivity getNavigationActivity() {
        return navigationActivity;
    }
}