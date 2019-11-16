package com.example.penelitianfile.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.penelitianfile.R;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    protected FragmentManager fm;
    protected First_Fragment first_f;
    protected Child_Fragment child_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.first_f = First_Fragment.newInstance();
        this.child_f = Child_Fragment.newInstance();

        this.fm = this.getSupportFragmentManager();

        FragmentTransaction ft = this.fm.beginTransaction();

        ft.add(R.id.fragment_container,this.first_f)
                .addToBackStack(null)
                .commit();
    }


//    Method untuk mengubah page fragment
    public void changePage(int page){
        FragmentTransaction ft = this.fm.beginTransaction();

        if(page==1){
            if(this.first_f.isAdded()){
                ft.show(this.first_f);
            }
            else{
                ft.add(R.id.fragment_container,this.first_f);
            }
            if(this.child_f.isAdded()){
                ft.hide(this.child_f);
            }
        }
        else if(page==2){
            if(this.child_f.isAdded()){
                ft.show(this.child_f);
            }
            else{
                ft.add(R.id.fragment_container,this.child_f)
                        .addToBackStack(null);
            }
            if(this.first_f.isAdded()){
                ft.hide(this.first_f);
            }
        }
        ft.commit();
    }

//    Method untuk menutup keyboard
    public void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
