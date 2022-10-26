import { Modal } from "react-bootstrap";
import {IoMdPaw} from "react-icons/io"

export default function modalPreForm(props) {
    if (!props.show){
        return null;
    }
    return (
        <div class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <h1>Atención</h1>
                </div>
                <div class= "modal-description">
                    <div class="modal-description-div">
                        <h4>Antes de continuar al formulario debes recordar lo siguiente respecto del animal</h4>
                        <ul class="modal-description-div-list">
                            <ModalList items={props.animalRestrictions}></ModalList>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn-exit" onClick={props.onClose}>Salir</button>
                    <button class="btn-continue">Continuar</button>
                </div>
            </div>
            
        </div>
    )
}


function ModalList(props){
    const items = props.items
    return (
        <>
        { 
            !items['convivirConCachorros'] && <li><IoMdPaw class="modal-description-div-list-icon"></IoMdPaw>No puede convivir con cachorros</li> 
        }
        { 
            !items['convivirConInfantes'] && <li><IoMdPaw class="modal-description-div-list-icon"></IoMdPaw>No puede convivir con infantes</li> 
        }
        { 
            !items['convivirConGatos'] && <li><IoMdPaw class="modal-description-div-list-icon"></IoMdPaw>No puede convivir con gatos</li> 
        }
        { 
            !items['convivirConPerrosAdultos'] && <li><IoMdPaw class="modal-description-div-list-icon"></IoMdPaw>No puede convivir con perros adultos</li> 
        }
        </>
    )
}


