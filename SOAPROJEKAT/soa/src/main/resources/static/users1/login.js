$(document).on("submit", "form", function (event) {

    event.preventDefault();

    var username = $("#username").val();
    var password = $("#password").val();


    var login = {
        username,
        password,

    }
    console.log(login);


    $.ajax({
        type: "POST",
        url: "http://localhost:8081/users1/api/korisnik/login1",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(login),
        success: function (res) {
            console.log(res);



            let n = 5;
            let role1 = res.role.slice(n);

                    localStorage.setItem("jwt", res.accessToken);
                    window.location.href = role1.toLowerCase() + ".html";



        },
        error: function () {
            alert("Niste uneli dobro korisnicko ime ili lozinku!");
        }

    });



});