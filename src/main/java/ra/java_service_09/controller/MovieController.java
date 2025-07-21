package ra.java_service_09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.java_service_09.model.dto.request.MovieDTO;
import ra.java_service_09.model.dto.response.ApiDataResponse;
import ra.java_service_09.model.entity.Movie;
import ra.java_service_09.service.MovieService;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Movie>>> findAll() {
        List<Movie> movies = movieService.findAll();
        ApiDataResponse<List<Movie>> response = new ApiDataResponse<>(movies, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiDataResponse<List<Movie>>> searchMovies(@RequestParam String title) {
        List<Movie> movies = movieService.searchMoviesByTitle(title);
        ApiDataResponse<List<Movie>> response = new ApiDataResponse<>(movies, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Movie>> save(@RequestBody MovieDTO movie) {
        Movie savedMovie = movieService.save(movie);
        ApiDataResponse<Movie> response = new ApiDataResponse<>(savedMovie, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ApiDataResponse<Movie>> update(@PathVariable Long id, @RequestBody Movie movie) {
        Movie updatedMovie = movieService.update(id, movie);
        ApiDataResponse<Movie> response = new ApiDataResponse<>(updatedMovie, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
