<%--
  Created by IntelliJ IDEA.
  User: wufeifei
  Date: 2016/12/16
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product Form</title>
</head>
<body>
<div id="global">
    <form action="/product_save" method="post">
        <fieldset>
            <legend>Add a product</legend>
            <p>
                <label for="name">Product Name:</label>
                <input type="text" id="name" name="name" tabindex="1">
            </p>
            <p>
                <label for="description">Product description:</label>
                <input type="text" id="description" name="description" tabindex="2">
            </p>
            <p>
                <label for="price">Product price:</label>
                <input type="text" id="price" name="price" tabindex="3">
            </p>
            <p>
                <input id="reset" type="reset" tabindex="4">Product price:</input>
                <input type="submit" id="submit" value="add a product" tabindex="5">
            </p>
        </fieldset>
    </form>
</div>
</body>
</html>
