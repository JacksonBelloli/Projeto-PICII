$(document).ready(function() {

    $('img').click(function() {
         $('img.active').removeClass("active"); 
         $(this).addClass("active"); 
     });
     $('#btnEscolher-1').click(function(){
        var selected = $("img.active").attr("src"),
            name = $('img.active').attr('data-name');
        document.getElementById("film-1").innerHTML = '<img data-name="'+ name +'" class="new-1" style="width: 200px;" src="'+ selected + '">';
        $('div#film-1').removeClass('thumb-film');
        $("img.active").removeClass("active");
    });
    $('#btnEscolher-2').click(function(){
        var selected = $("img.active").attr("src"),
            name = $('img.active').attr('data-name');
        document.getElementById("film-2").innerHTML = '<img data-name="'+ name +'" class="new-2" style="width: 200px;" src="'+ selected +'">';
        $('div#film-2').removeClass('thumb-film');
        $("img.active").removeClass("active");
    });
    $('#comb').on('click', function(){
        if (!$('#comb').attr('id').includes('back')){
            var film1 = $('#film-1 img').attr('data-name'),
                film2 = $('#film-2 img').attr('data-name'),
                new_film = EncontraFilm (film1, film2) ;

            $('<div id="result" class="col-md-12"><div class="link-modal"><img class="tam" src="filmes/' + new_film + '.jpg"> </div></div>').insertAfter('div.single-contact');
            $('img.tam').css('width', '200px');
            $('.btn-comb').html('<a class="genric-btn primary-border circle arrow" onclick="voltar();">Voltar<span class="lnr lnr-arrow-right"></span></a>');            
            $('remove-fim1').remove();
            $('remove-film2').remove();
        }
    });
 });
 function voltar(){
    location.reload();
}


