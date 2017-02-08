package es.ulpgc.eite.android.quiz;

import android.app.Application;
import android.content.Intent;

public class QuizApp extends Application {

  private QuestionState questionState;
  private CheatState cheatState;
  private QuestionModel questionModel;
  private QuestionView view;
  private Presenter presenter;

  @Override
  public void onCreate() {
    super.onCreate();

    questionState = new QuestionState();
    questionState.toolbarVisible = false;
    questionState.answerVisible = false;

  }

  /********************************
   *  Métodos para obtener el MVP *
   * ******************************/
  public QuestionModel getQuestionModel(){

    if (questionModel==null){
      questionModel= new QuestionModel();
    }
    return questionModel;

  }
  public Presenter getPresenter() {
    if (presenter==null){
      presenter= new Presenter(this);
    }
    return presenter;
  }



  public void setView(QuestionView view) {
    this.view = view;
  }

  public QuestionView getView(){
    return view;
  }

  /*******************************
   *  Métodos referidos al estado*
   * *****************************/

  public boolean isAnswerBtnClicked() {
    return questionState.answerBtnClicked;
  }

  public void setAnswerBtnClicked(boolean clicked) {
    questionState.answerBtnClicked = clicked;
  }

  public boolean isAnswerVisible() {
    return questionState.answerVisible;
  }

  public boolean isToolbarVisible() {
    return questionState.toolbarVisible;
  }

  public void setAnswerVisibility(boolean visible) {
    questionState.answerVisible = visible;
  }


  public void checkAnswerVisibility(){
    if(!isAnswerVisible()) {
      view.hideAnswer();
    } else {
      view.showAnswer();
    }
  }

  private void checkToolbarVisibility(){
    if (!isToolbarVisible()) {
      view.hideToolbar();
    }
  }


  public void checkVisibility(){
    checkToolbarVisibility();
    checkAnswerVisibility();
  }


  private class QuestionState {
    boolean toolbarVisible;
    boolean answerVisible;
    boolean answerBtnClicked;


  }

  private class CheatState {
    boolean toolbarVisible;
    boolean answerVisible;
    boolean answerBtnClicked;
  }

  /**************************************
   *  Salto y vuelta a la Activity Cheat*
   * ************************************/

  public void goToCheatScreen(QuestionView activity){
    cheatState = new CheatState();
    cheatState.toolbarVisible = false;
    cheatState.answerVisible = false;
    cheatState.answerBtnClicked = questionState.answerBtnClicked;

    activity.startActivity(new Intent(activity, CheatActivity.class));
  }


  public void backToQuestionScreen(CheatActivity activity){
    activity.finish();
  }




}
