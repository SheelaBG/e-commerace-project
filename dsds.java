import {  useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import './Signup.css';
import FormInput from "../InterviewMng/FormInput.jsx";
// import ToasterUi from 'toaster-ui';

const Signup = () => {

    let navigate=useNavigate();
    // const toaster = new ToasterUi();
    const [message, setMessage] = useState(null);
    const[value,setValue]=useState(
        {
          username:"",
          email:"",
          mobileNo:"",
          password:"",
        }
      );

      const inputs=[
        {
          id:1,
          label:"Username",
          placeholder:"Username",
          name:"username",
          type:"text",
          errorMessage:"User name should be 3-16 characters and shouldn't include special characters!",
          className:"form-control",
          id:"floatingInput",
          required:true,
          pattern:"^[A-Za-z0-9]{3,16}$"
        },
        
        {
          id:2,
          label:"Email",
          placeholder:"Email",
          name:"email",
          type:"email",
          className:"form-control",
          id:"floatingInput",
          errorMessage:"it should be valid email address!",
          required:true 
        },
        {
            id:3,
            label:"Mobile No",
            placeholder:"Mobile No",
            name:"mobileNo",
            type:"tel",
            errorMessage:"Enter Valid Number, it should be 10 digits!",
            maxLength:10,
            minLength:10,
            className:"form-control",
            id:"floatingInput",
            required:true,  
        },
        {
          id:4,
          label:"Password",
          placeholder:"password",
          name:"password",
          type:"password",
          errorMessage:"password should be 8-20 charaters and it includes atleast 1 letter, 1 number and 1 special characters !",
          required:true,
          className:"form-control",
          id:"floatingInput",
          pattern:"^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$"
        }
      ]

      const onChange=(e)=>{
        setValue({...value,[e.target.name]:e.target.value})
      }

    
      const handleSignUp = async (e) => {
        e.preventDefault();
     
      
        try {


          const response = await fetch("http://localhost:8080/api/auth/signup", {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(value)
          });

          if (response.ok) {
            const contentType = response.headers.get('content-type');
            if (contentType && contentType.includes('application/json')) {
              const responseData = await response.json();
              console.log(responseData);
              alert("SignUp successfully...");
              navigate("/Login")
             
            } else {
              const responseText = await response.text();
              alert(responseText);
              navigate("/Login");
            }
          } else {
            const errorMessage = await response.text();
            alert(errorMessage );
          
          }
        } catch (error) {
          alert('Error during sign-up:', error);
          if (error.response) {
            alert('Response text:', await error.response.text());
          }
      
          alert('An error occurred during sign-up');
          
        }
      };
      
    
    return ( 
        <>
         <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
    crossorigin="anonymous"
  />
     
      <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"
      ></script>
      <div className="signup-body">
      <div className="bg"></div>
      <main className="form-signup">
       
        <h1 className="h3">SignUp</h1>

        <form onSubmit={handleSignUp}>
        {inputs.map((input)=>(
                    <FormInput {...input} key={input.id} value={value[input.name]} onChange={onChange} />

                    ))}
                   
          <button className="w-100 btn btn-lg" type="submit">
            SignUp
          </button>
          <div className="login-link">
              <p style={{display:"inline-block"}}>Already a Member ? </p><Link to="/Login">Login</Link>  
          </div>    
        </form>
        
      </main>
      </div>
        </>
     );
}
 
export default Signup;
