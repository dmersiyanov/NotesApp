package com.mersiyanov.dmitry.notesapp.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;

public class InsertDataAsyncTask extends AsyncTask<ContentValues, Void, Void> {

    private ContentResolver contentResolver;
    private Uri uri;

    public InsertDataAsyncTask(ContentResolver contentResolver, Uri uri) {
        this.contentResolver = contentResolver;
        this.uri = uri;
    }

    @Override
    protected Void doInBackground(ContentValues... contentValues) {
        contentResolver.insert(uri, contentValues[0]);
        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        contentResolver = null;
    }
}
