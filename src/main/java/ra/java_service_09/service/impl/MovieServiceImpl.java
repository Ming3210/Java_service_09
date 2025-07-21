package ra.java_service_09.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.java_service_09.model.dto.request.MovieDTO;
import ra.java_service_09.model.entity.Movie;
import ra.java_service_09.repository.MovieRepository;
import ra.java_service_09.service.MovieService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(()-> new RuntimeException("Movie not found with id: " + id));
    }

    @Override
    public Movie save(MovieDTO movieDTO) {
        MultipartFile poster = movieDTO.getPosterFile();
        String url = "";
        if(poster!=null && !poster.isEmpty()){
            try {
                Map upload = cloudinary.uploader().upload(poster.getBytes(), ObjectUtils.emptyMap());
                url = upload.get("secure_url").toString();
                if(url==null || url.isEmpty()){
                    throw new RuntimeException("Url of image file is empty!");
                }
            } catch (IOException e) {
                throw new RuntimeException("Upload file error!");
            }
        }
        Movie movie = Movie.builder()
                .title(movieDTO.getTitle())
                .description(movieDTO.getDescription())
                .releaseDate(movieDTO.getReleaseDate())
                .poster(url)
                .build();
        return movieRepository.save(movie);

    }

    @Override
    public Movie update(Long id, Movie movie) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setReleaseDate(movie.getReleaseDate());
        existingMovie.setPoster(movie.getPoster());

        return movieRepository.save(existingMovie);
    }

    @Override
    public void delete(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
        movieRepository.delete(movie);
    }

    @Override
    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findMoviesByTitleContainingIgnoreCase(title);
    }

}
