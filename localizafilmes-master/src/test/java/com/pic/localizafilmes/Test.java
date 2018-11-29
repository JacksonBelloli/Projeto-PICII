package com.pic.localizafilmes;

import org.springframework.beans.factory.annotation.Autowired;

import com.pic.localizafilmes.services.FilmFounderService;
import com.pic.localizafilmes.utils.FileOpener;

public class Test {

	public static void main(String[] args) {
		FileOpener fileOpener = new FileOpener();
		FilmFounderService ffs = new FilmFounderService(fileOpener.execute());
		try {
			System.out.println(ffs.foundClosestFilm(8, 9));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
