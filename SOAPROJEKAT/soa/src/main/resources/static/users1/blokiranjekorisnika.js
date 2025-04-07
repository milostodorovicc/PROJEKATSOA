$(document).ready(function () {

        let url = new URL('http://localhost:8081/users1/api/korisnik/getall');

        $.ajax({
            type: "GET",

            url: url,
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwt"));
            },
            success: function (res) {
                console.log("SUCCESS:\n", res);
                for (i = 0; i < res.length; i++) {
                    let row = "<tr>";
                    row += "<td>" + res[i].username + "</td>";
                    row += "<td>" + res[i].email + "</td>";
                    let roles1 = res[i].roles;
                    for(let role of roles1) {
                        row += "<td>" + role.name + "</td>";
                    }
                    let btn = "<button class='blokiraj' data-id=" + res[i].id + ">Blokiraj</button>";
                    row += "<td>" + btn + "</td>"
                    row += "</tr>";
                    $('#aktivacijatrenera1').append(row);
                }
            },

            error: function (res) {

                console.log("ERROR:\n", res);

            }
        });
    });




$(document).on('click', '.blokiraj', function (){


        let trenerid = this.dataset.id;
        let url = new URL('http://localhost:8081/users1/api/korisnik/' + trenerid);

        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwt"));
            },
            success: function (res) {
                console.log("SUCCESS:\n", res);
                alert("Uspesno ste blokirali korisnika!");
                window.location.reload(true);

            },
            error: function (res) {

                    console.log("ERROR:\n", res);
                    alert("Niste uspeli da blokirate korisnika!");
                }
        });
    });



