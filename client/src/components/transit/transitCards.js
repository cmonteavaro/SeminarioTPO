import TransitCard from "./transitCard";
import NavSections from "../nav-sections/navSections";
import "../../styles/grid-transits.css";

export default function ListTransits({ props }) {
  return (
    <div>
      <h2>Transitos</h2>
      <NavSections />
      <div className="grid-transit">
        {props && props.length > 0
          ? props.map((transito) => <TransitCard animal={transito} />)
          : null}
      </div>
    </div>
  );
}
