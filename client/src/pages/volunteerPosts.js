import ListVolunteers from "../components/volunteers/volunteerCards";
import "../styles/posts.css";
import { useEffect, useState } from "react";
import { Loader } from "@mantine/core";
import NotFound from "./notFound";

export default function VolunteerPosts() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    fetch(`http://localhost:8080/api/publicaciones/voluntariados`)
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
        <h3>
          Estamos buscando a todos los posteos de voluntarios para presentarte
          😊
        </h3>
      </div>
    );
  }

  if (data.length < 1) {
    return <NotFound />;
  } else {
    return (
      <div>
        <section className="cards">
          <ListVolunteers props={data} />
        </section>
      </div>
    );
  }
}
