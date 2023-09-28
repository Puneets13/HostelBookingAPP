package com.example.hostelappnitj.Girls_Hostel_A;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.hostelappnitj.ModelResponse.PreRegisterResponse;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mgh_A_floor_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgh_a_floor2);
    }
    private void loadStatus() {
        //        API call for Status verification
        Call<PreRegisterResponse> call1 = RetrofitClient.getInstance().getApi().fetchAllHostelsStatus();
        call1.enqueue(new Callback<PreRegisterResponse>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<PreRegisterResponse> call, Response<PreRegisterResponse> response) {
                PreRegisterResponse responseFromAPI1 = response.body();
                if(response.isSuccessful()){
                    progressDialog.dismiss();  //if any error occurs then it need to be returned

                    hostelStatusList=responseFromAPI1.getHostelStatusList();
                    int n = hostelStatusList.size();
                    String status_received,room,hostel_name,nums;
                    String room2 = "";
                    for (int i =0 ; i<n;i++){
                        status_received=hostelStatusList.get(i).getStatus();
                        room=hostelStatusList.get(i).getRoomNumber();
                        hostel_name=hostelStatusList.get(i).getHostelName();
                        nums=hostelStatusList.get(i).getNums();

//                        room2=room+" ";

//                            if condition for evaluating the hostel name
                        //concept
//                        vacancy    status   output
//                           0        0         no
//                           1        0         light blue
//                           2        0         blue
//                           0        1       red
//                           1        1       red
//                           2        1       red
                        if(hostel_name.equals("Mega Girls Hostel")){
                            if(room!=null) {
                                if (status_received.equals("1")) {   //temporary blocked
                                    try{
                                        String btnid = "room" + room;
                                        int resId = getResources().getIdentifier(btnid, "id", getPackageName());  //to get the ID of resource at runtime
                                        Button b = (Button) findViewById(resId);
                                        b.setBackgroundResource(R.drawable.room_temporarily_blocked);
                                    }catch (NullPointerException e){
                                        progressDialog.dismiss();  //if any error occurs then it need to be returned
                                        e.printStackTrace();
                                    }
                                }
                                if (status_received.equals("0")&& nums.equals("1")) {   //unBlock room num people =1
                                    try{
                                        String btnid = "room" + room;
                                        int resId = getResources().getIdentifier(btnid, "id", getPackageName());  //to get the ID of resource at runtime
                                        Button b = (Button) findViewById(resId);
                                        b.setBackgroundResource(R.drawable.room_occupied_partially);
                                    }catch (NullPointerException e){
                                        progressDialog.dismiss();  //if any error occurs then it need to be returned
                                        e.printStackTrace();
                                    }
                                }
                                if (status_received.equals("0")&& nums.equals("2")) {   //unBlock room  , num people 2
                                    try{
                                        String btnid = "room" + room;
                                        int resId = getResources().getIdentifier(btnid, "id", getPackageName());  //to get the ID of resource at runtime
                                        Button b = (Button) findViewById(resId);
                                        b.setBackgroundResource(R.drawable.room_occupied_full);
                                    }catch (NullPointerException e){
                                        progressDialog.dismiss();  //if any error occurs then it need to be returned
                                        e.printStackTrace();
                                    }
                                }
                                if (status_received.equals("0")&& nums.equals("0")) {   //unBlock room  , num people 2
                                    try{
                                        String btnid = "room" + room;
                                        int resId = getResources().getIdentifier(btnid, "id", getPackageName());  //to get the ID of resource at runtime
                                        Button b = (Button) findViewById(resId);
                                        b.setBackgroundResource(R.drawable.room_gradient2);
                                    }catch (NullPointerException e){
                                        progressDialog.dismiss();  //if any error occurs then it need to be returned
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<PreRegisterResponse> call, Throwable t) {
                progressDialog.dismiss();  //if any error occurs then it need to be returned
            }
        });
    }
}
