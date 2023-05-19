<!DOCTYPE html>
    <head>

        <meta charset="UTF-8" />
        <title>Registro</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/login_styles.css">
    </head>

    <body>
      <div class="container">
        <input type="checkbox" id="check">
          <div class="login form">
          <header>Registrarse</header>
          <form name="signup" method="post" action="SignupForm" style="margin-top: 10px;">
            <div class="form-group">
              <label for="User">Usuario</label>
              <input type="text" name="user" class="form-control login-input" id="user">
            </div>
            <% if (request.getAttribute("UserIsAlreadyUsed") != null){ %>
              <div class="alert alert-danger" role="alert">
                El nombre de usuario ya est&aacute en uso.
              </div>
            <% }%> 
            <div class="form-group">
              <label for="Email">Direcci&oacuten de correo electr&oacutenico</label>
              <input type="text" name="email" class="form-control login-input" id="Email" aria-describedby="emailHelp">
            </div>
            <% if (request.getAttribute("EmailNotAccepted") != null){ %>
              <div class="alert alert-danger" role="alert">
                Dominio de email incorrecto.
              </div>
            <% }%> 
            <% if (request.getAttribute("EmailIsAlreadyUsed") != null){ %>
              <div class="alert alert-danger" role="alert">
                La direcci&oacuten de correo electr&oacutenico ya est&aacute registrada.
              </div>
            <% }%> 
            <div class="form-group">
              <label for="Password">Contrase&ntildea</label>
              <input type="password" name="password" class="form-control login-input" id="Password">
            </div>
            <div class="form-group">
                <label for="Password">Confirme contrase&ntildea</label>
                <input type="password" name="confirmpassword" class="form-control login-input" id="ConfirmPassword">
            </div>
            <% if (request.getAttribute("NotStrong") != null){ %>
              <div class="alert alert-danger" role="alert">
                La contrase&ntildea no es segura, use al menos 8 caracteres, may&uacutesculas, min&uacutesculas y sin espacios.
              </div>
            <% }%> 
            <% if (request.getAttribute("PasswordsAreNotTheSame") != null){ %>
              <div class="alert alert-danger" role="alert">
                Passwords are not equal!
              </div>
            <% }%> 
            <button type="submit" class="btn btn-info" style="margin-bottom: 10px; width: -webkit-fill-available;">Registrarse</button>
            
          </form>
        </div>
      </div>
    </body>