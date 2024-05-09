package com.example.hostelappnitj.Acitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelappnitj.ModelResponse.ConsumedItemsModel;
import com.example.hostelappnitj.ModelResponse.MessDetailModel;
import com.example.hostelappnitj.R;
import com.example.hostelappnitj.RetrofitClient;
import com.example.hostelappnitj.SharedPrefManager;
import com.example.hostelappnitj.detailMessAdapter;
import com.example.hostelappnitj.userAdapterMessRecord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMessBillActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    TextView txtRollNumber ,txtdetialBill, txtMonth, txtYear , txtPerDietCost , txtDietCount,txttotalInvoice,txtHostelName,txtemail,txtHostelNameHeading;
    String email ,  rollNumber , hostelName , roomNumber, month , year , mealType , formattedDate;
    ProgressDialog progressDialog;
    AppCompatButton btnPdfDowload;
    RecyclerView recyclerView;

    private View rootView;

    List<ConsumedItemsModel> consumedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mess_bill);
        recyclerView = findViewById(R.id.recyleview);

        btnPdfDowload=findViewById(R.id.downloadDetialedPDF);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        sharedPrefManager=new SharedPrefManager(DetailMessBillActivity.this);
        email = sharedPrefManager.getHostelResponse().getEmail();
        rollNumber = sharedPrefManager.getUser().getRollNumber();
        hostelName=sharedPrefManager.getHostelUser().getHostelName();
        roomNumber=sharedPrefManager.getHostelUser().getRoomNumber();

        rootView = getWindow().getDecorView().findViewById(android.R.id.content);


        btnPdfDowload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertLayoutToPdf();
            }
        });



        Intent intent=getIntent();
        month = intent.getStringExtra("month");
        year =  intent.getStringExtra("year");

//use intent to bring dates
//        month = "04";
//        year = "2024";
        Toast.makeText(this, rollNumber, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, hostelName, Toast.LENGTH_SHORT).show();
        MessDetailModel model = new MessDetailModel(rollNumber,hostelName,roomNumber,month,year);
        String token = sharedPrefManager.getToken();
        Call<MessDetailModel> call = RetrofitClient.getInstance().getApi().printConsumedItemsByStudent("Bearer " + token,model);
        call.enqueue(new Callback<MessDetailModel>() {
            @Override
            public void onResponse(Call<MessDetailModel> call, Response<MessDetailModel> response) {

                if(response.isSuccessful()){
//                    Toast.makeText(DetailMessBillActivity.this, "imin", Toast.LENGTH_SHORT).show();
                    if(response.body().getMessage().equals("Consumed items retrieved successfully")){
//                        Toast.makeText(DetailMessBillActivity.this, "inside", Toast.LENGTH_SHORT).show();
//                        txtdetialBill.setText(response.body().getConsumedItems().get(0).getDate());
                        consumedItems = response.body().getConsumedItems();
                        detailMessAdapter adapter = new detailMessAdapter(DetailMessBillActivity.this,consumedItems);
                        recyclerView.setAdapter(adapter);
//                        recyclerView.setAdapter(new detailMessAdapter(DetailMessBillActivity.this, consumedItems));

                    }
                    if(response.body().getMessage().equals("Internal server error")){
                        Toast.makeText(DetailMessBillActivity.this, "Internal server error1", Toast.LENGTH_SHORT).show();
                    }
                } else{
//                                check if token is not verified
                    if(response.code() == 500) {
                        // Unauthorized - Token is invalid or expired
                        // Redirect user to login screen or take appropriate action
                        AlertDialog.Builder builder = new AlertDialog.Builder(DetailMessBillActivity.this);
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
                        Toast.makeText(DetailMessBillActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MessDetailModel> call, Throwable t) {
                Toast.makeText(DetailMessBillActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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
        String fileName = "DetailedMessBill"+month+"_"+year+".pdf";
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