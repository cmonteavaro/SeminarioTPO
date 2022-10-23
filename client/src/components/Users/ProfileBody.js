import React from "react";
import { Avatar, Text, Group } from "@mantine/core";
import { IconMapPin } from "@tabler/icons";
import { FaInstagram, FaFacebook, FaTwitter } from "react-icons/fa";
import { Carousel } from "@mantine/carousel";

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
import ListAnimals from "../animals/animalCards";

const getTag = (tagName) => {
  switch (tagName) {
    case "FaInstagram":
      return <FaInstagram size={30} className="icon" title="Icono Instagram" />;
    case "FaFacebook":
      return <FaFacebook size={30} className="icon" title="Icono Facebook" />;
    case "FaTwitter":
      return <FaTwitter size={30} className="icon" title="Icono Twitter" />;
    default:
      return null;
  }
};

const socialMedia = (rrss) => {
  const names = ["instagram", "facebook", "twitter"];
  let arrayLinks = [];
  let current = "";
  for (let index = 0; index < rrss.length; index++) {
    current = rrss[index].redSocial.toLowerCase();

    if (names.includes(current)) {
      current = current[0].toUpperCase() + current.substring(1);
      current = "Fa" + current;
      arrayLinks.push(getTag(current));
    }
  }

  return (
    <div className="links-container-shelter">
      {arrayLinks.map((d) => {
        return d;
      })}
    </div>
  );
};

const ProfileBody = ({ refugio }) => {
  const redes = socialMedia(refugio.redesSociales);
  return (
    <section className="basic">
      <img className="hero" src={Five} />

      <section className="heading-shelter">
        <div className="logo-name-location">
          <Avatar src={logorefugio} size={200} radius={100} mt={-80} />
          <div style={{ flex: 1, marginLeft: 20 }}>
            <Text size="40px" weight={500}>
              {refugio.nombre}
            </Text>
            <Group spacing={5} position="left" wrap>
              <IconMapPin color="gray" size={20} stroke={2} />
              <Text color="gray" size="m">
                {refugio.direccion.localidad}
              </Text>
            </Group>
          </div>
        </div>
        <div className="links-shelter">
          {redes}
          <Link className="donate" to="/">
            Donar
          </Link>
        </div>
      </section>

      <section className="description-shelter">
        <article>
          <h2>Quienes somos</h2>
          <p>{refugio.perfilRefugio.descripcionLarga}</p>
        </article>
        <section className="caroussel">
          <Carousel slideSize="100%" height={500} slideGap="md" loop>
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
          </Carousel>
        </section>
      </section>
      <section className="posts-shelter">
        <article>
          <h2>Urgentes</h2>
          {/* <ListAnimals /> */}
        </article>
        <article>
          <h2>Nuestros Animales</h2>
          {/* <ListAnimals /> */}
        </article>
      </section>
    </section>
  );
};

export default ProfileBody;
