if(localStorage.getItem("uloga")=== "ADMINISTRATOR") {
    $(document).ready(function () {

        let url = new URL('http://localhost:8081/users1/api/korisnik');
        url.searchParams.append('uloga', localStorage.getItem("uloga"));
        $.ajax({
            type: "GET",

            url: url,
            dataType: "json",
            success: function (res) {
                console.log("SUCCESS:\n", res);
                for (i = 0; i < res.length; i++) {
                    let row = "<tr>";
                    row += "<td>" + res[i].korisnickoime + "</td>";
                    row += "<td>" + res[i].email + "</td>";
                    row += "<td>" + res[i].uloga + "</td>";

                    let btn = "<button class='blokiraj' data-id=" + res[i].id + ">Blokiraj</button>";
                    row += "<td>" + btn + "</td>"
                    row += "</tr>";
                    $('#aktivacijatrenera1').append(row);
                }
            },

            error: function (res) {
                if(localStorage.getItem("uloga") ==="VODIC"){
                    window.location.href = "vodic.html";
                }else{
                    window.location.href = "turista.html";
                }
                console.log("ERROR:\n", res);

            }
        });
    });
}
else{
    if(localStorage.getItem("uloga") ==="VODIC"){
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "vodic.html";
    }else if(localStorage.getItem("uloga") ==="TURISTA"){
        alert("Nemate pristup ovoj stranici!")
        window.location.href = "turista.html";
    }
}

if(localStorage.getItem("uloga")=== "ADMINISTRATOR") {
    $(document).on('click', '.blokiraj', function (){


        let trenerid = this.dataset.id;
        let url = new URL('http://localhost:8081/users1/api/korisnik/' + trenerid);
        url.searchParams.append('uloga', localStorage.getItem("uloga"));

        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",
            success: function (res) {
                console.log("SUCCESS:\n", res);
                alert("Uspesno ste blokirali korisnika!");
                window.location.reload(true);

            },
            error: function (res) {
                if(localStorage.getItem("uloga") ==="VODIC"){
                    window.location.href = "vodic.html";
                }else if(localStorage.getItem("uloga")==="TURISTA"){
                    window.location.href = "turista.html";
                }else {
                    console.log("ERROR:\n", res);
                    alert("Niste uspeli da blokirate korisnika!");
                }}
        });
    });}
else{
    if(localStorage.getItem("uloga") ==="VODIC"){
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "vodic.html";
    }else if(localStorage.getItem("uloga") === "TURISTA"){
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "turista.html";
    }else{
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "login.html";
    }


}