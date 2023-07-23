<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <head>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        </head>
    </head>
    <body>
    <h1 class="d-flex justify-content-center">Gio Hang</h1>
    <c:if test="${message != null}">
        <div class="alert alert-primary" role="alert">
                ${message}
        </div>
    </c:if>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">San pham</th>
            <th scope="col">Hinh anh</th>
            <th scope="col">So luong</th>
            <th scope="col">Gia</th>
            <th scope="col">Thanh tien</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCartViewModel}" var="cartProduct" varStatus="i">
            <tr>
                <th scope="row">${i.index + 1}</th>
                <td>${cartProduct.name}</td>
                <td><img style="height: 50px"  src="${cartProduct.image}"></td>
                <td>
                    <a href="/cart/reduce?idCartProduct=${cartProduct.id}" class="btn btn-primary"> - </a>
                        <input type="text" style="width: 50px" value="${cartProduct.quantity}" readonly>
                    <a href="/cart/increase?idCartProduct=${cartProduct.id}" class="btn btn-primary"> + </a>
                </td>
                <td>${cartProduct.price}</td>
                <td>${cartProduct.price * cartProduct.quantity}</td>
                <td><a class="btn btn-danger" href="/cart/delete?idCartProduct=${cartProduct.id}" onclick="return onDeleteCartProduct()">Xoa</a></td>
<%--                <td><a class="btn btn-danger" href="/product/delete?id=${product.id}" onclick="return onDelete()">Delete</a></td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="float: right;">
        <h4>Tong so tien: ${totalMoney} VND</h4>
    </div>
    <a href="/home-page" class="btn btn-danger">Back</a>
    <a href="/cart/buy" class="btn btn-primary" onclick="return onBuy()">Thanh toan</a>
    <script>
        function onDeleteCartProduct(){
            var deleteCartProduct = confirm("Ban co muon xoa san pham khoi gio hang?")
            if (deleteCartProduct == true){
                alert("Xoa thanh cong!");
                return true;
            } else {
                return false;
            }
        }

        function onBuy(){
            var buy = confirm("Ban co muon thanh toan khong?")
            if (buy == true){
                return true;
            } else {
                return false;
            }
        }
    </script>
    </body>
</html>