import React from 'react'
import { Template } from '../components/Template'
import loginImg from "../assets/login.png";

export const Login = ({setIsLoggedIn}) => {

  return (
    
    <Template
    title="Welcome Back"
    des1="Build skills for today, tomorrow, and beyond"
    des2="Education to future-proof your carrer"
    image={loginImg}
    formType="login"
    setIsLoggedIn={setIsLoggedIn}
    />
    
  )
}
