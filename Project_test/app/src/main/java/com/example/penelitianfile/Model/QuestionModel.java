package com.example.penelitianfile.Model;

//public class QuestionModel {
//
//    private String Operant;
//    private String Operator;
//    private String Answer;
//
//    public QuestionModel(String Operant,String Operator, String answer) {
//        this.Operant = Operant;
//        this.Operator = Operator;
//        this.Answer = answer;
//    }
//
//    public String getAnswer() {
//        return Answer;
//    }
//
//    public String getOperant() {
//        return Operant;
//    }
//
//    public String getOperator() {
//        return Operator;
//    }
//
//    public void setAnswer(String answer) {
//        Answer = answer;
//    }
//
//    public void setOperant(String operant) {
//        Operant = operant;
//    }
//
//    public void setOperator(String operator) {
//        Operator = operator;
//    }
//}

public class QuestionModel{
    private String question;
    private String answer;
    private String[] pilihan;

    public QuestionModel(String question,String answer,String pilhan){
        this.answer = answer;
        this.question = question;
        this.pilihan = pilhan.split(",");
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOperator(){
        String temp [];
        temp = this.question.trim().split(" ");
        return temp[1];
    }

    public String getOperan_1(){
        String temp [];
        temp = this.question.trim().split(" ");
        return temp[0];
    }

    public String getOperan_2(){
        String temp[];
        temp = this.question.trim().split(" ");
        return temp[3];
    }

    public String[] getPilihan() {
        return pilihan;
    }
}
