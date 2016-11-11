package com.lp.brandon.poetrydemo.Controllers;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.lp.brandon.poetrydemo.data.DatabaseHelper;
import com.lp.brandon.poetrydemo.models.Movie;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.elastique.poetry.json.JsonPersister;

/**
 * Created by brand on 10/11/2016.
 */
public class DbController {
    private final DatabaseHelper helper;

    public DbController(Context context) {
        helper = new DatabaseHelper(context);
    }

    private List<Movie> PgetAll(){
        try {
            Dao<Movie, Integer> movie_dao = helper.getDao(Movie.class);
            return movie_dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Movie> getAll(){
        List<Movie> list = PgetAll();
        List<Movie> inverlist = new ArrayList<>();
        for (int i = list.size()-1; i >= 0 ; i--) {

            inverlist.add(list.get(i));
        }
        return inverlist;
    }

    public void addMovie(JSONObject jsonObject){
        JsonPersister persister = new JsonPersister(helper.getWritableDatabase());
        try {
            persister.persistObject(Movie.class,jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
