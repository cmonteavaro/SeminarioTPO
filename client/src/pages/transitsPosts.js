import { Loader } from "@mantine/core";
import { useEffect, useState } from "react";
import ListTransits from "../components/transit/transitCards";
import NotFound from "./notFound";
import AnimalFilters from "../components/animals/animalFilters";

export default function Transits() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [filtersJSON, setfiltersJSON] = useState();
  const [filtersDict, setFiltersDict] = useState({});
  const [usarUbicacion, setUsarUbicacion] = useState(false);
	const [ubicacion, setUbicacion] = useState("");

  // Get the data from the server
  useEffect(() => {
    setLoading(true);
    Promise.all([
      fetch(`http://localhost:8080/api/publicaciones/transitos`),
      fetch(`http://localhost:8080/api/publicaciones/filtros`),
    ])
      .then(([resTransitos, resFiltros]) =>
        Promise.all([resTransitos.json(), resFiltros.json()])
      )
      .then(([dataTransitos, dataFiltros]) => {
        setData(dataTransitos);
        setfiltersJSON(dataFiltros);
      })
      .finally(() => setLoading(false));
  }, []);

  // Pass the filters into an array
  useEffect(() => {
    if (typeof filtersJSON !== "undefined") {
      filtersJSON.multivalores.TipoAnimalEnum.map((filtro) =>
        setFiltersDict((prevState) => ({ ...prevState, [filtro]: false }))
      );
      filtersJSON.multivalores.TamanioEnum.map((filtro) =>
        setFiltersDict((prevState) => ({ ...prevState, [filtro]: false }))
      );
      filtersJSON.booleanos.map((filtro) =>
        setFiltersDict((prevState) => ({ ...prevState, [filtro]: false }))
      );
      setFiltersDict((prevState) => ({ ...prevState, ["Es urgente"]: false }));
    }
  }, [data]);

  useEffect(() => {
		fetch(`http://localhost:8080/api/publicaciones/transitos/distance`,{
			method: "GET",
			headers: {
				'longitud': ubicacion[0],
				'latitud': ubicacion[1]
			}
		})
		.then((response) => response.json())
		.then((data) => {
			setData(data)
		})
	},[usarUbicacion]);

  // Callback function called on AnimalFilters to change the state of the filter given
  function handleCheckboxToggle(event) {
    const value = event.currentTarget.value;
    const checked = event.currentTarget.checked;
    let filtros = filtersDict;
    filtros[value] = checked;
    setFiltersDict(filtros);
  }

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
        <section className="filters">
          <AnimalFilters
            filtros={filtersJSON}
            callback={handleCheckboxToggle}
            setUbicacion={setUbicacion}
			      setUsarUbicacion={setUsarUbicacion}
          />
        </section>
        <section className="cards">
          <ListTransits props={data} />
        </section>
      </div>
    );
  }
}
