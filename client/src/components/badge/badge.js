import { Badge } from "@mantine/core";

export default function Tag(availability) {
  const { state } = availability;
  let color;
  let variant;
  let text;
  switch (state) {
    case "DISPONIBLE":
      color = "lime";
      variant = "dark";
      text = `${state}`;
      break;
    case "EN_PROCESO":
      color = "yellow";
      variant = "dark";
      text = "En proceso";
      break;
    case "FINALIZADA":
      color = "red";
      variant = "dark";
      text = `${state}`;
      break;
    case "URGENTE":
      color = "red";
      variant = "dark";
      text = `${state}`;
      break;
    default:
      return <></>;
  }
  return (
    <Badge color={color} variant={variant}>
      {" "}
      {text}{" "}
    </Badge>
  );
}
