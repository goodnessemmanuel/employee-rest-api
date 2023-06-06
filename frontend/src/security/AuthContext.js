import { loginErrorResponse } from "@remix-run/router";
import { createContext, useContext, useState } from "react";
import { loginUser, createUser } from "../api/UserService";

//Create a context
export const AuthContext = createContext()
//create a custom hook for authContext
export const useAuthContext = () => useContext(AuthContext)

//Share the context with other components via Provider
export default function AuthProvider({children}){
    //put some state in the context
    const [isAuthenticated, setAuthenticated] = useState(false)
    const [accessToken, setAccessToken] = useState("")
    const [isNewUser, setIsNewUser] = useState(false)
    
    function logout(){
        setAuthenticated(false)
    }

   async function login(username, password) {
     return await  loginUser(username, password)
        .then(response => successResponse(response))
        .catch(error => errorResponse(error))
    }

   async function register(name, email, password) {
    return await  createUser(name, email, password)
       .then(response => successResponse(response, true))
       .catch(error => errorResponse(error))
   }
    
   function successResponse(response, newUser = false){
    console.log("response success: ", response)
     setAuthenticated(true)
     setIsNewUser(newUser)
     if(response.data.token)
        setAccessToken(response.data.token)
     return true
   }
    
   function errorResponse(error){
     console.error("response error!", error)
     setAuthenticated(false)
     return false
   }

    const shareData = { login, logout, register, isAuthenticated, accessToken}

    return(
        <AuthContext.Provider value={ shareData }>
            {children}
        </AuthContext.Provider>
    )
}