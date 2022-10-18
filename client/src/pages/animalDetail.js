import Zaguates from "../images/shelters/zaguates.webp";
import Coco from "../images/coco.webp";
import { Badge } from "@mantine/core";
import { FaInstagram, FaFacebook, FaTwitter } from "react-icons/fa";
import { Link } from "react-router-dom";

import "../styles/animalDetail.css";

const db = [
  {
    name: "Coco",
    image: Coco,
    state: "Disponible",
    location: "CABA",
    description:
      "Coco es una perrita que fue abandonada en una caja con su hermano a los 3 meses. Por suerte, la persona que la encontro pudo darle un hogar a ella y a su hermano hasta que el refugio Zaguates llego a la ayuda. Ahora esta siendo transitada por una estudiante la cual le da mucho amor.Es una muy educada y jugetona, disfruta el dormir y el comer, esta esperando un hogar en el cual esten felices de tenerla.",
    shelter: {
      name: "Zaguates",
      logo: Zaguates,
    },
    properties: {
      tamanioActual: "Pequeño",
      tamanioEsperado: "Mediano",
      castrado: "No",
      edad: "3 meses",
      desparasitado: "Si",
      medicacion: "No",
      vacunas: "Completo",
      nacimiento: "24/06/2022",
      fechaPublicacion: "12/10/2022",
      puedeConvivir: {
        animalesAdultos: "Si",
        infantes: "Si",
        gatos: "Si",
        animalesCachorros: "Si",
      },
    },
  },
];

export default function AnimalDetail() {
  return (
    <main className="animal-detail">
      <div className="">
        <Link to="/posts" className="go-back-detail">
          {"<"} Volver atras
        </Link>
      </div>
      <section className="detail">
        <section className="images-detail">
          <img src={db[0].image} className="image-detail-big" />
        </section>
        <section className="info-detail">
          <div className="info-detail-wrapper">
            <div className="info-detail-heading">
              <h2>{db[0].name}</h2>
              <p className="fecha-publicacion">
                Fecha de Publicacion: {db[0].properties.fechaPublicacion}
              </p>
            </div>
            <div className="info-detail-status">
              <Badge color={"lime"} variant={"dark"}>
                {db[0].state.toUpperCase()}
              </Badge>
            </div>
          </div>

          <div className="property-wrapper">
            <p className="property">
              Tamanio Actual:
              <span className="property-info">
                {db[0].properties.tamanioActual}
              </span>
            </p>
            <p className="property">
              Tamanio Esperado:{" "}
              <span className="property-info">
                {db[0].properties.tamanioEsperado}
              </span>
            </p>
            <p className="property">
              Edad:{" "}
              <span className="property-info">{db[0].properties.edad}</span>
            </p>
            <p className="property">
              Nacimiento:{" "}
              <span className="property-info">
                {db[0].properties.nacimiento}
              </span>
            </p>
            <p className="property">
              Castrado:{" "}
              <span className="property-info">{db[0].properties.castrado}</span>
            </p>
            <p className="property">
              Desparasitado:{" "}
              <span className="property-info">
                {db[0].properties.desparasitado}
              </span>
            </p>
            <p className="property">
              Medicacion:{" "}
              <span className="property-info">
                {db[0].properties.medicacion}
              </span>
            </p>
            <p className="property">
              Vacunas:{" "}
              <span className="property-info">{db[0].properties.vacunas}</span>
            </p>
            <div>
              <p className="property">
                Puede convivir con:
                <div>
                  <ul className="convivencia-animal">
                    <li className="property-info convivencia-item">
                      Infantes: {db[0].properties.puedeConvivir.infantes}
                    </li>
                    <li className="property-info convivencia-item">
                      Gatos: {db[0].properties.puedeConvivir.gatos}
                    </li>
                    <li className="property-info convivencia-item">
                      Perros Cachorros:{" "}
                      {db[0].properties.puedeConvivir.animalesCachorros}
                    </li>
                    <li className="property-info convivencia-item">
                      Perros Adultos:{" "}
                      {db[0].properties.puedeConvivir.animalesAdultos}
                    </li>
                  </ul>
                </div>
              </p>
            </div>
          </div>

          <article className="info-detail-description">
            {db[0].description}
          </article>
          <div className="info-detail-footer">
            <div className="info-detail-shelter">
              <div className="info-detail-shelter-name">
                <img src={db[0].shelter.logo} />
                <h5>{db[0].shelter.name}</h5>
              </div>
              <div className="info-detail-shelter-links">
                <FaFacebook size={30} />
                <FaInstagram size={30} />
                <FaTwitter size={30} />
              </div>
            </div>
            <div className="info-detail-button">
              <button className="btn-adopt"> Adoptar</button>
            </div>
          </div>
        </section>
      </section>
    </main>
  );
}
