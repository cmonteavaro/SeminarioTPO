import React from 'react';
import { MDBCol, MDBContainer, MDBRow, MDBCard, MDBCardText, MDBCardBody, MDBCardImage, MDBBtn, MDBTypography } from 'mdb-react-ui-kit';


const ProfileBody = (( {refugio}) => {
      return (

    <section>
    <div className="gradient-custom-2" style={{ backgroundColor: '#9de2ff' }}>
      <MDBContainer className="py-0 h-100">
        <MDBRow className="justify-content-center align-items-center h-100">
          <MDBCol lg="20" xl="0" p="0">
            <MDBCard>
            <MDBCardImage src="https://st2.depositphotos.com/1606449/7516/i/950/depositphotos_75163555-stock-photo-cats-and-dogs-hanging-paws.jpg"
                    alt="Generic placeholder image" className="mt-4 mb-2 img-thumbnail align-content-center" fluid style={{ width: '1500px', zIndex: '1' }} />
                  <div className="ms-4 mt-0 d-flex flex-row justify-content-right" style={{ width: '150px' }}>
                  <MDBCardImage src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-profiles/avatar-1.webp"
                    alt="Generic placeholder image" className="mt-4 mb-2 img-thumbnail" fluid style={{ width: '150px', zIndex: '1' }} />
                  
                  <div className="flex-column">
                    <div className="mt-5 mb-0"  style={{height: '36px', overflow: 'visible'}}>
                    <MDBTypography tag="h4"> {refugio.nombre} </MDBTypography>
                    </div>
                    <div className="flex-column">

                    <div className="mt-0 mb-0"  style={{height: '36px', overflow: 'visible'}}>
                    <MDBTypography tag="h8"> {refugio.locacion} </MDBTypography>
                    </div>
                  </div>
                  </div>
                  
              
                <div className="flex-row">
                  <div className="mt-5 mb-0 ml-1" >
                    <MDBCardText className="mb-1 h5">253</MDBCardText>
                    <MDBCardText className="small text-muted mb-0">Photos</MDBCardText>
                  </div>
                  <div className="px-3">
                 <MDBBtn outline color="dark" style={{height: '36px', overflow: 'visible'}}>
                    Donar
                  </MDBBtn>
                  </div>
                  </div>
                </div>
                
              <MDBCardBody className="text-black p-4">
        
              <div className="d-flex justify-content-between align-items-center mb-4">

                <MDBRow>
                  <MDBCol>
                  <div className="d-flex justify-content-between align-items-center mb-4">
                  <div class="mr-auto p-2"> 
                  <p className="h1 lead fw-bold mb-1">Quienes Somos</p>
                <div className="p-0">
                  <MDBCardText className="font-italic mb-1">{ refugio.descripcion }</MDBCardText>
                </div>
                </div>
              </div>
                  </MDBCol>
                  <MDBCol class="d-flex flex-row justify-content-around">
                    <MDBRow class="d-flex flex-row justify-content-around">
                  <div class="p-2 ml-0"> 
                  <MDBCardImage src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(107).webp"
                      alt="image 1" className="w-50 rounded-3" />
                      </div>

                  <div class="p-2"> 
                  <MDBCardImage src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(107).webp"
                      alt="image 1" className="w-50 rounded-3" />
                      </div>

                     <div class="p-2"> 
                  <MDBCardImage src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(107).webp"
                      alt="image 1" className="w-50 rounded-3" />
                      </div>
                      
                      </MDBRow>
                  </MDBCol>
                </MDBRow>
                </div>
                <MDBRow className="g-2">
                  <MDBCol className="mb-2">
                    <MDBCardImage src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(108).webp"
                      alt="image 1" className="w-100 rounded-3" />
                  </MDBCol>
                  <MDBCol className="mb-2">
                    <MDBCardImage src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(114).webp"
                      alt="image 1" className="w-100 rounded-3" />
                  </MDBCol>
                </MDBRow>
               
              </MDBCardBody>
            </MDBCard>
          </MDBCol>
        </MDBRow>
      </MDBContainer>
    </div>
    </section>
  );
});

export default ProfileBody;