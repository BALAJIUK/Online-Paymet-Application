import Img from "./LOGO11.1.jpg";
import { Nav, Navbar } from "react-bootstrap";
import { Link, useHistory } from "react-router-dom";
import { IoOptions } from "react-icons/io5";

export const Navbar1 = () => {
  const history=useHistory();
  const homepage=()=>{
    history.push("/")
  }
  return (
    <div className="sticky-top  ">
      <Navbar expand="lg" style={{backgroundColor:"black"}}>
        <Navbar.Brand onClick={homepage}>
          <img
            src={Img}
            width="80"
            height="80"
            alt=""
            className="rounded-circle"
          />
        </Navbar.Brand>
        <h1 className="text-success" onClick={homepage}>Wal-Pay</h1>
        <Navbar.Toggle aria-controls="basic-navbar-nav">
        <span className="text-light"><IoOptions/></span>
          </Navbar.Toggle>
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ml-auto">
            <Nav.Link as={Link} to="/signup">
            <div className="d-flex justify-content-center align-items-center h4 text-light">
              <h4 className="text-light">Sign Up</h4> 
            </div>
            </Nav.Link>
            <Nav.Link  as={Link} to="/login">
            <div className="d-flex justify-content-center align-items-center h4 text-light">
              <h4 className="text-light">Log In</h4> 
              </div>
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
  );
};
