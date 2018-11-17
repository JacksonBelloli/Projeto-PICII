$(document).ready(function() {
    $('img').click(function() {
         $('img.active').removeClass("active"); //aqui removemos a class do item anteriormente clicado para que possamos adicionar ao item clicado
         $(this).addClass("active"); //aqui adicionamos a class ao item clicado
     });
     $('#btnEscolher-1').click(function(){
        var selecionada = $("img.active").attr("src");
        document.getElementById("film-1").innerHTML = '<img class="new-1">';
        $('div#film-1').removeClass('thumb-film');
        $("img.new-1").css("width", "200px");
        $("img.new-1").attr("src", selecionada);
        $("img.active").removeClass("active");
    });
    $('#btnEscolher-2').click(function(){
        var selecionada = $("img.active").attr("src");
        document.getElementById("film-2").innerHTML = '<img class="new-2">';
        $('div#film-2').removeClass('thumb-film');
        $("img.new-2").css("width", "200px");
        $("img.new-2").attr("src", selecionada);
        $("img.active").removeClass("active");
    });
    $('#comb').click(function(){
        var selecionada = $("#film-1 img").attr("src");
        $("div#film-1").css("display", "none");
        $("div#film-2").css("display", "none");
        $('<div class="col-md-12"><div class="link-modal"><img class="tam" src="' + selecionada + '"> </div></div>').insertAfter('div.single-contact');
        $('img.tam').css('width', '200px');
        $('a#comb').html('Voltar<span class="lnr lnr-arrow-right"></span>');
        $('a#comb').css('top', '-315px');
        $('a#comb').css('position', 'relative');
    });
 });



