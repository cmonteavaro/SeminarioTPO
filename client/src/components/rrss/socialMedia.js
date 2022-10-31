import { FaInstagram, FaFacebook, FaTwitter } from "react-icons/fa";

const getTag = (tagName, link) => {
  switch (tagName) {
    case "FaInstagram":
      return (
        <a href={link}>
          <FaInstagram size={30} className="icon" title="Icono Instagram" />
        </a>
      );
    case "FaFacebook":
      return (
        <a href={link}>
          <FaFacebook size={30} className="icon" title="Icono Facebook" />
        </a>
      );
    case "FaTwitter":
      return (
        <a href={link}>
          <FaTwitter size={30} className="icon" title="Icono Twitter" />
        </a>
      );
    default:
      return null;
  }
};

export default function SocialMedia(links) {
  const names = ["instagram", "facebook", "twitter"];
  let arrayLinks = [];
  let current = "";
  for (let index = 0; index < links.rrss.length; index++) {
    current = links.rrss[index].redSocial.toLowerCase();
    if (names.includes(current)) {
      current = current[0].toUpperCase() + current.substring(1);
      current = "Fa" + current;
      arrayLinks.push(getTag(current, links.rrss[index].link));
    }
  }
  console.log("entrante", links);
  console.log("resultado", arrayLinks);
  return (
    <div className="links-container-shelter">
      {arrayLinks.map((d) => {
        return d;
      })}
    </div>
  );
}
