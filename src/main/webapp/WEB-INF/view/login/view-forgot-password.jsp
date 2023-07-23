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

    <section class="vh-100" style="background-color: #508bfc;">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card shadow-2-strong" style="border-radius: 1rem;">
                        <div class="card-body p-5">

                            <h3 class="mb-5 text-center">Forgot Password</h3>
                            <c:if test="${message != null}">
                                <div class="alert alert-primary" role="alert">
                                        ${message}
                                </div>
                            </c:if>

                            <form action="/forgot-password/get-code" method="post">
                                <c:if test="${status == null}">
                                    <div class="form-outline mb-4">
                                        <input type="text" class="form-control form-control-lg" name="username" placeholder="Email" value="${param.username}"/>
                                    </div>
                                <button class="btn btn-primary btn-lg btn-block text-center" type="submit" onclick="return onSendCode()">Get Code</button>
                                    <a class="btn btn-danger btn-lg btn-block" href="/login/view">Back</a>
                                </c:if>
                            </form>

                            <form action="/forgot-password/submit" method="post">
                                <c:if test="${status == 1}">
                                    <div class="form-outline mb-4">
                                        <input type="text" class="form-control form-control-lg" name="code" placeholder="Code" value="${param.code}"/>
                                    </div>
                                    <button class="btn btn-primary btn-lg btn-block text-center" type="submit" >Submit</button>
                                    <a class="btn btn-danger btn-lg btn-block" href="/login/view">Back</a>
                                </c:if>
                            </form>

                            <form action="/forgot-password/reset-password" method="post">
                                <c:if test="${status == 2}">
                                    <div class="form-outline mb-4">
                                        <input type="text" class="form-control form-control-lg" name="newPassword" readonly value="1"/>
                                    </div>
                                    <button class="btn btn-primary btn-lg btn-block text-center" type="submit" onclick="return onResetPassword()">Reset password</button>
                                    <a class="btn btn-danger btn-lg btn-block" href="/login/view">Back</a>
                                </c:if>
                            </form>
                            <hr class="my-4">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

<script>
    function onSendCode(){
        var sendCode = confirm("Ban co chac muon nhan code khong?")
        if (sendCode == true){
            return true;
        } else {
            return false;
        }
    }

    function onResetPassword(){
        var sendCode = confirm("Ban co chac muon reset password khong?")
        if (sendCode == true){
            return true;
        } else {
            return false;
        }
    }
</script>
</body>
</html>