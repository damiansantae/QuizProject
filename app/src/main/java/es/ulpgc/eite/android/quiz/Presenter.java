package es.ulpgc.eite.android.quiz;

import android.view.View;

/**
 * Created by Damian on 01/02/2017.
 */

public class Presenter {



    private QuizApp mediator;

    public Presenter (QuizApp quizApp){

       mediator = quizApp;

    }
    public QuestionModel getModel() {
        return mediator.getQuestionModel();
    }

    public QuestionView getMyView() {
        return mediator.getView();
    }



    /*******************************************
     *  Acciones al pulsar un botón determinado*
     * *****************************************/

    public void onTrueBtnClicked(){
        getModel().onAnswerBtnClicked(true);
        getMyView().setAnswer(getModel().getCurrentAnswer());

        mediator.setAnswerVisibility(true);         //indicamos que la respuesta está visible

    }
    public void onFalseBtnClicked() {
        getModel().onAnswerBtnClicked(false);
        getMyView().setAnswer(getModel().getCurrentAnswer());
    }

    public void onNextBtnClicked(){
       String question= getModel().getNextQuestion();
        getMyView().setQuestion(question);

    }


    public void onCheatBtnClicked() {

        mediator.goToCheatScreen(getMyView());
    }


   /**************************************************************
    *  Metodos de primera pantalla (primera pregunta y respuesta)*
   * *************************************************************/
    public void getCurrentQuestion() {
        String question = getModel().getCurrentQuestion();
        getMyView().setQuestion(question);
    }

    public void fstAnswer() {
        String answer = getModel().getCurrentAnswer();
        getMyView().setAnswer(answer);
    }


    /**************************************************************
     *  Metodos para insertar el texto en los botones que existen *
     *  en la pantalla inicial*                                   *
     * ************************************************************/

    public void getTrueLabel() {
        getMyView().setTrueButton(getModel().getTrueLabel());
    }

    public void getFalseLabel() {
        getMyView().setFalseButton(getModel().getFalseLabel());
    }

    public void getCheatLabel() {
        getMyView().setCheatButton(getModel().getCheatLabel());
    }

    public void getNextLabel() {
        getMyView().setNextButton(getModel().getNextLabel());
    }
}
