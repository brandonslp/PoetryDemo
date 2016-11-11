package com.lp.brandon.poetrydemo.Controllers;

import android.content.Context;
import android.content.Intent;

import com.j256.ormlite.dao.Dao;
import com.lp.brandon.poetrydemo.models.Movie;
import com.lp.brandon.poetrydemo.data.DatabaseHelper;

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
    private Context context;
    private DatabaseHelper helper;

    public DbController(Context context) {
        this.context = context;
        helper = new DatabaseHelper(context);
    }

    public List<Movie> getAll(){
        try {
            Dao<Movie, Integer> movie_dao = helper.getDao(Movie.class);
            List<Movie> movies = movie_dao.queryForAll();
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
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
