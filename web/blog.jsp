<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="blog">

    <h2>Blog</h2>
    <%--<div class="question">

    <div class="question">
        <p>
            Wat ben je van plan om te doen vandaag?
        </p>
        <div class="input">
            <input type="text" id="aq2">
            <input type="button" value="submit" id="sq2">
        </div>
    </div>
    <div class="question">
        <p>
           Naar welke muziek ben je momenteel aan het luisteren?
        </p>
        <div class="input">
            <input type="text" id="aq3">
            <input type="button" value="submit" id="sq3">
        </div>
    </div>
    <div class="question">
        <p>
           Wat zijn de examenvragen voor web4?
        </p>
        <div class="input">
            <input type="text" id="aq4">

            <input type="button" value="submit" id="sq4">
        </div>
    </div>--%>
    <div id="test"></div>
    <div class=hidden id="userName">${user.getFirstName()}</div>
    <c:forEach items="${blogs}" var="blog">

        <div class="question" id="blog${blog.id}">
            <h3>${blog.vraag}</h3>
            <div class="commentBox"></div>
            <input type="text" name="antwoord" id="aq${blog.id}" class="antwoord">  <%--aq=awsnerquestion--%>
            <input type="number" name="rating" id="rb${blog.id}" class="antwoord" min="1" max="10"> <%--rb = ratingBlog--%>
            <input type="submit" id="bs${blog.id}" onclick="submitComment(${blog.id})" value="push awnser">

        </div>
    </c:forEach>
<%--
    <script type="text/javascript" src="js/webSocket.js"></script>
--%>

</div>
