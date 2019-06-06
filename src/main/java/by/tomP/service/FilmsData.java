package by.tomP.service;

import by.tomP.parserIMDB.Parser;
import by.tomP.parserIMDB.Parser;
import com.omertron.omdbapi.OMDBException;
import com.omertron.omdbapi.OmdbApi;
import com.omertron.omdbapi.model.OmdbVideoFull;
import com.omertron.omdbapi.tools.OmdbBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilmsData {

    String id;
    String title;
    String genre;
    String author;
    String date;
    String poster;

    OmdbApi omdb = new OmdbApi("apikey");


    List<String> imdbIdList = new ArrayList<String>();


    public void getImdbList() throws IOException {
        Parser rf = new Parser();

        String filename = "E:\\Artem\\Java Proj\\MovieBot\\data.txt";

        imdbIdList = Arrays.asList(rf.readLines(filename));
    }


    public FilmsData() {
        try {
            getImdbList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FilmsData(String title) throws OMDBException {
        /*OmdbVideoFull result = omdb.getInfo(new OmdbBuilder().setTitle(title).build());

        OmdbBuilder builder = new OmdbBuilder();


        this.id = result.getImdbID();
        this.title = result.getTitle();
        this.date = result.getYear();
        this.author = result.getDirector();
        this.genre = result.getGenre();
        this.poster = result.getPoster();*/

    }





    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return genre;
    }

    public void setDescription(String description) {
        this.genre = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return

                "title='" + title + '\n' +
                        "genre='" + genre + '\n' +
                        "author='" + author + '\n' +
                        "date='" + date + '\n' +
                        "IMDB='" + id + '\n' +
                        "poster=" + poster + "\n";
        //"omdb=" + omdb ;
    }


    public String getConcreteMovie(String title) throws OMDBException {
        OmdbVideoFull result = omdb.getInfo(new OmdbBuilder().setTitle(title).build());

        OmdbBuilder builder = new OmdbBuilder();


        this.id = result.getImdbID();
        this.title = result.getTitle();
        this.date = result.getYear();
        this.author = result.getDirector();
        this.genre = result.getGenre();
        this.poster = result.getPoster();

        return this.toString();
    }


    public String getRandomMovie() {

        int a = 0;
        int b = imdbIdList.size();
        OmdbVideoFull result = null;



        int random = a + (int) (Math.random() * b);


        try {
            result = omdb.getInfo(new OmdbBuilder().setImdbId(imdbIdList.get(random)).build());


            OmdbBuilder builder = new OmdbBuilder();

            this.id = result.getImdbID();
            this.title = result.getTitle();
            this.date = result.getYear();
            this.author = result.getDirector();
            this.genre = result.getGenre();
            this.poster = result.getPoster();

        } catch (OMDBException e) {
            e.printStackTrace();
        }
        //OmdbVideoFull result = omdb.getInfo(new OmdbBuilder().setTitle(title).build());

        return this.toString();
    }
}
