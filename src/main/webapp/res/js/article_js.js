/**
 * Created by ankys on 09.01.2017.
 */
function markArticleFavourite(id, event) {
    $.ajax({
        url: "favourite/?id=" + id,
        method: 'PUT'
    }).done(function(data) {
        if(data == true) {
            $target = $(event.target);
            $target.toggleClass("glyphicon-star glyphicon-star-empty");
        }
    });
}
