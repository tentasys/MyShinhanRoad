package com.example.shinple.vo;


public class QuizVO {
    String quizNum;
    String question;
    String quiz1;
    String quiz2;
    String quiz3;
    String quiz4;
    String answer;

    public String getquestion() {
        return question;
    }

    public void setquestion(String question) {
        this.question= question;
    }

    public String getQuizNum() {
        return quizNum;
    }

    public void setQuizNum(String quizNum) {
        this.quizNum= quizNum;
    }

    public String getQuiz1() {
        return quiz1;
    }

    public void setQuiz1(String quiz1) {
        this.quiz1= quiz1;
    }

    public String getQuiz2() {
        return quiz2;
    }

    public void setQuiz2(String quiz2) {
        this.quiz2 = quiz2;
    }

    public String getQuiz3() {
        return quiz3;
    }

    public void setQuiz3(String quiz3) {
        this.quiz3= quiz3;
    }

    public String getQuiz4() {
        return quiz4;
    }

    public void setQuiz4(String quiz4) {
        this.quiz4= quiz4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer= answer;
    }

    public QuizVO(String quizNum, String question, String quiz1, String quiz2, String quiz3, String quiz4, String answer) {
        this.question = question;
        this.quizNum = quizNum;
        this.quiz1 = quiz1;
        this.quiz2 = quiz2;
        this.quiz3 = quiz3;
        this.quiz4 = quiz4;
        this.answer = answer;
    }
}
