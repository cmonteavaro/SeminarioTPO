import TransitCard from "./TransitCard";
import "../../styles/grid-transits.css";

export default function ListTransits({ props }) {
  return (
    <div>
      <h2>Transitos</h2>
      <div className="grid-transit">
        {props && props.length > 0
          ? props.map((transito) => <TransitCard animal={transito} />)
          : null}
      </div>
    </div>
  );
}
