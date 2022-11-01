import { Link } from "react-router-dom";
import "../../styles/card.css";
import One from "../../images/juan.webp";
import Logo from "../../images/shelters/zaguates.webp";

import { LazyLoadImage } from "react-lazy-load-image-component";
import "react-lazy-load-image-component/src/effects/blur.css";
import Tag from "../badge/badge";


export default function AnimalCard({ animal }) {
  const route = `/publicaciones/${animal.idPublicacion}`;
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
          src={One}
          width={"250px"}
          effect="blur"
        className="card-img-animal"/>
        <div className="tag-urgente">{<Tag state={animal.esUrgente} />}</div>

      </div>
      <div className="card-bottom">
        <div className="card-shelter">
          <img src={Logo} className="card-img-shelter" alt="Imagen refugio" />
          <p>{animal.nombreRefugio}</p>
        </div>
        <Link to={route} className="btn-info">
          Adoptar
        </Link>
      </div>
    </div>
  );
}
