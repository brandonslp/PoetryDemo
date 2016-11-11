package com.lp.brandon.poetrydemo.data;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.lp.brandon.poetrydemo.models.Movie;

import nl.elastique.poetry.database.DatabaseConfiguration;

/**
 * Created by brand on 10/11/2016.
 */
public class DatabaseHelper extends nl.elastique.poetry.database.DatabaseHelper{

    public final static DatabaseConfiguration CONFIGURATION = new DatabaseConfiguration(1,new Class<?>[]{
            Movie.class
    });

    public DatabaseHelper(Context context) {
        super(context, CONFIGURATION);
    }


    public static DatabaseHelper getHelper(Context context)
    {
        return OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }
}
