
import { Link } from "react-router-dom";
import "../../styles/card.css";
import Logo from "../../images/shelters/zaguates.webp";
import { IconMapPin } from "@tabler/icons";
import { IconCalendar } from "@tabler/icons";

export default function VolunteerCard ({ voluntario }) {
    const route = `/volunteer_posts/${voluntario.id}`;
;
 
  return (
    <div className="volunteer-card">
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
            <img src={"https://static.vecteezy.com/system/resources/previews/006/998/435/non_2x/photo-camera-icons-photo-camera-icon-design-illustration-photo-camera-simple-sign-photo-camera-logo-free-vector.jpg"} className="card-volunteer" style={{ border: "1px solid #555"}} alt="Imagen animal" />
        </div>
      </div>
      <div className="card-bottom">
            <div className="card-volunteer-info">
            <p>{voluntario.descripcion}</p>
            
            </div>
        </div>
        <div className="card-bottom">
            <div className="card-shelter">
            <img src={Logo} className="card-img-shelter" alt="Imagen refugio" />
            <h6>{voluntario.nombreRefugio}</h6>
            </div>
            <div className="card-right-info">
            <Link to={route} className="btn-info">
            Postular
            </Link>
            </div>
        </div>
    </div>

  );
}
