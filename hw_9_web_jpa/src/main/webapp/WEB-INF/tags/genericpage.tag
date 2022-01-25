<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="titleInner" fragment="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<nav id="pageheader" class="navbar navbar-expand-md navbar-dark bg-dark">
    <a class="navbar-brand mx-4" href="#" onclick="document.location.reload();">
        <jsp:invoke fragment="titleInner"/>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04"
            aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-md-center">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/index/">Home</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/patients/">Patients</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/doctors/">Doctors</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/receptions/">Receptions</a>
            </li>
        </ul>
    </div>
</nav>
<div id="body">
    <jsp:doBody/>
</div>
<footer class="footer">
    <div class="container">
        <span class="text-muted">© 2021-2022</span>
    </div>
</footer>
