import Swal from 'sweetalert2'

async function postularPromise(url = "", data = {}) {
    return await fetch(url, {
        method: "POST",
        cors: "no-cors",
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
            "Content-Type": "application/json",
        },
        redirect: "follow",
        body: JSON.stringify(data),
    })
}
  
async function sendForm(url = "", data = {}) {
    Swal.fire({
        title: 'Enviando postulacion',
        html: 'El refugio recibirá tu solicitud y se ponda en contacto contigo',
        allowEscapeKey: false,
        allowOutsideClick: false,
        showConfirmButton: false,
        didOpen: async () => {
        Swal.showLoading();
        postularPromise(url,data)
        .then((httpRes)=>Swal.close({httpRes}));
        },
    }).then( (swalRes) => {
        if(swalRes.httpRes.status===200){
            Swal.fire({ 
                title: 'Postulacion enviada con exito!',
                html: 'Gracias por colaborar!',
                icon: "success",
                showConfirmButton: true,
                confirmButtonColor: "#008000",
                confirmButtonText:"Entendido",
                allowOutsideClick: false,
            })
        }else{
            Swal.fire({ 
                title: 'No se ha podido enviar la solicitud',
                html: 'Lo sentimos, tu solicitud no se ha podido enviar. Verifica que el correo ingresado sea correcto. En caso que lo sea, intentalo nuevamente mas tarde.',
                icon: "error",
                showCancelButton: true,
                showConfirmButton: false,
                cancelButtonText: 'Entendido',
                allowOutsideClick: false,
            })
        }
    })
}

export { sendForm };
