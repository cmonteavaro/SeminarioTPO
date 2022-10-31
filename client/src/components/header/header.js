import "./header.css";
import { Link, useMatch, useResolvedPath } from "react-router-dom";
import Logo from "../../images/LogoMiRefugio.png";

export default function NavBar() {
  return (
    <header class="header">
      <nav className="navegacion">
        <Link to="/" className="link">
          <img className="logo-img" src={Logo} alt="Perro y Gato mirandose" />
        </Link>
        <input class="menu-btn" type="checkbox" id="menu-btn" />
        <label class="menu-icon" for="menu-btn">
          <span class="navicon"></span>
        </label>
        <ul class="menu">
          <li>
            <CustomLink to="/">Inicio</CustomLink>
          </li>
          <li>
            <CustomLink to="/publicaciones">Publicaciones</CustomLink>
          </li>
          <li>
            <CustomLink to="/refugios">Refugios</CustomLink>
          </li>
          <li>
            <CustomLink to="/acerca">Acerca de</CustomLink>
          </li>
        </ul>
      </nav>
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
