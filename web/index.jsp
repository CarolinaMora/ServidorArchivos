<%-- 
    Document   : index
    Created on : 9/03/2015, 04:01:56 PM
    Author     : Carolina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="SubirArchivos" enctype="multipart/form-data" method="post">
            Fichero: <input type="file" name="archivo"/>
        <input type="submit" value="Subir"/> 
    </body>
</html>
