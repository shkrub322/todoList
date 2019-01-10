$(document).ready(function () {
    $("header p").click(function(){
        $.ajax({
            type: "get",
            url: "http://localhost:8080/todoList/logout",
            success: function(){
                location.reload();
            }
        });
    })
});