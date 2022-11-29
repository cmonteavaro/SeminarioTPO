import TransitCard from "./transitCard";
import "../../styles/grid-transits.css";

export default function ListTransits({ props }) {
  return (
    <div>
      <div className="grid-transit">
        {props && props.length > 0
          ? props.map((transito) => <TransitCard animal={transito} />)
          : null}
      </div>
    </div>
  );
}
