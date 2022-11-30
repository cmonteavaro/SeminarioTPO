import AnimalCard from "./animalCard";
import "../../styles/grid-animals.css";

export default function ListAnimals({ props }) {
  return (
    <>
      <div className="grid-animales">
        {props && props.length > 0
          ? props.map((animal,i) => <AnimalCard animal={animal} key={i}/>)
          : null}
      </div>
    </>
  );
}
