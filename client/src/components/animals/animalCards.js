import AnimalCard from "./animalCard";

export default function ListAnimals({ props }) {
  console.log(props);
  return (
    <div className="grid">
      <h2>papito</h2>
      {props && props.length > 0
        ? props.map((animal) => <AnimalCard animal={animal} />)
        : null}
    </div>
  );
}
