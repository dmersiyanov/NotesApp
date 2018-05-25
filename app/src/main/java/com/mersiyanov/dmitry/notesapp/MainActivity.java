package com.mersiyanov.dmitry.notesapp;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mersiyanov.dmitry.notesapp.db.NotesContract;
import com.mersiyanov.dmitry.notesapp.ui.NotesAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private NotesAdapter notesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLoaderManager().initLoader(0, null, this);


        RecyclerView recyclerView = findViewById(R.id.notes_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        notesAdapter = new NotesAdapter(null, onNoteClickListener);
        recyclerView.setAdapter(notesAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.create_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                startActivity(intent);
            }
        });

    }




    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, NotesContract.Notes.URI,
                NotesContract.Notes.LIST_PROJECTION, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        data.setNotificationUri(getContentResolver(), NotesContract.Notes.URI);
        notesAdapter.swapCursor(data);

        Log.i("Test", "Load finished: " + data.getCount());
        Toast.makeText(this, "Load finished: " + data.getCount(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private final NotesAdapter.OnNoteClickListener onNoteClickListener = new NotesAdapter.OnNoteClickListener() {
        @Override
        public void onNoteClick(long noteId) {
            Intent intent = new Intent(MainActivity.this, NoteActivity.class);
            intent.putExtra(NoteActivity.EXTRA_NOTE_ID, noteId);

            startActivity(intent);
        }
    };
}
