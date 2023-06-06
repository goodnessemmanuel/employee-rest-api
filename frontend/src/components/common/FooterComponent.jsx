import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFacebook, faInstagram, faTwitter, faYoutube } from '@fortawesome/free-brands-svg-icons'

export default function FooterComponent(){
    return (
        <footer className="container-fluid footer">
            <div className="text-center text-lg-start" style={{backgroundColor: "#db6930"}}>
                <div className="container d-flex justify-content-center py-5">
                <button type="button" className="btn btn-primary btn-lg btn-floating mx-2" style={{backgroundColor: "#54456b"}}>
                    <FontAwesomeIcon icon={faFacebook} />
                </button>
                <button type="button" className="btn btn-primary btn-lg btn-floating mx-2" style={{ backgroundColor: "#54456b"}}>
                    <FontAwesomeIcon icon={faYoutube} />
                </button>
                <button type="button" className="btn btn-primary btn-lg btn-floating mx-2" style={{ backgroundColor: "#54456b"}}>
                    <FontAwesomeIcon icon={faInstagram} />
                </button>
                <button type="button" className="btn btn-primary btn-lg btn-floating mx-2" style={{ backgroundColor: "#54456b" }}>
                    <i className="fab fa-twitter"></i>
                    <FontAwesomeIcon icon={faTwitter} />
                </button>
                </div>
                <div className="text-center text-white p-3" style={{backgroundColor: "rgba(0, 0, 0, 0.2)"}}>
                © 2023 Copyright:
                <a className="text-white" href="#">kotlinspringbootrestapi</a>
                </div>
            </div>
        </footer>
    )
}