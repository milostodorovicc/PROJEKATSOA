$(document).ready(function (){


    let url = new URL('http://localhost:8081/tours/api/ture/svekreiraneture');




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
                row += "<td>" + res[i].naziv + "</td>";
                row += "<td>" + res[i].opis + "</td>";
                row += "<td>" + res[i].duzina + "</td>"
                row += "<td>" + res[i].tezina + "</td>";
                row += "<td>" + res[i].cena + "</td>"
                row += "<td>" + res[i].tagovi + "</td>"


                row += "</tr>";
                $('#svekreiraneture').append(row);
            }
        },
        error: function () {
            alert("Greska!");
        }

    });
});