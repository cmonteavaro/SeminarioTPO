import "../styles/about.css";
import FotoAbout from "../images/AboutUsFotoFinal.png";

export default function About() {
  return (
    <div className="aboutDiv">
      <div>
        <img src={FotoAbout} className="imagen"></img>
      </div>
      <div className="acerca-de-detail">
        <h1 className="titulo">¿Quiénes somos?</h1>
        <p className="parrafo">
          Nosotros, quienes desarrollamos esta plataforma, somos un grupo de estudiantes de informática apasionados por lo que hacemos, fans del trabajo que hacen los refugios animales y simpatizantes de aquellas personas que deciden colaborar con ellos de alguna manera.
          Sabemos que los refugios, según el alcance en redes sociales, tienen grandes <span className="palabraRemarcada">problemáticas de comunicación</span> en algunos casos y de <span className="palabraRemarcada">falta de llegada</span> en otros. Mientras que los potenciales adoptantes y colaboradores se pierden entre la <span className="palabraRemarcada">información descentralizada</span> y se les dificulta 
          encontrar aquellos refugios y animales con los que serán compatibles.
        </p>
        <img src={FotoAbout} className="imagen-parrafo"></img>
        <p className="parrafo">
          No obstante, el crédito no es solo nuestro, sino también de todos los <span className="palabraRemarcada">refugios animales y colaboradores</span> que nos brindaron su tiempo para realizarles encuestas, entrevistas y observaciones. 
          Esperamos que disfruten de este gran <span className="palabraRemarcada">trabajo en equipo</span> centrado en mejorar la vida de los pichichos.
        </p>
      </div>
    </div>
  );
}
