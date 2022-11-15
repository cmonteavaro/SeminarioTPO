import { Link } from "react-router-dom";
import "../../styles/card.css";
import Logo from "../../images/shelters/zaguates.webp";
import { IconPill } from "@tabler/icons";
import { IconDogBowl } from "@tabler/icons";
import { IconShirtSport } from "@tabler/icons";
import { IconSquarePlus } from "@tabler/icons";
import { IconMapPin } from "@tabler/icons";
import { IconCalendar } from "@tabler/icons";

export default function DonationCard({ donacion }) {
  return (
    <div className="card">
      <div className="card-heading">
        <h2>{donacion.titulo}</h2>
      </div>
      <div className="card-center-info">
        <div className="card-left-info">
          <div className="card-shelter">
            <IconMapPin color="gray" size={17} stroke={2} />
            <h6>{donacion.direccionRefugio.localidad}</h6>
          </div>
          <div className="card-shelter">
            <IconCalendar color="gray" size={17} stroke={2} />
            <h6>{donacion.fechaPublicacion}</h6>
          </div>
        </div>
        <div className="card-right">
          <IconDonation caso={donacion.tipo}></IconDonation>
        </div>
      </div>
      <div className="card-center">
        <p>{donacion.descripcion}</p>
      </div>
      <div className="card-bottom">
        <div className="card-shelter">
          <img src={Logo} className="card-img-shelter" alt="Imagen refugio" />
          <h6>{donacion.nombreRefugio}</h6>
        </div>
        <div className="card-right-info">
          <Link to={`/refugees/${donacion.idRefugio}`} className="btn-info">
            Contactarse
          </Link>
        </div>
      </div>
    </div>
  );
}

function IconDonation(props) {
  const casos = props.caso;
  switch (casos) {
    case "ABRIGO":
      return <IconShirtSport></IconShirtSport>;
    case "ALIMENTO":
      return <IconDogBowl></IconDogBowl>;
    case "MEDICAMENTO":
      return <IconPill></IconPill>;
    // case "OTRO":
    //     return <IconSquarePlus></IconSquarePlus>;
  }
  return <IconSquarePlus></IconSquarePlus>;
}
