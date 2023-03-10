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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hostelappnitj.Acitvity.SignInActivity;
import com.example.hostelappnitj.Fragments.AboutFragment;
import com.example.hostelappnitj.Fragments.AccountSettings_Fragment;
import com.example.hostelappnitj.Fragments.ProfileFragment;
import com.example.hostelappnitj.Fragments.HelpFragment;
import com.example.hostelappnitj.Fragments.HostelPolicy_Fragment;
import com.example.hostelappnitj.Fragments.homeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;
SharedPrefManager sharedPrefManager;
    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    private DialogInterface.OnClickListener dialogClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigation_view);
        toolbar=findViewById(R.id.toolBar);
sharedPrefManager=new SharedPrefManager(MainActivity.this);
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
                    case R.id.profile:
                        toolbar.setTitle("UPDATE PROFILE");
                        fragment = new ProfileFragment();
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
                        logoutUser();
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

////    to close the drawer when backPress button is pressed on phone if it is opend
//    @Override
//    public void onBackPressed() {
//
//    }
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
//        int count = getSupportFragmentManager().getBackStackEntryCount();
//        if (count == 0) {
//            super.onBackPressed();
//            //additional code
//        } else {
//            getSupportFragmentManager().popBackStack();
//        }
    }


//    function to load the fragment
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_container,fragment);  //to move to backword
        fragmentTransaction.commit();
    }
    private void logoutUser() {
//        Creating Dialog Box
        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    // on below line we are setting a click listener
                    // for our positive button
                    case DialogInterface.BUTTON_POSITIVE:
                        // on below line we are displaying a toast message.
//                        Make the Logout Action
                        sharedPrefManager.logout();  //calling the logoutmethod described in shared preference
                        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        Toast.makeText(MainActivity.this, "You Have been LoggedOut", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                        break;
                    // on below line we are setting click listener
                    // for our negative button.
                    case DialogInterface.BUTTON_NEGATIVE:
                        // on below line we are dismissing our dialog box.
                        dialog.dismiss();
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // on below line we are setting message for our dialog box.
        builder.setMessage("Are you sure you want to Logout ?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show();
    }

}