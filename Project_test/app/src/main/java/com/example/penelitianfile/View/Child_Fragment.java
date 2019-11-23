package com.example.penelitianfile.View;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.example.penelitianfile.Model.Operand;
import com.example.penelitianfile.Model.Operator;
import com.example.penelitianfile.Presenter.MainPresenter;
import com.example.penelitianfile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Child_Fragment extends Fragment {
    protected FragmentListener listener;
    protected ProgressBar progressBar;
    protected TextView questionLabel, questionCountLabel, scoreLabel;
    protected Button btn_pil1,btn_pil2;
    protected MainPresenter presenter;
    protected ImageView iv_container;

    protected Paint paint;
    protected Canvas canvas;
    protected Bitmap bitmap;
    protected int bitmapW;
    protected int bitmapH;
    Context thiscontext;

    protected Operand operand;
    protected Operator operator;
    protected Bitmap bitmapOp;
    protected Bitmap bitmapOperator;

    int currentPosition = 0;
    int numberOfCorrectAnswer = 0;

    public Child_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thiscontext = container.getContext();
        final View view = inflater.inflate(R.layout.fragment_child_, container, false);

        final FrameLayout fl = (FrameLayout)view.findViewById(R.id.frameLayout);

//        fl.post(new Runnable() {
//            @Override
//            public void run() {
//                bitmapW = fl.getWidth();
//            }
//        });


        this.questionLabel = view.findViewById(R.id.question);
        this.questionCountLabel = view.findViewById(R.id.noQuestion);
        this.scoreLabel = view.findViewById(R.id.score);
        this.btn_pil1 = view.findViewById(R.id.btn_pilihan1);
        this.btn_pil2 = view.findViewById(R.id.btn_pilihan2);
        this.progressBar = view.findViewById(R.id.progress);
        this.iv_container = view.findViewById(R.id.iv_container1);

        this.presenter = new MainPresenter();
        this.bitmapW = 450;
        this.bitmapH = 300;

        Log.d("Check",bitmapW + " " + bitmapH);

        this.btn_pil1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean cek = presenter.CheckAnswer(btn_pil1.getText().toString().trim());
                if(cek) {
                    numberOfCorrectAnswer++;

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("GOOD JOB!")
                            .setContentText("RIGHT ANSWER")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    currentPosition++;
                                    setData();
                                    sweetAlertDialog.dismiss();
                                }
                            }).show();
                }
                else{
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("WRONG ANSWER")
                            .setContentText("The Right Answer is " + presenter.getAnswer(currentPosition))
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    currentPosition++;
                                    setData();
                                }
                            }).show();
                }
                int x = ((currentPosition+1) * 100) / presenter.getSize();
                progressBar.setProgress(x);
            }
        });

        this.btn_pil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean cek = presenter.CheckAnswer(btn_pil2.getText().toString().trim());
                if(cek) {
                    numberOfCorrectAnswer++;

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("GOOD JOB!")
                            .setContentText("RIGHT ANSWER")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    currentPosition++;
                                    setData();
                                    sweetAlertDialog.dismiss();
                                }
                            }).show();
                }
                else{
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("WRONG ANSWER")
                            .setContentText("The Right Answer is " + presenter.getAnswer(currentPosition))
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    currentPosition++;
                                    setData();
                                }
                            }).show();
                }
                int x = ((currentPosition+1) * 100) / presenter.getSize();
                progressBar.setProgress(x);
            }
        });
        return view;
    }

    public void setData(){
        if(presenter.getSize()>currentPosition) {
            if(presenter.getOperator().equals("+")){
//                bitmapOperator
            }
            questionLabel.setText(presenter.getQuestion(currentPosition));
            scoreLabel.setText("Score :" + numberOfCorrectAnswer + "/" + presenter.getSize());
            questionCountLabel.setText("Question No : " + (currentPosition + 1));
            btn_pil1.setText(presenter.getPilihan(currentPosition,1));
            btn_pil2.setText(presenter.getPilihan(currentPosition,2));
        }else{
            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("You have successfully completed the quiz")
                    .setContentText("Your score is " + numberOfCorrectAnswer + "/" + presenter.getSize())
                    .setConfirmText("Restart")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            currentPosition = 0;
                            numberOfCorrectAnswer = 0;
                            progressBar.setProgress(0);
                            setData();
                        }
                    })
                    .setCancelText("CLOSE")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            MainActivity main = (MainActivity)getActivity();
                            main.changePage(1);
                        }
                    }).show();
        }
    }

    public static Child_Fragment newInstance(){
        Child_Fragment f1 = new Child_Fragment();
        return f1;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  FragmentListener){
            this.listener = (FragmentListener) context;
        }
        else{
            throw  new ClassCastException(context.toString()
                    + "must implement FragmentListener");
        }
    }
}
