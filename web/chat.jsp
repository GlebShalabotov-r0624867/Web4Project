<%--
  Created by IntelliJ IDEA.
  User: Gleb0
  Date: 21/02/2019
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>

<body onload="ready()" >
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<main>

    <div id="welcome">
        <p>Welcome ${user.getFirstName()}!</p>
        <form method="post" action="Controller?action=LogOut">
            <p>
                <input type="submit" id="logoutbutton" value="Log Out">
            </p>
        </form>
    </div>

    <p>Chat</p>

    <p>Status: </p>
    <div id="status"></div>

    <h3> Change Status</h3>


    <div id=" changeStatusInput">

        <input type="text" name="newStatus" id="newStatus">
        <input type="button" value="change" id="changeStatus">
        <%--
                    <script type="text/javascript" src="js/webSocket.js"></script>
        --%>

    </div>
    <div id="feed"></div>
    <h3 id="specialEffect">Friends </h3>
    <div id="opdracht4">
    <table id="friendList">
        <tr>
            <th>Naam</th>
            <th>Status</th>
        </tr>
    </table>
    </div>


    <div id="addfriendo">
        <h3>Add a new Friend</h3>
        <input type="text" name="newFriendo" id="newFriendo">
        <input type="button" value="Add" id="addNewFriendo">
    </div>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script type="text/javascript" src="js/status.js"></script>
<script type="text/javascript" src="js/jQueryy.js"></script>
</body>
</html>
