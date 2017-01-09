/**
 * Created by ankys on 09.01.2017.
 */
function markArticleFavourite(id) {
    $.ajax({
        url: "admin/articles/favourite/" + id,
    }).done(function() {
        $(this).toggleClass("glyphicon-star glyphicon-star-empty");
    });
}
