package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.MainActivity;
import com.example.hostelappnitj.ModelResponse.DailyScannerModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.example.hostelappnitj.item_list_adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExtraSnacksActivity extends AppCompatActivity {
    EditText etExtraAmount;
    TextToSpeech textToSpeech ;

    AppCompatButton btnConfirmPayment;
    String rollNumber , hostelName , roomNumber;
    String email ,  month , year , mealType , formattedDate,item;
    int amount;
    SharedPrefManager sharedPrefManager;
    ProgressDialog progressDialog;
    TextView txtHostelname;

    private ListView listView;
    private item_list_adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_snacks);
//        etExtraAmount = findViewById(R.id.etExtrasAmount)
        btnConfirmPayment = findViewById(R.id.btnExtrasPayment);
        txtHostelname = findViewById(R.id.txtHostelname);

        listView = findViewById(R.id.listView);


        DateFormat dateFormat2 = new SimpleDateFormat("hh.mm aa");
        String dateString2 = dateFormat2.format(new Date()).toString();

        sharedPrefManager=new SharedPrefManager(ExtraSnacksActivity.this);
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        hostelName=sharedPrefManager.getHostelUser().getHostelName();
        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();
        txtHostelname.setText(hostelName);

        List<String> items = Arrays.asList("Samosa", "Curd", "Lassi", "Milk", "Half Milk", "Butter", "Pakoda", "Bread");
        adapter = new item_list_adapter(this, R.layout.list_item_checkbox, items);
        listView.setAdapter(adapter);

//
//        Spinner dropdown = findViewById(R.id.spinnerItems);
//        dropdown.setPrompt("items");
//        String[] items = new String[]{
//                "Select.."
//                , "Samosa"
//                , "Lassi"
//                ,  "tea"
//        };
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
////set the spinners adapter to the previously created one.
//        dropdown.setAdapter(adapter);
//        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                Log.v("item", (String) parent.getItemAtPosition(position));
//                if (position == 0) {
//                    item = null;
//                } else {
//                    item = (String) parent.getItemAtPosition(position);
//                Toast.makeText(ExtraSnacksActivity.this, item, Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//            }
//        });





        //        text To speech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!= TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean[] checkedItems = adapter.getCheckedItems();
                StringBuilder selectedItems = new StringBuilder();
                for (int i = 0; i < checkedItems.length; i++) {
                    if (checkedItems[i]) {
                        selectedItems.append(items.get(i)).append(" , ");
                    }
                }
                Toast.makeText(ExtraSnacksActivity.this, "Selected items:\n" + selectedItems.toString(), Toast.LENGTH_SHORT).show();

                item = selectedItems.toString();

                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                formattedDate = dateFormat.format(currentDate);

                // Extract year, month, and day
                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                year = yearFormat.format(currentDate);

                SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
                month = monthFormat.format(currentDate);

                SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
                int day = Integer.parseInt(dayFormat.format(currentDate));

                // Format the time
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                String formattedTime = timeFormat.format(currentDate);

                // Parse the formatted time to get hours and minutes
                int hour = Integer.parseInt(formattedTime.split(":")[0]);
                int minute = Integer.parseInt(formattedTime.split(":")[1]);
                mealType ="";
                // Check the time ranges and print appropriate messages
            if (hour >= 7 && hour < 11) {  // 7:00 AM to 11:00 AM
//                    System.out.println("Good Morning!");
                mealType = "breakfast";
            }
                if (hour >= 10 && hour < 13) {  // 7:00 AM to 11:00 AM
//                    System.out.println("Good Morning!");
//                Toast.makeText(getActivity(), "its ", Toast.LENGTH_SHORT).show();
                    mealType = "lunch";
                }
                else if (hour >= 12 && hour < 15) {   // 12:00PM to 3:00PM
//                    System.out.println("Good Afternoon!");
                    mealType = "lunch";
                }
//            else if ((hour >= 19 && hour < 22 && minute >= 30) || (hour == 22 && minute <= 30)) { // 7:30 PM to 10:30 PM
////                    System.out.println("Good Evening!");
//                mealType = "dinner";
//            }

                else if ((hour >= 16 && hour < 18 && minute >= 30) || (hour == 18 && minute <= 30)) { // 4:30 PM to 6:30 PM
                    mealType = "snacks";
                }

//            CHANGED
                else if ((hour >= 19 && hour < 24 ) ) { // 7:30 PM to 10:30 PM
//                    System.out.println("Good Evening!");
                    mealType = "dinner";
                }
                else {
//                    System.out.println("Hello!");
                    Toast.makeText(ExtraSnacksActivity.this, "Visit in Mess Timings", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(ExtraSnacksActivity.this);
                    builder.setTitle("ALERT");
                    builder.setMessage("Mess Closed\nPlease visit in Meal Timings\nHappy to see you for next meal. "+mealType);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
                }


                SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");

                // Get the current date and time
                Date currentDate1 = new Date();

                // Format the current time using SimpleDateFormat
                String formattedTime1 = dateFormat1.format(currentDate1);


//                String amount = etExtraAmount.getText().toString();
//                if(amount.isEmpty()){
//                    etExtraAmount.requestFocus();
//                    etExtraAmount.setError("Please enter Amount");
//                    return;
//                }
//                int amountInt = Integer.parseInt(amount);
//                if( amountInt<=0 || amountInt>1000){
//                    etExtraAmount.setText("");
//                    Toast.makeText(ExtraSnacksActivity.this, "Please enter valid amount", Toast.LENGTH_SHORT).show();
//                    return;
//                }else{
//
                    progressDialog = new ProgressDialog(ExtraSnacksActivity.this);
                    progressDialog.setTitle("Processing");
                    progressDialog.setMessage("Transaction in progress..");
                    progressDialog.show();
                    progressDialog.setCancelable(false);

//                    item = "Lassi";
                    amount = 20;

                    DailyScannerModel model = new DailyScannerModel(roomNumber,hostelName,month,year,mealType,formattedDate,formattedTime1,item,amount);
                    Call<DailyScannerModel> call = RetrofitClient.getInstance().getApi().getExtraMeal(model);
                    call.enqueue(new Callback<DailyScannerModel>() {
                        @Override
                        public void onResponse(Call<DailyScannerModel> call, Response<DailyScannerModel> response) {
                            progressDialog.dismiss();
                            DailyScannerModel responseFromAPI = response.body();
                            if(response.isSuccessful()){
                                if(responseFromAPI.getMessage().equals("you have consumed extras")){

                                    String speak = "item added.   Enjoy your meal";
                                    textToSpeech.setSpeechRate(1);
                                    textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
                                    Intent intent = new Intent(ExtraSnacksActivity.this, successScanActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();

                                }else if(responseFromAPI.getMessage().equals("you are on leave")){
                                    Toast.makeText(ExtraSnacksActivity.this, "You are on leave", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(ExtraSnacksActivity.this, "Transaction Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DailyScannerModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(ExtraSnacksActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
//                }


            }
        });
    }
}