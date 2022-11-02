import { Link } from "react-router-dom";
import "../../styles/nav-sections.css";

export default function NavSections() {
  return (
    <nav className="nav-sections">
      <Link to="/publicaciones" className="link-sections">
        Adopciones
      </Link>
      <Link to="/transitos" className="link-sections">
        Transitos
      </Link>
      <Link to="/voluntariados" className="link-sections">
        Voluntariados
      </Link>
      <Link to="/donation_posts" className="link-sections">
        Donaciones
      </Link>
    </nav>
  );
}
