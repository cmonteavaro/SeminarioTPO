import { Link, useMatch, useResolvedPath } from "react-router-dom";
import "../../styles/nav-sections.css";

export default function NavSections() {
  return (
    <nav className="nav-sections">
      <CustomLink to="/publicaciones">Adopciones</CustomLink>
      <CustomLink to="/transitos">Transitos</CustomLink>
      <CustomLink to="/voluntariados">Voluntariados</CustomLink>
      <CustomLink to="/donaciones">Donaciones</CustomLink>
    </nav>
  );
}

function CustomLink({ to, children, ...props }) {
  const resolvedPath = useResolvedPath(to);
  const isActive = useMatch({ path: resolvedPath.pathname, end: true });
  console.log(isActive);
  return (
    <Link
      className={isActive ? "active links-sections" : "links-sections"}
      to={to}
      {...props}
    >
      {children}
    </Link>
  );
}
