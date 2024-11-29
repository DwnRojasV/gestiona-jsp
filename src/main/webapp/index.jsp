<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GestionA - Sistema de gestión agrícola | Inciar Sesión </title>
    <link rel="icon" type="image/svg+xml" href="./assets/img/Favicon.svg">
    <link rel="stylesheet" href="./assets/css/reset.css">
    <link rel="stylesheet" href="./assets/css/global.css">
    <link rel="stylesheet" href="./assets/css/authentication.css">
    <script src="./assets/script/autentication.js" defer></script>
</head>

<body class="body">
    <div class="container">
        <aside class="container__slogan">
            <header class="slogan__title">GestionA</header>
            <h2 class="slogan__subtitle">Sistema de gestión agrícola</h2>
            <img class="slogan__logo" src="./assets/img/Logo.svg" alt="Logotipo de GestionA">
        </aside>
        <main class="container__cardform">
            <h1 class="cardform__title">Inicia Sesión</h1>
            <form class="cardform__form"
                  method="post"
                  action="login?operation=login">

                <label class="form__label--required" for="email">Correo</label>
                <input class="form__input" type="email" id="email" name="email" placeholder="Ingrese su correo"
                    required>
                <label class="form__label--required" for="password">Contraseña</label>
                <input class="form__input" type="password" id="password" name="password"
                    placeholder="Ingrese su contraseña" required>
                <button class="form__button" type="submit">Iniciar Sesión</button>
            </form>
            <p class="cardform__text">¿No tienes una cuenta?
                <a href="./sign-up.html" class="card_form__text--link">Registrate</a>
            </p>
        </main>
    </div>
</body>

</html>