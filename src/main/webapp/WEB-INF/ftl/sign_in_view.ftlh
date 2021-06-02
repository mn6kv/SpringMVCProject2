<!doctype html>
<html lang="ru">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign In</title>
    <style>
        .error {
            color: maroon;
        }
    </style>
</head>
<body>
<h2><a href="?lang=ru">PY</a></h2>
<h2><a href="?lang=en">EN</a></h2>
<h1><@spring.message 'sign_in_view.login.title'/></h1>
<@spring.bind "userForm"/>
<form action="/signIn" method="post">
    <@spring.formInput "userForm.email" "placeholder=Email"/>
    <@spring.showErrors "<br>" "error"/>
    <@spring.formInput "userForm.password" "placeholder=Password"/>
    <@spring.showErrors "<br>" "error"/>
    <input type="submit">
</form>
</body>
</html>