package com.lp.brandon.poetrydemo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lp.brandon.poetrydemo.Controllers.DbController;
import com.lp.brandon.poetrydemo.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtFilter;
    private RecyclerView rvmovies;
    private DbController dbController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        initialization();
        setListeners();

    }

    private void initialization(){
        dbController = new DbController(this);
        edtFilter = (EditText) findViewById(R.id.edtFilter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvmovies = (RecyclerView) findViewById(R.id.rcmovies);
        rvmovies.setLayoutManager(manager);
        MovieAdapter adapter = new MovieAdapter(dbController.getAll(), this, new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie item) {
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
            }
        });
        rvmovies.setAdapter(adapter);
    }

    private void setListeners(){
        //action execute, search
        edtFilter.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH){
                    Log.v("Brandon-lp", "buscando...");
                    search(textView);
                    return true;
                }

                return false;
            }
        });

        //action when user touch drawable rigth
        edtFilter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (motionEvent.getRawX() >= (edtFilter.getRight() - edtFilter.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){
                        search(view);
                        return true;
                    }
                }

                return false;
            }
        });
        addTextListener();
    }



    private void addTextListener(){
        edtFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    updatelist(dbController.getAll());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final List<Movie> filteredMovies = new ArrayList<>();
                try{
                    List<Movie> movies = dbController.getAll();
                    for (int j=0; j<movies.size();j++){
                        if (movies.get(j).getTitle().toLowerCase().contains(charSequence)) {
                            filteredMovies.add(movies.get(j));
                            Log.v("Brandon-lp", "se agrego ->" + movies.get(j).getTitle());
                        }

                    }
                    Log.v("Brandon-lp", " i -> " +i);
                    if (filteredMovies.isEmpty()){

                        Snackbar.make(edtFilter, "Presione "+new String(Character.toChars(0x1F50D))+" Para buscar en el servidor",Snackbar.LENGTH_SHORT).show();
                    }
                    updatelist(filteredMovies);
                }catch (NullPointerException e){
                    Snackbar.make(edtFilter, "Presione "+new String(Character.toChars(0x1F50D))+" Para buscar en el servidor",Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void updatelist(List<Movie> movies){
        rvmovies.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        MovieAdapter adapter = new MovieAdapter(movies, MainActivity.this, new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie item) {
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
            }
        });
        rvmovies.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void search(View view){
        RequestMovie requestMovie = new RequestMovie(this);
        requestMovie.getMovieFromServer(edtFilter.getText().toString());
        Snackbar.make(view,"Buscando...",Snackbar.LENGTH_LONG).show();
        edtFilter.setText("");
        updatelist(dbController.getAll());
        hideKeyWord(view);
    }



    private void hideKeyWord(View view){
        InputMethodManager manager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromInputMethod(view.getWindowToken(),0);
    }

    /*private List<Movie> datatest(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Star Wars","The official site for Star Wars, featuring the latest on Star Wars: Episode VII The Force Awakens and Star Wars Rebels, with daily news, games","https://images.wondershare.es/images/ringtones/star-wars-episode-7-release-date2.jpg"));
        movies.add(new Movie("The Internship","The Internship es una película de comedia estadounidense de 2013","https://images-na.ssl-images-amazon.com/images/M/MV5BMjM1MzczMDgwOV5BMl5BanBnXkFtZTcwMDM4NjM2OQ@@._V1_UY1200_CR89,0,630,1200_AL_.jpg"));
        movies.add(new Movie("The Imitation Game","The Imitation Game (titulada Descifrando Enigma en España y El código Enigma en Hispanoamérica) es un biopic bélico brito-estadounidense, con cierto","http://vignette4.wikia.nocookie.net/doblaje/images/5/5e/El-codigo-enigma.jpg/revision/latest?cb=20150225203954&path-prefix=es"));
        return movies;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
