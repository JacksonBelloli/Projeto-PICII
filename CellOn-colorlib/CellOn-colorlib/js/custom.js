$(document).ready(function() {

    $('img').click(function() {
         film1 = document.getElementById('remove-film1');
         film2 = document.getElementById('remove-film2');
         $('img.active').removeClass("active"); 
         $(this).addClass("active"); 
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
    $('#comb').on('click', function(){
        if (!$('#comb').attr('id').includes('back')){
            var selecionada = $('#film-1 img').attr('src');
            $('#film-1').css('display', 'none');
            $('#film-2').css('display', 'none');
            $('<div id="result" class="col-md-12"><div class="link-modal"><img class="tam" src="' + selecionada + '"> </div></div>').insertAfter('div.single-contact');
            $('img.tam').css('width', '200px');
            $('.btn-comb').html('<a class="genric-btn primary-border circle arrow" onclick="voltar();">Voltar<span class="lnr lnr-arrow-right"></span></a>');            
            film1.remove();
            film2.remove();
        }
    });
 });
 function voltar(){
    location.reload();
}


