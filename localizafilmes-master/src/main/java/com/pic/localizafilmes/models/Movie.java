package com.pic.localizafilmes.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Movie implements Serializable {
	private static final long serialVersionUID = 8156216028925555636L;


	private String name;
	private Integer year;
	private Integer runtime;
	private List<String> categories;
	private String release;
	private String director;
	private List<String> writer;
	private List<String> actors;
	private String storyline;

	public Movie(String name, Integer year, Integer runtime, List<String> categories, String release, String director,
			List<String> writer, List<String> actors, String storyline) {
		super();
		this.name = name;
		this.year = year;
		this.runtime = runtime;
		this.categories = categories;
		this.release = release;
		this.director = director;
		this.writer = writer;
		this.actors = actors;
		this.storyline = storyline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public List<String> getWriter() {
		return writer;
	}

	public void setWriter(List<String> writer) {
		this.writer = writer;
	}

	public List<String> getActors() {
		return actors;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	public String getStoryline() {
		return storyline;
	}

	public void setStoryline(String storyline) {
		this.storyline = storyline;
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + ", year=" + year + ", runtime=" + runtime + ", categories=" + categories
				+ ", release=" + release + ", director=" + director + ", writer=" + writer + ", actors=" + actors
				+ ", storyline=" + storyline + "]";
	}

}
