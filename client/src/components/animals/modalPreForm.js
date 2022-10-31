import { IoMdPaw } from "react-icons/io";

export default function modalPreForm(props) {
  if (!props.show) {
    return null;
  }
  return (
    <div className="modal">
      <div className="modal-content">
        <div className="modal-header">
          <h2>Atención</h2>
        </div>
        <div className="modal-description">
          <div className="modal-description-div">
            <h4>
              Antes de continuar al formulario debes recordar lo siguiente
              respecto del animal
            </h4>
            <ul className="modal-description-div-list">
              <ModalList items={props.animalRestrictions} />
            </ul>
          </div>
        </div>
        <div className="modal-footer">
          <button className="btn-exit" onClick={props.onClose}>
            Salir
          </button>
          <button className="btn-continue" onClick={props.openForm}>
            Continuar
          </button>
        </div>
      </div>
    </div>
  );
}

function ModalList(props) {
  const items = props.items;
  return (
    <>
      {!items["convivirConCachorros"] && (
        <li>
          <IoMdPaw class="modal-description-div-list-icon"></IoMdPaw>No puede
          convivir con cachorros
        </li>
      )}
      {!items["convivirConInfantes"] && (
        <li>
          <IoMdPaw class="modal-description-div-list-icon"></IoMdPaw>No puede
          convivir con infantes
        </li>
      )}
      {!items["convivirConGatos"] && (
        <li>
          <IoMdPaw class="modal-description-div-list-icon"></IoMdPaw>No puede
          convivir con gatos
        </li>
      )}
      {!items["convivirConPerrosAdultos"] && (
        <li>
          <IoMdPaw class="modal-description-div-list-icon"></IoMdPaw>No puede
          convivir con perros adultos
        </li>
      )}
    </>
  );
}
