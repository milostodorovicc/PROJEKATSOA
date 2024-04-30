$(document).on("submit", "#form1", function (event) {
    event.preventDefault();



        var naslov = $("#naslov").val();
        var opis = $("#opis").val();
        var images1 = new FormData();
        var noviblog = {
            naslov,
            opis
        }
        console.log(noviblog);
        var files = $('#imageInput')[0].files;
        for(var i=0; i<files.length; i++){
            images1.append('files', files[i]);
        }





     let url = new URL('http://localhost:8081/blogs/api/blogovi/noviblog');



    $.ajax({
            type: "POST",
            url: url,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(noviblog),
            success: function (res) {

                let url1 = new URL('http://localhost:8081/blogs/api/blogovi/noviblogimages');
                url1.searchParams.append("idbloga", res.id)
                // alert("Uspesno ste dodali novi komentar na izabrani blog!");
                // window.location.href ="http://localhost:8081/users1/" +  localStorage.getItem("uloga").toLowerCase() + ".html";
                $.ajax({

                        url: url1,
                        type: 'POST',
                        data: images1,
                        contentType: false, // Important: Let the browser set the Content-Type
                        processData: false, // Important: Don't process data (FormData)
                        success: function(response){
                            console.log(response);
                            alert("Uspesno ste kreirali blog sa slikama!")
                        },
                        error: function() {

                        }


            });},
            error: function () {
                alert("Greška!");
            }



        });
});



$(document).ready(function(){
    // Function to preview images
    function previewImages() {
        var preview = $('#imagePreview');
        preview.empty();
        if (this.files) {
            [].forEach.call(this.files, readAndPreview);
        }
        function readAndPreview(file) {
            if ( /\.(jpe?g|png|gif)$/i.test(file.name) ) {
                var reader = new FileReader();
                $(reader).on("load", function() {
                    $('<img>').attr('src', this.result).appendTo(preview);
                });
                reader.readAsDataURL(file);
            }
        }
    }

    // Event listener for image input
    $('#imageInput').on("change", previewImages);

    // // Function to handle image upload
    // $('#uploadButton').on("click", function(){
    //     var formData = new FormData();
    //     var files = $('#imageInput')[0].files;
    //     for(var i=0; i<files.length; i++){
    //         formData.append('images[]', files[i]);
    //     }
    //     $.ajax({
    //         url: 'backend_endpoint_url',
    //         type: 'POST',
    //         data: formData,
    //         contentType: false,
    //         processData: false,
    //         success: function(response){
    //             $('#response').html(response);
    //         },
    //         error: function(xhr, status, error) {
    //             console.error(xhr.responseText);
    //         }
    //     });
    // });
});