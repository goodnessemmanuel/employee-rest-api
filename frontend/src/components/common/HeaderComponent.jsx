import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFacebook, faInstagram } from '@fortawesome/free-brands-svg-icons'
import { faBars, faPowerOff, faSignIn } from '@fortawesome/free-solid-svg-icons'
import { Link, useParams } from 'react-router-dom'
import { faUser } from '@fortawesome/free-regular-svg-icons'
import { useAuthContext } from '../../security/AuthContext'

export default function HeaderComponent(){

    const authContext = useAuthContext()
    const { username } = useParams()
    function handleLogout(){
        authContext.logOut()
    }
    return (
        <header className='header'>
            <div className="container">
                <div className="row">
                <div className="col-md-12 col-sm-12 col-lg-12 col-xl-3">
                    <div className="logo">
                        <Link to={'/'} title="Logo">Kotlin Rest API</Link>
                    </div>
                    <div className="mob-menu">
                            <span>
                                <FontAwesomeIcon icon={faBars} />
                            </span>
                        </div>
                    </div>
                    <div className="col-md-12 col-sm-12 col-lg-12 col-xl-9">
                        <div className="main-menu">
                           { authContext.isAuthenticated &&  
                                <ul className="nav">
                                    <li><Link to={`/dashboard/${username}`} title="Logo">Profile</Link></li> 
                                </ul>
                            }
                            <ul className="right-nav">
                                {authContext.isAuthenticated && <li><Link 
                                className="btn" 
                                onClick={handleLogout}><FontAwesomeIcon icon={faPowerOff}/>Logout</Link></li>}
                                {!authContext.isAuthenticated && 
                                <li className='active'><Link to={'/register'} title="Register"><FontAwesomeIcon icon={faSignIn}/>Register</Link></li>}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    )
}