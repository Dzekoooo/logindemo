function format() {
    var x = document.getElementById("uname");
    var y = document.getElementById("upass");
    var z = document.getElementById("checkpass");
    var msg = "";
    if (x.value.length < 3 || x.value.length > 8){
        msg = "用户名长度该位3-8之间！";
        alert(msg);
        return false;
    } else if (x.value.charCodeAt(0) < 48 || x.value.charCodeAt(0)<=57){
        msg = "用户名首字符不能是数字 ！ .";
        alert(msg);
        return false;
    } else for (var i = 0; i < x.value.length; i++){
        if((x.value.charCodeAt(i)<48||x.value.charCodeAt(i)>57)&&
            (x.value.charCodeAt(i)<97||x.value.charCodeAt(i)>122)){
            msg ="只能包含字母或数字！.";
            alert(msg);
            return false;
        }
    }
    if(y.value.length<3||y.value.length > 8){
        msg ="密码长度该是:3-8";
        alert(msg);
        return false;
    }
    if(y.value!=z.value){
        msg="输入密码不一致。" ;
        alert(msg);
        return false;
    }else if(z.value==""){
        msg="未确认密码。";
        alert(msg);
        return false;
    }
}
