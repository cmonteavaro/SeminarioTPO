import SocialMedia from "../rrss/socialMedia";
import { LazyLoadImage } from "react-lazy-load-image-component";
import { Link } from "react-router-dom";
import Logo from "../../images/shelters/zaguates.webp";
import "../../styles/card-shelter.css";

export default function ShelterCard({ shelter }) {
  return (
    <div className="container-card-shelter">
      <div className="card-center-shelter">
        <LazyLoadImage
          alt={"Imagen animal"}
          height={"200px"}
          src={shelter.fotoPerfil}
          width={"200px"}
          effect="blur"
          className="card-logo-shelter"
        />
        <div className="card-detail-shelter">
          <p>{shelter.descripcionCorta}</p>

          <SocialMedia rrss={shelter.redesSociales} />
        </div>
      </div>
      <div className="card-shelter-end">
        <h2>{shelter.nombre}</h2>
        <Link to={`/refugios/${shelter.id}`} className="btn-info">
          Acceder
        </Link>
      </div>
    </div>
  );
}
