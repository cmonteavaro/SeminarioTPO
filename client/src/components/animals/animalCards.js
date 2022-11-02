import AnimalCard from "./animalCard";
import "../../styles/grid-animals.css";
import NavSections from "../nav-sections/navSections";

export default function ListAnimals({ props }) {
  return (
    <div>
      <h2>Conocelos</h2>
      <NavSections />
      <div className="grid-animales">
        {props && props.length > 0
          ? props.map((animal) => <AnimalCard animal={animal} />)
          : null}
      </div>
    </div>
  );
}
