package com.lp.brandon.poetrydemo.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import nl.elastique.poetry.json.annotations.MapFrom;

/**
 * Created by brand on 10/11/2016.
 */
@DatabaseTable
public class Movie {
    @DatabaseField(columnName ="Title")
    @MapFrom("Title")
    private String title;
    @DatabaseField(columnName ="Year")
    @MapFrom("Year")
    private String year;
    @DatabaseField(columnName ="Rated")
    @MapFrom("Rated")
    private String rated;
    @DatabaseField(columnName ="Released")
    @MapFrom("Released")
    private String released;
    @DatabaseField(columnName ="Runtime")
    @MapFrom("Runtime")
    private String runtime;
    @DatabaseField(columnName ="Genre")
    @MapFrom("Genre")
    private String genre;
    @DatabaseField(columnName ="Director")
    @MapFrom("Director")
    private String director;
    @DatabaseField(columnName ="Writer")
    @MapFrom("Writer")
    private String writer;
    @DatabaseField(columnName ="Actors")
    @MapFrom("Actors")
    private String actors;
    @DatabaseField(columnName ="Plot")
    @MapFrom("Plot")
    private String plot;
    @DatabaseField(columnName ="Language")
    @MapFrom("Language")
    private String language;
    @DatabaseField(columnName ="Country")
    @MapFrom("Country")
    private String country;
    @DatabaseField(columnName ="Awards")
    @MapFrom("Awards")
    private String awards;
    @DatabaseField(columnName ="Poster")
    @MapFrom("Poster")
    private String poster;
    @DatabaseField(columnName ="Metascore")
    @MapFrom("Metascore")
    private String metascore;
    @DatabaseField(columnName ="imdbRating")
    @MapFrom("imdbRating")
    private String imdbRating;
    @DatabaseField(columnName ="imdbVotes")
    @MapFrom("imdbVotes")
    private String imdbVotes;
    @DatabaseField(id = true, columnName ="imdbID")
    @MapFrom("imdbID")
    private String imdbID;
    @DatabaseField(columnName ="Type")
    @MapFrom("Type")
    private String type;
    @DatabaseField(columnName ="Response")
    @MapFrom("Response")
    private int response;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }
}
