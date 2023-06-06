import { useEffect } from "react"
import { useParams } from "react-router-dom"
import {useAuthContext } from "../../security/AuthContext"
import { getDummyUsers } from "../../api/UserService"

export default function DashboardComponent(){
    const { username } = useParams()
    const authContext = useAuthContext()

    useEffect(
        () => handleTestEmployeeAPI()
    )

   async function handleTestEmployeeAPI() {
        console.log("Testing api call...")
       getDummyUsers()
        .then(response => successResponse(response))
        .catch(error => errorResponse(error))
    }

    function successResponse(response){
        console.log("api success: ", response.data)
    }

    function errorResponse(error){
        console.error("api error: ", error)
    }

    return (
        <div className="d-flex justify-content-center flex-column align-items-center"> 
           <h4>Welcome {username} </h4>
           <br></br> <br></br>
           <p> Use this bearer token to access employee api</p>
           <p style={{ maxWidth: '800px', overflowWrap: "break-word"}}>{authContext.accessToken}</p>
           <br></br>
           <p>Employee API test endpoint is: http://localhost:8080/server/api/v1/employee/health</p>
           <p>Get a new token 
                <button className="btn btn-success" onClick={handleTestEmployeeAPI}>here</button>
            </p>
        </div>
    )
}