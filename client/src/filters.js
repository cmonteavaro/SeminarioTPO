const filterByEstadoPublicacion = (data, options) => {
	//Options es el array de las opciones seleccionadas
	//Data es la publicacion COMPLETA del animal
	return data.filter((element) => options.includes(element.estadoPublicacion));
};

const filterByTamanioActual = (data, options) => {
	//Options es el array de las opciones seleccionadas
	//Data es la publicacion COMPLETA del animal
	return data.filter((element) => options.includes(element.animal.tamanioActual));
};

const filterByTamanioEsperado = (data, options) => {
	//Options es el array de las opciones seleccionadas
	//Data es la publicacion COMPLETA del animal
	return data.filter((element) => options.includes(element.animal.tamanioEsperado));
};

const filterByEdad = (data, min, max) => {
	//Min y max definen el rango de edad
	//Data es la publicacion COMPLETA del animal
	return data.filter((element) => element.animal.edadInteger >= min && element.animal.edadInteger <= max);
};

const filterByUrgente = (data) => {
	//Data es la publicacion COMPLETA del animal
	return data.filter((element) => element.esUrgente);
};

const filterByNecesitaPatio = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option ? data.filter((element) => element.necesitaPatio) : data.filter((element) => !element.necesitaPatio);
};

const filterByPuedeConvivirConInfantes = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option ? data.filter((element) => element.puedeConvivirConInfantes) : data.filter((element) => !element.puedeConvivirConInfantes);
};

const filterByPuedeConvivirConGatos = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option ? data.filter((element) => element.puedeConvivirConGatos) : data.filter((element) => !element.puedeConvivirConGatos);
};

const filterByPuedeConvivirConCachorros = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option ? data.filter((element) => element.puedeConvivirConCachorros) : data.filter((element) => !element.puedeConvivirConCachorros);
};

const filterByPuedeConvivirConPerrosAdultos = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option
		? data.filter((element) => element.puedeConvivirConPerrosAdultos)
		: data.filter((element) => !element.puedeConvivirConPerrosAdultos);
};

const filterByRequiereHogarAmplio = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option ? data.filter((element) => element.requiereHogarAmplio) : data.filter((element) => !element.requiereHogarAmplio);
};

const filterByTransporteCubierto = (data, option) => {
	//Data es la publicacion COMPLETA del animal
	return option ? data.filter((element) => element.requiereHogarAmplio) : data.filter((element) => !element.requiereHogarAmplio);
};
