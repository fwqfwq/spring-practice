$( document ).ready(function() {
    $("#guess_btn").click(function () {
        const name = $('#name').val();
        $.getJSON(`/api/country?name=${name}`,
            function (data, textStatus, jqXHR) {  // success callback
                $('#country').text(`${name} is from ${data.country}`);
            }).fail(function(jqXHR, textStatus, errorThrown) { // fail
            alert("something is wrong");
        });

    });

    $("#cat_fact_btn").click(function () {
        const name = $('#name').val();
        $.getJSON(`/api/catFact`,
            function (data, textStatus, jqXHR) {  // success callback
                $('#cat_fact').text(data.catFact);
            }).fail(function(jqXHR, textStatus, errorThrown) { // fail
            alert("something is wrong");
        });
    });
});
