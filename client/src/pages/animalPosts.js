import ListAnimals from "../components/animals/animalCards";
import "../styles/posts.css";
import { useEffect, useState } from "react";
import { Loader } from "@mantine/core";
import NotFound from "./notFound";

export default function Posts() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    fetch(`http://localhost:8080/api/publicaciones/adopciones`)
      .then((e) => e.json())
      .then((d) => {
        return setData(d);
      })
      .finally(() => setLoading(false));
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
        {/* <section className="filters"></section> */}
        <section className="cards">
          <ListAnimals props={data} />
        </section>
      </div>
    );
  }
}
