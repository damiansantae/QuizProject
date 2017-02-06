package es.ulpgc.eite.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionView extends AppCompatActivity {


  private boolean toolbarVisible;
  private boolean answerVisible;
  //private QuestionModel questionModel;
  private boolean answerBtnClicked;

  private Toolbar toolbarScreen;
  private Button buttonTrue, buttonFalse, buttonCheat, buttonNext;
  private TextView labelQuestion, labelAnswer;

  private Presenter presenter;
  //private QuizApp quizApp;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_question);


    labelQuestion = (TextView) findViewById(R.id.labelQuestion);
    labelAnswer = (TextView) findViewById(R.id.labelAnswer);

    toolbarScreen = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbarScreen);

    /****************************************************
     *    Asociacion  boton respuesta true y su listener*
     ****************************************************/

    buttonTrue = (Button) findViewById(R.id.buttonTrue);
    buttonTrue.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.onTrueBtnClicked();
      }
    });

    /*****************************************************
     *    Asociacion  boton respuesta false y su listener*
     *****************************************************/
    buttonFalse = (Button) findViewById(R.id.buttonFalse);
    buttonFalse.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.onFalseBtnClicked();
      }
    });

    /**********************************************
     *    Asociacion  boton de cheat y su listener*
     **********************************************/
    buttonCheat = (Button) findViewById(R.id.buttonCheat);
    buttonCheat.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onCheatBtnClicked();
      }
    });

    /********************************************************
     *    Asociacion  boton siguiente pregunta y su listener*
     ********************************************************/

    buttonNext = (Button) findViewById(R.id.buttonNext);
    buttonNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.onNextBtnClicked();
      }
    });


    //Creamos presentador
    presenter = new Presenter(this);
    // Iniciamos con la primera pantalla
    onScreenStarted();

  }


  private void onScreenStarted() {
    //quizApp = (QuizApp) getApplication();



    //insertamos texto en los botones
    setButtonLabels();
    checkVisibility();

    //Insertamos primera pregunta
    presenter.fstQuestion();


    //s

    if(isAnswerBtnClicked()){
      presenter.fstAnswer();


    }
  }


  //Añadimos el texto a los botones
  private void setButtonLabels(){


    presenter.getTrueLabel();
    presenter.getFalseLabel();
    presenter.getCheatLabel();
    presenter.getNextLabel();
  }
  
  private void onCheatBtnClicked() {
    goToCheatScreen();
  }


  private boolean isAnswerVisible() {
    return answerVisible;
  }

  private boolean isToolbarVisible() {
    return toolbarVisible;
  }

  private void setAnswerVisibility(boolean visible) {
    answerVisible = visible;
  }

  private boolean isAnswerBtnClicked() {
    return answerBtnClicked;
  }

  private void setAnswerBtnClicked(boolean clicked) {
    answerBtnClicked = clicked;
  }


  private void goToCheatScreen(){
    startActivity(new Intent(this, CheatActivity.class));
    //quizApp.goToCheatScreen(this);
  }

  private void checkAnswerVisibility(){
    if(!isAnswerVisible()) {
      hideAnswer();
    } else {
      showAnswer();
    }
  }

  private void checkToolbarVisibility(){
    if (!isToolbarVisible()) {
      hideToolbar();
    }
  }


  private void checkVisibility(){
    checkToolbarVisibility();
    checkAnswerVisibility();
  }


  private void hideAnswer() {
    labelAnswer.setVisibility(View.INVISIBLE);
  }

  private void hideToolbar() {
    toolbarScreen.setVisibility(View.GONE);
  }

  //editada para mvp
  public void setAnswer(String text) {
    labelAnswer.setText(text);
    setAnswerVisibility(true);
    setAnswerBtnClicked(true);

    checkAnswerVisibility();
  }

  public void setCheatButton(String label) {
    buttonCheat.setText(label);
  }

  public void setFalseButton(String label) {
    buttonFalse.setText(label);
  }

  public void setNextButton(String label) {
    buttonNext.setText(label);
  }

  public void setQuestion(String text) {
    labelQuestion.setText(text);
  }

  public void setTrueButton(String label) {
    buttonTrue.setText(label);
  }

  private void showAnswer() {
    labelAnswer.setVisibility(View.VISIBLE);
  }


}
