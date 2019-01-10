$(document).ready(function () {
    let login;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/todoList/note",
        dataType: "json",
        success: function (data) {
            $(".content").empty();
            $(".content").append(
                '<h3>Welcome ' + login + '</h3>' +
                '<div class="create-note">' +
                '   <input type="text" class="form-control col-9" value="New note">' +
                '   <button class="btn btn-success col-4">Create note</button>' +
                '</div>'
            );
            data.forEach(element => {
                $(".content").append(
                '<div id="' + element.id + '" class="note row">' +
                '   <input type="text" class="form-control col-8" value="' + element.content + '">' +
                '   <button class="btn btn-primary" >Save</button>' +
                '   <button class="btn btn-danger" >Delete</button>' +
                '</div>');
            });
        }
    });

    $("#authSubm").click(function (e) {
        login = $(".log").find("input").val();
        password = $(".pass").find("input").val();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/todoList/login?login=" + login + "&password=" + password,
            statusCode: {
                200: function(){
                    $(".content").empty();
                    $(".content").append(
                        '<h3>Welcome ' + login + '</h3>' +
                        '<div class="create-note">' +
                        '   <input type="text" class="form-control col-9" value="New note">' +
                        '   <button class="btn btn-success col-4">Create note</button>' +
                        '</div>'
                    );
                    $.ajax({
                        type: "GET",
                        url: "http://localhost:8080/todoList/note",
                        dataType: "json",
                        success: function (data) {
                            data.forEach(element => {
                                $(".content").append(
                                '<div id="' + element.id + '" class="note row">' +
                                '   <input type="text" class="form-control col-8" value="' + element.content + '">' +
                                '   <button class="btn btn-primary" >Save</button>' +
                                '   <button class="btn btn-danger" >Delete</button>' +
                                '</div>');
                            });
                        }
                    });
                },
                500: function () {
                    alert("Incorrect login or password");
                }
            }
        });
    });
    $(document).on("click", ".btn-danger", function() {
        parent = $(this).parents(".note")
        id = parent.attr("id");
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/todoList/note?id=" + id,
            success: function (response) {
                parent.hide(); 
            }
        });
    });
    $(document).on("click", ".btn-primary", function(){
        parent = $(this).parents(".note");
        id = parent.attr("id");
        input = parent.find("input");
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/todoList/note?id=" + id + "&content=" + input.val(),
            success: function (response) {
            }
        });
    });
    $(document).on("click", ".btn-success", function(){
        parent = $(this).parents(".create-note");
        input = parent.find("input");
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/todoList/note?content=" + input.val(),
            success: function (response) {
                $(".content").append(
                    '<div id="' + response.id + '" class="note row">' +
                        '   <input type="text" class="form-control col-8" value="' + response.content + '">' +
                        '   <button class="btn btn-primary" >Save</button>' +
                        '   <button class="btn btn-danger" >Delete</button>' +
                    '</div>');    
            }
        });
    })
});