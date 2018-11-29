function EncontraFilm(film1, film2) { 
    return  $.ajax({
       datatype: "json",
       type: "GET",
       contentType: "application/json; charset=utf-8",
       url: "http://localhost:8080/api/movie/" + film1 + "/" + film2
     }).then(function(data) {
       return data.name;
     });
   }