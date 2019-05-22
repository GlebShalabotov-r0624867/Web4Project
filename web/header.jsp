<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header role="banner">
    <img alt="Books" src="images/eigenbanner.jpg">
    <h1><span>Chat App</span></h1>
    <nav>
        <ul>
            <c:choose>
                <c:when test="${empty user}">
                    <li id="actual"><a href="Controller">Home</a></li>
                </c:when>
                <c:otherwise>
                    <li><a id="navheader" href="Controller?action=Home">Home</a></li>
                    <li><a href="Controller?action=Chat">Chat</a></li>
                </c:otherwise>
            </c:choose>


        </ul>
    </nav>
    <h2>
        ${param.title}
    </h2>

</header>