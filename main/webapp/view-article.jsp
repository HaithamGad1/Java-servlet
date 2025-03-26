<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${article.title}</title>
</head>
<body>
<h1>${article.title}</h1>
<p>${article.content}</p>
<p>Category: ${article.category}</p>
<p>Views: ${article.views}</p>

<h2>Comments</h2>
<c:forEach var="comment" items="${comments}">
    <div>
        <p>${comment.commentText}</p>
        <p>By: ${comment.userId}</p>
    </div>
</c:forEach>

<form action="add-comment" method="post">
    <input type="hidden" name="articleId" value="${article.id}">
    <textarea name="commentText" required></textarea>
    <input type="submit" value="Add Comment">
</form>
</body>
</html>