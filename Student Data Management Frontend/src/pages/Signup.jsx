import React from 'react'
import signUpImg from "../assets/signup.png";
import { Template } from '../components/Template';
export const Signup = ({setIsLoggedIn}) => {

  return (
    <Template
    title="Join the millions learning to code with StudyNotion for free"
    des1="Build skills for today, tomorrow, and beyond"
    des2="Education to future-proof your carrer"
    image={signUpImg}
    formType="signup"
    setIsLoggedIn={setIsLoggedIn}
    />
  )
}
