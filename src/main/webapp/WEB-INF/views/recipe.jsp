
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>RecipeBook</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/add-recipe-form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/recipe.css">
    <%@ include file="/WEB-INF/segments/stylesheets.jspf" %>
</head>
<body>
<div class="container">
    <%@ include file="/WEB-INF/segments/header.jspf" %>

    <div class="single-recipe">

        <h1 class="recipe-header1" >${requestScope.recipe.title}</h1>

        <a>
            <img class="single-recipe-image" src="${requestScope.recipe.url}">
        </a>

        <strong class="vote">
            <a href="${pageContext.request.contextPath.concat('/recipe/vote?id=').concat(recipe.id)
                .concat('&type=UP')}" class="recipe-link upvote">
                <i class="fas fa-arrow-alt-circle-up recipe-upvote"></i>
            </a>
            <p class="recipe-votes">${recipe.voteCount}</p>
            <a href="${pageContext.request.contextPath.concat('/recipe/vote?id=').concat(recipe.id)
                .concat('&type=DOWN')}" class="recipe-link downvote">
                <i class="fas fa-arrow-alt-circle-down recipe-downvote"></i>
            </a>
        </strong>
        <p class="recipe-details">Added by: ${recipe.author} ${recipe.dateAdded.format(DateTimeFormatter
                .ofPattern("dd/MM/yyyy HH:mm"))}</p>

        <h2><i class="far fa-clock"></i> Preparation time: ${requestScope.recipe.prepTimeInMinutes} minutes.</h2>
        <h2>Ingredients:</h2>
        <a>
            ${requestScope.recipe.ingredients}
        </a>
        <h2>How to make ${requestScope.recipe.title}</h2>
        <a>
            ${requestScope.recipe.description}
        </a>
    </div>


    <form action="${pageContext.request.contextPath}/recipe/comment" method="post"  class="recipe-form">

        <input name="recipeId" type="hidden" value="${requestScope.recipe.id}">
        <textarea name="comment" placeholder="Add comment..." required></textarea>
        <button  class="recipe-form-button">Add</button>

    </form>
    <section class="comments">
    <h2>Comments:</h2>
        <c:forEach items="${requestScope.comments}" var="comment">
        <div class="comment">
            <p class="comment-details" >
                Added by: ${comment.username} ${comment.dateAdded.format(DateTimeFormatter
                    .ofPattern("dd/MM/yyyy HH:mm"))}
            </p>
            <a class="comment-contents">
                ${comment.content}
            </a>
        </div>
        </c:forEach>

    </section>


    <%@ include file="/WEB-INF/segments/add-recipe-button.jspf"%>
    <%@ include file="/WEB-INF/segments/github-button.jspf"%>
    <%@ include file="/WEB-INF/segments/footer.jspf" %>
</div>
<div>

</div>
</body>
</html>
