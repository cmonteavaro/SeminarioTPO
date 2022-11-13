const filterByEstadoPublicacion = (data, options) => {
	//TODO
	//Options es el array de las opciones seleccionadas
	//Data es la publicacion COMPLETA del animal
	return data.filter((element) => options.includes(element.estadoPublicacion));
};

const filterByTamanioActual = (data, options) => {
	//TODO
	//Options es el array de las opciones seleccionadas
	//Data es la publicacion COMPLETA del animal
	return data.filter((element) => options.includes(element.animal.tamanioActual));
};

const filterByTamanioEsperado = (data, options) => {
	//TODO
	//Options es el array de las opciones seleccionadas
	//Data es la publicacion COMPLETA del animal
	return data.filter((element) => options.includes(element.animal.tamanioEsperado));
};

const filterByEdad = (data, min, max) => {
	//TODO
	//Min y max definen el rango de edad
	//Data es la publicacion COMPLETA del animal
	return data.filter((element) => element.animal.edadInteger >= min && element.animal.edadInteger <= max);
};

const filterByUrgente = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	if (option) {
		return data.filter((element) => element.esUrgente);
	}
};

const filterByNecesitaPatio = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option ? data.filter((element) => element.necesitaPatio) : data.filter((element) => !element.necesitaPatio);
};

const filterByPuedeConvivirConInfantes = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option
		? data.filter((element) => element.puedeConvivirConInfantes)
		: data.filter((element) => !element.puedeConvivirConInfantes);
};

const filterByPuedeConvivirConGatos = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option
		? data.filter((element) => element.puedeConvivirConGatos)
		: data.filter((element) => !element.puedeConvivirConGatos);
};

const filterByPuedeConvivirConCachorros = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option
		? data.filter((element) => element.puedeConvivirConCachorros)
		: data.filter((element) => !element.puedeConvivirConCachorros);
};

const filterByPuedeConvivirConPerrosAdultos = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option
		? data.filter((element) => element.puedeConvivirConPerrosAdultos)
		: data.filter((element) => !element.puedeConvivirConPerrosAdultos);
};

const filterByRequiereHogarAmplio = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option
		? data.filter((element) => element.requiereHogarAmplio)
		: data.filter((element) => !element.requiereHogarAmplio);
};

const filterByTransporteCubierto = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option
		? data.filter((element) => element.requiereHogarAmplio)
		: data.filter((element) => !element.requiereHogarAmplio);
};

const init = async () => {
	await fetch("http://localhost:8080/api/publicaciones/adopciones/fullView")
		.then((res) => res.json())
		.then((json) => (fullData = json));
};

const config = {
	"Es urgente": filterByUrgente,
	"Necesita patio": filterByNecesitaPatio,
	"Puede convivir con infantes": filterByPuedeConvivirConInfantes,
	"Puede convivir con gatos": filterByPuedeConvivirConGatos,
	"Puede convivir con cachorros": filterByPuedeConvivirConCachorros,
	"Puede convivir con perros adultos": filterByPuedeConvivirConPerrosAdultos,
	"Requiere hogar amplio": filterByRequiereHogarAmplio,
	"Transporte cubierto": filterByTransporteCubierto,
};

let fullData = [];

init();

const applyFilters = (data, filters) => {
	let spreadFullData = [...fullData];
	console.log("spreadfulldata");
	console.log(spreadFullData);
	for (const [key, value] of Object.entries(filters)) {
		if (value) {
			spreadFullData = config[key](spreadFullData, value);
		}
	}
	const filteredIds = spreadFullData.map((item) => item.idPublicacion);
	console.log("Filtered ids");
	console.log(filteredIds);
	return data.filter((item) => filteredIds.includes(item.idPublicacion));
};

export default applyFilters;
