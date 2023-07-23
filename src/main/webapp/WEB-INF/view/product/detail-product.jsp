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
<h1 class="d-flex justify-content-center">DETAIL PRODUCT</h1>
<%--@elvariable id="product" type="jakarta"--%>
<c:if test="${message != null}">
    <div class="alert alert-primary" role="alert">
            ${message}
    </div>
</c:if>

<form:form modelAttribute="product" action="/product/update?id=${product.id}" method="post">
    <div class="mb-3">
        <label class="form-label">Name</label>
        <input type="text" class="form-control"  name="name" value="${product.name}">
        <form:errors path="name" class="error"></form:errors>
    </div>
    <div class="mb-3">
        <label class="form-label" >Price</label>
        <input type="text" class="form-control" name="price" value="${product.price}">
        <form:errors path="price" class="error"></form:errors>
    </div>
    <div class="mb-3">
        <label class="form-label" >Quantity</label>
        <input type="text" class="form-control" name="quantity" value="${product.quantity}">
        <form:errors path="quantity" class="error"></form:errors>
    </div>
    <div class="mb-3">
        <label class="form-label" >Weight</label>
        <input type="text" class="form-control" name="weight" value="${product.weight}">
        <form:errors path="weight" class="error"></form:errors>
    </div>
    <div class="mb-3">
        <label class="form-label" >Image</label>
        <input type="text" class="form-control" name="image" value="${product.image}">
        <form:errors path="image" class="error"></form:errors>
    </div>

    <label class="form-label">Origin</label>
    <select name="origin">
        <option value="Noi dia" <c:if test="${product.origin eq 'Noi dia'}">selected</c:if>>Noi dia</option>
        <option value="Nhap khau"<c:if test="${product.origin eq 'Nhap khau'}">selected</c:if>>Nhap khau</option>
    </select>

    <div class="mb-3">
        <label class="form-label">Description</label>
        <input class="form-control"  name="description" value="${product.description}"></input>
        <form:errors path="description" class="error"></form:errors>
    </div>

    <label class="form-label">Status</label>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="status" value="1" <c:if test="${product.status == 1}">checked</c:if>>
        <label class="form-check-label">
            Con hang
        </label>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="status" value="0" <c:if test="${product.status == 0}">checked</c:if>>
        <label class="form-check-label">
            Da het hang
        </label>
    </div>
    <button type="submit" class="btn btn-primary" onclick="return onUpdate()">Update</button>
    <a class="btn btn-danger" href="/product/view">Back</a>
</form:form>
<script>
    function onUpdate(){
        var updateProduct = confirm("Ban co muon cap nhat khong?")
        if (updateProduct == true){
            return true;
        } else {
            return false;
        }
    }
</script>
</body>
</html>