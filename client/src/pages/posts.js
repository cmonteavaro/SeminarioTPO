import ListAnimals from "../components/animals/animalCards";
import "../styles/posts.css";
import { useEffect, useState } from "react";
import { Loader } from "@mantine/core";

import Coco from "../images/coco.webp";
import Mila from "../images/mila.webp";

import Zaguates from "../images/shelters/zaguates.webp";
import PatitasAccion from "../images/shelters/patitasAccion.webp";

export default function Posts() {
  const db = [
    {
      name: "Coco",
      image: Coco,
      state: "Disponible",
      location: "CABA",
      shelter: {
        name: "Zaguates",
        logo: Zaguates,
      },
    },
    {
      name: "Mila",
      image: Mila,
      state: "En proceso",
      location: "Quilmes",
      shelter: {
        name: "Patitas en accion",
        logo: PatitasAccion,
      },
    },
  ];

  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
    }, 5000);
  }, []);

  useEffect(() => {
    if (db < 1) {
      console.log("No hay animalitos");
    }
    setData(
      db.map((animal) => {
        return { ...animal };
      })
    );
  }, [loading]);

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

  return (
    <div className="container">
      <section className="filters"></section>
      <section className="cards">
        <ListAnimals props={data} />
      </section>
    </div>
  );
}
