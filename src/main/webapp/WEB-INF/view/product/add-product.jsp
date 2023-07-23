<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <style type="text/css">
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
    <h1 class="d-flex justify-content-center">ADD PRODUCT</h1>
    <%--@elvariable id="product" type="jakarta"--%>
    <c:if test="${message != null}">
        <div class="alert alert-primary" role="alert">
            ${message}
        </div>
    </c:if>

    <form:form modelAttribute="product" action="/product/add" method="post">
        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" class="form-control"  name="name" value="${param.name}">
            <form:errors path="name" class="error"></form:errors>
        </div>
        <div class="mb-3">
            <label class="form-label" >Price</label>
            <input type="text" class="form-control" name="price" value="${param.price}">
            <form:errors path="price" class="error"></form:errors>
        </div>
        <div class="mb-3">
            <label class="form-label" >Quantity</label>
            <input type="text" class="form-control" name="quantity" value="${param.quantity}">
            <form:errors path="quantity" class="error"></form:errors>
        </div>
        <div class="mb-3">
            <label class="form-label" >Weight</label>
            <input type="text" class="form-control" name="weight" value="${param.weight}">
            <form:errors path="weight" class="error"></form:errors>
        </div>
        <div class="mb-3">
            <label class="form-label" >Image</label>
            <input type="text" class="form-control" name="image" value="${param.image}">
            <form:errors path="image" class="error"></form:errors>
        </div>

        <label class="form-label">Origin</label>
        <select name="origin">
            <option value="Noi dia">Noi dia</option>
            <option value="Nhap khau">Nhap khau</option>
        </select>

        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea class="form-control"  name="description"></textarea>
            <form:errors path="description" class="error"></form:errors>
        </div>

        <label class="form-label">Status</label>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="status" value="1">
            <label class="form-check-label">
                Con hang
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="status" value="0" checked>
            <label class="form-check-label">
                Da het hang
            </label>
        </div>
        <button type="submit" class="btn btn-primary" onclick="return onAdd()">Add</button>
        <a class="btn btn-danger" href="/product/view">Back</a>
    </form:form>
    <script>
        function onAdd(){
            var addProduct = confirm("Ban co muon them khong?")
            if (addProduct == true){
                return true;
            } else {
                return false;
            }
        }
    </script>
    </body>
</html>