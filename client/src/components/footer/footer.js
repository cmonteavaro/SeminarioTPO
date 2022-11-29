import {
  FaInstagram,
  FaFacebook,
  FaTwitter,
  FaHeart,
  FaGithub,
  FaBug,
} from "react-icons/fa";
import "./footer.css";

export default function Footer() {
  return (
    <footer>
      <div className="container">
        <h3>Síguenos</h3>
        <div className="icons-link">
          <a className="icons-footer" href="www.instagram.com">
            <FaInstagram size={30} className="icon" title="Icono Instagram" />
          </a>
          <a className="icons-footer" href="www.facebook.com">
            <FaFacebook size={30} className="icon" title="Icono Facebook" />
          </a>
          <a className="icons-footer" href="www.twitter.com">
            <FaTwitter size={30} className="icon" title="Icono Twitter" />
          </a>
        </div>
      </div>
      <div className="dedication">
        <h4>
          Hecho con amor para los pichichos <FaHeart />
        </h4>
      </div>
      <div className="container">
        <h3>Algún problema?</h3>
        <p>Contactate con nosotros</p>
        <div className="icons-link">
          <a
            className="icons-footer"
            href="https://github.com/cmonteavaro/SeminarioTPO"
          >
            <FaGithub size={30} className="icon" title="Icono GitHub" />
          </a>
          <a
            className="icons-footer"
            href="https://github.com/cmonteavaro/SeminarioTPO/issues"
          >
            <FaBug size={30} className="icon" title="Icono Bug" />
          </a>
        </div>
      </div>
    </footer>
  );
}
