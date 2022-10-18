import React from "react";
import  { MDBBtn,  MDBTypography } from "mdb-react-ui-kit";
import AnimalCard from "../animals/animalCard";
import { HeroImageBackground } from "./hero";
import { createStyles, Container, ActionIcon, Button, Avatar, Text, Group, UnstyledButton } from "@mantine/core";
import { IconMapPin } from "@tabler/icons";
import { FaInstagram, FaFacebook, FaTwitter } from "react-icons/fa";
import Posts from "../../pages/posts";
import ListAnimals from "../animals/animalCards";

// Import imagenes
import Mila from "../../images/coco.webp";
import Milo from "../../images/milo.webp";
import Coco from "../../images/coco.webp";
import logorefugio from "../../images/logorefugio.png";

// Import estilos
import "../../styles/refugee.css";



const ProfileBody = ({ refugio, animal }) => {
  return (
    <section className="basic" >
      <HeroImageBackground></HeroImageBackground>
      <div>
      <Container  >
      <Group spacing={430} position="left" wrap size= "xs">
      <Group spacing={5} position="left" wrap size= "xs">
        <Avatar src={logorefugio} size={200} radius={100} mt={-80} />
        <div style={{ flex: 1 }}>
              <Text size="40px" weight={500} >
                {refugio.nombre}
              </Text>
              <Group spacing={5} position="left" wrap>
              <IconMapPin size={14} stroke={2.5} />
              <Text size="xs">
                {refugio.locacion}
              </Text>
              </Group>
            </div>
          </Group>
        <Group spacing={5} position="right" wrap mt={-50}>
          
          <ActionIcon size="lg"  >
            <FaFacebook size={30} className="icon" title="Icono GitHub" />
          </ActionIcon>
          <ActionIcon size="lg">
            <FaTwitter size={30} className="icon" title="Icono Bug" />
          </ActionIcon>
          <ActionIcon size="lg">
            <FaInstagram size={30} className="icon" title="Icono Bug" />
          </ActionIcon>
           </Group>
           </Group>
          <Group spacing={0} position="right" style={{marginRight: '300'}} mt={-50} >
          <Button style={{ backgroundColor: '#D9D9D9',
        color: '#000000', width: '200px', marginRight: '200'}}>Donar</Button>
         </Group>
      </Container>
    </div>
      <section className="row row-left">
        <article>
          <h2 style={{ color: "#000000" }}>Quienes somos</h2>
          <p>{refugio.descripcion}</p>
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
