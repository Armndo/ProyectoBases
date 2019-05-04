<%-- 
    Document   : header
    Created on : 14/03/2019, 05:11:22 PM
    Author     : Armando
--%>
<header style="position: fixed; width: 100%; z-index: 1;">
    <div style="background-image: url('/Bases/img/header.jpg'); width: 100%; height: 40px;"></div>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-image: url('/Bases/img/header.jpg'); background-position-y: -150px; background-size: auto;">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown">
                        <%= persona.usuario().getEmail() %>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/Bases"><i class="fa fa-home"></i><strong> Inicio</strong></a>
                        <form action="/Bases/Logout" method="POST">
                            <button class="dropdown-item" type="submit">
                                <strong><i class="fa fa-sign-out"></i> Cerrar Sesión</strong>
                            </button>
                        </form>
                    </div>
                </li>
                <% if(persona.empleado() != null) { %>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown">
                        <i class="fa fa-users"></i> Pasajeros
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/Bases/Pasajero/Create"><i class="fa fa-plus"></i><strong> Alta</strong></a>
                        <a class="dropdown-item" href="/Bases/Pasajero"><i class="fa fa-bars"></i><strong> Lista</strong></a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown">
                        <i class="fa fa-plane"></i> Aeronaves
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/Bases/Aeronave/Create"><i class="fa fa-plus"></i><strong> Alta</strong></a>
                        <a class="dropdown-item" href="/Bases/Aeronave"><i class="fa fa-bars"></i><strong> Lista</strong></a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown">
                        <i class="fa fa-globe"></i> Aeropuertos
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/Bases/Aeropuerto/Create"><i class="fa fa-plus"></i><strong> Alta</strong></a>
                        <a class="dropdown-item" href="/Bases/Aeropuerto"><i class="fa fa-bars"></i><strong> Lista</strong></a>
                    </div>
                </li>
                <% if(persona.empleado().getPuesto().equals("Administrador")) { %>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown">
                        <i class="fa fa-building"></i> Aerolíneas
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/Bases/Aerolinea/Create"><i class="fa fa-plus"></i><strong> Alta</strong></a>
                        <a class="dropdown-item" href="/Bases/Aerolinea"><i class="fa fa-bars"></i><strong> Lista</strong></a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown">
                        <i class="fa fa-address-card"></i> Empleados
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/Bases/Empleado/Create"><i class="fa fa-plus"></i><strong> Alta</strong></a>
                        <a class="dropdown-item" href="/Bases/Empleado"><i class="fa fa-bars"></i><strong> Lista</strong></a>
                    </div>
                </li>
                <% } } %>
            </ul>
        </div>
    </nav>
</header>