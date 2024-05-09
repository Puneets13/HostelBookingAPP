package com.example.hostelappnitj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.hostelappnitj.Acitvity.SignInActivity;
import com.example.hostelappnitj.Acitvity.successScanActivity;
import com.example.hostelappnitj.ModelResponse.HostelRegisterationResponse;
import com.example.hostelappnitj.ModelResponse.User;
import com.example.hostelappnitj.ModelResponse.hostel;

public class SharedPrefManager {
    private static String SHARED_PREF_NAME = "Hostel_management";  //the corresponding to the shared preference
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor ;

    public  SharedPrefManager(Context context){   //the constructor to get the context of the application
        this.context=context;
    }

    public void SaveUser(User user){
//        this method will save all the values corressponding to the keys
        // Calculate the expiry time for the token (current time + 12 hours)

        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("_id", user.get_id());
        editor.putString("username", user.getUsername());
        editor.putString("avatar", user.getAvatar());
        editor.putString("email", user.getEmail());
        editor.putString("rollNumber",user.getRollNumber());
        editor.putString("branch", user.getBranch());
        editor.putString("address", user.getAddress());
        editor.putString("phone", user.getPhone());
        editor.putString("gender",user.getGender1());
        editor.putBoolean("logged",true);   //to check wheater the user is logged in or not
        editor.apply();
    }

    public void SaveHostelMessUser(hostelResponse hostelResponse){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,context.MODE_PRIVATE);
        editor= sharedPreferences.edit();
        editor.putString("userName",hostelResponse.getUserName());
        editor.putString("email", hostelResponse.getEmail());
        editor.putString("rollNumber", hostelResponse.getRollNumber());
        editor.putString("roomNumber", hostelResponse.getRoomNumber());
        editor.putString("hostelName", hostelResponse.getHostelName());
        editor.apply();

    }
    public hostelResponse getHostelResponse(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new hostelResponse(
                sharedPreferences.getString("userName",null),
                sharedPreferences.getString("email",null),
                sharedPreferences.getString("rollNumber",null),
                sharedPreferences.getString("roomNumber", null),
                sharedPreferences.getString("hostelName", null)

                );
    }


    public boolean isloggedIn() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        boolean isLoggedIn = sharedPreferences.getBoolean("logged", false);
        if (!isLoggedIn) {
            return false;
        }

        long expiryTimeMillis = sharedPreferences.getLong("expiryTime", 0);
        return System.currentTimeMillis() < expiryTimeMillis;

//        return sharedPreferences.getBoolean("logged",false);

//if the user is logged in then the shared preference will contain the logged key with true valu
// else the false default value will be returned
    }

    public void setAdmin(String userType){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("userType",userType);
        editor.apply();
    }

    public String getAdmin(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("userType",null);
    }

    public void setToken(String token){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        long expiryTimeMillis = System.currentTimeMillis() + 24 * 60 * 60 * 1000; // 12 hours expiration
//        long expiryTimeMillis = System.currentTimeMillis() + 60 * 1000; // 12 hours expiration
        editor.putLong("expiryTime", expiryTimeMillis);
        editor.putString("token",token);
        editor.apply();
    }
//    public String getToken(){
//        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
//        return sharedPreferences.getString("token",null);
//    }

    public String getToken() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        // Get the expiry time from SharedPreferences
        long expiryTimeMillis = sharedPreferences.getLong("expiryTime", 0);

        // Check if the expiry time has passed
        if (System.currentTimeMillis() >= expiryTimeMillis) {
            // Token has expired, perform logout and return null
            logout();

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Redirect to SignInActivity
                    Intent intent = new Intent(context, SignInActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }, 5000); // Delay for 5 seconds (5000 milliseconds)
            return null;
        } else {
            // Token is still valid, return the token
            return sharedPreferences.getString("token", null);
        }
    }

    public User getUser(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(sharedPreferences.getString("_id","-1"),
                sharedPreferences.getString("username",null),
                sharedPreferences.getString("avatar",null),
                sharedPreferences.getString("email",null),
                sharedPreferences.getString("rollNumber",null),
                sharedPreferences.getString("branch",null),
                sharedPreferences.getString("address",null),
                sharedPreferences.getString("phone",null),
                sharedPreferences.getString("gender",null)
                );

    }

    public String getGender(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("gender",null);
    }
//    public String getAvatar(){
//        sharedPreferences= context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
//        return sharedPreferences.getString("avatar",null);
//    }

    public void logout(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

//    shared preference for hostel
public void SaveHostelUser(hostel hostel){
//        this method will save all the values corressponding to the keys
    sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
    editor=sharedPreferences.edit();
    editor.putString("roomNumber", hostel.getRoomNumber());
    editor.putString("hostelName", hostel.getHostelName());
    editor.apply();
}
    public hostel getHostelUser(){
//        the constructor should match with the constructor present in Hostel class
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new hostel(
                sharedPreferences.getString("roomNumber",null),
                sharedPreferences.getString("hostelName",null)
        );
    }


}
