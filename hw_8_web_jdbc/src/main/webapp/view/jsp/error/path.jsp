<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="errorMessage" scope="request" type="java.lang.String"/>
<jsp:useBean id="showMessage" scope="request" type="java.lang.Boolean"/>
<jsp:useBean id="pathVar" scope="request" type="java.lang.String"/>

<t:basepage>
    <jsp:attribute name="title"> <c:out value="Error" default="Unknown Patient"/></jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Error</jsp:attribute>
            <jsp:body>
                <c:if test="${showMessage}">

                    <main role="main" class="container">
                        <h1 class="mt-5">
                            <c:out value="${errorMessage}" default="Unknown error"/></h1>
                        <button type="button" class="btn btn-warning" onclick="backPath(${pathVar})">Back <-</button>
                        <script type="text/javascript" src = "${pageContext.request.contextPath}/js/error.js"></script>
                    </main>
                </c:if>
            </jsp:body>
        </t:genericpage>
    </jsp:body>
</t:basepage>
