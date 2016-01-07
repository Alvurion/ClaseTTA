package es.tta.ejemplo31.presentation;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import es.tta.ejemplo31.model.Exercise;
import es.tta.ejemplo31.comunicaciones.RestClient;
import es.tta.ejemplo31.model.Status;
import es.tta.ejemplo31.model.Test;

public class Data {
    RestClient restClient;
    String server = "http://u017633.ehu.es:18080/AlumnoTta/rest/tta";

    String pathTest = "getTest?id=%d";
    String pathExercise = "getExercise?id=%d";
    String pathStatus = "getStatus?dni=%s";

    public Data(String user, String passwd) {
        restClient = new RestClient(server);
        restClient.setHttpBasicAuth(user, passwd);
    }

    //Implementamos los 5 métodos de la API Rest proporcionada

    //Metodos GET

    public Status getStatus (String dni, String password) throws IOException, JSONException{
        JSONObject jo = restClient.getJson(String.format(pathStatus,dni));
        int id = jo.getInt("id");
        String user = jo.getString("user");
        int lessonNumber = jo.getInt("lessonNumber");
        String lessonTitle = jo.getString("lessonTitle");
        int nextTest = jo.getInt("nextTest");
        int nextExercise = jo.getInt("nextExercise");

        Status s = new Status(id,user,lessonNumber,lessonTitle,nextTest,nextExercise,dni,password);
        return s;
    }

    public Exercise getExercise(int id)throws IOException,JSONException{
        JSONObject jo = restClient.getJson(String.format(pathExercise,id));
        Exercise ex = new Exercise(jo.getInt("id"),jo.getString("wording"));
        return ex;
    }

    public Test getTest(int id) throws JSONException, IOException {
        JSONObject json = restClient.getJson(String.format(pathTest, id));

        //Wording
        String wording = json.getString("wording");

        //Choices
        JSONArray choices = json.getJSONArray("choices");
        int choicesLength = choices.length();
        int [] choicesId = new int[choicesLength];
        String [] choicesAdvise = new String[choicesLength];
        String [] choicesAnswer = new String[choicesLength];
        boolean [] choicesCorrect = new boolean[choicesLength];
        String [] choicesResourceType = new String[choicesLength];

        for(int i = 0;i<choicesLength;i++){
            JSONObject jsonChoice = choices.getJSONObject(i);
            choicesId[i] = jsonChoice.getInt("id");
            choicesAdvise[i] = jsonChoice.getString("advise");
            choicesAnswer[i] = jsonChoice.getString("answer");
            choicesCorrect[i] = jsonChoice.getBoolean("correct");

            //ResourceType puede devolver "null" o información (3 campos) en un JSONObject
            if(jsonChoice.isNull("resourceType")){
                choicesResourceType[i] = null;
            }else{
                choicesResourceType[i] = jsonChoice.getJSONObject("resourceType").getString("mime");
            }
        }

        Test test = new Test(wording,choicesId,choicesAdvise,choicesAnswer,choicesCorrect,choicesResourceType);

        return test;
    }

    //Metodos POST
    public int postTest(int user, int choice) throws IOException, JSONException {
        JSONObject jo = new JSONObject();
        jo.put("userId", user);
        jo.put("choiceId", choice);

        return restClient.postJson(jo, "postChoice");
    }

    public int postExercise(Uri uri, int user, int exercise, String name) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(uri.getPath());
        String pathExercise = "postExercise?user="+user+"&id="+exercise;
        return restClient.postFile(pathExercise, is, name);
    }
}
