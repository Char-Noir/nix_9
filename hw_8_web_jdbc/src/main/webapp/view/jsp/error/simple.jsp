<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="errorMessage" scope="request" type="java.lang.String"/>
<jsp:useBean id="showMessage" scope="request" type="java.lang.Boolean"/>

<t:basepage>
    <jsp:attribute name="title"> <c:out value="Error" default="Unknown Patient"/></jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Error</jsp:attribute>
            <jsp:body>

                    <main role="main" class="container">
                        <h1 class="mt-5">Sorry, but happened error. If you sure that you are on right page, contact <a href="mailto:dburyak2002@gmail.com">me</a>!</h1>
                        <button type="button" class="btn btn-warning" onclick="back()">Back <-</button>
                    </main>

            </jsp:body>
        </t:genericpage>
    </jsp:body>
</t:basepage>
