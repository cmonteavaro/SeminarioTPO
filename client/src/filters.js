const noFilter = (data) => {
	console.log("Parametro no filtrable");
	return data;
};

const filterByEstadoPublicacion = (data, options) => {
	return data.filter((element) => options.includes(element.estadoPublicacion));
};

const filterByTipoAnimal = (data, options) => {
	//Options es el array de las opciones seleccionadas
	if (options.length === 0) {
		return data;
	}
	return data.filter((element) => options.includes(element.animal.tipoAnimal));
};

const filterByTamanioActual = (data, options) => {
	//Options es el array de las opciones seleccionadas
	if (options.length === 0) {
		return data;
	}
	return data.filter((element) => options.includes(element.animal.tamanioActual));
};

const filterByTamanioEsperado = (data, options) => {
	//Options es el array de las opciones seleccionadas
	if (options.length === 0) {
		return data;
	}
	return data.filter((element) => options.includes(element.animal.tamanioEsperado));
};

const filterByEdad = (data, min, max) => {
	//Min y max definen el rango de edad
	return data.filter((element) => element.animal.edadInteger >= min && element.animal.edadInteger <= max);
};

const filterByUrgente = (data) => {
	return data.filter((element) => element.esUrgente);
};

const filterByNecesitaPatio = (data) => {
	return data.filter((element) => element.necesitaPatio);
};

const filterByPuedeConvivirConInfantes = (data) => {
	return data.filter((element) => element.puedeConvivirConInfantes);
};

const filterByPuedeConvivirConGatos = (data) => {
	return data.filter((element) => element.puedeConvivirConGatos);
};

const filterByPuedeConvivirConCachorros = (data) => {
	return data.filter((element) => element.puedeConvivirConCachorros);
};

const filterByPuedeConvivirConPerrosAdultos = (data) => {
	return data.filter((element) => element.puedeConvivirConPerrosAdultos);
};

const filterByRequiereHogarAmplio = (data) => {
	return data.filter((element) => element.requiereHogarAmplio);
};

const filterByTransporteCubierto = (data) => {
	return data.filter((element) => element.requiereHogarAmplio);
};

const filterByCastrado = (data) => {
	return data.filter((element) => element.animal.castrado);
};

const init = (data) => {
	fullData = data;
};

const config = {
	//Publicacion
	"Es urgente": filterByUrgente,
	"Necesita patio": filterByNecesitaPatio,
	"Puede convivir con infantes": filterByPuedeConvivirConInfantes,
	"Puede convivir con gatos": filterByPuedeConvivirConGatos,
	"Puede convivir con cachorros": filterByPuedeConvivirConCachorros,
	"Puede convivir con perros adultos": filterByPuedeConvivirConPerrosAdultos,
	"Requiere hogar amplio": filterByRequiereHogarAmplio,
	Castrado: filterByCastrado,

	//No filter (no se puede/debe filtrar por estos parametros)
	"Gastos alimentacion cubiertos": noFilter,
	"Gastos medicos cubiertos": noFilter,
	"Esquema completo vacunas": noFilter,
	Desparasitado: noFilter,
	"Transporte cubierto": noFilter,
};

let fullData;

const applyFilters = (data, filters) => {
	const tamanios = [];
	filters["Chico"] && tamanios.push("Chico");
	filters["Mediano"] && tamanios.push("Mediano");
	filters["Grande"] && tamanios.push("Grande");
	filters["Indefinido"] && tamanios.push("Indefinido");
	delete filters["Chico"];
	delete filters["Mediano"];
	delete filters["Grande"];
	delete filters["Indefinido"];

	const tipoAnimal = [];
	filters["Gato"] && tipoAnimal.push("Gato");
	filters["Perro"] && tipoAnimal.push("Perro");
	delete filters["Gato"];
	delete filters["Perro"];

	let spreadFullData = [...fullData];
	//filtra tipo animal y tamanio
	spreadFullData = filterByTamanioEsperado(spreadFullData, tamanios);
	spreadFullData = filterByTipoAnimal(spreadFullData, tipoAnimal);

	//fitra booleanos
	for (const [key, value] of Object.entries(filters)) {
		if (value) {
			spreadFullData = config[key](spreadFullData);
		}
	}
	const filteredIds = spreadFullData.map((item) => item.idPublicacion);
	return data.filter((item) => filteredIds.includes(item.idPublicacion));
};

export { applyFilters, init };
