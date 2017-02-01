package es.ulpgc.eite.android.quiz;

/**
 * Created by Damian on 01/02/2017.
 */

public class Presenter {
    private QuestionView myView;
    private QuestionModel model;

    public Presenter (QuestionView view){
        myView = view;
        this.model = new QuestionModel();

    }

    public void onTrueBtnClicked(){
        model.onAnswerBtnClicked(true);
        myView.setAnswer(model.getCurrentAnswer());

    }
    public void onFalseBtnClicked() {
        model.onAnswerBtnClicked(false);
        myView.setAnswer(model.getCurrentAnswer());
    }

    public void onNextBtnClicked(){
       String question= model.getNextQuestion();
        myView.setQuestion(question);

    }


   /**************************************************************
    *  Metodos de primera pantalla (primera pregunta y respuesta)*
   * *************************************************************/
    public void fstQuestion() {
        String question = model.getCurrentQuestion();
        myView.setQuestion(question);
    }

    public void fstAnswer() {
        String answer = model.getCurrentAnswer();
        myView.setAnswer(answer);
    }


}
