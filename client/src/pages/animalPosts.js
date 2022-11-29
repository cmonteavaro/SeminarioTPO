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
	const [usarUbicacion, setUsarUbicacion] = useState(false);
	const [ubicacion, setUbicacion] = useState([]);
	const [ubicacionString, setUbicacionString] = useState("");


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
					let SSubicacion =  JSON.parse(sessionStorage.getItem("ubicacion"));
					let SSubicacionString = sessionStorage.getItem("ubicacionString");
					if(SSfilters!==null){
						setFiltersDict(SSfilters);
						if(SSubicacion!==null){
							setUbicacion(SSubicacion)
							setUsarUbicacion(true)
							setUbicacionString(SSubicacionString)
						}else{
							setDataDisplay(applyFilters([...dataAdopciones], { ...SSfilters }));
						}
					}else{
						buildEmptyFiltersDict(titulosFiltros);
						if(SSubicacion !== null){
							setUbicacion(SSubicacion);
							setUsarUbicacion(true);
							setUbicacionString(SSubicacionString)
						}else{
							setDataDisplay(dataAdopciones);
						}
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

	useEffect(() => {
		if(usarUbicacion){
			fetch(`http://localhost:8080/api/publicaciones/adopciones/distance`,{
				method: "GET",
				headers: {
					'longitud': ubicacion[0],
					'latitud': ubicacion[1]
				}
			})
			.then((response) => response.json())
			.then((data) => {
				if(data.length < 1){
					setDataDisplay([])
				}else{
					setDataFull(data)
					setDataDisplay(applyFilters([...data], {...filtersDict}))
					sessionStorage.setItem("ubicacion",JSON.stringify(ubicacion));
					sessionStorage.setItem("ubicacionString",ubicacionString);
				}
			})
		}else{
			fetch(`http://localhost:8080/api/publicaciones/adopciones`)
			.then((response) => response.json())
			.then((data) => {
				if(data.length < 1){
					setDataDisplay([])
				}else{
					setDataFull(data)
					setDataDisplay(applyFilters([...data], {...filtersDict}))
				}
			})
		}
	},[usarUbicacion, ubicacion, filtersDict]);

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

	function clearFilters(){
		clearLocation();
		buildEmptyFiltersDict(filtersJSON);
		sessionStorage.removeItem("filtersAdopciones")
	}

	function clearLocation(){
		setUsarUbicacion(false);
		setUbicacion([]);
		setUbicacionString("");
		sessionStorage.removeItem("ubicacion")
		sessionStorage.removeItem("ubicacionString")
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
        <h2>Adopciones</h2>
        <div className="grid-posts">
          <section className="filters">
            <AnimalFilters
              filtros = {filtersJSON}
			  filtrosDict = {filtersDict}
              callback={handleCheckboxToggle}
			  setUbicacion={setUbicacion}
			  setUsarUbicacion={setUsarUbicacion}
			  setUbicacionString={setUbicacionString}
			  clearFilters={clearFilters}
			  clearLocation={clearLocation}
			  ubicacionTextFromSS={ubicacionString}
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
