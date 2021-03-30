
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dodaj nowy przepis - RecipeBook</title>
    <%@ include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/add-recipe-form.css">
</head>

<body>

<div class="container">
    <%@ include file="../segments/header.jspf" %>

    <form action="${pageContext.request.contextPath}/recipe/add" method="post" class="recipe-form">
        <h2 class="recipe-form-title">ADD NEW RECIPE</h2>
        <input name="title" placeholder="Title" required>
        <input name="photo-url" placeholder="Photo URL" type="url" required>
        <input type="number" name="prep-time" placeholder="Preparing time in minutes (e.g. 20)" required min="0" max="361">
        <select name="categoryId">
            <c:forEach var="category" items="${requestScope.categories}">
                <option name="${category.name}" value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        <textarea name="ingredients" placeholder="Ingredients"></textarea>
        <textarea name="description" placeholder="Description"></textarea>

        <button class="recipe-form-button">Add</button>
    </form>

    <%@ include file="/WEB-INF/segments/github-button.jspf"%>
    <%@ include file="../segments/footer.jspf" %>
</div>
</body>
</html>
