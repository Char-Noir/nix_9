<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:basepage>
    <jsp:attribute name="title">Add Doctor</jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Add doctor</jsp:attribute>
            <jsp:body>
                <div class="row">
                    <div class="col-12">
                        <div class="card mt-2">
                            <div class="card-header">
                                <h4>New Doctor</h4>
                            </div>
                            <div class="card-body">
                                <form id="login" action="/doctors/create" method="post">
                                    <label>Doctors full name: </label>
                                    <input type="text" name="name">
                                    <br/>
                                    <label>Assessment of certification: </label>
                                    <input type="number" value="0" max="10" min="0" step="0.01" name="assessmentOfCertification">
                                    <br/>
                                    <label>Doctors note: </label>
                                    <input type="text" name="note">
                                    <br/>
                                    <label>Date Of Certification: </label>
                                    <input type="date" name="dateOfCertification">
                                    <br/>
                                    <label>Login: </label>
                                    <input type="text" name="login">
                                    <br/>
                                    <label>Password: </label>
                                    <input type="text" name="password">
                                    <br/>
                                    <select name="category" id="cat">
                                        <c:forEach var="category" items="${categories}">
                                            <option value="${category.name()}">
                                                <c:out value="${category.value}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <br/>
                                    <select name="specialization" id="spec">
                                        <c:forEach var="specialization" items="${specializations}">
                                            <option value="${specialization.name()}">
                                                <c:out value="${specialization.value}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <button>Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </jsp:body>
        </t:genericpage>
    </jsp:body>
</t:basepage>
