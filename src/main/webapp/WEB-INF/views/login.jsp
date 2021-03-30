<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Log in - RecipeApp</title>
    <%@ include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
</head>
<body>
<div class="container">
    <%@ include file="../segments/header.jspf" %>>

    <form class="user-form" action="j_security_check" method="post" >
        <h2 class="user-from-title">Log in on RecipeApp</h2>
        <input name="j_username" placeholder="Username" required>
        <input name="j_password" placeholder="Password" type="password" required>
        <button class="user-form-button">Log in</button>
        <p>Don't Have an Account? <a href="${pageContext.request.contextPath}/signup">Register</a></p>
    </form>

    <%@ include file="/WEB-INF/segments/github-button.jspf"%>
    <%@ include file="../segments/footer.jspf"%>
</div>
</body>
</html>
