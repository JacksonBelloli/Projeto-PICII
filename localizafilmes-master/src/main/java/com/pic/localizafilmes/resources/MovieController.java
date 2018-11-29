package com.pic.localizafilmes.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pic.localizafilmes.models.Movie;
import com.pic.localizafilmes.services.FilmFounderService;
import com.pic.localizafilmes.utils.FileOpener;

@RestController
@RequestMapping("/api")
public class MovieController {

	@GetMapping("/movie/{movieOne}/{movieTwo}")
	public Movie movie(@PathVariable String movieOne, @PathVariable String movieTwo) {
		
		FileOpener fileOpener = new FileOpener();
		List<Movie> movieList = fileOpener.execute();
		FilmFounderService filmFounderService = new FilmFounderService(movieList);
		Integer idMovieOne = -1;
		Integer idMovieTwo = -1;
		Integer increment = 0;
		Movie foundedMovie = null;

		for (Movie movie : movieList) {
			if (movie.getName().equals(movieOne)) {
				idMovieOne = increment;
			}
			if (movie.getName().equals(movieTwo)) {
				idMovieTwo = increment;
			}
			increment++;
		}
		try {
			foundedMovie = filmFounderService.foundClosestFilm(idMovieOne, idMovieTwo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return foundedMovie;
	}

	@GetMapping("/movie/list")
	public List<Movie> movieList() {
		FileOpener fileOpener = new FileOpener();
		List<Movie> list = fileOpener.execute();
		return list;
	}

}
