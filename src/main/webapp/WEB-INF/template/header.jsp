<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a href="/" class="navbar-brand">Jersey Mongo Starter</a>
			<button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="navbar-main">
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="/">Home</a>
				</li>
				<c:if test="${!model.isAuthenticated()}">
					<li>
						<a href="sign-in">Sign In</a>
					</li>
				</c:if>

				<c:if test="${model.isAuthenticated()}">
					<li class="dropdown">
						<a href="#" 
						   class="dropdown-toggle" 
						   data-toggle="dropdown" 
						   role="button" 
						   aria-haspopup="true" 
						   aria-expanded="false">
							My Account <span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="/profile">Profile</a>
							</li>
							<li>
								<a href="/sign-out">Sign Out</a>
							</li>
						</ul>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>