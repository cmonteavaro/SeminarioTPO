import { Link } from "react-router-dom";
import "../../styles/card.css";
import { LazyLoadImage } from "react-lazy-load-image-component";
import "react-lazy-load-image-component/src/effects/blur.css";
import Tag from "../badge/badge";

export default function AnimalCard({ animal }) {
  return (
    <div className="card">
      <div className="card-heading">
        <h3>{animal.nombreAnimal}</h3>
        <Tag state={animal.estadoPublicacion} />
      </div>
      <div className="card-center">
        <LazyLoadImage
          alt={"Imagen animal"}
          height={"300px"}
          src={animal.imagenAnimal}
          width={"250px"}
          effect="blur"
          className="card-img-animal"
        />
        <div className="tag-urgente">{<Tag state={animal.esUrgente} />}</div>
      </div>
      <div className="card-bottom">
        <div className="card-shelter">
          <Link to={`/refugios/${animal.idRefugio}`}>
            <img
              src={animal.fotoPerfilRefugio}
              className="card-img-shelter"
              alt="Imagen refugio"
            />
          </Link>
          <div>
            <p>{animal.nombreRefugio}</p>
          </div>
        </div>
        <Link
          to={`/publicaciones/${animal.idPublicacion}`}
          className="btn-info"
        >
          Adoptar
        </Link>
      </div>
    </div>
  );
}
