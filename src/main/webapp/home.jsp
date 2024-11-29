<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <jsp:include page="sidebar.jsp"/>
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