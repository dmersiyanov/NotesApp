package com.mersiyanov.dmitry.notesapp.db;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;

public class UpdateDataAsyncTask extends AsyncTask<ContentValues, Void, Void> {

    private ContentResolver contentResolver;
    private Uri uri;
    private long noteId;

    public UpdateDataAsyncTask(ContentResolver contentResolver, Uri uri, long noteId) {
        this.contentResolver = contentResolver;
        this.uri = uri;
        this.noteId = noteId;
    }

    @Override
    protected Void doInBackground(ContentValues... contentValues) {
        contentResolver.insert(uri, contentValues[0]);
        contentResolver.update(ContentUris.withAppendedId(NotesContract.Notes.URI, noteId),
                contentValues[0],
                null, null);
        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        contentResolver = null;
    }
}
