package com.example.hostelappnitj.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.ModelResponse.UpdateUserResponse;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountSettings_Fragment extends Fragment {
    EditText etoldpassword,etnewpassword;
    TextView txtEditProfile;
    AppCompatButton btnChangePassword;
    ProgressDialog progressDialog;
    String id;
    SharedPrefManager sharedPrefManager;

    public AccountSettings_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_account_settings_, container, false);
        btnChangePassword=view.findViewById(R.id.btnchangepassword);
        etoldpassword=view.findViewById(R.id.etoldPassword);
        etnewpassword=view.findViewById(R.id.etnewPassword);
        txtEditProfile=view.findViewById(R.id.txteditProfile);

        sharedPrefManager = new SharedPrefManager(getActivity());
        id = sharedPrefManager.getUser().get_id();

        btnChangePassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setTitle("UPDATING");
                progressDialog.setMessage("Updating Password...");
                progressDialog.show();
                progressDialog.setCancelable(false);

                String oldPassword = etoldpassword.getText().toString();
                String newPassword = etnewpassword.getText().toString();

                if (oldPassword.isEmpty()) {
                    etoldpassword.requestFocus();
                    etoldpassword.setError("Please enter your password");
                    progressDialog.dismiss();
                    return;
                }
                if (newPassword.isEmpty()) {
                    etnewpassword.requestFocus();
                    etnewpassword.setError("Please enter your password");
                    progressDialog.dismiss();
                    return;
                }
                if(newPassword.length()<8){
                    etnewpassword.requestFocus();
                    etnewpassword.setError("Minimum length Required is 8");
                    progressDialog.dismiss();
                    return;
                }
                else {
//            TO FIND THE SPECIAL CHARACTER IN PASSWORD
                    int count=0;
                    for (int i = 0; i < newPassword.length(); i++) {

                        // Checking the character for not being a
                        // letter,digit or space
                        if (!Character.isDigit(newPassword.charAt(i)) && !Character.isLetter(newPassword.charAt(i))
                                && !Character.isWhitespace(newPassword.charAt(i))) {
                            // Incrementing the countr for spl
                            // characters by unity
                            count++;
                        }
                    }
                    // When there is no special character encountered
                    if (count == 0){
                        Toast.makeText(getActivity(), "Password must contain special characters", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        return ;
                    }else{
//                loadingPB.setVisibility(View.VISIBLE);

//        create the modal to pass it as the body to the PUT Request containg the current and ol password
                        UpdateUserResponse modal_update_pass=new UpdateUserResponse(oldPassword,newPassword);
                        Call<UpdateUserResponse> call= RetrofitClient.getInstance().getApi().updatePassword(id ,modal_update_pass);

                        call.enqueue(new Callback<UpdateUserResponse>() {
                            @Override
                            public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                                UpdateUserResponse responseFromApi = response.body();
                                progressDialog.dismiss();
                                if(response.isSuccessful()) {
                                    if(responseFromApi.getError().equals("Password donot match")){
                                        etoldpassword.setText(" ");
                                        Toast.makeText(getActivity(), "Incorrect Current Password", Toast.LENGTH_SHORT).show();


                                    }else{
                                        etoldpassword.setText(" ");
                                        etnewpassword.setText(" ");
                                        Toast.makeText(getActivity(), "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                else{
                                    Toast.makeText(getActivity(),"Something went Wrong",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG);
                                progressDialog.dismiss();

                            }
                        });

                    }
                }


            }
        });
        return view;

    }
}