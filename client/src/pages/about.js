import "../styles/about.css";
import FotoAbout from "../images/AboutUsFotoFinal.png";

export default function About() {
  return (
    <div className="aboutDiv">
      <div>
        <img src={FotoAbout} className="imagen"></img>
      </div>
      <div>
        <h1>¿Quiénes somos?</h1>
        <p>Hola somos unos genios. Gracias!</p>
      </div>
    </div>
  );
}
