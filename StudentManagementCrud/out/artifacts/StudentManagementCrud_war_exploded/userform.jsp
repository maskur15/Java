<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 18-Sep-23
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->

    <title>Hello, world!</title>
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container col-md-6 offset-md-3">
    <h4>Login Form</h4>
    <form action="/insert" method="POST">
        <div class="form-group">
            <input type="text" class="form-control" id="name" name="name" placeholder="Enter Name" required>
        </div>
        <div class="form-group">
            <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="gender" name="gender" placeholder="Enter Gender" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="country" name="country" placeholder="Enter your country" required>
        </div>


        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>




<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>


</body>
</html>