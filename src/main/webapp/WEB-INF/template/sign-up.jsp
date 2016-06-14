
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
		<jsp:include page="head.jsp"/>
    </head>
    <body>
        <!-- Header -->
		<jsp:include page="header.jsp"/>

		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<div class="well">
						<form action="sign-up" 
							  method="POST" 
							  class="form-horizontal">
							<legend>Sign Up</legend>
							<fieldset>
								<div class="form-group">
									<label for="inputEmail" class="col-lg-2 control-label">Email</label>
									<div class="col-lg-10">
										<input name="email" 
											   type="text" 
											   class="form-control" 
											   id="inputEmail" 
											   placeholder="Email"
											   value="<c:out value="${model.email}"/>">
									</div>
								</div>
								<div class="form-group">
									<label for="inputName" class="col-lg-2 control-label">Name</label>
									<div class="col-lg-10">
										<input name="name" 
											   type="text" 
											   class="form-control" 
											   id="inputName" 
											   placeholder="Name"
											   value="<c:out value="${model.name}"/>">
									</div>
								</div>
								<div class="form-group">
									<label for="inputPassword" class="col-lg-2 control-label">Password</label>
									<div class="col-lg-10">
										<input name="password" 
											   type="password" 
											   class="form-control"
											   id="inputPassword" 
											   placeholder="Password">
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-10 col-lg-offset-2">
										<button type="submit" class="btn btn-primary">Sign Up</button>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
    </body>
</html>
