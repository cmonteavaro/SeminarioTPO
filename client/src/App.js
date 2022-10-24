import "./styles/style.css";
import NavBar from "./components/header/header";
import { Route, Routes } from "react-router-dom";
import Home from "./pages/home";
import Posts from "./pages/posts";
import Profile from "./pages/shelterDetail";
import About from "./pages/about";
import Footer from "./components/footer/footer";
import NotFound from "./pages/notFound";
import AnimalDetail from "./pages/animalDetail";
import Shelters from "./pages/shelters";

function App() {
  return (
    <>
      <NavBar />
      <main>
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route path="/publicaciones" element={<Posts />} />
          <Route path="/publicaciones/:id" element={<AnimalDetail />} />
          <Route path="/refugios/" element={<Shelters />} />
          <Route path="/refugios/:id" element={<Profile />} />
          <Route path="/acerca" element={<About />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </main>
      <Footer />
    </>
  );
}

export default App;
