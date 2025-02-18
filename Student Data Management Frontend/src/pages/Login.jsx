import React from 'react'
import loginImg from "../assets/Images/login.webp";
import { Template } from '../components/core/Auth/Template';

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
