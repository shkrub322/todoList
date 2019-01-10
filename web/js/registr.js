$(document).ready(function () {
    $("#registr").click(function (e) { 
        $(".content").empty();
        $(".content").append(
            '<h3>Registration</h3>' +
            '<div class="form-group log">' + 
            '   <label>Login</label>' +
            '   <input type="text" class="form-control" id="login" placeholder="Enter login">' + 
            '</div>' +
            '<div class="form-group pass">' +
            '   <label>Password</label>' +
            '   <input type="password" class="form-control" id="password" placeholder="Enter password">' + 
            '</div>' +
            '<button id="registrSubm" class="btn btn-primary">Submit</button>' +
            '<button id="auth" class="btn btn-primary">Autorization</button>');
    });
    $(document).on("click", "#registrSubm", function(){
        login = $(".log").find("input").val();
        password = $(".pass").find("input").val();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/todoList/registr?login=" + login + "&password=" + password,
            success: function (response) {
                location.reload();
            },
            statusCode: {
                405 :function(){
                    alert("Try again")
                }
            }
        });
    });
    $(document).on("click", "#auth", function(){
        location.reload();
    })
});