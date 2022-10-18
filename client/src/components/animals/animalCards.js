import AnimalCard from "./animalCard";

export default function ListAnimals({ props }) {
  console.log(props);
  return (
    <div className="grid-animales">
      <h2>Conocelos</h2>
      {props && props.length > 0
        ? props.map((animal) => <AnimalCard animal={animal} />)
        : null}
    </div>
  );
}
