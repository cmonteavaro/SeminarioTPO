import { Checkbox } from "@mantine/core";
import { useRef, useState } from "react";
import Navbar from "react-bootstrap/Navbar";
import "./filtros.scss";

export default function AnimalFilter(props) {
  const data = props.filtros;
  const [coordenadas, setCoordenadas] = useState([]);
  const inputSearch = useRef(null);
  function handleSearch () {
    fetch(`https://api.maptiler.com/geocoding/${inputSearch.current.value}.json?key=Mdlvp8JndCrWtOqNUat6`)
    .then((response) => response.json())
    .then((s) => {
      const obj = s['features'];
      const place = obj[0];
      const geometry = place['geometry'];
      const coords = geometry['coordinates'];
      console.log(coords);
      return setCoordenadas(coords)
    })

  }

  return (
    <Navbar bg="light" expand="lg">
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <nav className="me-auto filtros">
          <h3>Filtros</h3>
          <div className="filtros-container">
            <div className="filter-section-container">
              <h4>Urgencia</h4>
              <div className="filter-section">
                <Checkbox
                  value={"Es urgente"}
                  label={"Es urgente"}
                  onChange={(event) => props.callback(event)}
                />
              </div>
            </div>
            <div className="filter-section-container">
              <h4>Animal</h4>
              <div className="filter-section">
                {data.multivalores.TipoAnimalEnum &&
                data.multivalores.TipoAnimalEnum.length > 0
                  ? data.multivalores.TipoAnimalEnum.map((filtro) => (
                      <Checkbox
                        value={filtro}
                        label={filtro}
                        onChange={(event) => props.callback(event)}
                      />
                    ))
                  : null}
              </div>
            </div>
            <div className="filter-section-container">
              <h4>Tamaño</h4>
              <div className="filter-section">
                {data.multivalores.TamanioEnum &&
                data.multivalores.TamanioEnum.length > 0
                  ? data.multivalores.TamanioEnum.map((filtro) => (
                      <Checkbox
                        value={filtro}
                        label={filtro}
                        onChange={(event) => props.callback(event)}
                      />
                    ))
                  : null}
              </div>
            </div>
            <div className="filter-section-container">
              <h4>Extras</h4>
              <div className="filter-section">
                {data.booleanos && data.booleanos.length > 0
                  ? data.booleanos.map((filtro) => (
                      <Checkbox
                        value={filtro}
                        label={filtro}
                        onChange={(event) => props.callback(event)}
                      />
                    ))
                  : null}
              </div>
            </div>
          </div>
          <div className="maps">
            <input className="buscador-maps" autoComplete="off" id="search" type="text" ref={inputSearch} placeholder="Escribí tu ubicación..."/>
            <button className="boton-maps" onClick={handleSearch}>Ubicar</button>
          </div>
        </nav>
      </Navbar.Collapse>
    </Navbar>
  );
}
