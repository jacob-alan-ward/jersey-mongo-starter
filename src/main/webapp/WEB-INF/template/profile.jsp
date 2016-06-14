
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
		<jsp:include page="head.jsp"/>
    </head>
    <body>
        <!-- Header -->
		<jsp:include page="header.jsp"/>

		<div class="container">
			<div class="row">
				<div class="col-xs-6">
					${model.userContext.userId}
				</div>
				<div class="col-xs-6">
					<c:forEach var="permissionType" items="${model.userContext.permissionTypes}">
						${permissionType}
					</c:forEach>
				</div>
			</div>

			
		</div>
    </body>
</html>
