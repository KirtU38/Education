$(function () {

    const appendTodo = function (todo) {
        var todoCode = '<div><span>' + todo.id +' ' + '</span><a href="#" class="todo-link" data-id="' +
            todo.id + '">' + todo.text + '</a><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>' +
            '<button class="delete-todo" data-id="' + todo.id + '">Удалить</button></div>';

        $('#todo-list').append(todoCode);
    };

    // Show adding todo form
    $('#show-add-todo-form').click(function () {
        $('#todo-form').css('display', 'flex');
    });

    // Closing adding todo form
    $('#todo-form').click(function (event) {
        if (event.target === this) {
            $(this).css('display', 'none');
        }
    });

    // Show editing todo form
    $('#show-edit-todo-form').click(function () {
        $('#edit-form').css('display', 'flex');
    });

    // Closing editing todo form
    $('#edit-form').click(function (event) {
        if (event.target === this) {
            $(this).css('display', 'none');
        }
    });

    // Getting todo
    $(document).on('click', '.todo-link', getTodo);

    function getTodo() {
        var link = $(this);
        var todoId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/todos/' + todoId,
            success: function (response) {
                var code = '<span> Создано:' + response.date + '</span>';
                link.parent().append(code);
            },
            error: function (response) {
                if (response.status == 404) {
                    alert('Дело не найдено!');
                }
            }
        });
        return false;
    }

    // Adding todo
    $('#save-todo').click(addTodo);

    function addTodo() {
        var data = $('#todo-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/todos/',
            data: data,
            success: function (response) {
                $('#todo-form').css('display', 'none');
                appendTodo(response);
            }
        });
        return false;
    }

    // Deleting todo
    $(document).on('click', '.delete-todo', deleteTodo);

    function deleteTodo(){
        var link = $(this);
        var todoId = link.data('id');
        $.ajax({
            method: "DELETE",
            url: '/todos/' + todoId,
            success: function (response) {
                var code = '<span style="color: #ff0000;"> Удалено</span>';
                link.parent().append(code);
            }
        });
        return false;
    }

    // Deleting all todos
    $(document).on('click', '#delete-todos', deleteAll);

    function deleteAll(){
        $.ajax({
            method: "DELETE",
            url: '/todos/',
            success: function () {
                $('#whitespace').append('<span style="color: #ff0000;">Список очищен</span>');
            }
        });
        return false;
    }

    // Editing todo
    $('#edit-todo').click(editTodo);

    function editTodo() {
        var dataArray = $('#edit-form form').serializeArray();
        var todoId = dataArray[0]['value'];
        var data = dataArray[1]['name'] + '=' + dataArray[1]['value'];
        
        $.ajax({
            method: "PUT",
            url: '/todos/' + todoId,
            data: data,
            success: function () {
                $('#edit-form').css('display', 'none');
            }
        });
        return false;
    }    
});