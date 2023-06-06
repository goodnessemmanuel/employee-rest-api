import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthContext } from "../../security/AuthContext";

import "./LoginComponent.css";

export default function LoginComponent() {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [showFailure, setShowFailure] = useState(false)

    const navigate = useNavigate()
    const authContext = useAuthContext()

    function handleEmailChange(event){
        setEmail(event.target.value)
    }

    function handlePasswordChange(event){
        setPassword(event.target.value)
    }

    async function handleSubmit() {
        if (await authContext.login(email, password)) {
            navigate(`/dashboard/${email}`)
        } else {
            setShowFailure(true)
        }
    }

    return (
        <div>
           <h4> Login to generate employee API access token</h4>
           { showFailure && <div className="failure">Authentication failed, check your credentials </div> }
           <div>
            <label htmlFor="email">Email</label>
            <input type="email" name="email" value={email} onChange={handleEmailChange} />
           </div>
           <div>
            <label htmlFor="password">Password</label>
            <input type="password" name="password" value={password} onChange={handlePasswordChange} />
           </div>
           <div>
            <button type="button" onClick={handleSubmit}>Login</button>
           </div>
        </div>
    )
}