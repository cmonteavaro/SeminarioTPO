// import { Link } from "react-router-dom";
import "../../styles/card.css";
import Logo from "../../images/shelters/zaguates.webp";
import { IconMapPin } from "@tabler/icons";
import { IconCalendar } from "@tabler/icons";
import { AiFillCamera } from "react-icons/ai";
import { GiDogHouse } from "react-icons/gi";
import { IoMdPaw } from "react-icons/io";
import { FaCarAlt } from "react-icons/fa";
import { useState } from "react";
import FormExample from "../form/formVol";

export default function VolunteerCard({ voluntario }) {
  const [showForm, setShowForm] = useState(false);

  return (
    <>
      <div className="donation-card">
        <div className="card-heading">
          <h2>{voluntario.titulo}</h2>
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
        </div>
        <div className="card-center">
          <p>{voluntario.descripcion}</p>
        </div>
        <div className="card-bottom">
          <div className="card-shelter">
            <img src={voluntario.fotoPerfilRefugio} className="card-img-shelter" alt="Imagen refugio" />
            <h6>{voluntario.nombreRefugio}</h6>
          </div>
          <div className="card-right-info">
            <button className="btn-info" onClick={() => setShowForm(true)}>
              {" "}
              Postularse
            </button>
          </div>
        </div>
      </div>
      <FormExample
        show={showForm}
        data={voluntario}
        onClose={() => setShowForm(false)}
      />
    </>
  );
}

function IconVolunteer(props) {
  const casos = props.caso;
  switch (casos) {
    case "FOTOGRAFIA":
      return <AiFillCamera  style={{color: "purple"}} />;
    case "TRANSPORTE":
      return <FaCarAlt  style={{color: "blue"}} />;
    case "COMPANIA":
      return <GiDogHouse  style={{color: "red"}}/>;

  }
  return <IoMdPaw />;
}