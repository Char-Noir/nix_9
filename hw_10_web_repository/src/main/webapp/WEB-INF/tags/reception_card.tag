<jsp:useBean id="reception" scope="request"
             type="ua.com.alevel.hw_10_web_repository.dto.impl.reception.ReceptionFullResponseDto"/>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-8">

    <div class="card mb-3 border ">
        <div class="card-body">
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Date and Time</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    <c:out value="${reception.dateTime}" default="Unknown Date"/>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Price</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    <c:out value="${reception.receptionPrice}" default="-1"/>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Review</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    <c:out value="${reception.reviewComment}" default="Unknown reception"/>
                </div>
            </div>
            <hr>
            <div class='row'>
                <div class="col-sm-3">
                    <h6 class="mb-0">Doctor</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    <a href="/doctors/details/${reception.doctor.id}">
                        <c:out value="${reception.doctor.name}" default="Unknown Doctor"/>
                    </a>
                </div>
            </div>
            <hr>
            <div class='row'>
                <div class="col-sm-3">
                    <h6 class="mb-0">Patient</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    <a href="/patients/details/${reception.patient.id}">
                        <c:out value="${reception.patient.name}" default="Unknown Patient?<"/>
                    </a>
                </div>
            </div>
            <hr>
        </div>
    </div>

</div>
