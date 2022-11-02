import { Loader } from "@mantine/core";
import { useEffect, useState } from "react";
import ListTransits from "../components/transit/transitCards";
import NotFound from "./notFound";
import "../styles/transit.css";

const data = [
  {
    nombreAnimal: "Coco",
    estadoPublicacion: "Disponible",
    esUrgente: false,
    nombreRefugio: "Zaguates",
  },
];

export default function Transits() {
  // const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    setTimeout(setLoading(false), 5000);
  }, []);

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

  if (data.length < 1) {
    return <NotFound />;
  } else {
    return (
      <div className="container">
        <section className="cards">
          <ListTransits props={data} />
        </section>
      </div>
    );
  }
}
