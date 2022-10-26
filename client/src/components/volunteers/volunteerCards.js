import VolunteerCard from "./volunteerCard";

export default function ListVolunteers({ props }) {
    let voluntarios = props[0];
    return (
      <div>
        <h2>Publicaciones</h2>
        <div className="grid-voluntarios">
          {voluntarios && voluntarios.length > 0
            ? voluntarios.map((voluntario) => <VolunteerCard voluntario={voluntario} />)
            : null}
        </div>
      </div>
    );
  }