
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In</title>
		<jsp:include page="head.jsp"/>
    </head>
    <body>
        <!-- Header -->
		<jsp:include page="header.jsp"/>

		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<div class="well">
						<form action="sign-in" 
							  method="POST" 
							  class="form-horizontal">
							<legend>Sign In</legend>
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
										<button type="submit" class="btn btn-primary">Sign In</button>

										<span class="pull-right">
											<span>Not a member?</span>
											<a href="/sign-up" class="btn btn-success">Sign Up</a>
										</span>
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
