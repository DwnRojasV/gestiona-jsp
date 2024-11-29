<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logic.User" %>
<jsp:include page="validate.jsp"/>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Cultivo</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:ital,wght@0,400;0,700;1,700&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./assets/css/modulo_cultivos.css">
    <script src="./assets/script/global.js" defer></script>
</head>

<body>
    <!-- Sidebar -->
    <aside class="sidebar">
        <header class="sidebar__header">
            <a href="home.jsp">
                <img src="./assets/img/logo-accent.svg" alt="Logo de GestionA" class="header__logo">
                <div class="header__slogan">
                    <h1 class="slogan__title">GestionA</h1>
                    <h2 class="slogan__description">Sistema de gestión agrícola</h2>
                </div>
            </a>
            <i class="header__icon" id="sidebar-close"></i>
        </header>
        <nav class="sidebar__overwiew">
            <p class="sidebar__label">Gestión de cultivos</p>
            <ul>
                <li>
                    <a href="/cultivo.html" class="sidebar__option option__link">
                        <i class="option__icon plant"></i>
                        <span class="option__link--text">Cultivo</span>
                    </a>
                </li>
                <li>
                    <a href="/temporada.html" class="sidebar__option option__link">
                        <i class="option__icon loop"></i>
                        <span class="option__link--text">Temporada</span>
                    </a>
                </li>
                <li>
                    <a href="/insumos.html" class="sidebar__option option__link">
                        <i class="option__icon inventory"></i>
                        <span class="option__link--text">Inventario</span>
                    </a>
                </li>
                <li>
                    <a href="/actividades.html" class="sidebar__option option__link">
                        <i class="option__icon calendar"></i>
                        <span class="option__link--text">Actividades</span>
                    </a>
                </li>
                <li>
                    <a href="#" class="sidebar__option option__link">
                        <i class="option__icon production"></i>
                        <span class="option__link--text">Producción</span>
                    </a>
                </li>
                <li>
                    <a href="/registro_ventas.html" class="sidebar__option option__link">
                        <i class="option__icon sell"></i>
                        <span class="option__link--text">Ventas</span>
                    </a>
                </li>
            </ul>
            <p class="sidebar__label">Métricas</p>
            <ul>
                <li>
                    <a href="/informacion.html" class="sidebar__option option__link">
                        <i class="option__icon data"></i>
                        <span class="option__link--text">Información</span>
                    </a>
                </li>
                <li>
                    <a href="/dashboard.html" class="sidebar__option option__link">
                        <i class="option__icon dashboard"></i>
                        <span class="option__link--text">Dashboard</span>
                    </a>
                </li>
            </ul>
        </nav>
        <nav class="sidebar__account">
            <p class="sidebar__label">Cuenta</p>
            <ul>
                <li>
                    <a href="/profile.html" class="sidebar__option option__link">
                        <i class="option__icon settings"></i>
                        <span class="option__link--text">Configuración</span>
                    </a>
                </li>
                <li>
                    <a href="login?operation=logout" class="sidebar__option option__link">
                        <i class="option__icon logout"></i>
                        <span class="option__link--text">Cerrar Sesión</span>
                    </a>
                </li>
            </ul>
        </nav>
        <footer class="sidebar__footer">
            <div class="sidebar__profile-image">
                <span>A</span>
            </div>
            <div class="sidebar_profile-information">
                <%
                    User user = (User) session.getAttribute("Usuario");
                    String username = "";
                    String email = "";
                    System.out.println(user);
                    if(user != null){
                        username = user.getName();
                        email = user.getEmail();
                    }
                %>
                <p class="profile__username"><%=username%></p>
                <p class="profile__email"><%=email%></p>
            </div>
        </footer>
    </aside>
    <!-- Gestión cultivos -->
    <div class="page-container">
        <div class="title-menu">
            <i class="fa-solid fa-bars"></i>
            <h1>GESTIÓN CULTIVOS</h1>
            <div></div>
        </div>
        <main class="sections">
            <section class="section-cultivo">
                <a class="icon-section" href="/cultivo.html"><i class="fa-solid fa-seedling"></i></a>
                <a class="text-section" href="/cultivo.html">Cultivo</a>
            </section>
            <section class="section-temporada">
                <a class="icon-section" href="/temporada.html"><i class="fa-solid fa-sun-plant-wilt"></i></a>
                <a class="text-section" href="/temporada.html">Temporada</a>
            </section>
            <section class="section-insumo">
                <a class="icon-section" href="/insumos.html#insumo"><i class="fa-solid fa-spray-can"></i></a>
                <a class="text-section" href="/insumos.html#insumo">Invetario de insumos</a>
            </section>
            <section class="section-trabajador">
                <a class="icon-section" href="/actividades.html"><i class="fa-solid fa-person-walking"></i></a>
                <a class="text-section" href="/actividades.html">Registro de actividades</a>
            </section>
            <section class="section-produccion">
                <a class="icon-section" href=""><i class="fa-solid fa-box"></i></a>
                <a class="text-section" href="">Registro de producción </a>
            </section>
            <section class="section-ventas">
                <a class="icon-section" href="/registro_ventas.html"><i class="fa-solid fa-sack-dollar"></i></a>
                <a class="text-section" href="/registro_ventas.html">Registro de ventas</a>
            </section>
        </main>
    </div>
</body>

</html>