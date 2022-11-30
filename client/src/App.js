import "./styles/style.css";
import NavBar from "./components/header/header";
import { Route, Routes } from "react-router-dom";
import { lazy, Suspense } from "react";
import Footer from "./components/footer/footer";
import { Loader } from "@mantine/core";
import NavbarNEW from "./components/header/headerMantine";

const Home = lazy(() => import("./pages/home"));
const AnimalPosts = lazy(() => import("./pages/animalPosts"));
const Profile = lazy(() => import("./pages/shelterDetail"));
const About = lazy(() => import("./pages/about"));
const NotFound = lazy(() => import("./pages/notFound"));
const AnimalDetail = lazy(() => import("./pages/animalDetail"));
const Shelters = lazy(() => import("./pages/shelters"));
const VolunteerPosts = lazy(() => import("./pages/volunteerPosts"));

const DonationPosts = lazy(() => import("./pages/donationPosts"));
const Transits = lazy(() => import("./pages/transitsPosts"));
const TransitDetail = lazy(() => import("./pages/transitDetail"));

function App() {
  return (
    <>
      {/* <NavBar /> */}
      <NavbarNEW />
      <main>
        <Suspense fallback={<Loader color="lime" />}>
          <Routes>
            <Route exact path="/" element={<Home />} />
            <Route path="/publicaciones" element={<AnimalPosts />} />
            <Route path="/publicaciones/:id" element={<AnimalDetail />} />
            <Route path="/voluntariados" element={<VolunteerPosts />} />
            <Route path="/donation_posts" element={<DonationPosts />} />
            <Route path="/transitos" element={<Transits />} />
            <Route path="/transitos/:id" element={<TransitDetail />} />
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
