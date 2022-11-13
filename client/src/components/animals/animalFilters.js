import { useEffect, useState } from "react";
import { Checkbox } from '@mantine/core';

export default function AnimalFilter(props){

    const data = props.filtros;

    return (
        <div className="filtersContainer">
            <h1>Filtros</h1>
            <div className="filter-section">
                <h4>Urgencia</h4>
                <Checkbox value={"Es urgente"} label={"Es urgente"} onChange={event => props.callback(event)}/>
            </div>
            <div className="filter-section">
                <h4>Animal</h4>
                {data.multivalores.TipoAnimalEnum && data.multivalores.TipoAnimalEnum.length > 0 ? 
                    data.multivalores.TipoAnimalEnum.map(filtro => <Checkbox value={filtro} label={filtro} 
                                                        onChange={event => props.callback(event)}/>) 
                    : null  
                }
            </div>
            <div className="filter-section">
                <h4>Tamaño</h4>
                {data.multivalores.TamanioEnum && data.multivalores.TamanioEnum.length > 0 ? 
                    data.multivalores.TamanioEnum.map(filtro => <Checkbox value={filtro} label={filtro}
                                                            onChange={event => props.callback(event)}/>) : null  
                }
            </div>
            <div className="filter-section">
                <h4>Extras</h4>
                {data.booleanos && data.booleanos.length > 0 ? 
                    data.booleanos.map(filtro => <Checkbox value={filtro} label={filtro}
                                                            onChange={event => props.callback(event)}/>) : null  
                }
            </div>
        </div>
    )


}

