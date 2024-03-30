$(function(){
    $("#arrivalStation").hide();

    $("#departureStation").change(function(){
        if( $(".default").is(':selected') ){
            $("#arrivalStation").hide();
        } else {
            $("#arrivalStation").show();
        }
    });
});