import { Link, useMatch, useResolvedPath } from "react-router-dom";
import Logo from "../../images/LogoMiRefugioNavbar.png";
// import "bootstrap/dist/css/bootstrap.min.css";
import "./header.scss";
import { FaBars, FaTimes, FaArrowDown } from "react-icons/fa";
import { useRef } from "react";

export default function NavBar() {
  const navRef = useRef();
  const showNavbar = () => {
    navRef.current.classList.toggle("responsive_nav");
  };
  return (
    <header>
      <h3 className="logo" href="/">
        <Link to="/">
          <img className="logo-img" src={Logo} alt="Perro y Gato mirandose" />
        </Link>
      </h3>
      <nav ref={navRef}>
        <ul>
          <li>
            <CustomLink to="/">Inicio</CustomLink>
          </li>
          <div class="dropdown">
            <button class="dropbtn">Publicaciones v</button>
            <div class="dropdown-content">
              <CustomLink to="/publicaciones">Adopciones</CustomLink>
              <CustomLink to="/transitos">Transitos</CustomLink>
              <CustomLink to="/donation_posts">Donaciones</CustomLink>
              <CustomLink to="/voluntariados">Voluntariados</CustomLink>
            </div>
          </div>
          {/* Inicio Dropdown */}
          {/* Fin Dropdown */}
          <li>
            <CustomLink to="/refugios">Refugios</CustomLink>
          </li>
          <li>
            <CustomLink to="/acerca">Acerca de</CustomLink>
          </li>
        </ul>
        <button className="nav-btn nav-close-btn" onClick={showNavbar}>
          <FaTimes color="white" />
        </button>
      </nav>
      <button className="nav-btn" onClick={showNavbar}>
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
