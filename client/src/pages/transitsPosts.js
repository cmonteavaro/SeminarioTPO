import { Loader } from "@mantine/core";
import { useEffect, useState } from "react";
import ListTransits from "../components/transit/transitCards";
import NotFound from "./notFound";

export default function Transits() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    fetch(`http://localhost:8080/api/publicaciones/transitos`)
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
      <div className="own-container">
        <section className="cards">
          <ListTransits props={data} />
        </section>
      </div>
    );
  }
}
