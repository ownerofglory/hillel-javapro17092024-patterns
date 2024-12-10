package ua.ithillel.grasp.cohesion;

import java.util.List;
import java.util.Objects;

public class MoviesDaoDb implements MoviesDao {
    private Database database;

    public MoviesDaoDb(Database database) {
        this.database = database;
        database.createConn();
    }

    @Override
    public List<Movie> getAllMovies() {
        Objects objects = database.executeQuery("");
        return List.of();
    }
}
