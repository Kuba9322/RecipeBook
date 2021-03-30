<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>RecipeApp</title>
    <%@ include file="/WEB-INF/segments/stylesheets.jspf" %>
</head>
<body>
<div class="container">
    <%@ include file="/WEB-INF/segments/header.jspf" %>

    <main>
        <section class="searching">
            <%@ include file="/WEB-INF/segments/searching.jspf" %>
        </section>

        <c:if test="${not empty requestScope.category.name}">
        <h1 class="category-header1">${requestScope.category.name}</h1>
        </c:if>

        <section class="recipes">
            <%@ include file="/WEB-INF/segments/recipe-list.jspf" %>
        </section>
    </main>

    <%@ include file="/WEB-INF/segments/add-recipe-button.jspf"%>
    <%@ include file="/WEB-INF/segments/github-button.jspf"%>
    <%@ include file="/WEB-INF/segments/footer.jspf" %>
</div>
</body>
