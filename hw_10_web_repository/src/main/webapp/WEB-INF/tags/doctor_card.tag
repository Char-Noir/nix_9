<jsp:useBean id="doctor" scope="request" type="ua.com.alevel.hw_10_web_repository.dto.impl.doctor.DoctorFullResponseDto"/>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-8">
    <c:choose>
        <c:when test="${doctor.id==-1}">
            No Doctor Here
        </c:when>
        <c:otherwise>
            <div class="card mb-3 border ${doctor.status.deleted ? 'border-danger' :''}">
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">Full Name</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <c:out value="${doctor.name}" default="Unknown Doctor"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">Assessment Of Certification</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <c:out value="${doctor.assessmentOfCertification}" default="-1"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">Note</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <c:out value="${doctor.note}" default="Unknown Doctor"/>
                        </div>
                    </div>
                    <hr>
                    <div class='row ${doctor.isOutdated?"bg-danger bg-gradient":""}'>
                        <div class="col-sm-3">
                            <h6 class="mb-0">Date Of Certification</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <c:out value="${doctor.dateOfCertification}" default="1970-01-01"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">Login</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <c:out value="${doctor.login}" default="doctor-1"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">Status</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <c:out value="${doctor.status.name()}" default="Unknown"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">Category</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <c:out value="${doctor.category.value}" default="Unknown"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">Specialization</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <c:out value="${doctor.specialization.value}" default="Unknown"/>
                        </div>
                    </div>
                    <hr>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
