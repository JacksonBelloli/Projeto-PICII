function EncontraFilm (film1, film2) {
    $.ajax({
        datatype: 'json',
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        url: "http://rest-service.guides.spring.io/greeting"
    }).then(function(data) {
       $('#get-id').append(data.id);
       $('#get-content').append(data.content);
    });
}