package com.pic.localizafilmes.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;
import com.pic.localizafilmes.models.Movie;

public class FileOpener {

	private static List<Movie> getMovieList(final File folder) throws IOException {
		List<Movie> movieList = new ArrayList<Movie>();
		for (final File fileEntry : folder.listFiles()) {
			String fileContent = getFileContent(fileEntry);
			Gson gson = new Gson();
			Movie movie = gson.fromJson(fileContent, Movie.class);
			movieList.add(movie);
		}
		return movieList;
	}

	public List<Movie> execute() {
		List<Movie> movie = null;
		try {
			
//			ClassLoader classLoader = this.getClass().getClassLoader();
//			File file = new File(classLoader.getResource("movies").getFile());

			File file = ResourceUtils.getFile("classpath:movies");
			 movie = getMovieList(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return movie;
	}

	public static String getFileContent(File file) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		StringBuilder strb = new StringBuilder();
		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			strb.append(line);
		}
		br.close();
		return strb.toString();
	}
}
