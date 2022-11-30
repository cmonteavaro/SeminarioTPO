import "./newNavbar.css";
// import "./header.css";
import { useState } from "react";
import { Link, useMatch, useResolvedPath } from "react-router-dom";
import Logo from "../../images/LogoMiRefugioNavbar.png";
import { Menu } from "@mantine/core";

export default function NavbarNEW() {
  const [isNavExpanded, setIsNavExpanded] = useState(false);

  return (
    <nav className="navigation">
      <Link
        to="/"
        onClick={() => {
          setIsNavExpanded(false);
        }}
        className="logo"
      >
        <img className="logo-img" src={Logo} alt="Perro y Gato mirandose" />
      </Link>
      <button
        className="hamburger"
        onClick={() => {
          setIsNavExpanded(!isNavExpanded);
        }}
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="currentColor"
          class="bi bi-list"
          viewBox="0 0 16 16"
        >
          <path
            fill-rule="evenodd"
            d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"
          />
        </svg>
      </button>
      <div
        className={
          isNavExpanded ? "navigation-menu expanded" : "navigation-menu"
        }
      >
        <ul>
          <li>
            <CustomLink
              to="/"
              onClick={() => {
                setIsNavExpanded(false);
              }}
            >
              Inicio
            </CustomLink>
          </li>
          <Menu>
            <Menu.Target>
              <div className="contenedor-boton">
                <button styles="cursor: pointer" className="dropdown">
                  Publicaciones
                </button>
              </div>
            </Menu.Target>

            <Menu.Dropdown className="menu-dropdown">
              <Menu.Item className="menu-item">
                <CustomLink
                  to="/publicaciones"
                  onClick={() => {
                    setIsNavExpanded(false);
                  }}
                >
                  Adopciones
                </CustomLink>
              </Menu.Item>
              <Menu.Item className="menu-item">
                <CustomLink
                  to="/transitos"
                  onClick={() => {
                    setIsNavExpanded(false);
                  }}
                >
                  Tránsitos
                </CustomLink>
              </Menu.Item>
              <Menu.Item className="menu-item">
                <CustomLink
                  to="/donation_posts"
                  onClick={() => {
                    setIsNavExpanded(false);
                  }}
                >
                  Donaciones
                </CustomLink>
              </Menu.Item>
              <Menu.Item className="menu-item">
                <CustomLink
                  to="/voluntariados"
                  onClick={() => {
                    setIsNavExpanded(false);
                  }}
                >
                  Voluntariados
                </CustomLink>
              </Menu.Item>
            </Menu.Dropdown>
          </Menu>
          <li>
            <CustomLink
              to="/refugios"
              onClick={() => {
                setIsNavExpanded(false);
              }}
            >
              Refugios
            </CustomLink>
          </li>
          <li>
            <CustomLink
              to="/acerca"
              onClick={() => {
                setIsNavExpanded(false);
              }}
            >
              Acerca de
            </CustomLink>
          </li>
        </ul>
      </div>
    </nav>
  );
}
function CustomLink({ to, children, ...props }) {
  const resolvedPath = useResolvedPath(to);
  const isActive = useMatch({ path: resolvedPath.pathname, end: true });

  return (
    <Link className={isActive ? "active" : "links"} to={to} {...props}>
      {children}
    </Link>
  );
}
