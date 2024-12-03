<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="es-ES">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>GestionA | Perfil de usuario</title>
  <link rel="icon" type="image/svg+xml" href="./assets/img/Favicon.svg">
  <link rel="stylesheet" href="./assets/css/reset.css">
  <link rel="stylesheet" href="./assets/css/global.css">
  <link rel="stylesheet" href="./assets/css/profile.css">
  <script src="./assets/script/global.js" defer></script>
  <script src="./assets/script/profile.js" defer></script>
  <script src="./assets/script/crud.js" defer></script>

</head>

<body class="body-profile">
  <!-- Sidebar -->
    <%@ include file="sidebar.jsp" %>
  <!-- user profile -->
  <main class="main-container">
    <!-- User profile information -->
    <section class="main-containter__user-profile">
      <header class="user-profile__header">
        <h1>Perfil de usuario</h1>
      </header>
      <form
        action="account?operation=update"
        method="post"
        class="user-profile__form">
        <label for="user-fullname" class="form__label">Nombres y apellidos</label>
        <input type="text" name="user-fullname" id="user-fullname" class="form__input" type="text">
        <label for="user-email" class="form__label">Correo electrónico</label>
        <input type="text" name="user-email" id="user-email" class="form__input" type="email" disabled>
        <label for="user-telephone" class="form__label">Teléfono</label>
        <input type="text" name="user-telephone" id="user-telephone" class="form__input" type="number">
        <h2 class="user-profile__subtitle">Cambiar contraseña</h2>
        <div class="user-profile__change-password">
          <div>
            <label for="current-password" class="form__label">Contraseña actual</label>
            <input type="password" name="current-password" id="current-password" class="form__input"
              placeholder="Ingresa tu contraseña">
          </div>
          <div>
            <label for="new-password" class="form__label">Nueva contraseña</label>
            <input type="password" name="new-password" id="new-password" class="form__input"
              placeholder="Ingresa tu nueva contraseña">
          </div>
          <div>
            <label for="confirm-password" class="form__label">Confimar contraseña</label>
            <input type="password" name="confirm-password" id="confirm-password" class="form__input"
              placeholder="Confirma la contraseña">
          </div>
        </div>
        <button type="submit" class="form__button">Guardar cambios</button>
      </form>
    </section>
    <!-- Admin Users -->
    <section class="main-container__admin-user">
      <header class="user-profile__header">
        <h1>Administrar usuarios</h1>
      </header>
      <table class="admin-user__table">
        <thead class="table__head">
          <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Rol</th>
            <th>Permisos</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody class="table__body">
        </tbody>
      </table>
      <button type="button" class="primary-button" id="add-user">Agregar usuario</button>
    </section>
    <!-- modal agregar usuario -->
    <dialog class="modal add-user">
      <button class="modal__close" autofocus>
        <i class="modal__close--icon"></i>
      </button>
      <h2 class="add-user__title">Agregar un Usuario</h2>
      <form action="./" method="post" class="add-user__form">
        <label for="userName" class="form__label">Nombre Completo</label>
        <input type="text" name="userName" id="userName" class="form__input"
          placeholder="Ingrese el nombre completo del usuario">
        <label for="email" class="form__label">Correo</label>
        <input type="text" name="email" id="email" class="form__input" placeholder="Ingrese el correo del usuario">
        <label for="rol" class="form__label">Rol</label>
        <select name="rol" id="rol" class="form__input form__select">
          <optgroup class="form__optgroup" label="Selecciona el rol del usuario">
            <option class="form__option" value="Delegado">Delegado</option>
            <option value="Administrador">Administrador</option>
          </optgroup>
        </select>
        <label class="form__label">Permisos</label>
        <select name="permissions" id="permissions" class="form__input form__select">
          <optgroup class="form__optgroup" label="Selecciona el permiso del usuario">
            <option class="form__option" value="Lector">Ver</option>
            <option value="Editor">Editar</option>
          </optgroup>
        </select>
        <div class="form__container-buttons">
          <button type="submit" class="primary-button">Agregar Usuario</button>
        </div>
      </form>
    </dialog>
    <!-- Model Editar Usuario -->
    <dialog class="modal edit-user">
      <button class="modal__close--edit" autofocus>
        <i class="modal__close--icon"></i>
      </button>
      <h2 class="edit-user__title">Editar Usuario</h2>
      <form action="./" method="post" class="edit-user__form">
        <label for="userName" class="form__label">Nombre Completo</label>
        <input type="text" name="userName" id="userName" class="form__input"
          placeholder="Ingrese el nombre completo del usuario">
        <label for="email" class="form__label">Correo</label>
        <input type="text" name="email" id="email" class="form__input" placeholder="Ingrese el correo del usuario">
        <label for="rol" class="form__label">Rol</label>
        <select name="rol" id="rol" class="form__input form__select">
          <optgroup class="form__optgroup" label="Selecciona el rol del usuario">
            <option class="form__option" value="Delegado">Delegado</option>
            <option value="Administrador">Administrador</option>
          </optgroup>
        </select>
        <label class="form__label">Permisos</label>
        <select name="permissions" id="permissions" class="form__input form__select">
          <optgroup class="form__optgroup" label="Selecciona el permiso del usuario">
            <option class="form__option" value="Lector">Ver</option>
            <option value="Editor">Editar</option>
          </optgroup>
        </select>
        <div class="form__container-buttons">
          <button type="submit" class="primary-button">Guardar</button>
        </div>
      </form>
    </dialog>
  </main>
    <script type="text/javascript">
      window.onload=function(){
          const userNameInput = document.getElementById("user-fullname");
          const userEmailInput = document.getElementById("user-email");
          userNameInput.value = '<%=user.getName()%>'
          userEmailInput.value = '<%=user.getEmail()%>'
      }
    </script>
</body>

</html>