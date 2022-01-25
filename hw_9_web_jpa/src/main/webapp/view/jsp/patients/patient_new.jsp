<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:basepage>
    <jsp:attribute name="title">Add Patient</jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Add patient</jsp:attribute>
            <jsp:body>
                <div class="row">
                    <div class="col-12">
                        <div class="card mt-2">
                            <div class="card-header">
                                <h4>New Patient</h4>
                            </div>
                            <div class="card-body">
                                <form id="login" action="/patients/create" method="post">
                                    <label>Patient full name: </label>
                                    <input type="text" name="name">
                                    <br/>
                                    <label>Date Of Birth: </label>
                                    <input type="date" name="dateOfBirth">
                                    <br/>
                                    <label>Patient phoneNumber: </label>
                                    <input type="text" name="phoneNumber">
                                    <br/>
                                    <label>Patient documents: </label>
                                    <input type="text" name="userDocuments">
                                    <br/>
                                    <label>Patient login: </label>
                                    <input type="text" name="login">
                                    <br/>
                                    <label>Patient password: </label>
                                    <input type="text" name="hashPassword">
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
