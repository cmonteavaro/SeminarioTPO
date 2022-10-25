import { Badge } from "@mantine/core";
import { Link } from "react-router-dom";
import "../../styles/card.css";
import One from "../../images/juan.webp";
import Logo from "../../images/shelters/zaguates.webp";


const urgencia = (urg) => {
  let text;
  let color;
  let variant;
  if (urg) {
    color = "red";
    variant = "dark";
    text = "URGENTE";
    return (
      <Badge color={color} variant={variant}>
        {text}
      </Badge>
    );
  }else{
    return (
      <></>
    );
  }

  
}

export default function AnimalCard({ animal }) {
  const route = `/posts/${animal.idPublicacion}`;
  let color;
  let variant;
  let text;
  switch (animal.estadoPublicacion) {
    case "DISPONIBLE":
      color = "lime";
      variant = "dark";
      text = `${animal.estadoPublicacion}`;
      break;
    case "EN_PROCESO":
      color = "yellow";
      variant = "dark";
      text = "En proceso";
      break;
    case "FINALIZADA":
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
        <div className="tag-urgente">
          {urgencia(animal.esUrgente)}
        </div>
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