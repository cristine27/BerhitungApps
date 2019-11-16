package com.example.penelitianfile.Presenter;

import com.example.penelitianfile.Model.QuestionModel;
import com.example.penelitianfile.Model.QuestionOperation;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {
    protected List<QuestionModel> questionList;
    protected QuestionOperation questionOperation;

    public MainPresenter(){
        this.questionList = new ArrayList<>();
        this.questionOperation = new QuestionOperation(questionList);
        this.questionOperation.setUpQuestion();
    }

    public Boolean CheckAnswer(String answer){
        return this.questionOperation.checkAnswer(answer);
    }

    public String getAnswer(int index){
        return this.questionOperation.getAnswer(index);
    }

    public String getQuestion(int index){
        return this.questionOperation.getQuestion(index);
    }

    public int getSize(){
        return this.questionOperation.getSize();
    }

    public String getPilihan(int index,int nomor){
        return this.questionOperation.getPilihan(index, nomor);
    }
}
