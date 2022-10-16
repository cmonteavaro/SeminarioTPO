import "./styles/style.css";
import NavBar from "./components/header/header";
import { Route, Routes } from "react-router-dom";
import Home from "./pages/home";
import Posts from "./pages/posts";
import Refugees from "./pages/refugees";
import About from "./pages/about";
import Footer from "./components/footer/footer";

function App() {
  return (
    <>
      <NavBar />
      <main>
        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/posts" element={<Posts />}></Route>
          <Route path="/refugees" element={<Refugees />}></Route>
          <Route path="/about" element={<About />}></Route>
        </Routes>
      </main>
      <Footer />
    </>
  );
}
// =======
// import './App.css';
// import Profile from "./pages/PerfilRefugio"
// import { BrowserRouter as Router, Route, Redirect, Switch } from "react-router-dom";


// function App() {
//   return (
//   <Router>  
//     <Switch>
//       <Route path = "/" exact>
//         < Profile />
        
//       </Route>  
//     </Switch> 
//   </Router>
// 	);
//   }
// >>>>>>> Stashed changes

export default App;


