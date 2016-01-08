package es.tta.ejemplo31.model;

public class Choice {

    private int id;
    private String answer;
    private boolean correct;
    private String advise;
    private String resourceType;

    public Choice(int id, String wording, boolean correct, String advise, String resourceType) {
        this.id = id;
        this.answer = wording;
        this.advise = advise;
        this.correct = correct;
        this.resourceType = resourceType;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAdvise() {
        return advise;
    }

    public boolean isCorrect() {
        return correct;
    }

    public String getResourceType() {
        return resourceType;
    }
}
