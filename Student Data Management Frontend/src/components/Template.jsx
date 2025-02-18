import React from 'react'
import frameImage from "../assets/frame.png";
import { SignUpForm } from './SignUpForm';
import { LoginForm } from './LoginForm';

export const Template = ({title,des1,des2,image,formType,setIsLoggedIn}) => {
  return (
    <div>
        <div>
            <h1>{title}</h1>
            <p>
                <span>{des1}</span>
                <span>{des2}</span>
            </p>
            {
                formType === "signup" 
                ?
                (<SignUpForm setIsLoggedIn={setIsLoggedIn}/>)
                :
                (<LoginForm setIsLoggedIn={setIsLoggedIn}/>)
                
            }
            <div>
                <div></div>
                <p>OR</p>
                <div></div>
            </div>
            <button>
                <p>Sign In With Google</p>
            </button>
        </div>
        <div>
            <img src={frameImage}
            alt='Pattern'
            width={558}
            height={504}
            loading='lazy'/>

            <img src={image}
            alt='Students'
            width={558}
            height={504}
            loading='lazy'/>
        </div>
    </div>
  )
}
