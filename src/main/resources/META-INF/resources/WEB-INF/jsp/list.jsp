<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
<center>
<div>
    <form method="POST"  action="/listing">
            Select a File : <form:select path="files" name = "filename">
                  <form:option value="NONE" label="--- Select ---"/>
                  <form:options items="${files}" />
            </form:select>

       <p><input type="submit" value="Submit" />


   <c:if test="${not empty csvValidDataList}">
   <table>
         <tr><td>Order Id</td><td></td><td>Order Currency</td><td></td><td>Ordering Currency</td><td>Timestamp</td><td>Amount</td><td>Filename</td></tr>
         <c:forEach items="${csvValidDataList}" var="lists">
         <tr><td>${lists.csvOrderId}</td><td></td><td>${lists.csvOrderCurrency}</td><td></td><td>${lists.csvOrderingCurrency}</td><td>${lists.csvOrderTimestamp}</td><td>${lists.csvOrderAmount}</td><td>${lists.csvOrderFilename}</td></tr>
    </c:forEach>
   </table>
   </c:if>
</form>
</div>
</center>
</body>
</html>