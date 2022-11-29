import VolunteerCard from "./volunteerCard";
import "../../styles/grid-animals.css";

export default function ListVolunteers({ props }) {
  return (
    <div>
      <h2>Voluntariados</h2>
      <div className="grid-animales">
        {props && props.length > 0
          ? props.map((voluntario) => <VolunteerCard voluntario={voluntario} />)
          : null}
      </div>
    </div>
  );
}
