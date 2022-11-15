import { Link } from "react-router-dom";
import "../../styles/card.css";
import Logo from "../../images/shelters/zaguates.webp";
import { IconMapPin } from "@tabler/icons";
import { IconCalendar } from "@tabler/icons";
import { AiFillCamera } from "react-icons/ai";
import { GiDogHouse } from "react-icons/gi";
import { IoMdPaw } from "react-icons/io";
import { FaCarAlt } from "react-icons/fa";

export default function VolunteerCard({ voluntario }) {
  return (
    <div className="card">
      <div className="card-heading">
        <h2>{voluntario.titulo}</h2>
      </div>
      <div className="card-center-info">
        <div className="card-left-info">
          <div className="card-shelter">
            <IconMapPin color="gray" size={17} stroke={2} />
            <h6>{voluntario.direccionRefugio.localidad}</h6>
          </div>
          <div className="card-shelter">
            <IconCalendar color="gray" size={17} stroke={2} />
            <h6>{voluntario.fechaPublicacion}</h6>
          </div>
        </div>
        <div className="card-right">
          <IconVolunteer caso={voluntario.tipoVoluntariado}></IconVolunteer>
        </div>
      </div>
      <div className="card-center">
        <p>{voluntario.descripcion}</p>
      </div>
      <div className="card-bottom">
        <div className="card-shelter">
          <img src={Logo} className="card-img-shelter" alt="Imagen refugio" />
          <h6>{voluntario.nombreRefugio}</h6>
        </div>
        <div className="card-right-info">
          <Link to={`/volunteer_posts/${voluntario.id}`} className="btn-info">
            Postularse
          </Link>
        </div>
      </div>
    </div>
  );
}

function IconVolunteer(props) {
  const casos = props.caso;
  switch (casos) {
    case "FOTOGRAFIA":
      return <AiFillCamera />;
    case "TRANSPORTE":
      return <FaCarAlt />;
    case "COMPANIA":
      return <GiDogHouse />;
    case "OTRO":
      return <IoMdPaw />;
    default:
      return null;
  }
}
