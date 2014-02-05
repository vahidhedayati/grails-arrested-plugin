<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Login</title>
    <g:javascript src="UserCtrl.js" />
</head>
<body data-ng-controller="UserCtrl">
<form>
    <table>
        <tbody>
        <tr>
            <td>Username:</td>
            <td><input type="text" name="username" data-ng-model="user.username" /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" data-ng-model="user.password" /></td>
        </tr>
        <tr>
            <td />
            <td><input type="submit" value="Sign in" /></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
