package ra.java_service_09.service;

import ra.java_service_09.model.dto.request.MovieDTO;
import ra.java_service_09.model.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(Long id);
    Movie save(MovieDTO movieDTO);
    Movie update(Long id, Movie movie);
    void delete(Long id);
    List<Movie> searchMoviesByTitle(String title);

}
