import React from "react";
import { HeroImageBackground } from "./hero";
import { Container, Avatar, Text, Group } from "@mantine/core";
import { IconMapPin } from "@tabler/icons";
import { FaInstagram, FaFacebook, FaTwitter } from "react-icons/fa";
import Posts from "../../pages/posts";

// Import imagenes
import Mila from "../../images/coco.webp";
import Milo from "../../images/milo.webp";
import Coco from "../../images/coco.webp";
import logorefugio from "../../images/shelters/zaguates.webp";

// Import estilos
import "./profileBody.css";
import { Link } from "react-router-dom";

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
      <HeroImageBackground />

      <section className="heading-shelter">
        <div className="logo-name-location">
          <Avatar src={logorefugio} size={200} radius={100} mt={-80} />
          <div style={{ flex: 1 }}>
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

      <section className="row row-left">
        <article>
          <h2 style={{ color: "#000000" }}>Quienes somos</h2>
          <p>{refugio.perfilRefugio.descripcionLarga}</p>
        </article>
        <img
          className="image-row"
          alt="Pero"
          src={Mila}
          style={{ marginRight: 20, marginLeft: 40 }}
        />
        <img
          className="image-row"
          alt="Perra"
          src={Milo}
          style={{ marginRight: 20 }}
        />
        <img
          className="image-row"
          alt="Perro"
          src={Coco}
          style={{ marginRight: 20 }}
        />
      </section>
      <section className="row row-left">
        <article>
          <h2 style={{ color: "#000000" }}>Urgentes</h2>
          <Posts />
        </article>
      </section>
      <section className="row row-left">
        <article>
          <h2 style={{ color: "#000000" }}>Nuestros Animales</h2>
          <Posts />
        </article>
      </section>
    </section>
  );
};

export default ProfileBody;
