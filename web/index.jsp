<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<body >
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<main>
    <c:if test="${errors.size()>0 }">
        <div class="danger">
            <ul>
                <c:forEach var="error" items="${errors }">
                    <li>${error }</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <div id="feed"></div>
    <c:choose>
        <c:when test="${empty user}">
            <form method="post" action="Controller?action=LogIn">
                <p>
                    <label for="email">Your email </label>
                    <input type="text" id="email" name="email" value="jan@ucll.be">
                </p>
                <p>
                    <label for="password">Your password</label>
                    <input type="password" id="password" name="password" value="t">
                </p>
                <p>
                    <input type="submit" id="loginbutton" value="Log in">
                </p>
            </form>
        </c:when>
        <c:otherwise>
            <jsp:include page="blog.jsp"/>
        </c:otherwise>
    </c:choose>
    <%--    </c:otherwise>
    </c:choose>--%></main>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script type="text/javascript" src="js/status.js"></script>
<script type="text/javascript" src="js/webSocket.js"></script>

</body>
</html>