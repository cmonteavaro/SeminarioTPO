import Zaguates from "../images/shelters/zaguates.webp";
import Coco from "../images/coco.webp";
import Tag from "../components/badge/badge";
import { FaInstagram, FaFacebook, FaTwitter } from "react-icons/fa";
import { Link, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { Loader } from "@mantine/core";
import NotFound from "./notFound";

// import Modal from "../components/animals/modalPreForm";
// import Form from "../components/form/form";

import "../styles/animalDetail.css";

function isTrue(estado) {
  if (estado) return "Si";
  return "No";
}

export default function TransitDetail() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);
  //   const [showModal, setShowModal] = useState(false);
  //   const [showForm, setShowForm] = useState(false);

  const { id } = useParams();

  useEffect(() => {
    setLoading(true);
    fetch(`http://localhost:8080/api/publicaciones/transitos/${id}`)
      .then((e) => e.json())
      .then((d) => {
        return setData(d);
      })
      .finally(() => setLoading(false));
  }, []);
  if (loading) {
    return (
      <div className="loading">
        <div className="loader">
          <Loader color="lime" />
        </div>
        <h3>Estamos buscando a todos los animalitos para presentarte 😊</h3>
      </div>
    );
  }

  if (data.length < 1) return <NotFound />;
  return (
    <main className="animal-detail">
      <div>
        <Link to="/transitos" className="go-back-detail">
          {"<"} Volver atras
        </Link>
      </div>
      <section className="detail">
        <section className="images-detail">
          <img src={Coco} className="image-detail-big" alt="Imagen animal" />
        </section>
        <section className="info-detail">
          <div className="info-detail-wrapper">
            <div className="info-detail-heading">
              <h2>{data.animal.nombre}</h2>
              <p className="fecha-publicacion">
                Fecha de Publicacion: {data.fechaPublicacion}
              </p>
            </div>
            <div className="info-detail-status">
              <Tag state={data.estadoPublicacion} />
            </div>
          </div>

          <div className="property-wrapper">
            <p className="property">
              Tamaño Actual:{" "}
              <span className="property-info">{data.animal.tamanioActual}</span>
            </p>
            {/* <p className="property">
              Tamaño Esperado:{" "}
              <span className="property-info">
                {data.animal.tamanioEsperado}
              </span>
            </p> */}
            <p className="property">
              Edad: <span className="property-info">{data.animal.edad}</span>
            </p>
            {/* <p className="property">
              Nacimiento:{" "}
              <span className="property-info">{data.animal.fechaNac}</span>
            </p> */}
            <p className="property">
              Castrado:{" "}
              <span className="property-info">
                {isTrue(data.animal.castrado)}
              </span>
            </p>
            <p className="property">
              Desparasitado:{" "}
              <span className="property-info">
                {isTrue(data.animal.desparasitado)}
              </span>
            </p>
            {/* <p className="property">
              Medicacion: <span className="property-info">{"Si"}</span>
            </p> */}
            {/* <p className="property">
              Vacunas:{" "}
              <span className="property-info">
                {isTrue(data.animal.esquemaCompletoVacunas)}
              </span>
            </p> */}
            <div>
              <p className="property">
                Puede convivir con:
                <div>
                  <ul className="convivencia-animal">
                    <li className="property-info convivencia-item">
                      Infantes: {isTrue(data.puedeConvivirConInfantes)}
                    </li>
                    <li className="property-info convivencia-item">
                      Gatos: {isTrue(data.puedeConvivirConGatos)}
                    </li>
                    <li className="property-info convivencia-item">
                      Cachorros: {isTrue(data.puedeConvivirConCachorros)}
                    </li>
                    <li className="property-info convivencia-item">
                      Perros Adultos:{" "}
                      {isTrue(data.puedeConvivirConPerrosAdultos)}
                    </li>
                  </ul>
                </div>
              </p>
            </div>
          </div>

          <article className="info-detail-description">
            {data.descripcion}
          </article>
          <div className="info-detail-footer">
            <div className="info-detail-shelter">
              <div className="info-detail-shelter-name">
                <img src={Zaguates} alt="Logo Refugio" />
                <h5>{data.nombreRefugio}</h5>
              </div>
              <div className="info-detail-shelter-links">
                <FaFacebook size={30} />
                <FaInstagram size={30} />
                <FaTwitter size={30} />
              </div>
            </div>
            <div className="info-detail-button">
              <button
                className="btn-adopt"
                // onClick={() =>
                //   animalRestrictionsFiltered.length > 0
                //     ? setShowModal(true)
                //     : setShowForm(true)
                // }
              >
                {" "}
                Transitar
              </button>
            </div>
          </div>
        </section>
        {/* <Modal
          show={showModal}
          animalRestrictions={animalRestrictions}
          onClose={() => setShowModal(false)}
          openForm={() => {
            setShowForm(true);
            setShowModal(false);
          }}
        />
        <Form show={showForm} data={data} onClose={() => setShowForm(false)} /> */}
      </section>
    </main>
  );
}
