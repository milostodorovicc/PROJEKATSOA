$(document).ready(function (){

        //var uloga = localStorage.getItem("uloga");

        let url = new URL('http://localhost:8081/blogs/api/blogovi/svi');

        //url.searchParams.append('uloga', uloga);

        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",

            success: function (res) {

                for ( let i = 0; i < res.length; i++) {


                    let row = "<tr>";
                    row += "<td>" + res[i].naslov + "</td>";
                    row += "<td>" + res[i].opis + "</td>";
                    row += "<td>" + res[i].datum + "</td>"

                    row += "<td>" + "    <input id=" + i + " type=\"radio\" name=\"blog\"  value=" + res[i].id + "  />" + "</td>";

                    row += "</tr>";
                    $('#sviblogovi').append(row);
                }
            },
            error: function () {
                alert("Greska!");
            }

        });



    $(document).on("click", '#dodajkomentar', function(){

            var blog1 = $("input[name=blog]:checked").val();
            if (typeof blog1 === 'undefined') {
                alert("Niste izabrali blog");

            }else{


                window.location.href = "dodajkomentar.html?id=" + blog1;
            }


    });

    $(document).on("click", '#prikaziblog', function(){

        var blog1 = $("input[name=blog]:checked").val();
        if (typeof blog1 === 'undefined') {
            alert("Niste izabrali blog");

        }else{


            window.location.href = "blog.html?id=" + blog1;
        }


    });



    $(document).on("click", '#svikomentari', function(){

        var blog1 = $("input[name=blog]:checked").val();
        if (typeof blog1 === 'undefined') {
            alert("Niste izabrali blog");

        }else{


            window.location.href = "svikomentari.html?id=" + blog1;
        }


    });
});





