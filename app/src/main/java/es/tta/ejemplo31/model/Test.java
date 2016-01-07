package es.tta.ejemplo31.model;

public class Test {

    public static final String ADVICE_MIME_HTML="text/html";
    public static final String ADVICE_MIME_AUDIO="video/mp4";
    public static final String ADVICE_MIME_VIDEO="audio/mpeg";

    private String w;

    public Test(String wording, int [] choicesId, String [] choicesAdvise, String [] choicesAnswer,
                boolean [] choicesCorrect, String [] choicesResourceType){

        w=wording;


    }

}
