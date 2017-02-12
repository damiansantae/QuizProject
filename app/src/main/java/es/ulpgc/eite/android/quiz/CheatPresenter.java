package es.ulpgc.eite.android.quiz;

/**
 * Created by Damian on 08/02/2017.
 */

public class CheatPresenter {
    private QuizApp mediator;

    public CheatPresenter (QuizApp quizApp){

        mediator = quizApp;

    }

    /********************************
     *  Obtención de modelo y vista *
     * ******************************/

    public CheatModel getModel() {
        return mediator.getCheatModel();
    }

    public CheatActivity getMyView() {
        return mediator.getCheatView();
    }




    /*******************************************
     *  Acciones al pulsar un botón determinado*
     * *****************************************/

    public void onTrueBtnClicked(){
        getAnswer();

        mediator.setConfirmButtonClicked(true);
       mediator.setCheatAnswerVisibility(true);         //indicamos que la respuesta está visible


    }
    public void onFalseBtnClicked(CheatActivity cheat) {
        mediator.backToQuestionScreen(cheat);
    }

    public void getAnswer(){

         boolean answer =mediator.getQuestionModel().getCheatAnswer();
        if(answer){
            getMyView().setAnswer(getModel().getTrueLabel());
        }else {
            getMyView().setAnswer(getModel().getFalseLabel());
        }


    }

    public void setTrueButton(){
        getMyView().setCheatTrueButton(getModel().getTrueLabel());

    }
    public void setFalseButton(){
        getMyView().setCheatFalseButton(getModel().getFalseLabel());

    }
    public void setConfirm(){
        getMyView().setConfirmLabel(getModel().getConfirmLabel());

    }
}
