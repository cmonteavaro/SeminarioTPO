import ListAnimals from "../components/animals/animalCards";
import "../styles/posts.css";
import { useEffect, useState } from "react";
import { Loader } from "@mantine/core";
import NotFound from "./notFound";
import AnimalFilters from "../components/animals/animalFilters";
import { applyFilters, init as filtersInit } from "../filters";

export default function Posts() {
	const [dataDisplay, setDataDisplay] = useState([]);
	const [dataFull, setDataFull] = useState([]);
	const [loading, setLoading] = useState(false);
	const [filtersJSON, setfiltersJSON] = useState();
	const [filtersDict, setFiltersDict] = useState({});
	const [usarUbicacion, setUsarUbicacion] = useState(false);
	const [ubicacion, setUbicacion] = useState("");

	// Get the data from the server
	useEffect(() => {
		setLoading(true);
		Promise.all([
			fetch(`http://localhost:8080/api/publicaciones/adopciones`),
			fetch(`http://localhost:8080/api/publicaciones/filtros`),
			])
			.then(([resAdopciones, resFiltros]) =>
				Promise.all([resAdopciones.json(), resFiltros.json()])
			)
			.then(([dataAdopciones, dataFiltros]) => {
				setDataDisplay(dataAdopciones);
				setDataFull(dataAdopciones);
				setfiltersJSON(dataFiltros);
			})
			.finally(() => {
				fetch("http://localhost:8080/api/publicaciones/adopciones/fullView")
				.then((res) => res.json())
				.then((dataFullView)=>{
					filtersInit(dataFullView);
					setLoading(false);})
			});
	}, []);

	// Pass the filters into an array
	useEffect(() => {
		if (typeof filtersJSON !== "undefined") {
			filtersJSON.multivalores.TipoAnimalEnum.map((filtro) =>
				setFiltersDict((prevState) => ({
					...prevState,
					[filtro]: false,
				}))
			);
			filtersJSON.multivalores.TamanioEnum.map((filtro) =>
				setFiltersDict((prevState) => ({
					...prevState,
					[filtro]: false,
				}))
			);
			filtersJSON.booleanos.map((filtro) =>
				setFiltersDict((prevState) => ({
					...prevState,
					[filtro]: false,
				}))
			);
			setFiltersDict((prevState) => ({
				...prevState,
				["Es urgente"]: false,
			}));
		}
	}, [filtersJSON]);

	useEffect(() => {
		fetch(`http://localhost:8080/api/publicaciones/adopciones/distance`,{
			method: "GET",
			headers: {
				'longitud': ubicacion[0],
				'latitud': ubicacion[1]
			}
		})
		.then((response) => response.json())
		.then((data) => {
			setDataFull(data)
			setDataDisplay(applyFilters([...data], {...filtersDict}))
		})
	},[usarUbicacion]);

	// Callback function called on AnimalFilters to change the state of the filter given
	function handleCheckboxToggle(event) {
		const value = event.currentTarget.value;
		const checked = event.currentTarget.checked;
		let filtros = filtersDict;
		filtros[value] = checked;
		setFiltersDict(filtros);
		setDataDisplay(applyFilters([...dataFull], { ...filtersDict }));
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

  if (dataFull.length < 1) {
    return <NotFound />;
  } else {
    return (
      <>
        <h2>Conocelos</h2>
        <div className="grid-posts">
          <section className="filters">
            <AnimalFilters
              filtros={filtersJSON}
              callback={handleCheckboxToggle}
			  setUbicacion={setUbicacion}
			  setUsarUbicacion={setUsarUbicacion}
            />
          </section>
          <section className="cards">
            {dataDisplay.length < 1 ? (
						<p> No se encontraron publicaciones para tu busqueda</p>
					) : (
						<ListAnimals props={dataDisplay} />
					)}
          </section>
        </div>
      </>
    );
  }
}
