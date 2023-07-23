<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    <body>
    <jsp:include page="header.jsp"></jsp:include>
    <c:if test="${message != null}">
        <div class="alert alert-primary" role="alert">
                ${message}
        </div>
    </c:if>
    <!-- Sản phẩm -->
    <section class="row mb-5 mt-5">
        <div class="col-sm-1"></div>
<%--        <!-- Filter -->--%>
<%--        <div class="col-sm-2 bg-light">--%>

<%--        </div>--%>
        <!-- Sản phẩm -->
        <%--        San Pham 1--%>
        <div class="col-sm-10 bg-light pt-5 pb-5">
            <div class="row" >
                <c:if test="${listProduct.isEmpty()}">
                    <h2> Not found any records! </h2>
                </c:if>
                <c:if test="${not listProduct.isEmpty()}">
                <%--San Pham 1--%>
                    <c:forEach items="${listProduct.content}" var="product">
                        <div class="col-sm-3">
                            <div class="col mb-5">
                                <div class="card h-100" >
                                <!-- Product image-->
                                <img class="card-img-top" src="${product.image}" height="200px"
                                     alt="..." />
                                <!-- Product details-->
                                <div class="card-body p-4">
                                    <div class="text-center">
                                        <!-- Product name-->
                                        <h5 class="fw-bolder">${product.name}
                                        </h5>
                                        <!-- Product price-->
                                        ${product.price}
                                    </div>
                                    <div class="text-center">
                                        <h6>
                                            San pham con: ${product.quantity}
                                        </h6>
                                    </div>
                                </div>
                                <!-- Product actions-->
                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                    <c:if test="${customer != null}">
                                        <a class="btn btn-danger" href="/buy-now/view?id=${product.id}">Mua ngay</a>

                                        <a class="btn bi bi-cart4 btn-outline-dark" href="/add-to-cart/detail?id=${product.id}">Them vao gio</a>
                                    </c:if>
                                    <c:if test="${customer == null}">
                                        <a class="btn btn-danger" href="/login/view">Mua ngay</a>

                                        <a class="btn bi bi-cart4 btn-outline-dark" href="/login/view">Them vao gio</a>
                                    </c:if>
                                </div>
                        </div>
                    </div>
                </div>
                    </c:forEach>
                </c:if>
                <nav>
                    <ul class="pagination d-flex justify-content-center mt-3">
                        <c:if test="${listProduct.getNumber() gt 0}">
                            <a class="btn btn-primary" href="?num=${listProduct.getNumber() - 1}&nameSearch=${param.nameSearch}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">Previous</a>
                        </c:if>

                        <span>${listProduct.getNumber() + 1} / ${listProduct.getTotalPages()}</span>


                        <c:if test="${listProduct.getNumber() lt listProduct.totalPages - 1}">
                            <a class="btn btn-primary" href="?num=${listProduct.getNumber() + 1}&nameSearch=${param.nameSearch}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">Next</a>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="col-sm-1"></div>
    </section>
    <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>