<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:basepage>
    <jsp:attribute name="title">Update reception</jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Update reception</jsp:attribute>
            <jsp:body>
                <div class="row">
                    <div class="col-12">
                        <div class="card mt-2">
                            <div class="card-header">
                                <h4>New Reception</h4>
                            </div>
                            <div class="card-body">
                                <form id="login" action="/receptions/updated/${reception.id}" method="post">
                                    <label>Date and time: </label>
                                    <input type="datetime-local" name="datetime" value="${reception.dateTime}">
                                    <br/>
                                    <label>Review: </label>
                                    <input type="text" name="reviewComment" value="${reception.reviewComment}">
                                    <br/>
                                    <label>Price: </label>
                                    <input type="number" value="0" max="1000" min="0" step="0.01"
                                           value="${reception.receptionPrice}"
                                           name="price">
                                    <br/>
                                    <input type="hidden" name="doctor" value="${reception.doctor.id}">
                                    <br/>
                                    <input type="hidden" name="patient" value="${reception.patient.id}">
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
