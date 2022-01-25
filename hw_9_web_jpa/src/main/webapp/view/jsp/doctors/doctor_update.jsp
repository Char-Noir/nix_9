<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:basepage>
    <jsp:attribute name="title">${doctor.name}</jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Update doctor</jsp:attribute>
            <jsp:body>
                <div class="row">
                    <div class="col-12">
                        <div class="card mt-2">
                            <div class="card-header">
                                <h4>Update Doctor</h4>
                            </div>
                            <div class="card-body">
                                <form id="login" action="/doctors/updated/${doctor.id}" method="post">
                                    <label>Doctors full name: </label>
                                    <input type="text" name="name" value="${doctor.name}">
                                    <br/>
                                    <label>Assessment of certification: </label>
                                    <input type="number" value="${doctor.assessmentOfCertification}" max="10" min="0"
                                           step="0.01" name="assessmentOfCertification">
                                    <br/>
                                    <label>Doctors note: </label>
                                    <input type="text" name="note" value="${doctor.note}">
                                    <br/>
                                    <label>Date Of Certification: </label>
                                    <input type="date" name="dateOfCertification" value="${doctor.dateOfCertification}">
                                    <br/>
                                    <label>Login: </label>
                                    <input type="text" name="login" value="${doctor.login}">
                                    <br/>
                                    <label>Password: </label>
                                    <input type="text" name="password" value="${doctor.password}">
                                    <br/>
                                    <select name="category" id="cat" value="${doctor.category}">
                                        <c:forEach var="category" items="${categories}">
                                            <option value="${category.name()}" ${(doctor.category==category)?"selected":""}>
                                                <c:out value="${category.value}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <br/>
                                    <select name="specialization" id="spec" value="${doctor.specialization}">
                                        <c:forEach var="specialization" items="${specializations}">
                                            <option value="${specialization.name()}" ${(doctor.specialization==specialization)?"selected":""}>
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
