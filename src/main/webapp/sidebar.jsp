<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logic.User" %>

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