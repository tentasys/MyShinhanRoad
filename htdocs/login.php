

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Shinple Admin</title>
    <link rel="shortcut icon" href="images/Shinhanlogo.ico">

    <!-- Bootstrap -->
    <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="vendors/animate.css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="build/css/custom.min.css" rel="stylesheet">

    <style>
    html {
      height: 100%;
    }

    body {
      background-image: url('/images/login.png') !important;
      background-size: cover !important;
      background-position: center !important;
    }
    </style>
  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      
      <div class="login_wrapper">
        <div class="animate form login_form"style="
        padding-top: 100px;
        padding-left: 1px;">
          <section class="login_content">
            
            <form method='post' action='login_check.php'> 

              <h1>Admin Page</h1>
              <div>
                <input type="text" name="id" class="form-control" placeholder="ID" required="" />
              </div>
              <div>
                <input type="password" name="password" class="form-control" placeholder="Password" required="" />
              </div>
              <div>
                <button type="submit">Log in</button>
                
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><img src="images/logo.png" alt="" style="width: 40px; height: 40px;"></i>신한DS</h1>
                  <p>Copyright ⓒ 2019. Shinhan DS. All right reserved.</p>
                </div>
              </div>
              
            </form>
          
          </section>
          
        </div>
       
      </div>
      
      </div>
    </div>
    

  </body>
</html>
