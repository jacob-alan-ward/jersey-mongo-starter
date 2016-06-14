# jersey-mongo-starter
Starter template for a Java/Jersey/Mongo project.

This project mostly exists so that I can have a clean starting point for new personal projects.

Uses Jersey/JSP on the back end and Bootstrap with the [Flatly theme](https://bootswatch.com/flatly/) on the front end.

Includes basic setup for:
* Sign up
* Sign in
* Sessions
* Permissions

This project is designed to be used behind an nginx proxy with a server configuration similar to this:
```
server {
	listen					80;
	server_name				localhost;

	location / {
		proxy_set_header	X-Forwarded-Host $host;
		proxy_set_header	X-Forwarded-Server $host;
		proxy_set_header	X-Forwarded-For $proxy_add_x_forwarded_for;
		proxy_pass			http://localhost:8084/jersey-mongo-starter/;
		proxy_redirect		http://localhost:8084/jersey-mongo-starter/ /;
	}
}
```