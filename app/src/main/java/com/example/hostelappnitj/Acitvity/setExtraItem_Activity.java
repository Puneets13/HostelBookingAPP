package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hostelappnitj.ItemAdapter;
import com.example.hostelappnitj.ItemListAdapter;
import com.example.hostelappnitj.ModelResponse.constantsModel;
import com.example.hostelappnitj.ModelResponse.extra_item_model;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class setExtraItem_Activity extends AppCompatActivity {

    ExtendedFloatingActionButton btnAddItem;
//    SharedPrefManager sharedPrefManager;
    String hostelName;
    Map<String,Integer> items;
    RecyclerView recyclerView;

    private ItemAdapter adapter;
    private List<Map<String, String>> itemList;

    private Map<String, Integer> itemMap;
    List<String> items_list ,  checkedItems , item ;
//    private RecyclerView recyclerView;


//    private final Handler handler = new Handler(Looper.getMainLooper());
//
//    private final Runnable updateRunnable = new Runnable() {
//        @Override
//        public void run() {
//            // Call the method to fetch updated data from MongoDB and update the RecyclerView
//
//            showList();
//            // Schedule the next update after a delay (e.g., every 5 seconds)
//            handler.postDelayed(this, 10000); // Adjust the delay as needed
//        }
//    };


//    private BroadcastReceiver refreshReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals("refresh_adapter")) {
//                // Refresh the data or perform any necessary actions
//                showList(); // Call your method to refresh the list
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_extra_item);


//        // Register the BroadcastReceiver
//        IntentFilter filter = new IntentFilter("refresh_adapter");
//        registerReceiver(refreshReceiver, filter);

        btnAddItem=findViewById(R.id.floating_addButton);
        recyclerView= findViewById(R.id.recyleview_setExtra_item);
        items= new HashMap<>();


        itemMap = new HashMap<>();
        items_list = new ArrayList<>();
//        checkedItems = new ArrayList<>();
        item = new ArrayList<>();

//        sharedPrefManager=new SharedPrefManager(setExtraItem_Activity.this);
//        hostelName= sharedPrefManager.getHostelResponse().getHostelName();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        hostelName="Boys Hostel 7";////////
        Toast.makeText(this, hostelName, Toast.LENGTH_SHORT).show();


       showList();
//       handler.post(updateRunnable);


    btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAddItemDialog();
                showList();
            }
        });
    }

    private void showList() {

        constantsModel model = new constantsModel(hostelName);

        Call<constantsModel> call = RetrofitClient.getInstance().getApi().getMessItemsList(model);
        call.enqueue(new Callback<constantsModel>() {
            @Override
            public void onResponse(Call<constantsModel> call, Response<constantsModel> response) {
                if(response.isSuccessful()){
                    if(response.body().getMessage().equals("success")){
//                        progressDialog.dismiss();
                        itemMap = response.body().getItem();
                        for (Map.Entry<String, Integer> entry : itemMap.entrySet()) {
                            String item_price =  entry.getKey() + ": " + entry.getValue();
                            items_list.add(item_price);

                        }
                        Collections.sort(items_list);
                        adapter = new ItemAdapter(setExtraItem_Activity.this, items_list,hostelName);
                        recyclerView.setAdapter(adapter);

                    } else if (response.body().getMessage().equals("no item found")){
//                        progressDialog.dismiss();
                        Toast.makeText(setExtraItem_Activity.this, "No Items found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<constantsModel> call, Throwable t) {
                Toast.makeText(setExtraItem_Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showAddItemDialog() {
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
    LayoutInflater inflater = this.getLayoutInflater();
    View dialogView = inflater.inflate(R.layout.dialog_add_item, null);
    dialogBuilder.setView(dialogView);

    final EditText editTextItemName = dialogView.findViewById(R.id.editTextItemName);
    final EditText editTextItemPrice = dialogView.findViewById(R.id.editTextItemPrice);

    dialogBuilder.setTitle("Add Item");

    dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
            String itemName = editTextItemName.getText().toString().trim();
            String itemPricestr=editTextItemPrice.getText().toString().trim();
            Integer itemPrice = Integer.parseInt(itemPricestr);
            if(itemName.isEmpty() || itemPricestr.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please fill all required fields", Toast.LENGTH_SHORT).show();
                return;
            }
            extra_item_model model = new extra_item_model(itemPrice,hostelName,itemName);
            Call<extra_item_model> call = RetrofitClient.getInstance().getApi().addItemInConstant(model);
            call.enqueue(new Callback<extra_item_model>() {
                @Override
                public void onResponse(Call<extra_item_model> call, Response<extra_item_model> response) {
                    if(response.isSuccessful()){
                        if(response.body().getMessage().equals("success")){
                            items=response.body().getItems();
                        }else{
                            Toast.makeText(setExtraItem_Activity.this, "Error in inserting item", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<extra_item_model> call, Throwable t) {
                    Toast.makeText(setExtraItem_Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    });

    dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
            // Canceled.
            dialog.dismiss();
        }
    });

    AlertDialog addItemDialog = dialogBuilder.create();
    addItemDialog.show();
}

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // Unregister the BroadcastReceiver
//        unregisterReceiver(refreshReceiver);
//    }

}
