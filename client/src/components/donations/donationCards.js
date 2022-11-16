import DonationCard from "./donationCard";
import "../../styles/grid-animals.css";

export default function ListDonations({ props }) {
  let donaciones = props[0];
  return (
    <div>
      <h2>Donaciones</h2>
      <div className="grid-animales">
        {donaciones && donaciones.length > 0
          ? donaciones.map((donacion) => <DonationCard donacion={donacion} />)
          : null}
      </div>
    </div>
  );
}
