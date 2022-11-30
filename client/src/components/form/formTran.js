import { TextInput, Group, Textarea } from "@mantine/core";
import { useForm } from "@mantine/form";
import "./form.css";
import { sendForm } from "./sendForm"

export default function FormTran(data) {
  const form = useForm({
    validate: {
      correo: (value) =>
        /^\S+@\S+$/.test(value) ? null : "Correo Electronico no valido.",

      telefono: (value) =>
        value.length < 10 && value.length > 11
          ? "Coloque su numero de telefono con codigo de area, sin 0 y sin 15."
          : null,

      nombre: (value) =>
        value.length < 2 ? "El nombre debe de ser minimo 3 letras." : null,

      apellido: (value) =>
        value.length < 2 ? "El apellido debe de ser minimo 3 letras." : null,

      direccion: (value) =>
        value.length < 5 ? "Coloque una direccion valida." : null,
    },
  });
  if (!data.show) {
    return null;
  }

  return (
    <div className="form">
      <div className="form-content">
        <div className="form-header">
          <h2>Formulario Tránsito</h2>
          <button class="btn-exit" onClick={data.onClose}>
            X
          </button>
        </div>
        <div className="form-details">
          <form
            onSubmit={form.onSubmit((values) =>
              sendForm(
                `http://localhost:8080/api/publicaciones/transitos/${data.data.idPublicacion}/postular`,
                values
              )
                .catch((e) => console.log(e))
            )}
          >
            <TextInput
              withAsterisk
              label="Nombre"
              placeholder="Jhon"
              {...form.getInputProps("nombre")}
            />
            <TextInput
              withAsterisk
              label="Apellido"
              placeholder="Doe"
              {...form.getInputProps("apellido")}
            />
            <TextInput
              withAsterisk
              label="Teléfono"
              placeholder="2995763899"
              {...form.getInputProps("telefono")}
            />
            <TextInput
              withAsterisk
              label="Dirección"
              placeholder="Lima 775, Monserrat, Buenos Aires, Argentina"
              {...form.getInputProps("direccion")}
            />
            <TextInput
              withAsterisk
              label="Email"
              placeholder="jhondoe@correo.com"
              {...form.getInputProps("correo")}
            />
            <Textarea
              placeholder="Agregue información extra si lo considera necesario"
              label="Nota"
              {...form.getInputProps("notas")}
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
