import React from "react";
import { Avatar, Text, Group, Title } from "@mantine/core";
import { IconMapPin } from "@tabler/icons";
import { Carousel } from "@mantine/carousel";
import SocialMedia from "../rrss/socialMedia";

// Import imagenes
import logorefugio from "../../images/shelters/zaguates.webp";

import One from "../../images/carousel/1.jpg";
import Two from "../../images/carousel/2.jpg";
import Three from "../../images/carousel/3.jpg";
import Four from "../../images/carousel/4.jpg";
import Five from "../../images/carousel/5.jpg";

// Import estilos
import "./profileBody.css";
import { Link } from "react-router-dom";
// import ListAnimals from "../animals/animalCards";

const ProfileBody = ({ refugio }) => {
  console.log(refugio);
  const styleBtn = `background-color: ${refugio.perfilRefugio.color};color: white`;
  return (
    <section>
      <Link to="/refugios" className="go-back-profile">
        {"<"} Volver atras
      </Link>
      <img className="hero" src={Five} alt="Imagen animal" />
      <section className="basic">
        <section className="heading-shelter">
          <div className="logo-name-location">
            <Avatar src={logorefugio} size={200} radius={100} mt={-80} />
            <div style={{ flex: 1, marginLeft: 20 }}>
              <Title
                color={refugio.perfilRefugio.color}
                size="40px"
                weight={500}
              >
                {refugio.nombre}
              </Title>
              <Group spacing={5} position="left" wrap>
                <IconMapPin color="black" size={20} stroke={2} />
                <Text color={refugio.perfilRefugio.color} size="m">
                  {refugio.direccion.localidad}
                </Text>
              </Group>
            </div>
          </div>
          <div className="links-shelter">
            {
              <SocialMedia
                rrss={refugio.redesSociales}
                color={refugio.perfilRefugio.color}
              />
            }
            <a
              className="donate"
              style={{
                backgroundColor: refugio.perfilRefugio.color,
                color: "white",
              }}
              href={refugio.linkDonacionesMonetarias}
            >
              Donar
            </a>
          </div>
        </section>
        <section className="description-shelter">
          <article>
            <Title color={refugio.perfilRefugio.color}>Quienes somos</Title>
            <p>{refugio.perfilRefugio.descripcionLarga}</p>
          </article>
          <section className="caroussel">
            <Carousel slideSize="100%" height={500} slideGap="md" loop>
              <Carousel.Slide>
                <img className="carousel" src={One} alt="Imagen animal" />
              </Carousel.Slide>
              <Carousel.Slide>
                <img className="carousel" src={Two} alt="Imagen animal" />
              </Carousel.Slide>
              <Carousel.Slide>
                <img className="carousel" src={Three} alt="Imagen animal" />
              </Carousel.Slide>
              <Carousel.Slide>
                <img className="carousel" src={Four} alt="Imagen animal" />
              </Carousel.Slide>
            </Carousel>
          </section>
        </section>
        <section className="posts-shelter">
          <article>
            <Title color={refugio.perfilRefugio.color}>Urgentes</Title>
            {/* <ListAnimals /> */}
          </article>
          <article>
            <Title color={refugio.perfilRefugio.color}>Nuestros Animales</Title>
            {/* <ListAnimals /> */}
          </article>
        </section>
      </section>
    </section>
  );
};

export default ProfileBody;
