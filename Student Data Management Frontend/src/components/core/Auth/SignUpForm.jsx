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
        const [showConfirmPassword,setShowConfirmPassword]=useState(false);
        const [accountType,setAccountType]=useState("STUDENT");
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
            const finalData={
                ...accountData,
                accountType
            }

            console.log("Printing form data")
            console.log(finalData);

            navigate("/dashboard");
        
        }
  return (
    <div> 
        <div className=' flex bg-richblack-800 p-1 gap-x-1 my-6 rounded-full max-w-max'>
                <button 
                className={`${accountType==="STUDENT"
                    ?
                    " bg-richblack-900 text-richblack-5"
                    :
                    "bg-transparent text-richblack-200"
            } py-2 px-5 rounded-full transition-all duration-200`}
                onClick={()=>{
                    setAccountType("STUDENT")
                }}>Student</button>
                <button 
                 className={`${accountType==="FACULTY"
                    ?
                    " bg-richblack-900 text-richblack-5"
                    :
                    "bg-transparent text-richblack-200"
            } py-2 px-5 rounded-full transition-all duration-200`}
                onClick={()=>{
                    setAccountType("FACULTY")
                }}>Faculty</button>
            </div>
    
            <form onSubmit={submitHandler}>
    
               <div className='w-full flex justify-between'>
               <label>
                    <p className=' text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]'>First Name <sup className='text-pink-200'>*</sup></p>
                    <input 
                    required
                    type='text'
                    name='firstName'
                    onChange={changeHandler}
                    value={formData.firstName}
                    placeholder='Enter First Name'
                    className=' bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]'
                    />
                </label>
                <label>
                    <p className=' text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]'>Last Name <sup className='text-pink-200'>*</sup></p>
                    <input 
                    required
                    type='text'
                    name='lastName'
                    onChange={changeHandler}
                    value={formData.lastName}
                    placeholder='Enter Last Name'
                    className=' bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]'
                    />
                </label>
               </div>
    
               <label className=' w-full mt-4'>
                    <p className=' text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]'>Email Address <sup className='text-pink-200'>*</sup></p>
                    <input 
                    required
                    type='email'
                    name='email'
                    onChange={changeHandler}
                    value={formData.email}
                    placeholder='Enter Email Address'
                    className=' bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]'
                    />
                </label>
                <div className=' w-full flex  justify-between mt-4'>
                <label className=' relative'>
                    <p className=' text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]'>Create Password<sup className='text-pink-200'>*</sup></p>
                    <input 
                    required
                    type={showPassword?("text"):("password")}
                    name='password'
                    onChange={changeHandler}
                    value={formData.password}
                    placeholder='Your Password'
                    className=' bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]'
                    />
                    <span className=" absolute right-3 top-[38px] cursor-pointer" onClick={()=>{
                            setShowPassword((pre)=>!pre)
                        }}>
                        {showPassword?(<AiOutlineEyeInvisible fontSize={24} fill='#AFB2BF'/>):(<AiOutlineEye fontSize={24} fill='#AFB2BF'/>)}
                    </span>
                </label>
    
                <label className=' relative'>
                    <p className=' text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]'>Confirm Password<sup className='text-pink-200'>*</sup></p>
                    <input 
                    required
                    type={showConfirmPassword?("text"):("password")}
                    name='confirmPassword'
                    onChange={changeHandler}
                    value={formData.confirmPassword}
                    placeholder='Confirm Password'
                    className=' bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]'
                    />
                    <span className=" absolute right-3 top-[38px] cursor-pointer" onClick={()=>{
                            setShowConfirmPassword((pre)=>!pre)
                        }}>
                        {showConfirmPassword?(<AiOutlineEyeInvisible fontSize={24} fill='#AFB2BF'/>):(<AiOutlineEye fontSize={24} fill='#AFB2BF'/>)}
                    </span>
                </label>
                </div>
                <button className='w-full bg-yellow-50 rounded-[8px] font-medium text-richblack-900 px-[12px] py-[8px] mt-6'>Create Account</button>
            </form>
        </div>
  )
}
