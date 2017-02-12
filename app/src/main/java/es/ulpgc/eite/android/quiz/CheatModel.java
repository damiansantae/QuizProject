package es.ulpgc.eite.android.quiz;

/**
 * Created by Damian on 08/02/2017.
 */

public class CheatModel {

    private String falseLabel, trueLabel;
    private String confirmLabel;
    private boolean trueAnswer;

    public CheatModel(){
        falseLabel = "False";
        trueLabel = "True";
        confirmLabel = "Are you sure?";
    }

    public String getConfirmLabel() {
        return confirmLabel;
    }


    public String getFalseLabel() {
        return falseLabel;
    }

    public String getTrueLabel() {
        return trueLabel;
    }
}
