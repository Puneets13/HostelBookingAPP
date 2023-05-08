package com.example.hostelappnitj;

import android.provider.SyncStateContract;

import com.example.hostelappnitj.Acitvity.OTPActivity;
import com.example.hostelappnitj.ModelResponse.CreateProfileResponse;
import com.example.hostelappnitj.ModelResponse.DataModel;
import com.example.hostelappnitj.ModelResponse.ForgetpassModel;
import com.example.hostelappnitj.ModelResponse.HostelRegisterationResponse;
import com.example.hostelappnitj.ModelResponse.OTP_model;
import com.example.hostelappnitj.ModelResponse.PreRegisterResponse;
import com.example.hostelappnitj.ModelResponse.RegisterResponse;
import com.example.hostelappnitj.ModelResponse.UpdateUserResponse;
import com.example.hostelappnitj.ModelResponse.fetchAllStudentList;
import com.example.hostelappnitj.ModelResponse.fetchStudentList;
import com.example.hostelappnitj.ModelResponse.hostel;
import com.example.hostelappnitj.ModelResponse.hostel_ID_Response;
import com.example.hostelappnitj.ModelResponse.resetPasswordModel;
import com.example.hostelappnitj.ModelResponse.studentListModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {
    //  FOR REGISTERING USER
    @POST("Signup")
    Call<DataModel> register(
            @Body DataModel dataModel);
//    FOR VERIFICATION OTP
    @POST("verifyOTP")
    Call<OTP_model> veriftyOTP(
            @Body OTP_model otp_model);

    //    FOR VERIFICATION OTP
    @POST("resendOTP")
    Call<OTP_model> resendOTP(
            @Body OTP_model otp_model);
//    //    for LOGIN user
    @POST("login")
    Call<DataModel>login(
            @Body DataModel dataModel);

//    for uploading image
    @Multipart
    @POST("uploadProfile/{id}")
    Call<RegisterResponse>UploadProfile(
            @Path("id")String id,
            @Part MultipartBody.Part photo
    );


    //    to update the password from user USE PUT
    @PUT("password/{id}")
    Call<UpdateUserResponse>updatePassword(
            @Path("id")String user_id,
            @Body UpdateUserResponse updateUserResponse
    );

    //    //      FOR FETCHING ALL THE HOSTELS
    @GET("hostelbook/getHostels")
    Call<HostelRegisterationResponse>fetchAllHostels();


//    @Multipart
    @POST("hostelbook/registerRoom")   //since we are sending the paramter in the path with id (so we use @PATH)
    Call<HostelRegisterationResponse>updateHostelRecord(
//            @Part MultipartBody.Part pdf,
@Body HostelRegisterationResponse hostelRegisterationResponse
//            @Field ("userName") String userName,
//            @Field ("roomNumber") String roomNumber,
//            @Field ("email") String email,
//            @Field("rollNumber")String rollNumber
            );


//    For processing of Hostel button
    @POST("hostelbook/proceed")
    Call<PreRegisterResponse> PreRegisterResponse(
            @Body PreRegisterResponse preRegisterResponse
    );

    @GET("hostelbook/getAllRoomStatus")
    Call<PreRegisterResponse>fetchAllHostelsStatus();

    @POST("hostelbook/expire")
    Call<PreRegisterResponse> PreRegisterExpireResponse(
            @Body PreRegisterResponse preRegisterResponse
    );

    @POST("hostelbook/searchbyRoom")
    Call<studentListModel> studentListResponse(
      @Body studentListModel studentListModel
    );

    @POST("hostelbook/searchbyName")
    Call<fetchStudentList>fetchStudentList(
            @Body fetchStudentList fetchStudentList
    );
    @POST("hostelbook/searchOnlybyName")
    Call<fetchStudentList>fetchStudentListHome(
            @Body fetchStudentList fetchStudentList
    );

    @POST("hostelbook/searchAllOnehostel")
    Call<fetchAllStudentList>fetchAllStudents(
            @Body fetchAllStudentList fetchAllStudentList
    );

    @POST("hostelbook/deleteHostelList")
    Call<DataModel>deleteUsers(
            @Body DataModel dataModel
    );

    @POST("forgetPass")
    Call<ForgetpassModel>forgetpassword(
            @Body ForgetpassModel forgetpassModel
    );
    //    FOR VERIFICATION OTP
    @POST("submitForgetPassOTP")
    Call<OTP_model> veriftyOTPForgetPass(
            @Body OTP_model otp_model);

    @POST("resetPassword")
    Call<resetPasswordModel> resetpassword(
            @Body resetPasswordModel resetPasswordModel);

    @POST("hostelbook/sendEmail")
    Call<studentListModel> snedEmail(
            @Body studentListModel studentListModel
    );


//    @Multipart
//    @POST("XXXX") resetPassword
//    Call<HostelRegisterationResponse> update(@Part(SyncStateContract.Constants.ACTION_ID) RequestBody actionId, @Part(Constants.OFFER_CODE) RequestBody offerCode);



//    @Multipart
//    @POST("uploadProfile/{id}")
//    Call<RegisterResponse>UploadProfile(
//            @Path("id")String id,
//            @Part MultipartBody.Part photo
//    );


//
//@GET("hostel_booking/BookHostel/{roomNumber}")  //to get the id of the particular room number
//Call<hostel_ID_Response> getHostelId(
//        @Path("roomNumber") String hostel_id
//);

//
//
//
//        MAKE SURE THE PARAMTER IN { } MUST MATCHES WITH THAT PASSED IN API   /:id
    @PUT("{id}")   //since we are sending the paramter in the path with id (so we use @PATH)
    Call<CreateProfileResponse>createUserProfile(
            @Path("id") String user_id,
            @Body CreateProfileResponse createProfileResponse
    );

//
//    //    to update the password from user USE PUT
//    @PUT("password/{id}")
//    Call<UpdateUserResponse>updatePassword(
//            @Path("id")String user_id,
//            @Body UpdateUserResponse updateUserResponse
//    );
//
//    //    To delete the account
//    @DELETE("{id}")
//    Call<RegisterResponse>deleteuser(
//            @Path("id")  String user_id);
//
//    //to update the image on the server  //USING HASHMAP
//    @Multipart
//    @POST("uploadProfile/{id}")
//    Call<RegisterResponse>UploadProfile(
//            @Path("id")String id,
//            @Part MultipartBody.Part photo
//    );
//
//    @DELETE("deleteProfile/{id}")
//    Call<RegisterResponse>deleteProfile(
//            @Path("id") String id ,
//            @Query("imageURL") String imageURL
//    );
}
