<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SimpleBlog - Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">SimpleBlog</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp"><i class="bi bi-box-arrow-in-right"></i> Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="register.jsp"><i class="bi bi-person-plus"></i> Register</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row mb-4">
        <div class="col-12 text-center">
            <h1 class="display-4">Welcome to SimpleBlog</h1>
            <p class="lead">Read and share your thoughts with the world</p>
        </div>
    </div>

    <div class="row mb-4">
        <div class="col-md-6 offset-md-3">
            <form action="search" method="get" class="d-flex">
                <input class="form-control me-2" type="search" name="query" placeholder="Search articles...">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-search"></i>
                </button>
            </form>
        </div>
    </div>

    <h2 class="mb-4"><i class="bi bi-newspaper"></i> Latest Articles</h2>

    <c:choose>
        <c:when test="${empty articles}">
            <div class="alert alert-info">
                No articles found. Be the first to create one!
            </div>
        </c:when>
        <c:otherwise>
            <div class="row row-cols-1 row-cols-md-2 g-4">
                <c:forEach var="article" items="${articles}">
                    <div class="col">
                        <div class="card article-card h-100">
                            <div class="card-body">
                                <h5 class="card-title">${article.title}</h5>
                                <p class="card-text text-truncate">${article.content}</p>
                                <div class="article-meta mb-3">
                                    <span class="badge bg-secondary">${article.category}</span>
                                    <span class="ms-2"><i class="bi bi-eye"></i> ${article.views}</span>
                                </div>
                                <a href="view-article.jsp?id=${article.id}" class="btn btn-primary">
                                    Read More <i class="bi bi-arrow-right"></i>
                                </a>
                            </div>
                            <div class="card-footer text-muted">
                                <small>Posted on ${article.createdAt}</small>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<footer class="footer mt-5 py-3 bg-light">
    <div class="container text-center">
        <span class="text-muted">© 2023 SimpleBlog. All rights reserved.</span>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>