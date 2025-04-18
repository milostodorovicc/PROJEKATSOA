$(document).ready(function () {


    let url = new URL('http://localhost:8081/blogs/api/blogovi/jedanblog');
    let urlParams = new URLSearchParams(window.location.search);
    let blogid = urlParams.get('id');
    url.searchParams.append("blogid",blogid);

    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwt"));
        },

        success: function (res) {

            let url1 = new URL('http://localhost:8081/blogs/api/blogovi/slikebloga');

            url1.searchParams.append("idblog",blogid);


            $("input#naslov").val(res.naslov);
            $("input#opis").val(res.opis);
            $("input#datumkreiranja").val(res.datum);
            $.ajax({
                type: "GET",
                url: url1,
                dataType: "json",
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwt"));
                },

                success: function (res) {
                    for ( let i = 0; i < res.length; i++) {
                    // var imgElement = $("<img>").attr("src", res);
                    // var imageItem = $("<div>").addClass("image-item").append(imgElement);
                    // $("#imageContainer").append(imageItem);
                        alert(res[i]);
                        // var imagePath = "upload/static/blogs/" + res[i];
                        // $("body").append( "<img src='"+ res[i] +"'>" );
                        var imagePath = "/uploads/" + res[i];
                        $("body").append("<img src='" + imagePath + "'>");

                }},
                error: function () {
                    alert("Greska!");
                }

            });



        },
        error: function () {
            alert("Greska!");
        }

    });
});