<jsp:useBean id="patient" scope="request" type="ua.com.alevel.hw_9_web_jpa.dto.impl.patient.PatientFullResponseDto"/>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="titleInner" fragment="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-8">
    <div class="card mb-3 border ${patient.status.deleted ? 'border-danger' :''}">
        <div class="card-body">
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Full Name</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    <c:out value="${patient.name}" default="Unknown Patient"/>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Status</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    <c:out value="${patient.status.name()}" default="Unknown Patient"/>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Date of birth</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    <c:out value="${patient.dateOfBirth}" default="1000-01-01"/>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Phone</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    <c:out value="${patient.phoneNumber}" default="+125417"/>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Login</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    <c:out value="${patient.login}" default="patient_0"/>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">User documents</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    <c:out value="${patient.userDocuments}" default="No"/>
                </div>
            </div>
            <hr>
        </div>
    </div>
</div>
