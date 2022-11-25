import ListAnimals from "../components/animals/animalCards";
import "../styles/posts.css";
import { useEffect, useState } from "react";
import { Loader } from "@mantine/core";
import NotFound from "./notFound";
import AnimalFilters from "../components/animals/animalFilters";
import { applyFilters, init as filtersInit } from "../filters";
import FiltersEmptySearch from "../components/animals/filtersEmptySearch"

export default function Posts() {
	const [dataDisplay, setDataDisplay] = useState([]);
	const [dataFull, setDataFull] = useState([]);
	const [loading, setLoading] = useState(false);
	const [filtersJSON, setfiltersJSON] = useState();
	const [filtersDict, setFiltersDict] = useState({});

	// Get the data from the server
	useEffect(() => {
		setLoading(true);
		//Primero fetcheamos la info completa para los filtros
		fetch("http://localhost:8080/api/publicaciones/adopciones/fullView")
		.then((res) => res.json())
		.then((dataFullView) => filtersInit(dataFullView))
		.then(() => {
			//Luego la info de las cards 
			Promise.all([
				fetch(`http://localhost:8080/api/publicaciones/adopciones`),
				fetch(`http://localhost:8080/api/publicaciones/filtros`),
				])
				.then(([resAdopciones, resFiltros]) =>
					Promise.all([resAdopciones.json(), resFiltros.json()])
				)
				.then(([dataAdopciones, titulosFiltros]) => {
					setfiltersJSON(titulosFiltros);
					setDataFull(dataAdopciones);
					//Verificamos si existen filtros ya cargados
					let SSfilters = JSON.parse(sessionStorage.getItem("filtersAdopciones"));
					if(SSfilters!==null){
						setFiltersDict(SSfilters);
						setDataDisplay(applyFilters([...dataAdopciones], { ...SSfilters }));
					}else{
						buildEmptyFiltersDict(titulosFiltros);
						setDataDisplay(dataAdopciones);
					}				
				})
				.finally(() => {
					setLoading(false);
				});
			})
	}, []);

	// Pass the filters into an array
	const buildEmptyFiltersDict = (titulosFiltros) => {
		if (typeof titulosFiltros !== "undefined") {
			titulosFiltros.multivalores.TipoAnimalEnum.map((filtro) =>
				setFiltersDict((prevState) => ({
					...prevState,
					[filtro]: false,
				}))
			);
			titulosFiltros.multivalores.TamanioEnum.map((filtro) =>
				setFiltersDict((prevState) => ({
					...prevState,
					[filtro]: false,
				}))
			);
			titulosFiltros.booleanos.map((filtro) =>
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
	};

	// Callback function called on AnimalFilters to change the state of the filter given
	function handleCheckboxToggle(event) {
		const value = event.currentTarget.value;
		const checked = event.currentTarget.checked;
		let filtros = filtersDict;
		filtros[value] = checked;
		setFiltersDict(filtros);
		sessionStorage.setItem("filtersAdopciones",JSON.stringify(filtersDict));
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
              filtros = {filtersJSON}
			  filtrosDict = {filtersDict}
              callback={handleCheckboxToggle}
            />
          </section>
          <section className="cards">
            {dataDisplay.length < 1 ? (
						<FiltersEmptySearch/>
					) : (
						<ListAnimals props={dataDisplay} />
					)}
          </section>
        </div>
      </>
    );
  }
}
