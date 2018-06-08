package com.mersiyanov.dmitry.notesapp.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class InsertDataAsyncTask extends AsyncTask<ContentValues, Void, Long> {

    private ContentResolver contentResolver;
    private Uri uri;
    private final WeakReference<onSuccessListener> onSuccessListenerWeakReference;

    public InsertDataAsyncTask(ContentResolver contentResolver, Uri uri, onSuccessListener onSuccessListener) {
        this.contentResolver = contentResolver;
        this.uri = uri;
        this.onSuccessListenerWeakReference = new WeakReference<>(onSuccessListener);
    }

    @Override
    protected Long doInBackground(ContentValues... contentValues) {

        Uri id = contentResolver.insert(uri, contentValues[0]);
        return Long.valueOf(id.getLastPathSegment());

    }

    @Override
    protected void onPostExecute(Long noteId) {
        super.onPostExecute(noteId);
        contentResolver = null;

        onSuccessListener onSuccessListener = onSuccessListenerWeakReference.get();
        if(onSuccessListener != null) {
            onSuccessListener.onSuccess(noteId);
        }
    }

    public interface onSuccessListener {
        void onSuccess(long noteId);
    }
}
