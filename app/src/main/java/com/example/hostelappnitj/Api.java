package com.example.hostelappnitj;

import com.example.hostelappnitj.ModelResponse.CreateProfileResponse;
import com.example.hostelappnitj.ModelResponse.DataModel;
import com.example.hostelappnitj.ModelResponse.RegisterResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
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
//
//    //    for LOGIN user
    @POST("login")
    Call<DataModel>login(
            @Body DataModel dataModel);

    @Multipart
    @POST("uploadProfile/{id}")
    Call<RegisterResponse>UploadProfile(
            @Path("id")String id,
            @Part MultipartBody.Part photo
    );

//
//
//    //      FOR FETCHING ALL THE USERS
//    @GET("fetchUsers")
//    Call<FetchUserResponse>fetchAllUsers();
//
    //    MAKE SURE THE PARAMTER IN { } MUST MATCHES WITH THAT PASSED IN API   /:id
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
