package com.mersiyanov.dmitry.notesapp.db;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.os.AsyncTask;

public class DeleteDataAsyncTask extends AsyncTask<Long, Void, Void> {

    private final ContentResolver contentResolver;
    private Uri uri;

    public DeleteDataAsyncTask(ContentResolver contentResolver, Uri uri) {
        this.contentResolver = contentResolver;
        this.uri = uri;
    }


    @Override
    protected Void doInBackground(Long... imageId) {
        contentResolver.delete(ContentUris.withAppendedId(uri, imageId[0]), null, null);
        return null;
    }
}
