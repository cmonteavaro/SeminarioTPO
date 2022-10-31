import { TextInput, Group, Textarea } from "@mantine/core";
import { useForm } from "@mantine/form";

async function sendForm(url = "", data = {}) {
  //   const response = await fetch(url, {
  //     method: "POST",
  //     cors: "no-cors",
  //     cache: "no-cache",
  //     credentials: "same-origin",
  //     headers: {
  //       "Content-Type": "application/json",
  //     },
  //     redirect: "follow",
  //     body: JSON.stringify(data),
  //   });

  //   return response.json();
  console.log("Form enviado");
  console.log(url);
  console.log(data);
  //   Implementar envio exitoso o fallido
  return true;
}

export default function Form(data) {
  const form = useForm({
    validate: {
      email: (value) =>
        /^\S+@\S+$/.test(value) ? null : "Correo Electronico no valido.",

      tel: (value) =>
        value.length < 10 && value.length > 11
          ? "Coloque su numero de telefono con codigo de area, sin 0 y sin 15."
          : null,

      firstName: (value) =>
        value.length < 2 ? "El nombre debe de ser minimo 3 letras." : null,

      lastName: (value) =>
        value.length < 2 ? "El apellido debe de ser minimo 3 letras." : null,

      location: (value) =>
        value.length < 5 ? "Coloque una direccion valida." : null,
    },
  });
  console.log();
  if (!data.show) {
    return null;
  }

  return (
    <div className="form">
      <div className="form-content">
        <div className="form-header">
          <h2>Formulario de Adopcion</h2>
          <button class="btn-exit" onClick={data.onClose}>
            X
          </button>
        </div>
        <div className="form-details">
          <form
            onSubmit={form.onSubmit((values) =>
              sendForm(
                `http://localhost:8080/api/publicaciones/adopciones/${data.data.idPublicacion}/postular`,
                values
              ).then((data) => console.log(data))
            )}
          >
            <TextInput
              withAsterisk
              label="Nombre"
              placeholder="Jhon"
              {...form.getInputProps("firstName")}
            />
            <TextInput
              withAsterisk
              label="Apellido"
              placeholder="Doe"
              {...form.getInputProps("lastName")}
            />
            <TextInput
              withAsterisk
              label="Telefono"
              placeholder="2995763899"
              {...form.getInputProps("tel")}
            />
            <TextInput
              withAsterisk
              label="Direccion"
              placeholder="Lima 775, CABA, Buenos Aires"
              {...form.getInputProps("location")}
            />
            <TextInput
              withAsterisk
              label="Email"
              placeholder="jhondoe@correo.com"
              {...form.getInputProps("email")}
            />
            <Textarea
              placeholder="Agregue información extra si lo considera necesario"
              label="Nota"
            />

            <Group position="right" mt="md">
              <button className="btn-continue" type="submit">
                Enviar
              </button>
            </Group>
          </form>
        </div>
      </div>
    </div>
  );
}
