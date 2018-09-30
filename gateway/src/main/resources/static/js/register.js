/**
 * Created by terry on 2018/9/13.
 */
function signup() {

    var username = $("input[id='u_name']").val();
    var password = $("input[id='u_pwd']").val();
    var mobile=$("#u_mobile").val();

    if (mobile.length!==11) {
        alert("请输入11位手机号!");
        return;
    }

    if (username && password) {
        $.ajax({
            url: 'user/register',
            datatype: 'json',
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                username: username,
                password: password,
                mobile:mobile
            }),
            success: function (data) {
                if("10000"===data.recode){
                    window.location.href="login.html";
                }else{
                    alert(data.remsg);
                }
                // requestOauthToken(username, password);
                // initAccount(getCurrentAccount());

            },
            error: function (xhr, ajaxOptions, thrownError) {
                if (xhr.status === 400) {
                    alert("Sorry, account with the same name already exists.");
                } else {
                    alert("An error during account creation. Please, try again.");
                }
            }
        });

    } else {
        alert("Please, fill all the fields.");
    }
}

function signIn() {
    var username = $("input[id='u_name']").val();
    var password = $("input[id='u_pwd']").val();
    var success=requestOauthToken(username, password);
    if(success){
        alert("登录成功");
        window.location.href="../home.html";
    }else{
        alert("登录失败");
    }
}

function requestOauthToken(username, password) {

    var success = false;

    $.ajax({
        url: 'uaa/oauth/token',
        datatype: 'json',
        type: 'post',
        headers: {'Authorization': 'Basic YnJvd3Nlcjo='},
        async: false,
        data: {
            scope: 'ui',
            username: username,
            password: password,
            grant_type: 'password'
        },
        success: function (data) {
            if(data.access_token!==null && data.access_token!==undefined){
                localStorage.setItem('token', data.access_token);
                success = true;
            }
        },
        error: function () {
            removeOauthTokenFromStorage();
        }
    });

    return success;
}


function getCurrentAccount() {
    var token = getOauthTokenFromStorage();
    if (token) {
        $.ajax({
            url: 'user/current',
            datatype: 'json',
            type: 'get',
            headers: {'Authorization': 'Bearer ' + token},
            async: false,
            success: function (data) {
                var all_balance=0;
                var cash_balance=0;
                var float_balance=0;
                for(var index in data){
                    var account=data[index];
                    all_balance+=account.n_balance;
                    if(account.subject==="01"){
                        //现金
                        cash_balance+=account.n_balance;
                    }else{
                        //在途
                        float_balance+=account.n_balance;
                    }
                }
                $("#all_amt").html(all_balance);
                $("#cash_amt").html(cash_balance);
                $("#float_amt").html(float_balance);
            },
            error: function () {
                removeOauthTokenFromStorage();
                alert("未授权");
                window.location.href="../login.html";
            }
        });
    }else{
        removeOauthTokenFromStorage();
        alert("未授权");
        window.location.href="../login.html";
    }
}

function getOauthTokenFromStorage() {
    return localStorage.getItem('token');
}

function removeOauthTokenFromStorage() {
    return localStorage.removeItem('token');
}