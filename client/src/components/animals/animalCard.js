import { Badge } from "@mantine/core";
import { Link } from "react-router-dom";
import "../../styles/card.css";
import One from "../../images/juan.webp";
import Logo from "../../images/shelters/zaguates.webp";

export default function AnimalCard({ animal }) {
  const route = `/posts/${animal.idPublicacion}`;
  let color;
  let variant;
  let text;
  switch (animal.estadoPublicacion) {
    case "Disponible":
      color = "lime";
      variant = "dark";
      text = `${animal.estadoPublicacion}`;
      break;
    case "En_proceso":
      color = "yellow";
      variant = "dark";
      text = "En proceso";
      break;
    case "Finalizada":
      color = "red";
      variant = "dark";
      text = `${animal.estadoPublicacion}`;
      break;
    default:
      color = "gray";
      variant = "dark";
      text = "Sin info";
      break;
  }
  return (
    <div className="card">
      <div className="card-heading">
        <h2>{animal.nombreAnimal}</h2>
        <Badge color={color} variant={variant}>
          {text}
        </Badge>
      </div>
      <div className="card-center">
        <img src={One} className="card-img-animal" alt="Imagen animal" />
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
