

function loadArticles() {
    $.ajax('/rest/articles/', {
        method: 'GET',
        error: function (xhr, status, error) {
            console.log(status, error);
        },
        success: function (data, status, xhr) {
            console.log(data);
        }
    });
}

function addArticle() {
    var newArticle = {
        authors: 'unknown',
        content: 'Sample...',
        title: 'Test Article'
    };

    $.ajax('/rest/articles/', {
        method: 'POST',
        error: function (xhr, status, error) {
            console.log(status, error);
        },
        data: newArticle,
        success: function (data, status, xhr) {
            console.log(data);
        }
    });
}