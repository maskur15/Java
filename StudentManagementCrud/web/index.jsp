<%@ page import="model.User" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 17-Sep-23
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  $END$
  <%-- Add this line to inspect the attribute --%>
  <%-- Add this line to inspect the attribute --%>
  <form id="registrationForm" action="your-servlet-url" method="post">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>

    <label for="confirmPassword">Confirm Password:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required>

    <button type="submit">Register</button>
  </form>

  <script>
      document.addEventListener("DOMContentLoaded", function() {
          var form = document.getElementById("registrationForm");
          form.addEventListener("submit", function(event) {
              var password = document.getElementById("password").value;
              var confirmPassword = document.getElementById("confirmPassword").value;

              if (password !== confirmPassword) {
                  alert("Passwords do not match. Please check and try again.");
                  event.preventDefault(); // Prevent form submission
              }
          });
      });

  </script>




<script type="text/javascript" src="js/script.js" ></script>
  </body>
</html>
