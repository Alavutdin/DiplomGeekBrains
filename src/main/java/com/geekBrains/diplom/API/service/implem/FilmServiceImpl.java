package com.geekBrains.diplom.API.service.implem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.geekBrains.diplom.API.dto.FilmDto;
import com.geekBrains.diplom.API.dto.factory.FilmDtoFactory;
import com.geekBrains.diplom.API.exception.BadRequestException;
import com.geekBrains.diplom.API.exception.NotFoundException;
import com.geekBrains.diplom.API.model.FilmModel;
import com.geekBrains.diplom.API.service.FilmService;
import com.geekBrains.diplom.STORE.entity.CategoryEntity;
import com.geekBrains.diplom.STORE.entity.FilmCategory;
import com.geekBrains.diplom.STORE.entity.FilmEntity;
import com.geekBrains.diplom.STORE.repository.CategoryRepository;
import com.geekBrains.diplom.STORE.repository.FilmCategoryRepository;
import com.geekBrains.diplom.STORE.repository.FilmRepository;

@Service
public class FilmServiceImpl implements FilmService {
    private static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);
    @Value("${data.folder}")
    private String dataFolder;

    private final FilmRepository filmDao;
    private final FilmDtoFactory filmDtoFactory;
    private final CategoryRepository categoryDao;
    private final FilmCategoryRepository filmCategoryDao;

    @Autowired
    public FilmServiceImpl(FilmRepository filmDao, FilmDtoFactory filmDtoFactory, CategoryRepository categoryDao,
                           FilmCategoryRepository filmCategoryDao) {
        super();
        this.filmDao = filmDao;
        this.filmDtoFactory = filmDtoFactory;
        this.categoryDao = categoryDao;
        this.filmCategoryDao = filmCategoryDao;
    }

    @Override
    public List<FilmDto> getFilmsByCategory(Long categoryId) {
        CategoryEntity category = findCategoryById(categoryId);
        return filmDtoFactory.createListFilmDto(
                filmCategoryDao
                        .findAllByCategory(category)
                        .stream()
                        .map(FilmCategory::getFilm)
                        .collect(Collectors.toList()));
    }

    @Override
    public FilmDto addFilmInTheCategories(FilmModel model) {
        FilmEntity film = filmDao.saveAndFlush(new FilmEntity(
                model.getTitle(),
                model.getDescrition(),
                model.getDirector(),
                model.getDuration()));
        film.setImage(saveImage(film, model.getImage()));
        saveCategory(model, film);
        return filmDtoFactory.createFilmDto(film);
    }

    @Override
    @Transactional
    public FilmDto updateFilm(FilmModel model, Long filmId) {
        FilmEntity film = findFilmById(filmId);
        if(model.getImage()!=null) {
            deleteImage(film);
            film.setImage(saveImage(film, model.getImage()));
        }
        film.getFilmCategory().clear();
        film.setDescrition(model.getDescrition());
        film.setDirector(model.getDirector());
        film.setDuration(model.getDuration());
        saveCategory(model, film);
        return filmDtoFactory.createFilmDto(film);
    }


    @Override
    public void deleteFilm(Long filmId) {
        FilmEntity film = findFilmById(filmId);
        deleteImage(film);
        filmDao.deleteById(filmId);
    }

    private CategoryEntity findCategoryById(Long categoryId) {
        return categoryDao.findById(categoryId).orElseThrow(()->
                new NotFoundException(
                        String.format(
                                "Категория с ID \"%s\" не найдена",
                                categoryId)));
    }

    private FilmEntity findFilmById(Long filmId) {
        return filmDao.findById(filmId).orElseThrow(()->
                new NotFoundException(
                        String.format("Фильм с ID \"%s\" не найден", filmId)));
    }

    private boolean saveCategory(FilmModel model, FilmEntity film) {
        Arrays.stream(model.getCategories()).forEach((c)->{
                    CategoryEntity category = findCategoryById(c);
                    filmCategoryDao.save(new FilmCategory(film, category));
                }
        );
        return true;
    }

    private String saveImage(FilmEntity film, MultipartFile image) {
        if(image==null) {
            throw new BadRequestException("Отсутствует изображение к фильму");
        }
        Path directory = Path.of(dataFolder, film.getFilmId().toString());
        try {
            image.transferTo(Path.of(Files.createDirectories(directory).toString(),
                    image.getOriginalFilename()));
        } catch (IOException e) {
            logger.error("",e);
            filmDao.deleteById(film.getFilmId());
            throw new BadRequestException("Ошибка при загрузке изображения к фильму");
        }
        logger.info(image.getOriginalFilename());
        return image.getOriginalFilename();
    }

    private void deleteImage(FilmEntity film) {
        Path path = Path.of(dataFolder, film.getFilmId().toString(), film.getImage());
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
