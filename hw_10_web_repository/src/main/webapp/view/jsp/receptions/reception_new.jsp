<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:basepage>
    <jsp:attribute name="title">Add Reception</jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Add Reception</jsp:attribute>
            <jsp:body>
                <div class="row">
                    <div class="col-12">
                        <div class="card mt-2">
                            <div class="card-header">
                                <h4>New Reception</h4>
                            </div>
                            <div class="card-body">
                                <form id="login" action="/receptions/create" method="post">
                                    <label>Date and time: </label>
                                    <input type="datetime-local" name="datetime">
                                    <br/>

                                    <label>Review: </label>
                                    <input type="text" name="reviewComment">
                                    <br/>
                                    <label>Price: </label>
                                    <input type="number" value="0" max="1000" min="0" step="0.01"
                                           name="price">
                                    <br/>

                                    <select name="doctor" id="doc">
                                        <c:forEach var="doctor" items="${doctors.items}">
                                            <c:choose>
                                                <c:when test="${doctor.status.isDeleted() || doctor.isOutdated}"></c:when>
                                                <c:otherwise>
                                                    <option value="${doctor.id}">
                                                        <c:out value="${doctor.name}"/>
                                                    </option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                    <br/>
                                    <select name="patient" id="pat">
                                        <c:forEach var="patient" items="${patients.items}">
                                            <c:choose>
                                                <c:when test="${patient.status.isDeleted()}"></c:when>
                                                <c:otherwise>
                                                    <option value="${patient.id}">
                                                        <c:out value="${patient.name}"/>
                                                    </option>
                                                </c:otherwise>
                                            </c:choose>
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
