import "./header.css";
import { Link, useMatch, useResolvedPath } from "react-router-dom";
import logo from "../../images/logo.png";
import Logo from "../../images/LogoMiRefugio.png";

export default function NavBar() {
  return (
    <nav className="nav">
      <div className="logo">
        <Link to="/" className="link">
          <img className="logo-img" src={Logo} alt="Perro y Gato mirandose" />
        </Link>
      </div>
      <div className="navigation">
        <ul>
          <li>
            <CustomLink to="/">Inicio</CustomLink>
          </li>
          <li>
            <CustomLink to="/posts">Publicaciones</CustomLink>
          </li>
          <li>
            <CustomLink to="/refugees">Refugios</CustomLink>
          </li>
          <li>
            <CustomLink to="/about">Acerca de</CustomLink>
          </li>
        </ul>
      </div>
    </nav>
  );
}

// Esta es una funcion para ver en que prop estamos actualmente,
// debido a que usamos router para ahorrar el re renderizado de componentes
// no se va a renderizar toda la pagina. Este es el metodo para cambiar el active link
function CustomLink({ to, children, ...props }) {
  const resolvedPath = useResolvedPath(to);
  const isActive = useMatch({ path: resolvedPath.pathname, end: true });

  return (
    <li className={isActive ? "active" : "links"}>
      <Link to={to} {...props}>
        {children}
      </Link>
    </li>
  );
}
