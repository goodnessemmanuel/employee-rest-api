import { faSignIn } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuthContext } from "../../security/AuthContext";

export default function RegisterComponent(){
    const [email, setEmail] = useState("")
    const [name, setName] = useState("")
    const [password, setPassword] = useState("")
    const [showFailure, setShowFailure] = useState(false)
    const [role, setRole] = useState("USER")

    const navigate = useNavigate()
    const authContext = useAuthContext()

    function handleEmailChange(event){
        setEmail(event.target.value)
    }
    function handleNameChange(event){
        setName(event.target.value)
    }

    function handlePasswordChange(event){
        setPassword(event.target.value)
    }
    
    function handleRoleChange(event){
            setRole(event.target.value)
    }
    async function handleSubmit() {
        if (await authContext.register(name, email, password)) {
            navigate(`/`)  
        } else {
            setShowFailure(true)
        }
    }

    return (
        <div>
           <h4> Create new employee API user</h4>
           { showFailure && <div className="failure">Registeration error </div> }
           <div>
            <label htmlFor="name">Full name</label>
            <input type="name" name="name" value={name} onChange={handleNameChange} />
           </div>
           <div>
            <label htmlFor="email">Email</label>
            <input type="email" name="email" value={email} onChange={handleEmailChange} />
           </div>
           <div>
            <label htmlFor="password">Password</label>
            <input type="password" name="password" value={password} onChange={handlePasswordChange} />
           </div>
           <div>
            <button type="button" onClick={handleSubmit}>Create</button>
            <button className='active'><Link to={'/'} title="Sign in"><FontAwesomeIcon icon={faSignIn}/>Login</Link></button>
           </div>
        </div>
    )
}