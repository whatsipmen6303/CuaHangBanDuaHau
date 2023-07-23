<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<%--        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>--%>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
              integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <link href="" rel="shortcut icon" type="image/x-icon">
        <script data-require="angular.js@1.1.5" data-semver="1.1.5" src="http://code.angularjs.org/1.1.5/angular.min.js">
        </script>
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <img src="https://png.pngtree.com/png-vector/20190328/ourlarge/pngtree-watermelon-icon-design--gastronomy-icon-vector-design-png-image_880393.jpg" height="70px" width="100px">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="col-sm-7">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/home-page">Home</a>
                        </li>
                        <c:if test="${customer != null}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                        ${customer.name}<i class="bi bi-person"></i>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="/update-account/view">Cap nhat tai khoan</a></li>
                                    <li><a class="dropdown-item" href="/change-password/view">Doi mat khau</a></li>
                                    <li><a class="dropdown-item" href="/home-page/logout" onclick="return onLogout()">Dang xuat</a></li>
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${customer == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="/login/view">Tai khoan<i class="bi bi-person"></i></a>
                            </li>
                        </c:if>

                        <li class="nav-item">
                            <c:if test="${customer != null}">
                                <a class="nav-link" href="/cart/view?idCustomer=${customer.id}" tabindex="-1" aria-disabled="true">Gio hang<i class="bi bi-cart4"></i></a>
                            </c:if>
                            <c:if test="${customer == null}">
                                <a class="nav-link" href="/login/view" tabindex="-1" aria-disabled="true">Gio hang<i class="bi bi-cart4"></i></a>
                            </c:if>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle btn btn-info" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                CRUD
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="/product/view">San pham</a></li>
                                <li><a class="dropdown-item" href="/bill/view">Hoa don</a></li>
                                <li><a class="dropdown-item" href="">Thong ke</a></li>
                                <li><a class="dropdown-item" href="">Giam gia</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="col-sm-5">
                    <form class="d-flex">
                        <input class="form-control me-2" type="search" placeholder="Nhap ten san pham..."
                               aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search<i class="bi bi-search"></i></button>
                    </form>
                </div>
            </div>
        </div>
    </nav>
    <script>
        function onLogout(){
            var logout = confirm("Ban co muon dang xuat khong?")
            if (logout == true){
                return true;
            } else {
                return false;
            }
        }
    </script>
    </body>
</html>