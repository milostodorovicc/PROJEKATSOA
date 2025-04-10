$(document).ready(function (){

    //var uloga = localStorage.getItem("uloga");

    let url1 = new URL('http://localhost:8081/tours/api/ture/novakorpa');



    $.ajax({
        type: "GET",
        url: url1,
        dataType: "json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwt"));
        },

        success: function (res) {

            console.log(res.id)
            alert("Uspesno je kreirana nova korpa!");
            localStorage.setItem("idkorpe", res.id);

        },
        error: function () {
            alert("Greška!");
        }
    });

    let url = new URL('http://localhost:8081/tours/api/ture/sve');

    //url.searchParams.append('uloga', uloga);

    $('#korpa tr:not(:first)').remove();

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

                row += "<td>" + "    <input id=" + i + " type=\"radio\" name=\"tura\"  value=" + res[i].id + "  />" + "</td>"

                row += "</tr>";
                $('#sveture').append(row);
            }
        },
        error: function () {
            alert("Greska!");
        }

    });
});




$(document).on("click", '#dodajukorpu', function(){
    var brojljudi = $("#brojljudi").val();
    var tura1 = $("input[name=tura]:checked").val();
    if (typeof tura1 === 'undefined') {
        alert("Niste izabrali turu");

    }else{
        let url = new URL('http://localhost:8081/tours/api/ture/dodajukorpu');
        url.searchParams.append('idkorpe', localStorage.getItem("idkorpe"));

        url.searchParams.append('tura1', tura1);
        url.searchParams.append('brojljudi', brojljudi);
        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwt"));
            },

            success: function (res) {
                var cena = 0;
                $('#korpa tr:not(:first)').remove();
                //$('#korpa').empty();
                for ( let i = 0; i < res.length; i++) {


                    let row = "<tr>";
                    row += "<td>" + res[i].naziv + "</td>";
                    row += "<td>" + res[i].cena + "</td>"
                    row += "<td>" + res[i].brojljudi + "</td>"

                    row += "<td>" + "    <input id=" + i + " type=\"radio\" name=\"turaukorpi1\"  value=" + res[i].id + "  />" + "</td>";

                    row += "</tr>";

                    $('#korpa').append(row);
                    cena = cena + res[i].cena*res[i].brojljudi;
                }
                $('#ukupnacena').text('Ukupna cena:' + cena );

            },
            error: function () {
                alert("Greška!");
            }
        });
    }
});



$(document).on("click", '#ukloniizkorpe', function(){

        var turaukorpi = $("input[name=turaukorpi1]:checked").val();
        console.log(turaukorpi);
        if (typeof turaukorpi === 'undefined') {
            alert("Niste izabrali turu");

        }else{
            let url = new URL('http://localhost:8081/tours/api/ture/ukloniizkorpe');
            url.searchParams.append('idkorpe', localStorage.getItem("idkorpe"));

            url.searchParams.append('turaukorpi', turaukorpi);
            $.ajax({
                type: "GET",
                url: url,
                dataType: "json",
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("jwt"));
                },

                success: function (res) {
                    var cena = 0;
                    //$('#korpa').empty();
                    $('#korpa tr:not(:first)').remove();
                    for ( let i = 0; i < res.length; i++) {
                        alert(res[i].naziv);

                        let row = "<tr>";
                        row += "<td>" + res[i].naziv + "</td>";
                        row += "<td>" + res[i].cena + "</td>"
                        row += "<td>" + res[i].brojljudi + "</td>"

                        row += "<td>" + "    <input id=" + i + " type=\"radio\" name=\"turaukorpi1\"  value=" + res[i].id + "  />" + "</td>";

                        row += "</tr>";
                        $('#korpa').append(row);
                        cena = cena + res[i].cena*res[i].brojljudi;
                    }
                    $('#ukupnacena').text('Ukupna cena:' + cena );

                },
                error: function () {
                    alert("Greška!");
                }
            });
        }

    });