<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<h1 class="d-flex justify-content-center">Bill</h1>

<c:if test="${message != null}">
    <div class="alert alert-primary" role="alert">
            ${message}
    </div>
</c:if>
<a class="btn btn-danger" href="/home-page">Back</a>
<%--<form action="/product/search?nameSearch=${param.nameSearch}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}" method="get">--%>
<%--    Name: <input type="text"  class="mt-3" name="nameSearch" value="${param.nameSearch}">--%>
<%--    Min price: <input type="text" name="minPrice" value="${param.minPrice}">--%>
<%--    Max price: <input type="text" name="maxPrice" value="${param.maxPrice}">--%>
<%--    <button  class="btn btn-primary">Search</button>--%>
<%--</form>--%>
<c:if test="${listBill.isEmpty()}">
    <h2> Not found any records! </h2>
</c:if>
<c:if test="${not listBill.isEmpty()}">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Date Created</th>
            <th scope="col">Date Of Payment</th>
            <th scope="col">Status</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listBill.content}" var="bill" varStatus="i">
            <tr>
                <th scope="row">${i.index + 1}</th>
                <td>${bill.dateCreated}</td>
                <td>${bill.dateOfPayment}</td>
                <td><c:if test="${bill.status == 1}">Da thanh toan</c:if>
                    <c:if test="${bill.status == 2}">Cho thanh toan</c:if>
                </td>
                <td><a class="btn btn-primary" href="/bill/detail?id=${bill.id}">View</a></td>
                <c:if test="${bill.status == 2}">
                    <td><a class="btn btn-danger" href="/bill/update-status?id=${bill.id}" onclick="return onUpdateStatus()">Cap nhat</a></td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<nav>
    <ul class="pagination d-flex justify-content-center">
        <c:if test="${listBill.getNumber() gt 0}">
            <a class="btn btn-primary" href="?num=${listBill.getNumber() - 1}">Previous</a>
        </c:if>

        <span>${listBill.getNumber() + 1} / ${listBill.getTotalPages()}</span>


        <c:if test="${listBill.getNumber() lt listBill.totalPages - 1}">
            <a class="btn btn-primary" href="?num=${listBill.getNumber() + 1}">Next</a>
        </c:if>
    </ul>
</nav>

<script>
    function onUpdateStatus(){
        var updateStatus = confirm("Ban co muon cap nhat trang thai khong?")
        if (updateStatus == true){
            alert("Cap nhat trang thai thanh cong!");
            return true;
        } else {
            return false;
        }
    }
</script>
</body>
</html>