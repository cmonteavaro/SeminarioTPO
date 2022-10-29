import { Badge } from "@mantine/core";
import { Link } from "react-router-dom";
import "../../styles/card.css";
import One from "../../images/juan.webp";
import Logo from "../../images/shelters/zaguates.webp";
import { LazyLoadImage } from "react-lazy-load-image-component";
import "react-lazy-load-image-component/src/effects/blur.css";

function getState(state) {
  let color;
  let variant;
  let text;
  switch (state) {
    case "DISPONIBLE":
      color = "lime";
      variant = "dark";
      text = `${state}`;
      break;
    case "EN_PROCESO":
      color = "yellow";
      variant = "dark";
      text = "En proceso";
      break;
    case "FINALIZADA":
      color = "red";
      variant = "dark";
      text = `${state}`;
      break;
    default:
      color = "gray";
      variant = "dark";
      text = "Sin info";
      break;
  }
  const result = [color, variant, text];
  return result;
}

export default function AnimalCard({ animal }) {
  const route = `/publicaciones/${animal.idPublicacion}`;
  const badgeValues = getState(animal.estadoPublicacion);
  return (
    <div className="card">
      <div className="card-heading">
        <h2>{animal.nombreAnimal}</h2>
        <Badge color={badgeValues[0]} variant={badgeValues[1]}>
          {badgeValues[2]}
        </Badge>
      </div>
      <div className="card-center">
        <LazyLoadImage
          alt={"Imagen animal"}
          height={"300px"}
          src={One}
          width={"250px"}
          effect="blur"
        />
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
