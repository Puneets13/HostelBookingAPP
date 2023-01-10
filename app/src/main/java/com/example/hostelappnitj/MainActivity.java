package com.example.hostelappnitj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.hostelappnitj.Fragments.AboutFragment;
import com.example.hostelappnitj.Fragments.AccountSettings_Fragment;
import com.example.hostelappnitj.Fragments.ComplaintFragment;
import com.example.hostelappnitj.Fragments.HelpFragment;
import com.example.hostelappnitj.Fragments.HostelPolicy_Fragment;
import com.example.hostelappnitj.Fragments.homeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigation_view);
        toolbar=findViewById(R.id.toolBar);

        setSupportActionBar(toolbar );

        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        loadFragment(new homeFragment());  //for loading the default fragment
//        for handling the clickListners on navigationBar
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment =null;
                switch (item.getItemId()) {
                    case R.id.nitj_hostels:
                        toolbar.setTitle("NITJ HOSTELS");
                        fragment = new homeFragment();    //passing the new fragment that we have created
                        break;
                    case R.id.hostel_policy:
                        toolbar.setTitle("HOSTEL POLICY");
                        fragment = new HostelPolicy_Fragment();
                        break;
                    case R.id.complaint_section:
                        toolbar.setTitle("COMPLAINTS SECTIONS");
                        fragment = new ComplaintFragment();
                        break;
                    case R.id.notifications:

                        break;
                    case R.id.account_settings:
                        toolbar.setTitle("ACCOUNT SETTINGS");
                        fragment = new AccountSettings_Fragment();
                        break;
                    case R.id.about:
                        toolbar.setTitle("ABOUT HOSTELS");
                        fragment = new AboutFragment();
                        break;
                    case R.id.help:
                        toolbar.setTitle("Help");
                        fragment = new HelpFragment();
                        break;
                    case R.id.logout:
//                        on click listener for logging out
                        break;
                }
                 if(fragment!=null){
                        loadFragment(fragment);
                    }

            drawerLayout.closeDrawer(GravityCompat.START);  //to close the drawer when any item is clicked
                return true;
            }
        });
    }

//    to close the drawer when backPress button is pressed on phone if it is opend
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

//    function to load the fragment
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_container,fragment);
        fragmentTransaction.commit();
    }
}