<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@ include file="/WEB-INF/segments/stylesheets.jspf" %>
    <meta charset="UTF-8">
    <title>RecipeApp</title>
</head>
<body>
<div class="container">
    <%@ include file="/WEB-INF/segments/header.jspf" %>

    <aside class="categories">
        <ul>
            <c:forEach var="category" items="${applicationScope.categories}">
                <li><a href="${pageContext.request.contextPath.concat('/category?id=').concat(category.id)}">${category.name}</a></li>
            </c:forEach>
        </ul>
    </aside>

    <main>
        <section class="searching">
            <%@ include file="/WEB-INF/segments/searching.jspf" %>
        </section>
        <section class="recipes">
            <%@ include file="/WEB-INF/segments/recipe-list.jspf" %>
        </section>
    </main>

    <%@ include file="/WEB-INF/segments/add-recipe-button.jspf"%>
    <%@ include file="/WEB-INF/segments/github-button.jspf"%>
    <%@ include file="/WEB-INF/segments/footer.jspf" %>
</div>
</body>
