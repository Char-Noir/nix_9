<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:basepage>
    <jsp:attribute name="title"> <c:out value="${patient.name}" default="Unknown Patient"/></jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Patient description</jsp:attribute>
            <jsp:body>
                <div class="d-flex align-items-start m-4 ">
                    <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist"
                         aria-orientation="vertical">
                        <button class="nav-link active" id="v-pills-profile-tab" data-bs-toggle="pill"
                                data-bs-target="#v-pills-profile" type="button" role="tab"
                                aria-controls="v-pills-profile" aria-selected="true">Profile
                        </button>
                        <button class="nav-link" id="v-pills-receptions-tab" data-bs-toggle="pill"
                                data-bs-target="#v-pills-receptions" type="button" role="tab"
                                aria-controls="v-pills-receptions" aria-selected="false">Receptions
                        </button>
                        <button class="nav-link" id="v-pills-management-tab" data-bs-toggle="pill"
                                data-bs-target="#v-pills-management" type="button" role="tab"
                                aria-controls="v-pills-management" aria-selected="false">Management
                        </button>
                        <button class="nav-link" id="v-pills-doctor-tab" data-bs-toggle="pill"
                                data-bs-target="#v-pills-doctor" type="button" role="tab" aria-controls="v-pills-doctor"
                                aria-selected="false">Doctor
                        </button>
                    </div>
                    <div class="tab-content border-primary mh-100 w-100 m-1 h-100" id="v-pills-tabContent">
                        <div class="tab-pane fade show active" id="v-pills-profile" role="tabpanel"
                             aria-labelledby="v-pills-profile-tab">
                            <t:patient_card>

                            </t:patient_card>
                        </div>
                        <div class="tab-pane fade h-100 " id="v-pills-receptions" role="tabpanel"
                             aria-labelledby="v-pills-receptions-tab">
                            <iframe src="../../receptions/patient_receptions/${patient.id}" class="w-100 "
                                    style="height:75vh">

                            </iframe>
                        </div>
                        <div class="tab-pane fade" id="v-pills-management" role="tabpanel"
                             aria-labelledby="v-pills-management-tab">
                            <c:choose>
                                <c:when test="${(patient.status.deleted)}">
                                    <p>No options available</p>
                                </c:when>
                                <c:otherwise>
                                    <div>
                                        <a href="../update/${patient.id}" class="btn btn-warning">Update</a>
                                        <button type="button" id="${patient.id}" data-bs-toggle="modal"
                                                data-bs-target="#exampleModal"
                                                data-bs-whatever="${patient.id}"
                                                class="btn btn-danger">
                                            Delete
                                        </button>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="tab-pane fade" id="v-pills-doctor" role="tabpanel"
                             aria-labelledby="v-pills-doctor-tab">
                            <t:doctor_card>

                            </t:doctor_card>
                        </div>
                    </div>
                </div>
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
                                <form method="post" action="/patients/delete/${patient.id}" id="form">
                                    <div id="who" data-who="patients"></div>
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
