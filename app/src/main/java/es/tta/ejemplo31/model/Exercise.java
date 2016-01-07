package es.tta.ejemplo31.model;

public class Exercise {
    private int id;
    private String wording;

    public Exercise(int id, String wording) {
        this.id = id;
        this.wording = wording;
    }

    public int getId() {
        return id;
    }

    public String getWording() {
        return wording;
    }

}
