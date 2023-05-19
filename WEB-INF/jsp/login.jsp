<!DOCTYPE html>
    <head>

        <meta charset="utf-8" />
        <title>Iniciar Sesi&oacuten</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/login_styles.css">
    </head>

    <body class="login-body">
      <div class="container">
        <input type="checkbox" id="check">
        <div class="login form">
          <header>Iniciar Sesion</header>
          <form name="login" method="POST" action="LoginForm">
            <div class="form-group">
              <label for="User">Nombre de usuario</label>
              <input type="text" name="name" class="form-control login-input" id="user">
            </div>
            <div class="form-group">
              <label for="Password">Contrase&ntildea</label>
              <input type="password" name="Password" class="form-control login-input" id="Password">
            </div>
            <input type="submit" value="Iniciar Sesion" action="inicio" class="button">
            <% if (request.getAttribute("error") != null){ %>
              <div class="alert alert-danger" role="alert">
                Usuario no encontrado
            </div>
            <% }%>
            <small id="Register" style="margin-bottom: 5px; margin-top: 20px;" class="form-text text-muted">Si todav&iacutea no tienes una cuenta.</small>
          </form>
          <div class="signup">
              <form action="signup" method="POST">
                <input type="submit" value="Registrarse" class="btn btn-info">
              </form>
             </span>
          </div>
          <p>&nbsp;</p>
        </div>
    </body>