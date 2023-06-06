import { createBrowserRouter } from "react-router-dom";
import DashboardComponent from "./components/dashboard/DashboardComponent";
import LoginComponent from "./components/login/LoginComponent";
import RegisterComponent from "./components/register/RegisterComponent";
import ErrorPage from "./ErrorPage";
import App from "./App";
import { useAuthContext } from "./security/AuthContext";

/**
 * root layout upon 
 * where other elements
 * added to the dom
 * @returns Root JSX layout for app
 */

const router = createBrowserRouter([
    {
        path: "/",
        element: <App />,
        errorElement: <ErrorPage />,
        children: [
            {
                path: "/",
                element: <LoginComponent />
            },
            {
                path: "/dashboard/:username",
                element: <AuthenticatedRoute children={ <DashboardComponent/> } />
            },
            {
                path: "/register",
                element: <RegisterComponent />
            }
        ]
    },

]);
export default router;

function AuthenticatedRoute({children}) {
    const authContext = useAuthContext()

    if(authContext.isAuthenticated)
        return children
    return <LoginComponent />
}