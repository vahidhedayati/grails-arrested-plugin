<html>
<head>
    <meta name="layout" content="main"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Login</title>
    <g:javascript src="userCtrl.js" />
</head>
<body>
<form data-ng-controller="UserCtrl">
    <table>
        <tbody>
        <tr>
            <td>Username:</td>
            <td><input type="text" name="username" data-ng-model="user.username" /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" data-ng-model="user.passwordHash" /></td>
        </tr>
        <tr>
            <td />
            <td><input type="submit" value="Sign in" data-ng-click="login()"/></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
