$(document).on("click", '#dodajkomentar1', function () {


        var tekst = $("#komentar").val();
        let urlParams = new URLSearchParams(window.location.search);
        let blog1 = urlParams.get('id');

        console.log(blog1)

        if(!komentar){
            alert("Morate uneti sve podatke!");
            return;
        }

        if(blog1 === 'undefined' || blog1 ===null ){
            alert("Niste odabrali blog!");
            window.location.href = "sviblogovi.html";
        }





            var novikomentar ={
                tekst
            }

            // var fitnescentar1 = localStorage.getItem('fitnescentar1');
            //var uloga = localStorage.getItem('uloga');

            let url = new URL('http://localhost:8081/api/blogovi/novikomentar');

            url.searchParams.append('blog1', blog1);
            //url.searchParams.append('uloga', uloga);


            $.ajax({
                type: "POST",
                url: url,
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(novikomentar),
                success: function (res) {
                    alert("Uspesno ste dodali novi komentar na izabrani blog!");

                },
                error: function () {
                    alert("Greška!");
                }
            });




});
