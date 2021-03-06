package com.mersiyanov.dmitry.notesapp.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.AsyncTask;

public class InsertDataAsyncTask extends AsyncTask<ContentValues, Void, Void> {

    private final ContentResolver contentResolver;

    public InsertDataAsyncTask(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override
    protected Void doInBackground(ContentValues... contentValues) {
        contentResolver.insert(NotesContract.Notes.URI, contentValues[0]);
        return null;

    }

}
