import React from "react";
import ProfileBody from "../components/Users/ProfileBody";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import NotFound from "./notFound";
import { Loader } from "@mantine/core";

const Profile = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);
  const shelter = useParams();
  useEffect(() => {
    setLoading(true);
    fetch(`http://localhost:8080/api/refugios/${shelter.id}/perfil`)
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
    return <ProfileBody refugio={data} />;
  }
};

export default Profile;
