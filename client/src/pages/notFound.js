import "../styles/notFound.css";
import Surpised from "../images/notfound.webp";
import { Link } from "react-router-dom";

export default function NotFound() {
  return (
    <div className="notFound">
      <h3>Algo no salio bien...</h3>
      <img src={Surpised} alt="Perros asustados" />
      <p>
        Lamentamos el inconveniente, pero lo que buscas no esta disponible...
      </p>
      <Link to="/" className="goBack">
        Volver atras
      </Link>
    </div>
  );
}
