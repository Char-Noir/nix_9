<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:basepage>
    <jsp:attribute name="title">Receptions</jsp:attribute>
    <jsp:body>
        <t:datatable>
            <jsp:attribute name="who">receptions-patient/${id}</jsp:attribute>
            <jsp:body>
                <c:choose>
                    <c:when test="${pageData.items.size()==0}">
                        <tr>
                            <td colspan="8">
                                <div class="text-center w-100 m-1">
                                    There are no receptions yet!
                                    <br>
                                    <button class="btn btn-success justify-content-sm-center add" data-who="receptions">
                                        Add
                                    </button>
                                </div>

                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${pageData.items}" var="reception">
                            <tr>
                                <th scope="row"><c:out value="${reception.id}"/></th>
                                <td><c:out value="${reception.dateTime}"/></td>
                                <td><c:out value="${reception.doctorItem.name}"/></td>

                                <td>
                                    <button id="${reception.id}" data-who="receptions" type="button" class="btn btn-primary details">details
                                    </button>
                                </td>
                                <td>
                                    <button id="${reception.id}" type="button" data-who="receptions" class="btn btn-warning update">update
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </jsp:body>
        </t:datatable>
    </jsp:body>
</t:basepage>
