import DonationCard from "./donationCard";

export default function ListDonations({ props }) {
    let donaciones = props[0];
    return (
      <div>
        <h2>Publicaciones</h2>
        <div className="grid-animales">
          {donaciones && donaciones.length > 0
            ? donaciones.map((donacion) => <DonationCard donacion={donacion} />)
            : null}
        </div>
      </div>
    );
  }