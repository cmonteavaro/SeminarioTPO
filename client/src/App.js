import "./styles/style.css";
import NavBar from "./components/header/header";
import { Route, Routes } from "react-router-dom";
import Home from "./pages/home";
import AnimalPosts from "./pages/animalPosts";
import Profile from "./pages/shelterDetail";
import About from "./pages/about";
import Footer from "./components/footer/footer";
import NotFound from "./pages/notFound";
import AnimalDetail from "./pages/animalDetail";
import Shelters from "./pages/shelters";
import VolunteerPosts from "./pages/volunteerPosts";

function App() {
  return (
    <>
      <NavBar />
      <main>
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route path="/volunteer_posts" element={<VolunteerPosts />} />
          <Route path="/posts" element={<AnimalPosts />} />
          <Route path="/posts/:id" element={<AnimalDetail />} />
          <Route path="/refugees/" element={<Shelters />} />
          <Route path="/refugees/:id" element={<Profile />} />
          <Route path="/about" element={<About />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </main>
      <Footer />
    </>
  );
}

export default App;
