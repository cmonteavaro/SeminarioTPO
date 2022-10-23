import AnimalCard from "./animalCard";
import "../../styles/grid-animals.css";

export default function ListAnimals({ props }) {
  let animales = props[0];
  return (
    <div>
      <h2>Conocelos</h2>
      <div className="grid-animales">
        {animales && animales.length > 0
          ? animales.map((animal) => <AnimalCard animal={animal} />)
          : null}
      </div>
    </div>
  );
}
