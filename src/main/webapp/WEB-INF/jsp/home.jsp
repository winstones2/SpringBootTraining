<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
    <title>Home</title>
    <body>
        <div>
            <p>name: "${requestScope.count}"</p>
        </div>
        <div>
            value=${count}
        </div>

        <form action="add" method="post">
            name：<input type="text" name="name">
            id：<input type="text" name="id">
            <input type="submit" value="点击">
        </form>
    </body>
</html>