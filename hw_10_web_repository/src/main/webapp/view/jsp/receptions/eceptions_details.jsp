<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:basepage>
    <jsp:attribute name="title"> <c:out value="${reception.doctor.name}" default="Unknown Doctor"/>-<c:out value="${reception.patient.name}" default="Unknown Doctor"/></jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Reception</jsp:attribute>
            <jsp:body>
                <div class="d-flex align-items-start m-4 ">
                    <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist"
                         aria-orientation="vertical">
                        <button class="nav-link active" id="v-pills-profile-tab" data-bs-toggle="pill"
                                data-bs-target="#v-pills-profile" type="button" role="tab"
                                aria-controls="v-pills-profile" aria-selected="true">Description
                        </button>
                        <button class="nav-link" id="v-pills-receptions-tab" data-bs-toggle="pill"
                                data-bs-target="#v-pills-receptions" type="button" role="tab"
                                aria-controls="v-pills-receptions" aria-selected="false">Doctor
                        </button>
                        <button class="nav-link" id="v-pills-reception-tab" data-bs-toggle="pill"
                                data-bs-target="#v-pills-reception" type="button" role="tab"
                                aria-controls="v-pills-reception" aria-selected="false">Patient
                        </button>
                        <button class="nav-link" id="v-pills-management-tab" data-bs-toggle="pill"
                                data-bs-target="#v-pills-management" type="button" role="tab"
                                aria-controls="v-pills-management" aria-selected="false">Management
                        </button>
                    </div>
                    <div class="tab-content border-primary mh-100 w-100 m-1 h-100" id="v-pills-tabContent">
                        <div class="tab-pane fade show active" id="v-pills-profile" role="tabpanel"
                             aria-labelledby="v-pills-profile-tab">
                            <t:reception_card>

                            </t:reception_card>
                        </div>
                        <div class="tab-pane fade h-100 " id="v-pills-receptions" role="tabpanel"
                             aria-labelledby="v-pills-receptions-tab">
                            <t:doctor_card>

                            </t:doctor_card>
                        </div>
                        <div class="tab-pane fade h-100 " id="v-pills-reception" role="tabpanel"
                             aria-labelledby="v-pills-reception-tab">
                            <t:patient_card>

                            </t:patient_card>
                        </div>
                        <div class="tab-pane fade" id="v-pills-management" role="tabpanel"
                             aria-labelledby="v-pills-management-tab">

                            <div>
                                <a href="../update/${reception.id}" class="btn btn-warning">Update</a>
                                <button type="button" id="${reception.id}" data-bs-toggle="modal"
                                        data-bs-target="#exampleModal"
                                        data-bs-whatever="${reception.id}"
                                        class="btn btn-danger ">
                                    Delete
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Delete Reception</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form method="post" action="delete/-1" id="form">
                                    <div id="who" data-who="receptions"></div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                <button type="button" onclick="$('#form').submit()" class="btn btn-primary">Delete
                                </button>
                            </div>
                        </div>
                    </div>
                    <script src="${pageContext.request.contextPath}/js/one.js"></script>
                </div>
            </jsp:body>
        </t:genericpage>
    </jsp:body>
</t:basepage>
