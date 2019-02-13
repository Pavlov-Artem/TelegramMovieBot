package by.tomP.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class MoviesData {

    @Id
    private String id;

    private String title;

    private Date release;

    private String genre;

    private Boolean isFilmOrSerial; // true if film


    public MoviesData(String id, String title, Date release, String genre, Boolean isFilmOrSerial) {
        this.id = id;
        this.title = title;
        this.release = release;
        this.genre = genre;
        this.isFilmOrSerial = isFilmOrSerial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public Boolean getFilmOrSerial() {
        return isFilmOrSerial;
    }

    public void setFilmOrSerial(Boolean filmOrSerial) {
        isFilmOrSerial = filmOrSerial;
    }
}
