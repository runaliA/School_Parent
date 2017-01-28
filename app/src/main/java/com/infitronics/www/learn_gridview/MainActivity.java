package com.infitronics.www.learn_gridview;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.action;
import static android.R.attr.label;
import static android.R.id.list;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private boolean viewIsAtHome;
    NavigationView navigationView;
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
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();

        navigationView= (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayView(R.id.home);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!viewIsAtHome) { //if the current view is not the News fragment
            displayView(R.id.home); //display the News fragment
        } else {
            moveTaskToBack(true);  //If view is in News fragment, exit application
        }
    }
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }*/


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
            case R.id.attendance:
                fragment = new Attendance_Fragment();
                title  = "Attendance";
                viewIsAtHome = false;
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            assert getSupportActionBar() != null;
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);

    }

   }