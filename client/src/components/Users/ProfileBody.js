import React, { useEffect, useState } from "react";
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
import { Link, Navigate, useNavigate, useParams } from "react-router-dom";
import AnimalCard from "../animals/animalCard";
import TransitCard from "../transit/transitCard";
import VolunteerCard from "../volunteers/volunteerCard";
import DonationCard from "../donations/donationCard";
// import ListAnimals from "../animals/animalCards";

const ProfileBody = ({ refugio }) => {
  const [expressAdopt, setExpressAdopt] = useState([]);
  const [expressTran, setExpressTran] = useState([]);
  const [adopt, setAdopt] = useState([]);
  const [tran, setTran] = useState([]);
  const [vol, setVol] = useState([]);
  const [don, setDon] = useState([]);
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    fetch(
      `http://localhost:8080/api/refugios/${id}/publicacionesAdopcion/urgentes`
    )
      .then((e) => e.json())
      .then((d) => {
        return setExpressAdopt(d);
      });
  }, []);

  useEffect(() => {
    fetch(
      `http://localhost:8080/api/refugios/${id}/publicacionesTransito/urgentes`
    )
      .then((e) => e.json())
      .then((d) => {
        return setExpressTran(d);
      });
  }, [expressAdopt]);

  useEffect(() => {
    fetch(`http://localhost:8080/api/refugios/${id}/publicacionesAdopcion`)
      .then((e) => e.json())
      .then((d) => {
        return setAdopt(d);
      });
  }, [expressTran]);

  useEffect(() => {
    fetch(`http://localhost:8080/api/refugios/${id}/publicacionesTransito`)
      .then((e) => e.json())
      .then((d) => {
        return setTran(d);
      });
  }, [setAdopt]);

  useEffect(() => {
    fetch(`http://localhost:8080/api/refugios/${id}/publicacionesVoluntariado`)
      .then((e) => e.json())
      .then((d) => {
        return setVol(d);
      });
  }, [setTran]);

  useEffect(() => {
    fetch(`http://localhost:8080/api/refugios/${id}/publicacionesDonacion`)
      .then((e) => e.json())
      .then((d) => {
        return setDon(d);
      });
  }, [setVol]);

  return (

    <>
    <button className="go-back-detail" onClick={() => navigate(-1)}>
      {"<"} Volver atras
    </button>
    <section className="container-perfil">
      <img className="hero" src={refugio.perfilRefugio.banner} alt="Imagen animal" />
      <section className="basic">
        <section className="heading-shelter">
          <div className="logo-name-location">
            <Avatar src={refugio.perfilRefugio.fotoPerfil} size={200} radius={100} mt={-80} />
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
          <div className="description-gallery">
            {
              refugio.perfilRefugio.galeriaImagenes.map((linkImagen, pos) => {
                return (
                    <img key={pos} src={linkImagen} alt={`Foto galeria nro. ${pos}`} />
                )
              })
            }
          </div>
        </section>
        <section className="posts-shelter">
          <article>
            {expressAdopt && expressAdopt.length > 0 ? (
              <Title color={refugio.perfilRefugio.color}>Urgentes</Title>
            ) : null}
            <div className="grid-profile">
              {expressAdopt && expressAdopt.length > 0
                ? expressAdopt.map((animal) => <AnimalCard animal={animal} />)
                : null}
              {expressTran && expressTran.length > 0
                ? expressAdopt.map((animal) => <TransitCard animal={animal} />)
                : null}
            </div>
          </article>
          <article>
            {adopt && adopt.length > 0 ? (
              <Title color={refugio.perfilRefugio.color}>Adopciones</Title>
            ) : null}
            <div className="grid-profile">
              {adopt && adopt.length > 0
                ? adopt.map((animal) => <AnimalCard animal={animal} />)
                : null}
            </div>
          </article>
          <article>
            {tran && tran.length > 0 ? (
              <Title color={refugio.perfilRefugio.color}>Transitos</Title>
            ) : null}
            <div className="grid-profile">
              {tran && tran.length > 0
                ? tran.map((animal) => <TransitCard animal={animal} />)
                : null}
            </div>
          </article>
          <article>
            {vol && vol.length > 0 ? (
              <Title color={refugio.perfilRefugio.color}>Voluntariados</Title>
            ) : null}
            <div className="grid-profile">
              {vol && vol.length > 0
                ? vol.map((voluntario) => (
                    <VolunteerCard voluntario={voluntario} />
                  ))
                : null}
            </div>
          </article>
          <article>
            {don && don.length > 0 ? (
              <Title color={refugio.perfilRefugio.color}>Donaciones</Title>
            ) : null}
            <div className="grid-profile">
              {don && don.length > 0
                ? don.map((donacion) => <DonationCard donacion={donacion} />)
                : null}
            </div>
          </article>
        </section>
      </section>
    </section>
    </>
  );
};

export default ProfileBody;
