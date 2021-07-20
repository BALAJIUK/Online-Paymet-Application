import Img from "./LOGO11.1.jpg";
import { Nav, Navbar, NavDropdown } from "react-bootstrap";
import { AiFillHome, AiOutlineUserAdd } from "react-icons/ai";
import { Link, useHistory } from "react-router-dom";
import { IoOptions, IoWallet } from "react-icons/io5";
import { MdAccountBalance, MdDescription } from "react-icons/md";
import { IoMdSettings } from "react-icons/io";
import { HiCurrencyRupee } from "react-icons/hi";
import { BiLogOut } from "react-icons/bi";
import { useDispatch } from "react-redux";
import { logOut } from "../../redux/reducers/loginReducer";
export const Navbar2 = () => {
  const history = useHistory();
  const dispatch=useDispatch();
  const homepage = () => {
    history.push("/");
  };
  const logout=()=>{
    dispatch(logOut());
    history.push("/login")
  }
  const profile=()=>{
    history.push("/profile")
  }
  const updatepassword=()=>{
    history.push("/updatepassword")
  }
  return (
    <div className="sticky-top  ">
      <Navbar expand="lg" style={{ backgroundColor: "black" }}>
        <Navbar.Brand onClick={homepage}>
          <img
            src={Img}
            width="80"
            height="80"
            alt=""
            className="rounded-circle"
          />
        </Navbar.Brand>
        <h1 className="text-success mr-2" onClick={homepage}>
          Wal_Pay
        </h1>
        <Navbar.Toggle aria-controls="basic-navbar-nav">
          <span className="text-light"><IoOptions/></span>
        </Navbar.Toggle>
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link as={Link} to="/home">
              <div className="d-flex justify-content-center align-items-center h4 text-light navbar-links">
                <AiFillHome />
                Home
              </div>
            </Nav.Link>
            <Nav.Link as={Link} to="/wallet">
              <div className="d-flex justify-content-center align-items-center h4 text-light navbar-links">
                <IoWallet />
                Wallet
              </div>
            </Nav.Link>
            <Nav.Link as={Link} to="/bank">
              <div className="d-flex justify-content-center align-items-center h4 text-light navbar-links">
                <MdAccountBalance />
                Bank_Account
              </div>
            </Nav.Link>
            <Nav.Link as={Link} to="/bills">
              <div className="d-flex justify-content-center align-items-center h4 text-light navbar-links">
                <HiCurrencyRupee />
                Bill_Payments
              </div>
            </Nav.Link>
            <Nav.Link as={Link} to="/beneficiary">
              <div className="d-flex justify-content-center align-items-center h4 text-light navbar-links">
                <AiOutlineUserAdd />
                Beneficiary
              </div>
            </Nav.Link>
            <Nav.Link as={Link} to="/transactions">
              <div className="d-flex justify-content-center align-items-center h4 text-light navbar-links">
                <MdDescription />
                Transactions
              </div>
            </Nav.Link>
          </Nav>
          <Nav className="ml-auto">
            <div className="d-flex justify-content-center align-items-center h4 text-light">
              <IoMdSettings />
              <NavDropdown
                title={<span className="text-light my-auto">Settings</span>}
                id="collasible-nav-dropdown"
              >
                <NavDropdown.Item onClick={profile} >Your Profile</NavDropdown.Item>
                <NavDropdown.Item onClick={updatepassword}>
                  Update password
                </NavDropdown.Item>
              </NavDropdown>
            </div>
            <Nav.Link onClick={logout} >
              <div className="d-flex justify-content-center align-items-center h4 text-light">
                <BiLogOut />
                Log_Out
              </div>
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
  );
};
