package es.tta.ejemplo31.model;

public class Test {

    private String wording;
    private Choice [] choices;

    public Test(String frase, int [] choicesId, String [] choicesAdvise, String [] choicesAnswer,
                boolean [] choicesCorrect, String [] choicesResourceType){

        wording=frase;
        //Bucle for para cada choice dentro del test

    }

    public String getWording(){
        return wording;
    }

    public Choice[] getChoices(){ return choices; }

    public Choice getChoice(int i){
        return choices[i];
    }

}
