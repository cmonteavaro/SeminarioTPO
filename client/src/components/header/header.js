import { Link, useMatch, useResolvedPath } from "react-router-dom";
import Logo from "../../images/LogoMiRefugioNavbar.png";
// import "bootstrap/dist/css/bootstrap.min.css";

import { FaBars, FaTimes } from "react-icons/fa";
import { Menu } from "@mantine/core";
import { useState } from "react";

export default function NavBar() {
  const [burgerOpen, setBurgerOpen] = useState(false);

  return (
    <header>
      <Link to="/">
        <img className="logo-img" src={Logo} alt="Perro y Gato mirandose" />
      </Link>
      <nav className="nav">
        <ul>
          <li>
            <CustomLink to="/">Inicio</CustomLink>
          </li>
          <Menu>
            <Menu.Target>
              <button className="dropdown">Publicaciones</button>
            </Menu.Target>

            <Menu.Dropdown className="menu-dropdown">
              <Menu.Item>
                <CustomLink to="/publicaciones">Adopciones</CustomLink>
              </Menu.Item>
              <Menu.Item>
                <CustomLink to="/transitos">Transitos</CustomLink>
              </Menu.Item>
              <Menu.Item>
                <CustomLink to="/donation_posts">Donaciones</CustomLink>
              </Menu.Item>
              <Menu.Item>
                <CustomLink to="/voluntariados">Voluntariados</CustomLink>
              </Menu.Item>
            </Menu.Dropdown>
          </Menu>
          <li>
            <CustomLink to="/refugios">Refugios</CustomLink>
          </li>
          <li>
            <CustomLink to="/acerca">Acerca de</CustomLink>
          </li>
        </ul>
      </nav>
      <button
        className={burgerOpen ? "show" : "nav-btn"}
        onClick={() => setBurgerOpen(true)}
      >
        <FaBars color="white" />
      </button>
    </header>
  );
}

// Esta es una funcion para ver en que prop estamos actualmente,
// debido a que usamos router para ahorrar el re renderizado de componentes
// no se va a renderizar toda la pagina. Este es el metodo para cambiar el active link
function CustomLink({ to, children, ...props }) {
  const resolvedPath = useResolvedPath(to);
  const isActive = useMatch({ path: resolvedPath.pathname, end: true });

  return (
    <Link className={isActive ? "active" : "links"} to={to} {...props}>
      {children}
    </Link>
  );
}
