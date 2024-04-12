$(document).on("submit", "#form1", function (event) {
    event.preventDefault();



        var naslovbloga = $("#naslovbloga").val();
        var opisbloga = $("#opisbloga").val();

        var noviblog = {
            naslovbloga,
            opisbloga
        }
        console.log(noviblog);


        $.ajax({
            type: "POST",
            url: "http://localhost:8081/api/blogovi/noviblog",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(noviblog),
            success: function (res) {
                console.log(res);
                alert("Blog " + res.id + " je uspesno kreiran!");
                window.location.href = localStorage.getItem("uloga") + ".html";
            },
            error: function () {
                alert("Greška!");
            }

        });
});
