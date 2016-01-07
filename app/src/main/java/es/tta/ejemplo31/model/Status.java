package es.tta.ejemplo31.model;

//Status del usuario

public class Status {
    private int id;
    private String user;
    private int lesson;
    private String lessonTitle;
    private int nextTest;
    private int nextExercise;
    private String userDni;
    private String userPassword;

    public Status(int id, String user, int lesson, String lessonTitle, int nextTest,
                      int nextExercise,String userDni,String userPassword){
        this.id = id;
        this.user = user;
        this.lesson = lesson;
        this.lessonTitle = lessonTitle;
        this.nextTest = nextTest;
        this.nextExercise = nextExercise;
        this.userDni = userDni;
        this.userPassword = userPassword;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public int getLesson() {
        return lesson;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public int getNextTest() {
        return nextTest;
    }

    public int getNextExercise() {
        return nextExercise;
    }

    public String getUserDni() {
        return userDni;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
