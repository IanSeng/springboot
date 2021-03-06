<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf"%>
<div class="container">
  <h1>Your Todos</h1>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Description</th>
      <th>Target Date</th>
      <th>Status</th>
      <th>Update Todo</th>
      <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    JSTL For Loop
    <c:forEach items="${todos}" var="todo">
      <tr>
        <td>${todo.desc}</td>
        <td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
        <td>${todo.done}</td>
        <td><a class="btn btn-success" type="button" href="update-todo?id=${todo.id}">Update</a></td>
        <td><a class="btn btn-warning" type="button" href="delete-todo?id=${todo.id}">Delete</a></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div>
    <a class="button" href="add-todo">Add a Todo</a>
  </div>


  <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
  <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</div>

<%@include file="common/footer.jspf"%>
