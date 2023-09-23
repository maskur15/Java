<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 22-Sep-23
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.PrintWriter" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <title>Title</title>
</head>
<body style="background-color:#6a2ca5;">
<jsp:include page="header.jsp"></jsp:include>

<div class="container col-md-6 offset-md-3">
    <div class="card mt-5">
        <div class="card-header ">
            <h4 style="text-align: center; color: #6a2ca5;">Login Form</h4>

            <%
                String message = (String) session.getAttribute("message");

                if (message != null) {
                    // Display the message
                    PrintWriter pout = response.getWriter();
                    pout.print(message);

                    // Optionally, remove the message from the session after displaying it
                    session.removeAttribute("message");
                }
            %>


        </div>
        <div class="card-body">
            <form action="/login" method="POST">
                <div class="form-group">
                    <input type="email" class="form-control" id="eamil" name="email" placeholder="Enter Email">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
                </div>

                <button type="submit" class="btn btn-outline-primary" >Login</button>
            </form>

        </div>
    </div>
</div>
<script src="js/script.js"></script>
<!-- Include Bootstrap JS and jQuery (optional) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
