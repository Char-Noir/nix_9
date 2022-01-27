<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:basepage>
    <jsp:attribute name="title">Doctors</jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Doctors</jsp:attribute>
            <jsp:body>
                <t:datatable>
                    <jsp:attribute name="who">doctors</jsp:attribute>
                    <jsp:body>
                        <c:choose>
                            <c:when test="${pageData.items.size()==0}">
                                <tr>
                                    <td colspan="8">
                                        <div class="text-center w-100 m-1">
                                            There are no doctors yet!
                                            <br>
                                            <button type="button" class="btn btn-success m-1" onclick="add('doctors')">
                                                Add
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>

                                <c:forEach items="${pageData.items}" var="doctor">
                                    <tr class="${doctor.status.deleted ? 'table-danger' :''}">
                                        <th scope="row"><c:out value="${doctor.id}"/></th>
                                        <td><c:out value="${doctor.name}"/></td>
                                        <td><c:out value="${doctor.dateOfCertification}"/></td>
                                        <td><c:out value="${doctor.category.value}"/></td>
                                        <td><c:out value="${doctor.specialization.value}"/></td>
                                        <td><c:out value="${doctor.status}"/></td>
                                        <td><c:out value="${doctor.recCount}"/></td>
                                        <c:choose>
                                            <c:when test="${doctor.status.deleted}">
                                                <td>
                                                    <button type="button" class="btn btn-primary details"
                                                            data-who="doctors"
                                                            id="${doctor.id}">details
                                                    </button>
                                                </td>
                                                <td>
                                                    <button onclick="deleted()" type="button" disabled
                                                            class="btn btn-danger">
                                                        delete
                                                    </button>
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>
                                                    <button type="button" class="btn btn-primary details"
                                                            data-who="doctors"
                                                            id="${doctor.id}">details
                                                    </button>
                                                </td>
                                                <td>
                                                    <button type="button" id="${doctor.id}" data-bs-toggle="modal"
                                                            data-bs-target="#exampleModal"
                                                            data-bs-whatever="${doctor.id}"
                                                            class="btn btn-danger delete">
                                                        delete
                                                    </button>
                                                </td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </jsp:body>
                </t:datatable>

                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Delete doctor</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form method="post" action="/delete/-1" id="form">
                                    <div id="who" data-who="../doctors"></div>
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
