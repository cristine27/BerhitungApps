package com.example.penelitianfile.View;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.penelitianfile.R;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class First_Fragment extends Fragment {
    protected Context context;
    protected Button btn_start;
    protected Button btn_exit;
    protected ImageButton ib_music;
    protected FragmentListener listener;
    protected MediaPlayer mediaPlayer;
    protected boolean isMusicPlay=false;

    public First_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_, container, false);
        this.context = container.getContext();
        Animatoo.animateZoom(this.context);
        System.out.println("ukuran "+container.getWidth()+" "+container.getHeight());
        this.mediaPlayer = MediaPlayer.create(getActivity(),R.raw.backsound);
        this.btn_start = view.findViewById(R.id.btn_start);
        this.btn_exit = view.findViewById(R.id.btn_exit);
        this.ib_music = view.findViewById(R.id.ib_music);
        this.btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity ma = (MainActivity)getActivity();
                ma.changePage(2);
            }
        });

        this.btn_exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivity ma = (MainActivity)getActivity();
                ma.finish();
                System.exit(0);
            }
        });

        this.ib_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!isMusicPlay){
                   mediaPlayer.start();
                   FancyToast.makeText(getActivity(),"Music Play",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                   isMusicPlay = true;
               }
               else{
                   mediaPlayer.pause();
                   FancyToast.makeText(getActivity(),"Music Pause",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                   isMusicPlay = false;
               }
            }
        });

        return view;
    }

    public static First_Fragment newInstance(){
        First_Fragment f1 = new First_Fragment();
        return f1;
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }
        else{
            throw new ClassCastException(context.toString()
                    + "must implement FragmentListener");
        }
    }
}
