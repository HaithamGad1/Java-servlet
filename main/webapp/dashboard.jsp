<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
<!-- شريط التنقل -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
    <div class="container">
        <a class="navbar-brand" href="#">SimpleBlog</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="dashboard">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="create-article.jsp">New Article</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <span class="nav-link">Welcome, ${fn:escapeXml(user.username)}</span>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- المحتوى الرئيسي -->
<div class="container">
    <div class="row mb-4">
        <div class="col-12">
            <h1 class="display-4">Your Articles</h1>
            <a href="create-article.jsp" class="btn btn-success">
                <i class="bi bi-plus-circle"></i> Create New Article
            </a>
        </div>
    </div>

    <c:choose>
        <c:when test="${empty articles}">
            <div class="alert alert-info">
                No articles found. Create your first article!
            </div>
        </c:when>
        <c:otherwise>
            <div class="row">
                <c:forEach items="${articles}" var="article">
                    <div class="col-md-6 col-lg-4">
                        <div class="card article-card">
                            <div class="card-body">
                                <h5 class="card-title">${fn:escapeXml(article.title)}</h5>
                                <p class="card-text text-truncate">${fn:escapeXml(article.content)}</p>

                                <div class="article-meta mb-3">
                                    <span class="badge bg-secondary">${fn:escapeXml(article.category)}</span>
                                    <span class="ms-2"><i class="bi bi-eye"></i> ${article.views}</span>
                                    <span class="ms-2">
                                            <i class="bi bi-calendar"></i>
                                            <fmt:formatDate value="${article.createdAt}" pattern="MMM dd, yyyy"/>
                                        </span>
                                </div>

                                <div class="d-flex justify-content-between">
                                    <a href="edit-article?id=${article.id}" class="btn btn-sm btn-primary btn-action">
                                        <i class="bi bi-pencil"></i> Edit
                                    </a>
                                    <a href="delete-article?id=${article.id}"
                                       class="btn btn-sm btn-danger btn-action"
                                       onclick="return confirm('Are you sure you want to delete this article?')">
                                        <i class="bi bi-trash"></i> Delete
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<!-- التذييل -->
<footer class="footer mt-5">
    <div class="container text-center">
        <span class="text-muted">© 2023 SimpleBlog. All rights reserved.</span>
    </div>
</footer>

<!-- الأكواد البرمجية -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</body>
</html>