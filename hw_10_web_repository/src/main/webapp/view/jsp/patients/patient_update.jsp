<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:basepage>
    <jsp:attribute name="title">${patient.name}</jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Update patient</jsp:attribute>
            <jsp:body>
                <div class="row">
                    <div class="col-12">
                        <div class="card mt-2">
                            <div class="card-header">
                                <h4>Update Patient</h4>
                            </div>
                            <div class="card-body">
                                <form id="login" action="/patients/updated/${patient.id}" method="post">
                                    <label>
                                        Patient full name:
                                        <input type="text" name="name" value="${patient.name}">
                                    </label>
                                    <br/>
                                    <label>
                                        Date Of Birth:
                                        <input type="date" name="dateOfBirth" value="${patient.dateOfBirth}">
                                    </label>
                                    <br/>
                                    <label>
                                        Patient phone number:
                                        <input type="text" name="phoneNumber" value="${patient.phoneNumber}">
                                    </label>
                                    <br/>
                                    <label>
                                        Patient documents:
                                        <input type="text" name="userDocuments" value="${patient.userDocuments}">
                                    </label>
                                    <br/>
                                    <label>
                                        Patient login:
                                        <input type="text" name="login" value="${patient.login}">
                                    </label>
                                    <br/>
                                    <label>
                                        Patient password:
                                        <input type="text" name="hashPassword" value="${patient.password}">
                                    </label>
                                    <br/>
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
