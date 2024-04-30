$(document).on("submit", "form", function (event) {

    event.preventDefault();

    var korisnickoime = $("#korisnickoime").val();
    var lozinka = $("#lozinka").val();


    var login = {
        korisnickoime,
        lozinka,

    }
    console.log(login);


    $.ajax({
        type: "POST",
        url: "http://localhost:8081/users1/api/korisnik/login",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(login),
        success: function (res) {
            console.log(res);
            if(res.uloga === "VODIC"){

                    localStorage.setItem("id", res.id);
                    localStorage.setItem("uloga", "VODIC");
                    window.location.href = "vodic.html";

            }else if(res.uloga === "ADMINISTRATOR"){
                localStorage.setItem("id", res.id);
                localStorage.setItem("uloga", "ADMINISTRATOR");
                window.location.href = "administrator.html"
            }else{
                localStorage.setItem("id", res.id);
                localStorage.setItem("uloga", "TURISTA");
                window.location.href = "turista.html"
            }

        },
        error: function () {
            alert("Niste uneli dobro korisnicko ime ili lozinku!");
        }

    });



});