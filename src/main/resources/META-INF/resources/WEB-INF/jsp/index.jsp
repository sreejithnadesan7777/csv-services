<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true" %>
<html>
<body>

<center>
<br>
<br>
<br>
<br>
<br>

<div>
    <form method="POST" enctype="multipart/form-data" action="/upload">

        <table>
            <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
            <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
        </table>

        <div>

         <c:if test="${message.countStatus == true}">
               <table>
                 <tr><td>Number of imported deals : </td><td>${message.validCount}</td></tr>
                 <tr><td>Number of invalid deals : </td><td>${message.invalidCount}</td></tr>
                 <tr><td>Prosess Time : </td><td>${message.processTime}</td></tr>
               </table>
         </c:if>

        </div>
    </form>
</div>
 <h3>${message.description}</h3>

<a href="list">List files</a>
</center>
</body>
</html>