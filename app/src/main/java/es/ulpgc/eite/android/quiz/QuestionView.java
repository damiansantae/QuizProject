package es.ulpgc.eite.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionView extends AppCompatActivity {





  private Toolbar toolbarScreen;
  private Button buttonTrue, buttonFalse, buttonCheat, buttonNext;
  private TextView labelQuestion, labelAnswer;


  private QuizApp quizApp;


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
        getPresenter().onTrueBtnClicked();
      }
    });

    /*****************************************************
     *    Asociacion  boton respuesta false y su listener*
     *****************************************************/
    buttonFalse = (Button) findViewById(R.id.buttonFalse);
    buttonFalse.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onFalseBtnClicked();
      }
    });

    /**********************************************
     *    Asociacion  boton de cheat y su listener*
     **********************************************/
    buttonCheat = (Button) findViewById(R.id.buttonCheat);
    buttonCheat.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onCheatBtnClicked();
      }
    });

    /********************************************************
     *    Asociacion  boton siguiente pregunta y su listener*
     ********************************************************/

    buttonNext = (Button) findViewById(R.id.buttonNext);
    buttonNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onNextBtnClicked();
      }
    });


    // Iniciamos con la primera pantalla
    onScreenStarted();
  }

  //Metodo para obtener presentador
  public  Presenter getPresenter(){
    return quizApp.getPresenter();
  }


  private void onScreenStarted() {

    //Asignamos mediador a la vista
    quizApp = (QuizApp) getApplication();

    //Informamos al mediador de quien es la vista
    quizApp.setView(this);


    //insertamos texto en los botones
    setButtonLabels();
    quizApp.checkVisibility();

    //Insertamos primera pregunta
    getPresenter().fstQuestion();


    if(quizApp.isAnswerBtnClicked()){
      getPresenter().fstAnswer();
    }
  }


  //Añadimos el texto a los botones
  private void setButtonLabels(){


    getPresenter().getTrueLabel();
    getPresenter().getFalseLabel();
    getPresenter().getCheatLabel();
    getPresenter().getNextLabel();
  }


  public void hideAnswer() {
    labelAnswer.setVisibility(View.INVISIBLE);
  }

  public void hideToolbar() {
    toolbarScreen.setVisibility(View.GONE);
  }

  //editada para mvp
  public void setAnswer(String text) {
    labelAnswer.setText(text);
    setAnswerVisibility(true);
    setAnswerBtnClicked(true);

    quizApp.checkAnswerVisibility();
  }

  private void setAnswerVisibility(boolean visible) {
    quizApp.setAnswerVisibility(visible);
  }
  private void setAnswerBtnClicked(boolean visible) {
    quizApp.setAnswerBtnClicked(visible);
  }

  private boolean isAnswerVisible() {
    return quizApp.isAnswerVisible();
  }

  private boolean isToolbarVisible() {
    return quizApp.isToolbarVisible();
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

  public void showAnswer() {
    labelAnswer.setVisibility(View.VISIBLE);
  }


}
