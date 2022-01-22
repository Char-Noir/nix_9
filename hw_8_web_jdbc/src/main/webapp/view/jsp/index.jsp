<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:basepage>
    <jsp:attribute name="title">Start page</jsp:attribute>
    <jsp:body>
        <t:genericpage>
            <jsp:attribute name="titleInner">Start page</jsp:attribute>
            <jsp:body>
                <main role="main" class="container">
                    <h1 class="mt-5">Sticky footer with fixed navbar</h1>
                    <p class="lead">Pin a fixed-height footer to the bottom of the viewport in desktop browsers with this custom HTML and CSS. A fixed navbar has been added with <code>padding-top: 60px;</code> on the <code>body &gt; .container</code>.</p>
                </main>
            </jsp:body>
        </t:genericpage>
    </jsp:body>
</t:basepage>
