<jsp:useBean id="pageData" scope="request"
             type="ua.com.alevel.hw_9_web_jpa.view.PageData<ua.com.alevel.hw_9_web_jpa.dto.impl.patient.PatientResponseDto>"/>
<jsp:useBean id="headerDataList" scope="request"
             type="java.util.List<ua.com.alevel.hw_9_web_jpa.controller.AbstractController.HeaderData>"/>
<jsp:useBean id="cardHeader" scope="request" type="java.lang.String"/>
<jsp:useBean id="createUrl" scope="request" type="java.lang.String"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="who" fragment="true" %>

<div class="row">
    <div class="col-12">
        <div class="card mb-2 mt-2">
            <div class="card-header px-5" style="display:flex; flex-direction:row; justify-content: space-between;">
                <b>
                    <c:out value="${cardHeader}" default="Column"/>
                </b>
                <button class="btn btn-success justify-content-sm-center add" data-who="<jsp:invoke fragment="who"/>">Add</button>
            </div>
            <div class="card-body">
                <table class="table table-sm table-hover">
                    <thead>
                    <tr>
                        <c:forEach var="columnHeader" items="${headerDataList}">
                            <th>
                                <c:if test="${columnHeader.sortable}">
                                    <c:if test="${columnHeader.active}">
                                        <span></span>
                                        <c:if test="${columnHeader.order.equals('desc')}">
                                            <i class="fa fa-sort-desc float-right sort" aria-hidden="true"
                                               data-sort="${columnHeader.sort}"
                                               data-order="desc"
                                               onclick="runSort(this.getAttribute('data-sort'), this.getAttribute('data-order'))">
                                                <c:out value="${columnHeader.headerName}" default="Column"/>
                                            </i>
                                        </c:if>
                                        <c:if test="${columnHeader.order.equals('asc')}">
                                            <i class="fa fa-sort-asc float-right sort" aria-hidden="true"
                                               data-sort="${columnHeader.sort}"
                                               data-order="asc"
                                               onclick="runSort(this.getAttribute('data-sort'), this.getAttribute('data-order'))">
                                                <c:out value="${columnHeader.headerName}" default="Column"/>
                                            </i>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${!columnHeader.active}">
                                        <span></span>
                                        <i class="fa fa-sort float-right sort" aria-hidden="true"
                                           data-sort="${columnHeader.sort}"
                                           data-order="${columnHeader.order}"
                                           onclick="runSort(this.getAttribute('data-sort'), this.getAttribute('data-order'))">
                                            <c:out value="${columnHeader.headerName}" default="Column"/>
                                        </i>
                                    </c:if>
                                </c:if>
                                <c:if test="${!columnHeader.sortable}">
                                    <span> <c:out value="${columnHeader.headerName}" default="Column"/></span>
                                </c:if>
                            </th>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody class="table-hover">
                    <jsp:doBody/>
                    </tbody>
                </table>
                <div class="d-flex justify-content-end align-items-center">
                    <div class="p-1">Showing <c:out value="${pageData.currentShowFromEntries.toString()}" default="-1"/>
                        to
                        <c:out value="${pageData.currentShowToEntries}" default="-1"/> of <c:out
                                value="${pageData.itemsSize}" default="-1"/> entries
                    </div>
                    <div class="p-1">
                        <!-- Default dropup button -->
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                <c:out value="${pageData.pageSize}" default="-1"/>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <c:forEach var="size" items="${pageData.pageSizeItems}">
                                    <li>
                                        <a class="dropdown-item" href="#" onclick="runPagination(1, ${size}, 0)">
                                            <c:out value="${size}" default="-1"/>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <!--====================== -->
                    </div>
                    <div class="p-1" style="margin-top: 1em !important;">
                        <ul class="pagination align-middle">
                            <li class="page-item ${pageData.showFirst ? '' : 'disabled'}">
                                <a class="page-link" href="#"
                                   onclick="runPagination(1, ${pageData.pageSize}, 0)" title="First Page"><i
                                        class="fa fa-fast-backward">First Page</i>
                                </a>
                            </li>
                            <li class="page-item ${pageData.showPrevious ? '' : 'disabled'}">
                                <a class="page-link" href="#"
                                   onclick="runPagination(${pageData.currentPage}, ${pageData.pageSize}, -1)"
                                   title="Previous Page"><i class="fa fa-backward">Previous Page</i></a>
                            </li>
                            <li class="page-item disabled">
                                <a class="page-link" href="">
                                    <c:out value="${pageData.currentPage}" default="-1"/>
                                </a>
                            </li>
                            <li class="page-item ${pageData.showNext ? '' : 'disabled'}">
                                <a class="page-link" href="#"
                                   onclick="runPagination(${pageData.currentPage}, ${pageData.pageSize}, 1)"
                                   title="Next Page"><i class="fa fa-forward">Next Page</i></a>
                            </li>
                            <li class="page-item ${pageData.showLast ? '' : 'disabled'}">
                                <a class="page-link" href="#"
                                   onclick="runPagination(${pageData.totalPageSize}, ${pageData.pageSize}, 0)"
                                   title="Last Page"><i class="fa fa-fast-forward">Last Page</i></a>
                            </li>
                        </ul>
                    </div>
                    <div class="p-1">
                        <a class="page-link btn btn-secondary" href="#"
                           onclick="runPagination(${pageData.currentPage}, ${pageData.pageSize}, 0)"
                           title="Refresh Page"><i class="fa fa-refresh">Refresh Page</i></a>
                    </div>
                    <div class="p-1">
                        <a class="page-link btn btn-secondary" href="#" onclick="runPagination(1, 10, 0)"
                           title="Reset Page"><i class="fa fa-trash">Reset Page</i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<form method="POST" action="${createUrl}" id="personalSearch">
    <input type="submit"
           id="personalSearchSubmit"
           style="visibility: hidden"
    />
</form>

<span data-sort="${pageData.sort}"
      data-order="${pageData.order}"
      data-page="${pageData.currentPage}"
      data-size="${pageData.pageSize}"
      id="pageData">
    </span>
