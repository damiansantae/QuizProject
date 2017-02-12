package es.ulpgc.eite.android.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

  private boolean toolbarVisible;
  //private boolean confirmBtnClicked;
  private boolean answerVisible;
  private QuizApp quizApp;

  private String falseLabel, trueLabel;
  private String confirmLabel;
  private boolean trueAnswer;

  private Toolbar toolbarScreen;
  private Button buttonTrue, buttonFalse;
  private TextView labelConfirm, labelAnswer;




  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cheat);

    labelConfirm = (TextView) findViewById(R.id.labelConfirm);
    labelAnswer = (TextView) findViewById(R.id.labelAnswer);

    toolbarScreen = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbarScreen);

    buttonTrue = (Button) findViewById(R.id.buttonTrue);
    buttonTrue.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        onTrueBtnClicked();
      }
    });

    buttonFalse = (Button) findViewById(R.id.buttonFalse);
    buttonFalse.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onFalseBtnClicked();
      }
    });



    onScreenStarted();
  }

  public void hideCheatAnswer() {
    labelAnswer.setVisibility(View.INVISIBLE);
  }
  public void showCheatAnswer() {
    labelAnswer.setVisibility(View.VISIBLE);
  }


  private void onScreenStarted() {
    quizApp = (QuizApp) getApplication();

    quizApp.setCheatView(this);


    /*falseLabel = "False";
    trueLabel = "True";
    confirmLabel = "Are you sure?";*/

    setButtonLabels();

    checkVisibility();

    if(quizApp.isConfirmButtonClicked()){
      getPresenter().getAnswer();
    }
  }

  private void onFalseBtnClicked() {
    //finish();
    getPresenter().onFalseBtnClicked(this);

  }

  private void onTrueBtnClicked() {
    getPresenter().onTrueBtnClicked();
    //setAnswer(true);
    //setAnswer(getAnswer());

   // confirmBtnClicked = true;
   // checkAnswerVisibility();
  }

  private CheatPresenter getPresenter(){
    return quizApp.getCheatPresenter();


  }


  //private void setAnswerVisibility(boolean visible) {
   // quizApp.setCheatAnswerVisibility(visible);
 // }

 /* private boolean isAnswerVisible() {
    return quizApp.isCheatAnswerVisible();
  }

  private boolean isToolbarVisible() {
    return quizApp.isToolbarVisible();
  }*/

  private boolean isAnswerVisible() {
    return answerVisible;
  }

  private boolean isToolbarVisible() {
    return toolbarVisible;
  }






  /*private void checkAnswerVisibility(){
    if(!isAnswerVisible()) {
      hideAnswer();
    } else {
      showAnswer();
    }
  }*/


  private void checkToolbarVisibility(){
    if (!isToolbarVisible()) {
      hideToolbar();
    }
  }

  private void checkVisibility(){
    quizApp.checkCheatAnswerVisibility();
  }


  private void setButtonLabels(){
    getPresenter().setTrueButton();
    getPresenter().setFalseButton();
    getPresenter().setConfirm();
  }

  
  private void hideToolbar() {
    toolbarScreen.setVisibility(View.GONE);
  }


  public void setAnswer(String txt) {

    labelAnswer.setText(txt);
    setAnswerVisibility(true);
    setConfirmBtnClicked(true);
    checkVisibility();
  }

  private void setConfirmBtnClicked(boolean confirm){
   quizApp.setConfirmButtonClicked(confirm);
 }
  private void setAnswerVisibility(boolean visible) {
    quizApp.setCheatAnswerVisibility(visible);
  }

  public void setConfirmLabel(String text) {
    labelConfirm.setText(text);
  }

  public void setCheatFalseButton(String label) {
    buttonFalse.setText(label);
  }

  public void setCheatTrueButton(String label) {
    buttonTrue.setText(label);
  }







/*  private String getAnswer() {
    if(quizApp.getQuestionModel().getCheatAnswer()) {
      return trueLabel;
    } else {
      return falseLabel;
    }
  }*/


  private void setAnswer(boolean answer){
    trueAnswer = answer;
  }
}
