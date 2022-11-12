import { useEffect, useState } from "react";
import { Checkbox } from '@mantine/core';

export default function AnimalFilter(){

    const [data, setData] = useState();
    const [filtersLoading, setLoading] = useState(true);
    const [filters, setFilters] = useState({}); 

    useEffect(() => {
        setLoading(true);
        fetch(`http://localhost:8080/api/publicaciones/filtros`)
          .then((e) => e.json())
          .then((d) => {
            setData(d)})     
        .finally(() => setLoading(false))
      }, []);
    
      useEffect(() => {
        if (typeof data !== 'undefined'){
            data.multivalores.TipoAnimalEnum.map(filtro => setFilters(prevState => ({...prevState, [filtro]: false })));
            data.multivalores.TamanioEnum.map(filtro => setFilters(prevState => ({...prevState, [filtro]: false })));
            data.booleanos.map(filtro => setFilters(prevState => ({...prevState, [filtro]: false })));
            setFilters(prevState => ({...prevState, ['Es urgente']: false }));
        }
    }, [data]);

    function handleCheckboxToggle(event){
        const value = event.currentTarget.value;
        const checked = event.currentTarget.checked;
        let filtros = filters;
        filtros[value] = checked;
        setFilters(filtros);  
    }  

    if (!filtersLoading) {
        return (
            <div className="filtersContainer">
                <h1>Filtros</h1>
                <div className="filter-section">
                    <h4>Urgencia</h4>
                    <Checkbox value={"Es urgente"} label={"Es urgente"} onChange={event => handleCheckboxToggle(event)}/>
                </div>
                <div className="filter-section">
                    <h4>Animal</h4>
                    {data.multivalores.TipoAnimalEnum && data.multivalores.TipoAnimalEnum.length > 0 ? 
                        data.multivalores.TipoAnimalEnum.map(filtro => <Checkbox value={filtro} label={filtro} 
                                                            onChange={event => handleCheckboxToggle(event)}/>) 
                        : null  
                    }
                </div>
                <div className="filter-section">
                    <h4>Tamaño</h4>
                    {data.multivalores.TamanioEnum && data.multivalores.TamanioEnum.length > 0 ? 
                        data.multivalores.TamanioEnum.map(filtro => <Checkbox value={filtro} label={filtro}
                                                                onChange={event => handleCheckboxToggle(event)}/>) : null  
                    }
                </div>
                <div className="filter-section">
                    <h4>Extras</h4>
                    {data.booleanos && data.booleanos.length > 0 ? 
                        data.booleanos.map(filtro => <Checkbox value={filtro} label={filtro}
                                                                onChange={event => handleCheckboxToggle(event)}/>) : null  
                    }
                </div>
            </div>
        )
    }
    return <></>
}

