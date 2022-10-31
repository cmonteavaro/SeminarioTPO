import ShelterCard from "./shelterCard";
import "../../styles/grid-shelters.css";

export default function ListShelters({ props }) {
  return (
    <div>
      <h2>Refugios</h2>
      <div className="grid-refugios">
        {props && props.length > 0
          ? props.map((refugio) => <ShelterCard shelter={refugio} />)
          : null}
      </div>
    </div>
  );
}
