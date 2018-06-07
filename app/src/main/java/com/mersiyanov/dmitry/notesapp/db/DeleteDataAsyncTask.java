package com.mersiyanov.dmitry.notesapp.db;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.os.AsyncTask;

public class DeleteDataAsyncTask extends AsyncTask<Long, Void, Void> {

    private final ContentResolver contentResolver;

    public DeleteDataAsyncTask(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }


    @Override
    protected Void doInBackground(Long... imageId) {
        contentResolver.delete(ContentUris.withAppendedId(NotesContract.Images.URI, imageId[0]), null, null);
        return null;
    }
}
