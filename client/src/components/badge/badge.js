import { Badge } from "@mantine/core";

export default function Tag(availability) {
	const { state } = availability;
	let color;
	let variant;
	let text;
	let size = "xl";
	switch (state) {
		case "Disponible":
			color = "lime";
			variant = "dark";
			text = `${state}`;
			break;
		case "En proceso":
			color = "yellow";
			variant = "dark";
			text = `${state}`;
			break;
		case "Finalizada":
			color = "gray";
			variant = "dark";
			text = `${state}`;
			break;
		case true:
			color = "red";
			variant = "dark";
			text = `URGENTE`;
			break;
		default:
			return <></>;
	}
	return (
		<Badge color={color} variant={variant} size={size}>
			{" "}
			{text}{" "}
		</Badge>
	);
}
