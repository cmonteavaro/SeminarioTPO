// Import estilos
import "../styles/home.css";

// Import imagenes
import Mila from "../images/mila.webp";
import Coco from "../images/coco.webp";
import Paws from "../images/pawsBack.png";
import House from "../images/petHouse.png";
import Milo from "../images/milo.webp";

// Import iconos
import Donation from "../images/icons/donation.webp";
import HappyDog from "../images/icons/happyDog.webp";
import TY from "../images/icons/ty.webp";
import Volunteer from "../images/icons/volunteer.webp";
import Transit from "../images/icons/transit.webp";

// Import carousel
import { Carousel } from "@mantine/carousel";

// Import imagenes carousel
import One from "../images/carousel/1.jpg";
import Two from "../images/carousel/2.jpg";
import Three from "../images/carousel/3.jpg";
import Four from "../images/carousel/4.jpg";
import Five from "../images/carousel/5.jpg";
import Six from "../images/carousel/6.jpg";

export default function Home() {
  return (
    <section>
      <section className="caroussel">
        <Carousel
          slideSize="70%"
          height={550}
          slideGap="xl"
          loop
          withIndicators
        >
          <Carousel.Slide>
            <img className="carousel" src={One} />
          </Carousel.Slide>
          <Carousel.Slide>
            <img className="carousel" src={Two} />
          </Carousel.Slide>
          <Carousel.Slide>
            <img className="carousel" src={Three} />
          </Carousel.Slide>
          <Carousel.Slide>
            <img className="carousel" src={Four} />
          </Carousel.Slide>
          <Carousel.Slide>
            <img className="carousel" src={Five} />
          </Carousel.Slide>
          <Carousel.Slide>
            <img className="carousel" src={Six} />
          </Carousel.Slide>
        </Carousel>
      </section>
      <section className="basic">
        <section className="row  row-left">
          <img
            className="image-row"
            alt="Perro mirando a una pileta"
            src={Mila}
          />
          <article className="info r">
          <h2 className="titulo">Nuestra motivaci??n</h2>
            <p className="parrafo">
              A lo largo de nuestra vida, hemos visto una
              enorme cifra de animales que han sido abandonados o que no han
              recibido amor y, a su vez, una gran cantidad de refugios que no cuentan con la ayuda necesaria.
              Hoy en d??a a trav??s de las redes sociales hemos podido conectar con personas 
              que tienen el fin de ayudarlos.
            </p>
          </article>
          <article className="background">
            <img src={Paws} />
          </article>
        </section>
        <section className="row row-right">
          <article className="background">
            <img src={House} />
          </article>
          <article className="info r">
            <h2 className="titulo">Mi Refugio</h2>
            <p className="parrafo">
              Nuestro fin es poder facilitar la conexi??n entre las personas y aquellos animales que
              en b??squeda de hogar o tr??nsito. 
              Por esta raz??n, decidimos desarrollar un sitio 
              donde las comunidades, refugios y voluntarios puedan conectar de una forma m??s 
              f??cil, r??pida y centralizada, para poder darles una mejor calidad de vida a cada uno de
              los animales.
            </p>
          </article>
          <img
            className="image-row"
            alt="Perra tomando sol en balcon"
            src={Coco}
          />
        </section>
        <section className="row  row-center">
          <img
            className="image-row"
            alt="Perro mirando a una pileta"
            src={Milo}
          />
          <article className="info r">
          <h2  className="titulo">C??mo funciona?</h2>
            <p className="parrafo">
             Si tenes intenciones de colaborar con alg??n refugio, podes encontrarlos
              en la secci??n "Refugios". Si deseas transitar o adoptar
              a alg??n animal, podr??s encontrarlos en la secci??n "Publicaciones".
            </p>
          </article>
        </section>
        <section className="stats">
          <section className="column">
            <h2>Al d??a de hoy hemos logrado...</h2>
            <article className="row">
              <div>
                <img className="stats-img" src={HappyDog} />
                <p className="stats-info">+200 adopciones</p>
              </div>
              <div>
                <img className="stats-img" src={Transit} />
                <p className="stats-info">+300 tr??nsitos</p>
              </div>
            </article>
            <article className="row">
              <div>
                <img className="stats-img" src={Volunteer} />
                <p className="stats-info">+120 voluntariados</p>
              </div>
              <div>
                <img className="stats-img" src={Donation} />
                <p className="stats-info">+2300 donaciones</p>
              </div>
            </article>
            <section className="center">
              <img src={TY} className="thankYou" />
            </section>
          </section>
        </section>
      </section>
    </section>
  );
}

