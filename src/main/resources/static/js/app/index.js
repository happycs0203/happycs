/**
 * var init = function (){

    }
 으로하면 다른 곳에서(a.js)에서 사용하면 중복이되서 function을 덮어 써서 실행되지 않기때문에 main변수 속성을 사용한다.
 index.js만의 유효범위를 만들게 된다면 중복복문제를 할 수 있게된다.
 */
var main ={
    init : function (){
        var _this = this;
        $('#btn-save').on('click', function(){
           _this.save();
        });

        $('#btn-update').on('click', function (){
            _this.update();
        })

        $('#btn-delete').on('click', function (){
           _this.delete();
        });
    },
    save : function (){
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        }
        ;

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이등록되었습니다.');
            window.location.href = '/';

        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    },
    update : function (){
        var data = {
            title : $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' +id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.')
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    delete : function (){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function (){
            alert('글이 삭제되었습니다.')
            window.location.href = '/'
        }).fail(function (error){
            alert(JSON.stringify(error))
        });
    }

};
/**
 * REST에서 CRUD는 HTTP Method에 매핑된다.
 * 생성 POST
 * 읽기 GET
 * 수정 PUT
 * 삭제 DELETE
 */

main.init();