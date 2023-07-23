<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="" rel="shortcut icon" type="image/x-icon">
</head>
<body>
<form action="/change-password/change" method="post">
    <section class="vh-100" style="background-color: #508bfc;">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card shadow-2-strong" style="border-radius: 1rem;">
                        <div class="card-body p-5">

                            <h3 class="mb-5 text-center">Change Password</h3>
                            <c:if test="${message != null}">
                                <div class="alert alert-primary" role="alert">
                                        ${message}
                                </div>
                            </c:if>

                            <div class="form-outline mb-4">
                                <input type="password" id="typeEmailX-2" class="form-control form-control-lg" name="oldPassword" placeholder="Old password" value="${param.oldPassword}"/>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="password" id="typePasswordX-2" class="form-control form-control-lg" name="newPassword" placeholder="New password" value="${param.newPassword}"/>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="password"  class="form-control form-control-lg" name="confirmPassword" placeholder="Re-enter password" value="${param.confirmPassword}"/>
                            </div>

                            <button class="btn btn-primary btn-lg btn-block text-center" type="submit" onclick="return onChange()">Change</button>
                            <a class="btn btn-danger btn-lg btn-block" href="/home-page">Back</a>
                            <hr class="my-4">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</form>
<script>
    function onChange(){
        var change = confirm("Ban co muon cap nhat khong?")
        if (change == true){
            return true;
        } else {
            return false;
        }
    }
</script>
</body>
</html>