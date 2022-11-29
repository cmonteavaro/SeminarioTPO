import { Checkbox } from "@mantine/core";
import { useRef, useState } from "react";
import { NavDropdown } from "react-bootstrap";
import Navbar from "react-bootstrap/Navbar";
import "./filtros.scss";


export default function AnimalFilter(props) {

  async function handleSearch (inputSearch) {
    let coords = [];
    await fetch(`https://api.maptiler.com/geocoding/${inputSearch}.json?key=Mdlvp8JndCrWtOqNUat6`)
    .then((response) => response.json())
    .then((s) => {
      const obj = s['features'];
      const place = obj[0];
      const geometry = place['geometry'];
      coords = geometry['coordinates'];
    })
    if(typeof(coords[0]) === "number"){
        setUbicacion(coords);
        setUsarUbicacion(true);
    } else {
        if(typeof(coords[0][0]) === "number"){
          setUbicacion(coords[0]);
          setUsarUbicacion(true);
        } else {
          setUbicacion(coords[0][0]);
          setUsarUbicacion(true);
        }
    }
    
  }

  const data = props.filtros;
  const setUbicacion = props.setUbicacion;
  const setUsarUbicacion = props.setUsarUbicacion;
  const inputSearch = useRef();
  const filtrosDict = props.filtrosDict;

  return (
    <Navbar bg="light" expand="lg">
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav" className="filters-bar">
        <nav className="me-auto filtros">
          <h3>Filtros</h3>
          <div className="filtros-container">
            <div className="filter-section-container">
              <h4>Ubicacion</h4>
              <div>
                <input className="buscador-maps" autoComplete="off" id="search" type="text" ref={inputSearch} placeholder="Lima 775, Monserrat, Buenos Aires"/>
                <button className="boton-maps" onClick={() => handleSearch(inputSearch.current.value)}>Ubicar</button>
              </div>
            </div>
            <div className="filter-section-container">
              <h4>Urgencia</h4>
              <div className="filter-section">
                <Checkbox
                  value={"Es urgente"}
                  label={"Es urgente"}
                  checked={filtrosDict["Es urgente"]}
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
                        checked={filtrosDict[filtro]}
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
                        checked={filtrosDict[filtro]}
                        onChange={(event) => props.callback(event)}
                      />
                    ))
                  : null}
              </div>
            </div>
            <div className="filter-section-container">
              <NavDropdown title="Extras" className="dropwdown-filters">
                  {data.booleanos && data.booleanos.length > 0
                    ? data.booleanos.map((filtro) => (
                        <Checkbox
                          value={filtro}
                          label={filtro}
                          checked={filtrosDict[filtro]}
                          onChange={(event) => props.callback(event)}
                        />
                      ))
                    : null}
              </NavDropdown>
            </div>
          </div>
        </nav>
      </Navbar.Collapse>
    </Navbar>
  );
}
