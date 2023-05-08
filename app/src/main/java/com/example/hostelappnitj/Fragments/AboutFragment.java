package com.example.hostelappnitj.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.hostelappnitj.R;

import java.util.Locale;


public class AboutFragment extends Fragment {
//CardView cardView;
public Drawable drawableRight;
Integer x=0;
//    private DrawableClickListener clickListener;
    TextToSpeech textToSpeech;
    String speak;
public AppCompatButton textRoom;
    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_about, container, false);
        textRoom=view.findViewById(R.id.textRoom);
        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!= TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
                textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                    @Override
                    public void onStart(String utteranceId) {

                    }

                    @Override
                    public void onDone(String utteranceId) {
                        textRoom.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.mute), null);

                    }

                    @Override
                    public void onError(String utteranceId) {

                    }
                });
            }
        });

//        cardView = (CardView) view.findViewById(R.id.cardView3);
//        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
//        int mHeightScreen = displaymetrics.heightPixels;
////        cardView.setMinimumHeight((int)(0.5)*mHeightScreen);


        speak="The overall atmosphere of the hostel is calm and peaceful. There are various kinds of facilities available in hostels such as  Saloon, Canteen, Mess etc. The facilities of reading room, indoor games, washing machines etc. are available in each hostel. Internet facility is provided in every hostel. Other facilities like geysers, water purifiers and water coolers are provided in all the hostels." +
                "The hostels at NITJ offer comfortable and fully furnished rooms with personal storage space, ensuring a comfortable and homely stay for students." +
                "The hostels at NITJ provide a supportive and inclusive environment for students, where they can make new friends and develop a sense of community.";
        textRoom.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;


                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (textRoom.getRight() - textRoom.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {


                        if(x==0){
                            x++;
                            textRoom.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.unmute), null);
                            textToSpeech.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
                            return true;
                        }
                         else if(x==1){
                             x--;
                             textToSpeech.stop();
                            textRoom.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.mute), null);
                            return true;
                        }
                       }
                    }

                return false;
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        textToSpeech.stop();
        textRoom.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.mute), null);


    }
}