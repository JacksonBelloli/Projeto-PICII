package com.pic.localizafilmes.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pic.localizafilmes.models.Movie;
import com.pic.localizafilmes.utils.Dijkstra;

@Component
public class FilmFounderService {

	List<Movie> movies;
	Movie filme = null;
	Integer ix = 0;

	public FilmFounderService(List<Movie> films) {
		this.movies = films;
	}

	public Movie foundClosestFilm(Integer origin, Integer destination) throws Exception {

		Dijkstra dij = new Dijkstra(movies.size());

		if (destination > origin) {
			Integer aux = destination;
			destination = origin;
			origin = aux;
		}

		for (int i = 0; i < movies.size(); i++) {
			for (int j = movies.size() - 1; j >= 0; j--) {
				if (origin == i && destination == j) {
					dij.insertEdge(i, j, 0); // impede conexão direta
				} else if (i < j) {
					dij.insertEdge(i, j, calculateDistance(movies.get(i), movies.get(j)));
				} else {
					dij.insertEdge(i, j, calculateDistance(movies.get(i), movies.get(j)));
				}
			}
		}

		// dij.printMatrix();

		// Encontra o menor caminho entre a origem e o destino
		dij.findSmallestPath(origin, destination);
		System.out.println("ta aqui");
		// Pega os caminhos percorridos

		dij.getPathToDestination().forEach((key, edge) -> {

			if (ix == 0) {
				filme = movies.get(edge.getNodeDestin());
			}
			ix++;
			System.out.println(movies.get(edge.getNodeOrigin()).getName() + "->"
					+ movies.get(edge.getNodeDestin()).getName() + "=" + edge.getAccumulatedDistance());
		});

		return filme;
		// TO DO localizar o filme do meio da distancia

	}

	private Integer calculateDistance(Movie film1, Movie film2) {

		Integer difference = 10;

		for (int i = 0; i < film1.getCategories().size(); i++) {
			for (int j = 0; j < film2.getCategories().size(); j++) {
				if (film1.getCategories().get(i).equals(film2.getCategories().get(j))) {
					difference -= 2;
				}
			}
		}

		if (difference < 1) {
			difference = 1;
		}

		return difference;
	}

}