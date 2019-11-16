package com.example.penelitianfile.Model;

import com.example.penelitianfile.Model.QuestionModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionOperation {
    private List<QuestionModel> list;
    private int numberOfCorrectAnswer;
    private int currentPosition;
    private Boolean checkAnswer;

    public QuestionOperation(List<QuestionModel> list){
        this.list = list;
        this.currentPosition = 0;
        this.numberOfCorrectAnswer = 0;
        this.checkAnswer = false;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public int getNumberOfCorrectAnswer() {
        return numberOfCorrectAnswer;
    }

    public void addQuestion(String question, String answer,String pilihan){
        this.list.add(new QuestionModel(question,answer,pilihan));
    }

    public String getQuestion(int index){
        return this.list.get(index).getQuestion();
    }

    public String getAnswer(int index){
        return this.list.get(index).getAnswer();
    }

    public Boolean checkAnswer(String answer){
        boolean answer_check = false;
        if(answer.equalsIgnoreCase(this.getAnswer(this.currentPosition))){
            numberOfCorrectAnswer++;
            currentPosition++;
            answer_check = true;
        }
        else{
            currentPosition++;
        }
        return answer_check;
    }

//   untuk soal default
    public void setUpQuestion(){
        this.addQuestion("What is 1+2 ? ","3","3,1");
        this.addQuestion("What is 1+8 ? ","9","6,9");
        this.addQuestion("What is 9-2 ? ","7","7,4");
        this.addQuestion("What is 8-2 ? ","6","6,1");
        this.addQuestion("What is 12+3 ? ","15","5,15");
    }

    public List<QuestionModel> getList(){
        return this.list;
    }

    public int getSize(){
        return this.list.size();
    }

    public String getPilihan(int index,int nomor){
        String[] temp = this.list.get(index).getPilihan();
        if(nomor==1){
            return temp[0];
        }
        else{
            return temp[1];
        }
    }
}
