<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:basepage>
    <jsp:attribute name="title">Patients</jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Patients</jsp:attribute>
            <jsp:body>
                <t:datatable>
                    <jsp:attribute name="who">patients</jsp:attribute>
                    <jsp:body>
                        <c:if test="${pageData.items.size()==0}">
                            <tr>
                                <td colspan="8">
                                    <div class="text-center w-100 m-1">
                                        There are no patients yet!
                                        <br>
                                        <button type="button" class="btn btn-success m-1" onclick="add('patients')">
                                            Add
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                        <c:forEach items="${pageData.items}" var="patient">
                            <tr class="${patient.status.deleted ? 'table-danger' :''}">
                                <th scope="row"><c:out value="${patient.id}"/></th>
                                <td><c:out value="${patient.name}"/></td>
                                <td><c:out value="${patient.dateOfBirth}"/></td>
                                <td><c:out value="${patient.phoneNumber}"/></td>
                                <td><c:out value="${patient.status}"/></td>
                                <td><c:out value="${patient.recCount}"/></td>
                                <c:choose>
                                    <c:when test="${patient.status.deleted}">
                                        <td>
                                            <button type="button" class="btn btn-primary details" data-who="patients"
                                                    id="${patient.id}">details
                                            </button>
                                        </td>
                                        <td>
                                            <button onclick="deleted()" type="button" disabled class="btn btn-danger">
                                                delete
                                            </button>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            <button type="button" class="btn btn-primary details" data-who="patients"
                                                    id="${patient.id}">details
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button" id="${patient.id}" data-bs-toggle="modal"
                                                    data-bs-target="#exampleModal"
                                                    data-bs-whatever="${patient.id}"
                                                    class="btn btn-danger delete">
                                                delete
                                            </button>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </jsp:body>
                </t:datatable>

                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Delete patient</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form method="post" action="delete/-1" id="form">
                                    <div id="who" data-who="../patients"></div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                <button type="button" onclick="$('#form').submit()" class="btn btn-primary">Delete
                                </button>
                            </div>
                        </div>
                    </div>
                    <script src="${pageContext.request.contextPath}/js/list.js"></script>
                </div>
            </jsp:body>
        </t:genericpage>
    </jsp:body>
</t:basepage>
