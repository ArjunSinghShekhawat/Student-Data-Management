import { Route, Routes } from "react-router-dom";
import "./App.css";
import { Home } from "./pages/Home";
import { Login } from "./pages/Login";
import { Signup } from "./pages/Signup";
import { Dashboard } from "./pages/Dashboard";
import { useState } from "react";
import { Navbar } from "./components/common/Navbar";
import PrivateRoute from "./components/core/Auth/PrivateRoute";

function App() {

  const [isLoggedIn,setIsLoggedIn]=useState(false);

  return (
  <div className="flex min-h-screen w-screen flex-col bg-richblack-900 font-inter">

    <Routes>
      <Route path="/" element={<Home/>}/>
    </Routes>
    {/* <Navbar isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn}/>
    <Routes>
      <Route path="/" element={<Home isLoggedIn={isLoggedIn}/>}/>
      <Route path="/login" element={<Login setIsLoggedIn={setIsLoggedIn}/>}/>
      <Route path="/signup" element={<Signup setIsLoggedIn={setIsLoggedIn}/>}/>
      <Route path="/dashboard" element={
        <PrivateRoute isLoggedIn={isLoggedIn}>
          <Dashboard/>
        </PrivateRoute>
        }/>
    </Routes> */}
  </div>
  );
}

export default App;
