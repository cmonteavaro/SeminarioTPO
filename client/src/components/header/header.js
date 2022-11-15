import { Link, useMatch, useResolvedPath } from "react-router-dom";
import Logo from "../../images/LogoMiRefugio.png";
// import "bootstrap/dist/css/bootstrap.min.css";
import "./header.scss";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";

export default function NavBar() {
  return (
    <header>
      <Navbar bg="light" expand="lg">
        <Navbar.Brand className="logo" href="/">
          <img className="logo-img" src={Logo} alt="Perro y Gato mirandose" />
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <CustomLink to="/">Inicio</CustomLink>
            <NavDropdown title="Publicaciones" id="basic-nav-dropdown">
              <NavDropdown.Item>
                <CustomLink to="/publicaciones">Adopciones</CustomLink>
              </NavDropdown.Item>
              <NavDropdown.Item>
                <CustomLink to="/transitos">Transitos</CustomLink>
              </NavDropdown.Item>
              <NavDropdown.Item>
                <CustomLink to="/donation_posts">Donaciones</CustomLink>
              </NavDropdown.Item>
              <NavDropdown.Item>
                <CustomLink to="/voluntariados">Voluntariados</CustomLink>
              </NavDropdown.Item>
            </NavDropdown>
            <CustomLink to="/refugios">Refugios</CustomLink>
            <CustomLink to="/acerca">Acerca de</CustomLink>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
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
