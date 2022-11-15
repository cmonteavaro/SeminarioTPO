import { Link } from "react-router-dom";
import "../../styles/card.css";
import Logo from "../../images/shelters/zaguates.webp";

import { LazyLoadImage } from "react-lazy-load-image-component";
import "react-lazy-load-image-component/src/effects/blur.css";
import Tag from "../badge/badge";

export default function TransitCard({ animal }) {
  return (
    <div className="card">
      <div className="card-heading">
        <h2>{animal.nombreAnimal}</h2>
        <Tag state={animal.estadoPublicacion} />
      </div>
      <div className="card-center">
        <LazyLoadImage
          alt={"Imagen animal"}
          height={"300px"}
          src={animal.imagenAnimal}
          width={"250px"}
          effect="blur"
        />
        <div className="tag-urgente">{<Tag state={animal.esUrgente} />}</div>
      </div>
      <div className="card-bottom">
        <div className="card-shelter">
          <img src={Logo} className="card-img-shelter" alt="Imagen refugio" />
          <p>{animal.nombreRefugio}</p>
        </div>
        <Link to={`/transitos/${animal.idPublicacion}`} className="btn-info">
          Transitar
        </Link>
      </div>
    </div>
  );
}
