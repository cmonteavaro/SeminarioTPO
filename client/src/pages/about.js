import "../styles/about.css";
import FotoAbout from "../images/AboutUsFotoFinal.png";

export default function About() {
  return (
    <div className="aboutDiv">
      <div>
        <img src={FotoAbout} className="imagen"></img>
      </div>
      <div className="info-detail">
        <h1 className="titulo">¿Quiénes somos?</h1>
        <p className="parrafo">En el siguiente documento, se estará presentando el trabajo realizado durante los últimos meses en el marco de la materia Seminario de Integración Profesional I. 
Como equipo, hemos decidido orientar nuestro proyecto en torno a los refugios de animales en Argentina. 
A lo largo del archivo, se podrá observar cómo fue el proceso de investigación y de recolección de datos mediante diferentes técnicas de relevamiento e ideación, tales como brainstorming, design thinking (con modelos como User Persona y mapas de empatía), entrevistas, encuestas, mapas de empatía entre otras. Buscamos encontrar las mayores debilidades y focos de dolor que afrontan tanto los refugios, como así también, aquellas personas interesadas en colaborar con ellos. 
Con esto, planteamos la siguiente hipótesis: actualmente, hay grandes trabas y problemas de comunicación entre refugios y personas interesadas en colaborar, los cuales serán detallados en la siguiente sección: Problema.
Detectamos mediante research propio y técnicas de relevamiento, que los refugios con grandes cantidades de seguidores o gran exposición en las redes sociales, no cuentan con la capacidad de poder responder a todas las consultas que reciben y, en la otra cara de la moneda, los refugios con menos exposición se ven ante la dificultad de poder llegar a una mayor cantidad de personas que puedan adoptar, transitar, donar o colaborar con ellos de alguna manera. 
 </p>
      </div>
    </div>
  );
}
