import DonationCard from "./donationCard";
import NavSections from "../nav-sections/navSections";

export default function ListDonations({ props }) {
  let donaciones = props[0];
  return (
    <div>
      <h2>Publicaciones</h2>
      <NavSections />
      <div className="grid-animales">
        {donaciones && donaciones.length > 0
          ? donaciones.map((donacion) => <DonationCard donacion={donacion} />)
          : null}
      </div>
    </div>
  );
}
