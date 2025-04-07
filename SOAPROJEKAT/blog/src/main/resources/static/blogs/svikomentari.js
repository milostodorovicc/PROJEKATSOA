$(document).ready(function (){

    //var uloga = localStorage.getItem("uloga");

    let url = new URL('http://localhost:8081/blogs/api/blogovi/svikomentari');
    let urlParams = new URLSearchParams(window.location.search);
    let blog1 = urlParams.get('id');
    url.searchParams.append("blog1",blog1);

    //url.searchParams.append('uloga', uloga);

    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwt"));
        },

        success: function (res) {

            for ( let i = 0; i < res.length; i++) {


                let row = "<tr>";
                row += "<td>" + res[i].korisnickoime + "</td>";
                row += "<td>" + res[i].tekst + "</td>";
                row += "<td>" + res[i].datum + "</td>"



                row += "</tr>";
                $('#svikomentari').append(row);
            }
        },
        error: function () {
            alert("Greska!");
        }

    });})