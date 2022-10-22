import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App.js";
import { MantineProvider } from "@mantine/core";

import { BrowserRouter } from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  // <React.StrictMode>
  <MantineProvider theme={{ loader: "bars" }}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </MantineProvider>
  // </React.StrictMode>
);
