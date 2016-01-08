package es.tta.ejemplo31.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public abstract class ProgressTask<T> extends AsyncTask<Void, Void, T> {

    protected final Context context;
    private final ProgressDialog dialog;
    private Exception e;


    public ProgressTask(Context context) {
        this.context = context;
        dialog = new ProgressDialog(context);
        dialog.setMessage("Conectando ...");
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected T doInBackground(Void... params) {
        T result = null;
        try {
            result = work();
        } catch (Exception e) {
            this.e = e;
        }
        return result;
    }

    @Override
    protected void onPostExecute(T result) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        if (e != null) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } else
            onFinish(result);
    }

    protected abstract T work() throws Exception;

    public abstract void onFinish(T result);

}
