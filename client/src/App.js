import "./styles/style.css";
import NavBar from "./components/header/header";
import { Route, Routes } from "react-router-dom";
import { lazy, Suspense } from "react";
import Footer from "./components/footer/footer";
import { Loader } from "@mantine/core";

const Home = lazy(() => import("./pages/home"));
const AnimalPosts = lazy(() => import("./pages/animalPosts"));
const Profile = lazy(() => import("./pages/shelterDetail"));
const About = lazy(() => import("./pages/about"));
const NotFound = lazy(() => import("./pages/notFound"));
const AnimalDetail = lazy(() => import("./pages/animalDetail"));
const Shelters = lazy(() => import("./pages/shelters"));
const VolunteerPosts = lazy(() => import("./pages/volunteerPosts"));

function App() {
  return (
    <>
      <NavBar />
      <main>
        <Suspense fallback={<Loader color="lime" />}>
          <Routes>
            <Route exact path="/" element={<Home />} />
            <Route path="/publicaciones" element={<AnimalPosts />} />
            <Route path="/publicaciones/:id" element={<AnimalDetail />} />
            <Route path="/voluntariados" element={<VolunteerPosts />} />
            <Route path="/refugios/" element={<Shelters />} />
            <Route path="/refugios/:id" element={<Profile />} />
            <Route path="/acerca" element={<About />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </Suspense>
      </main>
      <Footer />
    </>
  );
}

export default App;
