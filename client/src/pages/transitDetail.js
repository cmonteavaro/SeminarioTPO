import Tag from "../components/badge/badge";
import { Link, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { Loader } from "@mantine/core";
import NotFound from "./notFound";
import SocialMedia from "../components/rrss/socialMedia";

import "../styles/animalDetail.css";
import FormTran from "../components/form/formTran";

function isTrue(estado) {
  if (estado) return "Si";
  return "No";
}

export default function TransitDetail() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [showForm, setShowForm] = useState(false);

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
          <img
            src={data.animal.imagenPrincipal}
            className="image-detail-big"
            alt="Imagen animal"
          />
          <div className="image-gallery-detail">
            {data.animal.galeriaImagenes.length > 0
              ? data.animal.galeriaImagenes.map((imagen) => (
                  <img src={imagen} alt="Imagen adicional del animal." />
                ))
              : null}
          </div>
        </section>
        <section className="info-detail">
          <div className="info-detail-wrapper">
            <div className="info-detail-heading">
              <h2>{data.animal.nombre}</h2>
              <p className="fecha-publicacion">
                Fecha de Publicación: {data.fechaPublicacion}
              </p>
              <p className="ubicacion">
                Ubicación: {data.direccionRefugio.localidad}
              </p>
            </div>
            <div className="info-detail-urgente">
              <Tag state={data.esUrgente} />
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
            <p className="property">
              Tamaño Esperado:{" "}
              <span className="property-info">
                {data.animal.tamanioEsperado}
              </span>
            </p>
            <p className="property">
              Tipo Animal:{" "}
              <span className="property-info">{data.animal.tipoAnimal}</span>
            </p>
            <p className="property">
              Edad: <span className="property-info">{data.animal.edad}</span>
            </p>
            <p className="property">
              Castrado:{" "}
              <span className="property-info">
                {isTrue(data.animal.castrado)}
              </span>
            </p>
            <p className="property">
              Vacunacion Completa:{" "}
              <span className="property-info">
                {isTrue(data.animal.esquemaCompletoVacunas)}
              </span>
            </p>
            <p className="property">
              Desparasitado:{" "}
              <span className="property-info">
                {isTrue(data.animal.desparasitado)}
              </span>
            </p>
            <p className="property">
              Necesita Patio:{" "}
              <span className="property-info">
                {isTrue(data.necesitaPatio)}
              </span>
            </p>
            <p className="property">
              Necesita Hogar Amplio:{" "}
              <span className="property-info">
                {isTrue(data.requiereHogarAmplio)}
              </span>
            </p>
            <p className="property">
              Duración Mínima:{" "}
              <span className="property-info">{data.duracionMinima}</span>
            </p>
            <div>
              <p className="property">
                <p className="convivencia-animal"> Puede convivir con:</p>
                <div className="">
                  <p className="property-info convivencia-item">
                    Infantes: {isTrue(data.puedeConvivirConInfantes)}
                  </p>
                  <p className="property-info convivencia-item">
                    Gatos: {isTrue(data.puedeConvivirConGatos)}
                  </p>
                  <p className="property-info convivencia-item">
                    Cachorros: {isTrue(data.puedeConvivirConCachorros)}
                  </p>
                  <p className="property-info convivencia-item">
                    Perros Adultos: {isTrue(data.puedeConvivirConPerrosAdultos)}
                  </p>
                </div>
              </p>
            </div>
            <div>
              <p className="property">
                <p className="convivencia-animal"> Gastos Cubiertos:</p>
                <div className="">
                  <p className="property-info convivencia-item">
                    Transporte: {isTrue(data.transporteCubierto)}
                  </p>
                  <p className="property-info convivencia-item">
                    Medicos: {isTrue(data.gastosMedicosCubiertos)}
                  </p>
                  <p className="property-info convivencia-item">
                    Alimentacion: {isTrue(data.gastosAlimentacionCubiertos)}
                  </p>
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
                <Link to={`/refugios/${data.idRefugio}`}>
                  <img
                    src={data.fotoPerfilRefugio}
                    className="card-img-shelter"
                    alt="Imagen refugio"
                  />
                </Link>
                <h5>{data.nombreRefugio}</h5>
              </div>

              <div className="info-detail-shelter-links">
                {<SocialMedia rrss={data.redesSocialesRefugio} />}
              </div>
            </div>
            <div className="info-detail-button">
              <button className="btn-adopt" onClick={() => setShowForm(true)}>
                {" "}
                Transitar
              </button>
            </div>
          </div>
        </section>
        <FormTran
          show={showForm}
          data={data}
          onClose={() => setShowForm(false)}
        />
      </section>
    </main>
  );
}
