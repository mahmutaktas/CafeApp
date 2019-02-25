$(document).ready(function() {
    $(".productCategory").change(function (e) {
        $("#abc").closest("form").submit();
    })
});