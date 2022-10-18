import { Badge } from "@mantine/core";
import { Link } from "react-router-dom";
import "../../styles/card.css";

export default function AnimalCard({ animal }) {
  let color;
  let variant;
  let text;
  switch (animal.state) {
    case "Disponible":
      color = "lime";
      variant = "dark";
      text = `${animal.state}`;
      break;
    case "En proceso":
      color = "yellow";
      variant = "dark";
      text = `${animal.state}`;
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
        <h2>{animal.name}</h2>
        <Badge color={color} variant={variant}>
          {text}
        </Badge>
      </div>
      {/* Arreglar ruta */}
      <div className="card-center">
        <img src={animal.image} className="card-img-animal" alt="Imagen aqui" />
      </div>
      <div className="card-bottom">
        <div className="card-shelter">
          <img src={animal.shelter.logo} className="card-img-shelter" alt="" />
          <p>{animal.shelter.name}</p>
        </div>
        <Link to="/posts/coco" className="btn-info">
          Adoptar
        </Link>
      </div>
    </div>
  );
}
