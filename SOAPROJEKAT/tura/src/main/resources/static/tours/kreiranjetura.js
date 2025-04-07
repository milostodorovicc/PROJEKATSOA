$(document).on("submit", "#turaform", function (event) {
    event.preventDefault();

    var tagovi = [];
    if(document.getElementById("tag1").checked)
    {
        tagovi.push("TAG1");
    }
    if(document.getElementById("tag2").checked)
    {
        tagovi.push("TAG2");
    }
    if(document.getElementById("tag3").checked)
    {
        tagovi.push("TAG3");
    }

    console.log(tagovi);
    var naziv = $("#nazivture").val();
    var opis = $("#opisture").val();
    var duzina = $("#duzinature").val();
    var tezina = $("#tezinature").val();
    var cena = $("#cenature").val();


    var novatura = {
        naziv,
        opis,
        duzina,
        tezina,
        cena,
        tagovi
    }
    console.log(novatura);


    $.ajax({
        type: "POST",
        url: "http://localhost:8081/tours/api/ture/novatura",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(novatura),
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwt"));
        },
        success: function (res) {
            console.log(res);
            alert("Tura " + res.id + " je uspesno kreirana!");
            // window.location.href = "http://localhost:8081/users1/" + localStorage.getItem("uloga").toLowerCase() + ".html";
        },
        error: function () {
            alert("Greška!");
        }

    });
});