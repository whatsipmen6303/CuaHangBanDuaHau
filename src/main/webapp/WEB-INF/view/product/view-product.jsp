<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
    <jsp:include page="../header.jsp"></jsp:include>
        <h1 class="d-flex justify-content-center">CRUD PRODUCT</h1>

        <c:if test="${message != null}">
            <div class="alert alert-primary" role="alert">
                ${message}
            </div>
        </c:if>
        <a class="btn btn-primary" href="/product/view-add">Add</a>
        <a class="btn btn-danger" href="/home-page">Back</a>
        <form action="/product/search?nameSearch=${param.nameSearch}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}" method="get">
            Name: <input type="text"  class="mt-3" name="nameSearch" value="${param.nameSearch}">
            Min price: <input type="text" name="minPrice" value="${param.minPrice}">
            Max price: <input type="text" name="maxPrice" value="${param.maxPrice}">
            <button  class="btn btn-primary">Search</button>
        </form>
            <c:if test="${listProduct.isEmpty()}">
                <h2> Not found any records! </h2>
            </c:if>
            <c:if test="${not listProduct.isEmpty()}">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Weight</th>
                    <th scope="col">Image</th>
                    <th scope="col">Origin</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price Reduced</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listProduct.content}" var="product" varStatus="i">
                    <tr>
                        <th scope="row">${i.index + 1}</th>
                        <td>${product.name}</td>
                        <td>${product.price} VND</td>
                        <td>${product.quantity}</td>
                        <td>${product.weight} Kg</td>
                        <td><img style="height: 50px" src="${product.image}"></td>
                        <td>${product.origin}</td>
                        <td>${product.description}</td>
                        <td>${product.priceReduced}</td>
                        <td><c:if test="${product.status == 1}">Con hang</c:if>
                            <c:if test="${product.status == 0}">Het hang</c:if>
                        </td>
                        <td><a class="btn btn-primary" href="/product/detail?id=${product.id}">View</a></td>
                        <td><a class="btn btn-danger" href="/product/delete?id=${product.id}" onclick="return onDelete()">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
            </c:if>
        <nav>
            <ul class="pagination d-flex justify-content-center">
            <c:if test="${listProduct.getNumber() gt 0}">
                <a class="btn btn-primary" href="?num=${listProduct.getNumber() - 1}&nameSearch=${param.nameSearch}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">Previous</a>
            </c:if>

                <span>${listProduct.getNumber() + 1} / ${listProduct.getTotalPages()}</span>


            <c:if test="${listProduct.getNumber() lt listProduct.totalPages - 1}">
                <a class="btn btn-primary" href="?num=${listProduct.getNumber() + 1}&nameSearch=${param.nameSearch}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">Next</a>
            </c:if>
            </ul>
        </nav>

        <script>
            function onDelete(){
                var deleteProduct = confirm("Ban co muon xoa khong?")
                if (deleteProduct == true){
                    alert("Xoa thanh cong!");
                    return true;
                } else {
                    return false;
                }
            }
        </script>
    </body>
</html>