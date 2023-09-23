<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 17-Sep-23
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg">
            <table class="table table-sm  table-hover">
                <thead class="thead-dark">
                <tr>
                    <th scope="col" >Id</th>
                    <th scope="col" >Name</th>
                    <th scope="col" >Email</th>
                    <th scope="col" >Gender</th>
                    <th scope="col" >Country</th>
                    <th scope="col" class="text-center">Action</th>
                    <th scope="col"> <input type="text" id="searchInput" placeholder="Search by Name or ID" onkeyup="filterTable()"> </th>


                </tr>
                </thead>
                <tbody>
                <% for (User student : (List<User>) request.getAttribute("studentList")) { %>
                <tr>
                    <td><%= student.getId() %></td>
                    <td><%= student.getName() %></td>
                    <td><%= student.getEmail() %></td>
                    <td><%= student.getGender() %></td>
                    <td><%= student.getCountry() %></td>
                    <td>
                        <div class="row">
                            <div class="col-md-4">
                                <form action="delete" method="post">
                                    <input type="hidden" name="studentId" value="<%=student.getId()%>">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </div>
                            <div class="col-md-4">
                                <form action="edit" method="post">
                                    <input type="hidden" name="studentId" value="<%=student.getId()%>">
                                    <button type="submit" class="btn btn-primary">Edit</button>
                                </form>
                            </div>
                            <div class="col-md-4">
                                <form action="edit" method="post">
                                    <input type="hidden" name="studentId" value="<%=student.getId()%>">
                                    <button type="submit" class="btn btn-success">View</button>
                                </form>
                            </div>
                        </div>


                    </td>
                    <!-- Add more cells for other student attributes -->
                </tr>
                <% } %>
                </tbody>
            </table>

        </div>
    </div>

</div>


<script type="text/javascript" src="js/script.js"></script>
<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

</body>
</html>
