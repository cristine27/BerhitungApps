package com.example.penelitianfile.View;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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

    int jumOperand1=1;
    int jumOperand2=2;
    int tandaOperator=1;
    int batas=25;
    int trigerOperator1 = 0;
    int trigerOperator2 = 0;

    protected Operand operand;
    protected Operator operatorTambah;
    protected Operator operatorKurang;
    protected Operand operand2;

//    operand 1
    int defaultOperand1x;
    int defaultOperand1y;

//    operand 2
    int defaultOperand2x;
    int defaultOperand2y;

//    operator
    int defaultOperatorx;
    int defaultOperatory;

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

        this.questionLabel = view.findViewById(R.id.question);
        this.questionCountLabel = view.findViewById(R.id.noQuestion);
        this.scoreLabel = view.findViewById(R.id.score);
        this.btn_pil1 = view.findViewById(R.id.btn_pilihan1);
        this.btn_pil2 = view.findViewById(R.id.btn_pilihan2);
        this.progressBar = view.findViewById(R.id.progress);
        this.iv_container = view.findViewById(R.id.iv_container1);

        this.presenter = new MainPresenter();
        this.paint = new Paint();
        this.bitmapW = 450;
        this.bitmapH = 300;

        this.initiateCanvas();

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
                                    resetCanvas();
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
                                    resetCanvas();
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
                                    resetCanvas();
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
                                    resetCanvas();
                                }
                            }).show();
                }
                int x = ((currentPosition+1) * 100) / presenter.getSize();
                progressBar.setProgress(x);
            }
        });
        return view;
    }

    public void initiateCanvas(){
        this.bitmap = Bitmap.createBitmap(this.bitmapW,this.bitmapH,Bitmap.Config.ARGB_8888);

        Bitmap operand = BitmapFactory.decodeResource(getResources(),R.drawable.apel);
        Bitmap operand2 = BitmapFactory.decodeResource(getResources(),R.drawable.bebek);
        Bitmap tambah = BitmapFactory.decodeResource(getResources(),R.drawable.plus);
        Bitmap kurang = BitmapFactory.decodeResource(getResources(),R.drawable.minus);

        this.bitmap = this.bitmap.copy(Bitmap.Config.ARGB_8888,true);
        this.canvas = new Canvas(this.bitmap);

        this.defaultOperand1x = this.bitmapW/3-operand.getWidth()-80;
        this.defaultOperand1y = this.bitmapH/2-operand.getHeight()/2;

        this.defaultOperand2x = this.bitmapW-operand2.getWidth()/2-50;
        this.defaultOperand2y = this.bitmapH/2-operand.getHeight()/2;

        this.operand = new Operand(1,this.bitmapW/3-operand.getWidth()-80,this.bitmapH/2-operand.getHeight()/2,operand);
        this.operand2 = new Operand(1,this.bitmapW-operand2.getWidth()/2-50,this.bitmapH/2-operand.getHeight()/2,operand2);
        this.operatorTambah = new Operator(this.bitmapW/2-tambah.getWidth()/2,this.bitmapH/2-tambah.getHeight()/2,tambah);
        this.operatorKurang = new Operator(this.bitmapW/2-kurang.getWidth()/2,this.bitmapH/2-kurang.getHeight()/2,kurang);
        this.iv_container.setImageBitmap(this.bitmap);

        this.resetCanvas();
    }

    public void resetCanvas(){
        this.trigerOperator2 = 0;
        this.trigerOperator1 = 0;
        this.operand.setX(this.defaultOperand1x);
        this.operand.setY(this.defaultOperand1y);

        this.operand2.setX(this.defaultOperand2x);
        this.operand2.setY(this.defaultOperand2y);
        Log.d("Check Masuk","reset canvas masuk");
        this.bitmap.eraseColor(Color.TRANSPARENT);

        this.paint = new Paint();
        for (int i = 0; i < jumOperand1; i++) {
            if(trigerOperator1<2) {
                this.canvas.drawBitmap(this.operand.getBitmap(), this.operand.getX(), this.operand.getY(), paint);
                this.operand.setX(this.operand.getX() + 20 + batas);
                this.operand.setY(this.operand.getY());
                this.trigerOperator1++;
            }
            else {
                this.canvas.drawBitmap(this.operand.getBitmap(),this.operand.getX(),this.operand.getY(),paint);
                this.operand.setX(this.defaultOperand1x);
                this.operand.setY(this.operand.getY() + 20 + batas);
                this.trigerOperator1 = 0;
            }
        }
        if(tandaOperator==1){
            this.canvas.drawBitmap(this.operatorTambah.getBitmap(),this.operatorTambah.getX(),this.operatorTambah.getY(),paint);
        }
        else{
            this.canvas.drawBitmap(this.operatorKurang.getBitmap(),this.operatorKurang.getX(),this.operatorKurang.getY(),paint);
        }

        for (int i = 0; i < jumOperand2; i++) {
            if(trigerOperator2<2){
                this.canvas.drawBitmap(this.operand2.getBitmap(),this.operand2.getX(),this.operand2.getY(),paint);
                this.operand2.setX(this.operand2.getX() - 20 - batas);
                this.operand2.setY(this.operand2.getY());
                this.trigerOperator2++;
            }
            else {
                this.canvas.drawBitmap(this.operand2.getBitmap(),this.operand2.getX(),this.operand2.getY(),paint);
                this.operand2.setX(this.defaultOperand2x);
                this.operand2.setY(this.operand2.getY() - 20 - batas);
                this.trigerOperator2 = 0;
            }
        }
        this.iv_container.invalidate();
    }

    public void setData(){
        if(presenter.getSize()>currentPosition) {
            //keterangan 1 == + dan o == -
            if(presenter.getOperator(currentPosition).equals("+")){
                this.tandaOperator = 1;
            }
            else{
                this.tandaOperator = 0;
            }
            this.jumOperand1 = presenter.getOperand1(currentPosition);
            this.jumOperand2 = presenter.getOperand2(currentPosition);
            System.out.println("jumOperan1 " + this.jumOperand1);
            System.out.println("jumOperan2 " + this.jumOperand2);

//            questionLabel.setText(presenter.getQuestion(currentPosition));
//            scoreLabel.setText("Score :" + numberOfCorrectAnswer + "/" + presenter.getSize());
//            questionCountLabel.setText("Question No : " + (currentPosition + 1));
//            btn_pil1.setText(presenter.getPilihan(currentPosition,1));
//            btn_pil2.setText(presenter.getPilihan(currentPosition,2));
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
