import AnimalCard from "./animalCard";
import "../../styles/grid-animals.css"

export default function ListAnimals({ props }) {
  console.log(props);
  return (
    <div>
      <h2>Conocelos</h2>
      <div className="grid-animales">
        {props && props.length > 0
          ? props.map((animal) => <AnimalCard animal={animal} />)
          : null}
      </div>
    </div>
  );
}
