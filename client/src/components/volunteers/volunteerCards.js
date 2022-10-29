import VolunteerCard from "./volunteerCard";

export default function ListVolunteers({ props }) {
  return (
    <div>
      <h2>Publicaciones</h2>
      <div className="grid-animales">
        {props && props.length > 0
          ? props.map((voluntario) => <VolunteerCard voluntario={voluntario} />)
          : null}
      </div>
    </div>
  );
}
