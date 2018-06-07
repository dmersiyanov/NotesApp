package com.mersiyanov.dmitry.notesapp.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class InsertDataAsyncTask extends AsyncTask<ContentValues, Void, Void> {

    private ContentResolver contentResolver;
    private Uri uri;

    public InsertDataAsyncTask(ContentResolver contentResolver, Uri uri) {
        this.contentResolver = contentResolver;
        this.uri = uri;
    }

    @Override
    protected Void doInBackground(ContentValues... contentValues) {

        // TO-DO получить id заметки в активити
        Uri id = contentResolver.insert(uri, contentValues[0]);
        Log.e(this.getClass().toString(), id.getLastPathSegment());

        System.out.println(id.toString());
        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        contentResolver = null;
    }
}
