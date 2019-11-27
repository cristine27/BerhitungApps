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
        this.addQuestion("What is 1 + 2 ? ","3","3,1");
        this.addQuestion("What is 2 - 1 ? ","1","1,3");
        this.addQuestion("What is 5 - 2 ? ","3","3,4");
        this.addQuestion("What is 6 - 2 ? ","4","6,4");
        this.addQuestion("What is 4 + 3 ? ","7","5,7");
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
