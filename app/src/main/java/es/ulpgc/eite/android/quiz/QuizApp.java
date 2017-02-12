package es.ulpgc.eite.android.quiz;

import android.app.Application;
import android.content.Intent;

public class QuizApp extends Application {

  private QuestionState questionState;
  private CheatState cheatState;

  private QuestionModel questionModel;
  private QuestionView view;
  private Presenter presenter;

  private CheatModel cheatModel;
  private CheatActivity cheatActivity;
  private CheatPresenter cheatPresenter;



  @Override
  public void onCreate() {
    super.onCreate();

    questionState = new QuestionState();
    questionState.toolbarVisible = false;
    questionState.answerVisible = false;

  }

  /**************************************************
   *  Métodos para obtener el MVP activity principal*
   * ************************************************/
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

  /***********************************************
   *  Métodos para obtener el MVP activity cheat *
   * *********************************************/
  public CheatModel getCheatModel(){

    if (cheatModel==null){
      cheatModel= new CheatModel();
    }
    return cheatModel;

  }
  public CheatPresenter getCheatPresenter() {
    if (cheatPresenter==null){
      cheatPresenter= new CheatPresenter(this);
    }
    return cheatPresenter;
  }



  public void setCheatView(CheatActivity view) {
    this.cheatActivity = view;
  }

  public CheatActivity getCheatView(){
    return cheatActivity;
  }

  /*********************************************************
   *  Métodos referidos al estado de la activisad principal*
   * *******************************************************/

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
  /*********************************************************
   *  Métodos referidos al estado del cheat*
   * *******************************************************/

  private class CheatState {
    private boolean toolbarVisible;
    private boolean answerVisible;
    private boolean confirmBtnClicked;
    private boolean answerBtnClicked;
  }
  public void setCheatAnswerVisibility(boolean visible){
    cheatState.answerVisible = visible;
  }
  public void setConfirmButtonClicked(boolean confirm){
    cheatState.confirmBtnClicked = confirm;
  }
  public boolean isConfirmButtonClicked(){
    return cheatState.confirmBtnClicked;
  }
  public boolean getConfirmButtonClicked(){
    return cheatState.confirmBtnClicked;
  }
  public boolean isCheatAnswerVisible() {
    return cheatState.answerVisible;
  }

  public void checkCheatAnswerVisibility(){
    if(!isCheatAnswerVisible()) {
      cheatActivity.hideCheatAnswer();
    } else {
      cheatActivity.showCheatAnswer();
    }
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
