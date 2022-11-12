import { useEffect, useState } from "react";
import { Checkbox } from '@mantine/core';

export default function AnimalFilter(){

    const [data, setData] = useState();
    const [loading, setLoading] = useState(true);
    const [filters, setFilters] = useState({}); 


    useEffect(() => {
        console.log(filters);
    }, [filters]);

    useEffect(() => {
        setLoading(true);
        fetch(`http://localhost:8080/api/publicaciones/filtros`)
          .then((e) => e.json())
          .then((d) => {
            setData(d);
            data.tipoAnimal.map(filtro => setFilters(prevState => ({...prevState, [filtro]: false })))
            data.tamanioAnimal.map(filtro => setFilters(prevState => ({...prevState, [filtro]: false })))
            return null;
          }).finally(() => setLoading(false))
      }, []);
    
    function handleCheckboxToggle(event){
        const value = event.currentTarget.value;
        console.log(value)
        const checked = event.currentTarget.checked
        setFilters(prevState => ({...prevState, `${value}`: checked }))    
    }  

    if (!loading) {
        return (
            <div className="filtersContainer">
                <h1>Filtros</h1>
                <div className="filter-section">
                    <h4>Urgencia</h4>
                    <Checkbox value={"Es urgente"} label={"Es urgente"} onChange={event => handleCheckboxToggle(event)}/>
                </div>
                <div className="filter-section">
                    <h4>Animal</h4>
                    {data.tipoAnimal && data.tipoAnimal.length > 0 ? 
                        data.tipoAnimal.map(filtro => <Checkbox value={filtro} label={filtro} 
                                                            onChange={event => handleCheckboxToggle(event)}/>) 
                        : null  
                    }
                </div>
                <div className="filter-section">
                    <h4>Tamaño</h4>
                    {data.tamanioAnimal && data.tamanioAnimal.length > 0 ? 
                        data.tamanioAnimal.map(filtro => <Checkbox value={filtro} label={filtro}
                                                                onChange={event => handleCheckboxToggle(event)}/>) : null  
                    }
                </div>
            </div>
        )
    }
    return <></>
}

