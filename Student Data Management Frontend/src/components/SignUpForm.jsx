import {React, useState } from 'react'
import toast from 'react-hot-toast';
import { AiOutlineEyeInvisible } from "react-icons/ai";
import { AiOutlineEye } from "react-icons/ai";
import { useNavigate } from 'react-router-dom';

export const SignUpForm = ({setIsLoggedIn}) => {
     const [formData,setFormData]=useState({
            firstName:"",
            lastName:"",
            email:"",
            password:"",
            confirmPassword:""
        })
        const [showPassword,setShowPassword]=useState(false);
        const navigate = useNavigate();
    
    
        function changeHandler(event){
            setFormData((prevData)=>(
                {
                    ...prevData,
                    [event.target.name]:event.target.value
                }
            ));
        }
        function submitHandler(event){
            event.preventDefault();

            if(formData.password!==formData.confirmPassword){
                toast.error("Password do not match")
                return;
            }
            setIsLoggedIn(true);
            toast.success("Account Created !");

            const accountData = {
                ...formData
            }
            console.log(accountData);
            navigate("/dashboard");
        
        }
  return (
    <div> 
        <div>
                <button>Student</button>
                <button>Faculty</button>
            </div>
    
            <form onSubmit={submitHandler}>
    
               <div>
               <label>
                    <p>First Name <sup>*</sup></p>
                    <input 
                    required
                    type='text'
                    name='firstName'
                    onChange={changeHandler}
                    value={formData.firstName}
                    placeholder='Enter Your First Name'
                    />
                </label>
                <label>
                    <p>Last Name <sup>*</sup></p>
                    <input 
                    required
                    type='text'
                    name='lastName'
                    onChange={changeHandler}
                    value={formData.lastName}
                    placeholder='Enter Your Last Name'
                    />
                </label>
               </div>
    
               <label>
                    <p>Email Address <sup>*</sup></p>
                    <input 
                    required
                    type='email'
                    name='email'
                    onChange={changeHandler}
                    value={formData.email}
                    placeholder='Enter Email Address'
                    />
                </label>
                <div>
                <label>
                    <p>Create Password<sup>*</sup></p>
                    <input 
                    required
                    type={showPassword?("text"):("password")}
                    name='password'
                    onChange={changeHandler}
                    value={formData.password}
                    placeholder='Enter Your Password'
                    />
                    <span onClick={()=>{
                            setShowPassword((pre)=>!pre)
                        }}>
                        {showPassword?(<AiOutlineEyeInvisible/>):(<AiOutlineEye/>)}
                    </span>
                </label>
    
                <label>
                    <p>Confirm Password<sup>*</sup></p>
                    <input 
                    required
                    type={showPassword?("text"):("password")}
                    name='confirmPassword'
                    onChange={changeHandler}
                    value={formData.confirmPassword}
                    placeholder='Enter Your Confirm Password'
                    />
                    <span onClick={()=>{
                            setShowPassword((pre)=>!pre)
                        }}>
                        {showPassword?(<AiOutlineEyeInvisible/>):(<AiOutlineEye/>)}
                    </span>
                </label>
                </div>
                <button>Create Account</button>
            </form>
        </div>
  )
}
