package com.geekBrains.diplom.API.controller;

import com.geekBrains.diplom.STORE.entity.FilmEntity;
import com.geekBrains.diplom.STORE.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;


@RestController
@CrossOrigin("*")
public class ImageController {

    @Value("${data.folder}")
    private String dataFolder;

    private final FilmRepository filmDao;
    private final String src = "/Users/asatutterin/eclipse-workspace/CinemaChain/src/main/resources/static/images/";

    @Autowired
    public ImageController(FilmRepository filmDao) {
        super();
        this.filmDao = filmDao;
    }

    @GetMapping(path = "/api/images/{filmId}", produces = MediaType.IMAGE_JPEG_VALUE)
    @Transactional
    public byte[] image(@PathVariable("filmId") Long filmId) throws IOException {
        Optional<FilmEntity> film = filmDao.findById(filmId);
        if (film.isPresent()) {
            String photoName = film.get().getImage();
            File file = new File(src
                    + photoName + ".jpeg");
            Path path = Path.of(dataFolder, film.get().getFilmId().toString(), film.get().getImage());
            return Files.readAllBytes(path);
        }
        return null;
    }
}
