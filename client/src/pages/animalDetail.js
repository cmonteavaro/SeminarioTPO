import Zaguates from "../images/shelters/zaguates.webp";
import Coco from "../images/coco.webp";
import { Badge } from "@mantine/core";
import { FaInstagram, FaFacebook, FaTwitter } from "react-icons/fa";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Loader } from "@mantine/core";
import NotFound from "./notFound";
import Modal from "../components/animals/modalPreForm"
import AnimalCard from "../components/animals/animalCard.js";

import "../styles/animalDetail.css";
import { useSetState } from "@mantine/hooks";
import { Urgencia } from "../components/animals/animalCard";

function isTrue(estado) {
  if (estado) return "Si";
  return "No";
}

function ageCalculator(date) {
  //collect input from HTML form and convert into date format
  let dob = new Date(date);

  let ageString = "";
  //check user provide input or not
  if (dob == null || dob == "") {
    return "No hay fecha";
  }

  //execute if the user entered a date
  else {
    //extract the year, month, and date from user date input
    let dobYear = dob.getYear();
    let dobMonth = dob.getMonth();
    let dobDate = dob.getDate();

    //get the current date from the system
    let now = new Date();
    //extract the year, month, and date from current date
    let currentYear = now.getYear();
    let currentMonth = now.getMonth();
    let currentDate = now.getDate();

    //declare a variable to collect the age in year, month, and days

    //get years
    let yearAge = currentYear - dobYear;

    //get months
    let monthAge;
    if (currentMonth >= dobMonth) {
      //get months when current month is greater
      monthAge = currentMonth - dobMonth;
    } else {
      yearAge--;
      monthAge = 12 + currentMonth - dobMonth;
    }

    //get days
    let dateAge;
    if (currentDate >= dobDate) {
      //get days when the current date is greater
      dateAge = currentDate - dobDate;
    } else {
      monthAge--;
      dateAge = 31 + currentDate - dobDate;

      if (monthAge < 0) {
        monthAge = 11;
        yearAge--;
      }
    }
    //group the age in a single variable
    let age = {};
    age = {
      years: yearAge,
      months: monthAge,
      days: dateAge,
    };

    if (age.years > 0 && age.months > 0 && age.days > 0) {
      ageString =
        age.years + " años, " + age.months + " meses, y " + age.days + " dias.";
    } else if (age.years == 0 && age.months == 0 && age.days > 0) {
      ageString = age.days + " dias.";
    }
    //when current month and date is same as birth date and month
    else if (age.years > 0 && age.months == 0 && age.days == 0) {
      ageString = age.years + " años. Es el cumpleaños 🎂";
    } else if (age.years > 0 && age.months > 0 && age.days == 0) {
      ageString = age.years + " años y " + age.months + " meses.";
    } else if (age.years == 0 && age.months > 0 && age.days > 0) {
      ageString = age.months + " meses y " + age.days + " dias.";
    } else if (age.years > 0 && age.months == 0 && age.days > 0) {
      ageString = age.years + " años, y" + age.days + " dias.";
    } else if (age.years == 0 && age.months > 0 && age.days == 0) {
      ageString = age.months + " meses.";
    }
    //when current date is same as dob(date of birth)
    else {
      ageString = "Recien nacido 👶";
    }
  }

  return ageString;
}

export default function AnimalDetail() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [showModal, setShowModal] = useState(false);

  const {id} = useParams();

  useEffect(() => {
    setLoading(true);
    fetch(`http://localhost:8080/api/publicaciones/adopciones/${id}`)
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

  // retrieve the restrictions
  var animalRestrictions = {'convivirConCachorros': data.puedeConvivirConCachorros,
                              'convivirConInfantes': data.puedeConvivirConInfantes,
                              'convivirConGatos': data.puedeConvivirConGatos,
                              'convivirConPerrosAdultos': data.puedeConvivirConPerrosAdultos};
  
    // if the animal has any restriction we need to show the modal before going to the form
    var animalRestrictionsFiltered =   Object.entries(animalRestrictions).filter(([key,value]) => value == false);

  if (data.length < 1) {
    return <NotFound />;
  } else {
    let color;
    let variant;
    let text;
    switch (data.estadoPublicacion) {
      case "DISPONIBLE":
        color = "lime";
        variant = "dark";
        text = `${data.estadoPublicacion}`;
        break;
      case "EN_PROCESO":
        color = "yellow";
        variant = "dark";
        text = "En proceso";
        break;
      case "FINALIZADA":
        color = "red";
        variant = "dark";
        text = `${data.estadoPublicacion}`;
        break;
      default:
        color = "gray";
        variant = "dark";
        text = "Sin info";
        break;
    }

    return (
      <main className="animal-detail">
        <div className="">
          <Link to="/posts" className="go-back-detail">
            {"<"} Volver atras
          </Link>
        </div>
        <section className="detail">
          <section className="images-detail">
            <img src={Coco} className="image-detail-big" />
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
                <div className="info-urgente">
                  <Urgencia urg={data.esUrgente}></Urgencia>
                </div>
                <Badge color={color} variant={variant}>
                  {text}
                </Badge>
              </div>
            </div>

            <div className="property-wrapper">
              <p className="property">
                Tamaño Actual:{" "}
                <span className="property-info">
                   {data.animal.tamanioActual}
                </span>
              </p>
              <p className="property">
                Tamaño Esperado:{" "}
                <span className="property-info">
                  {data.animal.tamanioEsperado}
                </span>
              </p>
              <p className="property">
                Edad:{" "}
                <span className="property-info">
                  {ageCalculator(data.animal.fechaNac)}
                </span>
              </p>
              <p className="property">
                Nacimiento:{" "}
                <span className="property-info">{data.animal.fechaNac}</span>
              </p>
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
              <p className="property">
                Medicacion: <span className="property-info">{"Si"}</span>
              </p>
              <p className="property">
                Vacunas:{" "}
                <span className="property-info">
                  {isTrue(data.animal.esquemaCompletoVacunas)}
                </span>
              </p>
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
                  <img src={Zaguates} />
                  <h5>{data.nombreRefugio}</h5>
                </div>
                <div className="info-detail-shelter-links">
                  <FaFacebook size={30} />
                  <FaInstagram size={30} />
                  <FaTwitter size={30} />
                </div>
              </div>
              <div className="info-detail-button">
                <button className="btn-adopt" onClick={() => animalRestrictionsFiltered.length>0 ? setShowModal(true) : console.log('IR AL FORM') }> Adoptar</button>
              </div>
            </div>
          </section>
          <Modal show={showModal} animalRestrictions={animalRestrictions} onClose={() => setShowModal(false)}/>
        </section>
      </main>
    );
  }
}
