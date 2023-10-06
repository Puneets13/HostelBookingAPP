package com.example.hostelappnitj.MessFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hostelappnitj.Acitvity.ExtraSnacksActivity;
import com.example.hostelappnitj.Acitvity.successScanActivity;
import com.example.hostelappnitj.R;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class ExtrasScannerFragment extends Fragment {
    AppCompatButton btnExtrasScanner;
    public ExtrasScannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_extras_scanner, container, false);
        btnExtrasScanner = view.findViewById(R.id.extras_scanner);

        ScanCode();

        btnExtrasScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanCode();
            }
        });
        return view;
    }
    private void ScanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(false);
        options.setCaptureActivity(CaptureActivity.class);
        barLauncherExtras.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLauncherExtras = registerForActivityResult(new ScanContract(), result->{
        if(result.getContents() != null)
        {

            String dailyScanner = "@#$%^NIT_MESS_DailyScanner@#$%^";
            int hashNumberDaily= dailyScanner.hashCode();
            String hashCodeNumberString = String.valueOf(hashNumberDaily);

            String ExtraScanner = "@#$%^NIT_MESS_ExtraScanner";
            int hashNumberExtras= ExtraScanner.hashCode();
            String hashCodeExtraNumberString = String.valueOf(hashNumberExtras);

            if(hashCodeExtraNumberString.equals(result.getContents())){
//            Extra scanner
                Intent intent = new Intent(getActivity(), ExtraSnacksActivity.class);
                Toast.makeText(getActivity(), "Extra meal", Toast.LENGTH_SHORT).show();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            if(hashCodeNumberString.equals(result.getContents())){

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("ALERT");
                builder.setMessage("Please use Daily Meal Scanner");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }

        }
    });

}