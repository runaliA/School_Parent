package com.infitronics.www.School_Parent;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.infitronics.www.School_Parent.ui.Attendance_Fragment;
import com.infitronics.www.School_Parent.ui.Gallery_Fragment;
import com.infitronics.www.School_Parent.ui.Home_Fragment;
import com.infitronics.www.School_Parent.ui.Homework_Fragment;
import com.infitronics.www.School_Parent.ui.Leavenote_Fragment;
import com.infitronics.www.School_Parent.ui.MyAccount;
import com.infitronics.www.School_Parent.ui.Notice_Fragment;
import com.infitronics.www.School_Parent.ui.Projectdemo_Fragment;
import com.infitronics.www.School_Parent.ui.Remark_Fragment;
import com.infitronics.www.School_Parent.ui.Result_Fragment;
import com.infitronics.www.School_Parent.ui.Timetable_Fragment;
import com.infitronics.www.School_Parent.utils.BackHandledFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private boolean viewIsAtHome;
    NavigationView navigationView;
    ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set Custom Toolbar

        Toolbar mytoolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(mytoolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //Add Nav Drawer
        DrawerLayout mDrawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();

        navigationView= (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayView(R.id.home);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            /*Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();*/

            Snackbar.make(findViewById(R.id.main_activity_layout), "Please click BACK again to exit", Snackbar.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displayView(item.getItemId());
        return true;
    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.home:
                fragment = new Home_Fragment();
                title  = "Home";
                viewIsAtHome = true;
                break;
            case R.id.homework:
                fragment = new Homework_Fragment();
                title  = "HomeWork";
                viewIsAtHome = false;
                break;
            case R.id.noticeboard:
                fragment = new Notice_Fragment();
                title  = "Notice Board";
                viewIsAtHome = false;
                break;
            case R.id.timetable:
                fragment = new Timetable_Fragment();
                title  = "Time Table";
                viewIsAtHome = false;
                break;
            case R.id.result:
                fragment = new Result_Fragment();
                title  = "Result";
                viewIsAtHome = false;
                break;
            case R.id.remark:
                fragment = new Remark_Fragment();
                title  = "Remark";
                viewIsAtHome = false;
                break;
            case R.id.attendance:
                fragment = new Attendance_Fragment();
                title  = "Attendance";
                viewIsAtHome = false;
                break;
            case R.id.projectdemo:
                fragment = new Projectdemo_Fragment();
                title  = "Project Demo";
                viewIsAtHome = false;
                break;
            case R.id.gallery:
                fragment = new Gallery_Fragment();
                title  = "Gallery";
                viewIsAtHome = false;
                break;
            case R.id.leavenote:
                fragment = new Leavenote_Fragment();
                title  = "Leave Note";
                viewIsAtHome = false;
                break;

            case R.id.account:
                fragment = new MyAccount();
                title  = "My Account";
                viewIsAtHome = false;
                break;

        }

        if (fragment != null ) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            if (!viewIsAtHome) {
                ft.addToBackStack("tag");
            }
            ft.commit();
        }

        setActionBarTitle(title);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);

    }
}