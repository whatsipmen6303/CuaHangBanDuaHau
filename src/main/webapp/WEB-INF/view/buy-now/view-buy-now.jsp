<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <style>
        body{background-color: #ADD8E6}.card{border:none}.product{background-color: #eee}.brand{font-size: 13px}.act-price{color:red;font-weight: 700}.dis-price{text-decoration: line-through}.about{font-size: 14px}.color{margin-bottom:10px}label.radio{cursor: pointer}label.radio input{position: absolute;top: 0;left: 0;visibility: hidden;pointer-events: none}label.radio span{padding: 2px 9px;border: 2px solid #ff0000;display: inline-block;color: #ff0000;border-radius: 3px;text-transform: uppercase}label.radio input:checked+span{border-color: #ff0000;background-color: #ff0000;color: #fff}.btn-danger{background-color: #ff0000 !important;border-color: #ff0000 !important}.btn-danger:hover{background-color: #da0606 !important;border-color: #da0606 !important}.btn-danger:focus{box-shadow: none}.cart i{margin-right: 10px}
    </style>
</head>
<body>
<form method="post" action="/buy-now/buy">
    <div class="container mt-5 mb-5">
        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <div class="card">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="images p-3">
                                <div class="text-center p-4"> <img id="main-image" src="${product.image}" width="250" /> </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="product p-4">
                                <c:if test="${message != null}">
                                    <div class="alert alert-primary" role="alert">
                                            ${message}
                                    </div>
                                </c:if>
                                <div class="mt-4 mb-3"> <span class="text-uppercase text-muted brand"></span>
                                    <input class="text-uppercase d-none" id="idProduct" name="id" value="${product.id}">
                                    <h5 class="text-uppercase">${product.name}</h5>
                                    <div class="price d-flex flex-row align-items-center"> <span class="act-price">${product.price} VND</span>
                                        <%--                                        <div class="ml-2"> <small class="dis-price">--%>
                                        <%--                                            $59</small> <span>40% OFF</span> --%>
                                        <%--                                        </div>--%>
                                    </div>
                                </div>
                                <div>
                                    <h6>So luong con: ${product.quantity} san pham</h6>
                                </div>
                                <div>
                                    <h6>Can nang: ${product.weight} kg</h6>
                                </div>
                                <p class="about">${product.description}</p>

                                <div>
                                    So luong mua: <input type="number" min="1" value="1" class="w-25" name="quantityBuy">
                                </div>
                                <div class="cart mt-4 align-items-center">
                                    <button class="btn btn-primary text-uppercase"type="submit" onclick="return onBuyNow()">Buy now</button> <i class="fa fa-heart text-muted"></i> <i class="fa fa-share-alt text-muted"></i>
                                    <a href="/home-page" class="btn btn-danger">Back</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<script>
    function onBuyNow(){
        var buyNow= confirm("Ban co muon mua ngay khong?")
        if (buyNow == true){
            return true;
        } else {
            return false;
        }
    }
</script>
</body>
</html>
