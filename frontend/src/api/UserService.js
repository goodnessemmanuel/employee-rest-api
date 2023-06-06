import axios from "axios"
const API_BASE_URL = "http://localhost:8080"

export async function getDummyUsers(){
     console.log("fetching dummy users...")
     return await axios.get(`${API_BASE_URL}/auth/dummy`)
}

export async function loginUser(email, password){
     let requestMeta = {
           auth: { "username": email, "password": password},
           mode: "cors"
          }
     console.log("authenticating user with meta: ", requestMeta)     
    return await axios.post(`${API_BASE_URL}/auth/token`,  {}, requestMeta)
}

export async function createUser(name, email, password){
     let data = {"name" : name, "email": email, "password": password}
     console.log("creating new user with data: ", data) 

    return await axios.post(`${API_BASE_URL}/auth/register`,  data)
}