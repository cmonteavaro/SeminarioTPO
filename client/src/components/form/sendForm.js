import Swal from 'sweetalert2'

async function handleSearch (inputSearch) {
    let coords = [];
    await fetch(`https://api.maptiler.com/geocoding/${inputSearch}.json?key=Mdlvp8JndCrWtOqNUat6`)
    .then((response) => response.json())
    .then((s) => {
      const obj = s['features'];
      const place = obj[0];
      const geometry = place['geometry'];
      coords = geometry['coordinates'];
    })
    if(typeof(coords[0]) === "number"){
        console.log(coords)
        return coords;
    } else {
        if(typeof(coords[0][0]) === "number"){
            console.log(coords[0])
            return coords[0];
        } else {
            return coords[0][0];
        }
    }
    
  }

async function postularPromise(url = "", data = {}) {
    const coordenadas = await handleSearch(data.direccion);
    data.coordenadas = coordenadas;
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
        html: 'El refugio recibirá tu solicitud y se pondra en contacto contigo',
        allowEscapeKey: false,
        allowOutsideClick: false,
        showConfirmButton: false,
        didOpen: async () => {
        Swal.showLoading();
        postularPromise(url,data)
        .then((httpRes)=>Swal.close({httpRes: httpRes}));
        },
    }).then( async (swalRes) => {
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
            let errormsg;
            await swalRes.httpRes.text().then((data ) => errormsg = data)
            Swal.fire({ 
                title: 'No se ha podido enviar la solicitud',
                html: errormsg,
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
