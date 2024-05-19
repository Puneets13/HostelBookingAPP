package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.ModelResponse.innvoiceModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InnvoiceActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    TextView txtRollNumber , txtMonth, txtYear , txtPerDietCost , txtDietCount,txttotalInvoice,txtHostelName,txtemail,txtHostelNameHeading;
    String email ,  rollNumber , hostelName , roomNumber, month , year , mealType , formattedDate;
    ProgressDialog progressDialog;
    AppCompatButton btnDetailInvocie , btnPrintInvoice;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innvoice);
        txtDietCount=findViewById(R.id.dietCount);
        txtRollNumber=findViewById(R.id.invoice_roll_number);
        txtHostelName=findViewById(R.id.invoice_hostel_name);
        txtHostelNameHeading = findViewById(R.id.txtHostelNameHeading);
        txtMonth =findViewById(R.id.invoice_month);
        txtYear =findViewById(R.id.invoice_year);
        txtPerDietCost =findViewById(R.id.perDietCost);
        txttotalInvoice = findViewById(R.id.totalInvoice);
        txtemail = findViewById(R.id.invoice_email);
         btnDetailInvocie = findViewById(R.id.btnDetailInvocie);
         btnPrintInvoice = findViewById(R.id.btnPrintInvoice);

//        rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = getWindow().getDecorView().findViewById(R.id.rootViewInnvocie);

        sharedPrefManager=new SharedPrefManager(InnvoiceActivity.this);
        email = sharedPrefManager.getHostelResponse().getEmail();
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        hostelName=sharedPrefManager.getHostelUser().getHostelName();
        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();

        progressDialog = new ProgressDialog(InnvoiceActivity.this);
        progressDialog.setTitle("Generating Invoice");
        progressDialog.setMessage("Processing..\nHave patience.....");
        progressDialog.show();
        progressDialog.setCancelable(false);


        Intent intent=getIntent();
        month = intent.getStringExtra("month");
        year =  intent.getStringExtra("year");

//        month = "04";
//        year="2024";

        txtemail.setText(email);
        txtMonth.setText(month);
        txtYear.setText(year);
        txtRollNumber.setText(rollNumber);
        txtHostelName.setText(hostelName);
        txtHostelNameHeading.setText("Mess Invoice\n"+hostelName);
//        Toast.makeText(this, hostelName, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, rollNumber, Toast.LENGTH_SHORT).show();


        btnPrintInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertLayoutToPdf();
            }
        });

        btnDetailInvocie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailMessBillActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                startActivity(intent);
            }
        });
        innvoiceModel model = new innvoiceModel(rollNumber,hostelName,month,year);
        String token = sharedPrefManager.getToken();
        Call<innvoiceModel> call = RetrofitClient.getInstance().getApi().generateInvoice("Bearer " + token,model);
        call.enqueue(new Callback<innvoiceModel>() {
            @Override
            public void onResponse(Call<innvoiceModel> call, Response<innvoiceModel> response) {

                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    String mess = response.body().getMessage();
                    String error = response.body().getError();

                    if(response.body().getMessage().equals("Invoice generated successfully")){
                        // Convert the string to a double
                        String dietCount = response.body().getDietCount();
                        String PerDietCost = response.body().getPerDietCost();
                        String totalInvoice = response.body().getTotalInvoice();

                        double doubledietCount = Double.parseDouble(dietCount);
                        double doublePerDietCost = Double.parseDouble(PerDietCost);
                        double doubletotalInvoice = Double.parseDouble(totalInvoice);

                        // Round the double to three decimal places


                        // Use DecimalFormat to format the number
                        txtDietCount.setText(roundToThreeDecimal(doubledietCount));
                        txtPerDietCost.setText(roundToThreeDecimal(doublePerDietCost)+"/-");
                        txttotalInvoice.setText(roundToThreeDecimal(doubletotalInvoice)+"/-");
                    }
                    if(response.body().getMessage().equals("Wait till end of month")){
                        progressDialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(InnvoiceActivity.this);
                        builder.setTitle("Error");
                        builder.setMessage("Invoice has not been generated yet");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        }).show();
                    }
                    if(response.body().getMessage().equals("Hostel not found")){
                        progressDialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(InnvoiceActivity.this);
                        builder.setTitle("Error");
                        builder.setMessage("Invoice details has not been updated\nContact Mess Admin");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        }).show();
                    }
                } else{
//                                check if token is not verified
                    if(response.code() == 500) {
                        // Unauthorized - Token is invalid or expired
                        // Redirect user to login screen or take appropriate action
                        AlertDialog.Builder builder = new AlertDialog.Builder(InnvoiceActivity.this);
                        builder.setTitle("ALERT");
                        builder.setMessage("Your Session expired\nPlease login Again");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                return;
                            }
                        }).show();
                        // Redirect to login screen or logout user
                    } else {
                        // Handle other HTTP error codes
                        Toast.makeText(InnvoiceActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<innvoiceModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(InnvoiceActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    // Function to round a double to three decimal places
    public static String roundToThreeDecimal(double value) {
        DecimalFormat df = new DecimalFormat("#.###"); // Define the format

        // Use DecimalFormat to format the number
        String roundedString = df.format(value);

        // Parse the formatted string back to a double
        return roundedString ;
    }



    private void convertLayoutToPdf() {
        // Capture the entire content of the screen
        Bitmap bitmap = getLayoutBitmap(rootView);

        if (bitmap != null) {
            // Convert the captured content to PDF
            convertToPdf(bitmap);
        } else {
            Toast.makeText(this, "Failed to capture screen", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap getLayoutBitmap(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private void convertToPdf(Bitmap bitmap) {
        // Step 1: Convert Bitmap to PDF


        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, 0, 0, paint);
        document.finishPage(page);

        // Step 2: Save PDF to File
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());
        String pdfFileName = currentDateAndTime;

        String fileName = "MessInvoice_"+ pdfFileName +".pdf";
        File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);
        try {
            document.writeTo(new FileOutputStream(pdfFile));
            document.close();

            // Step 3: Provide Download Option
            openPdfFile(pdfFile);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save PDF", Toast.LENGTH_SHORT).show();
        }
    }

    private void openPdfFile(File pdfFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = FileProvider.getUriForFile(this, Objects.requireNonNull(getPackageName()) + ".fileprovider", pdfFile);
        intent.setDataAndType(uri, "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }


}