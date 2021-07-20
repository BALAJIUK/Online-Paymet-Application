import { useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { logIn } from "../../redux/reducers/loginReducer";
import "./login.css"

export const Login=()=>{
  const formEl = useRef();
  const dispatch = useDispatch();
  const history=useHistory();
  const state = useSelector((state) => state);
  let [mobileNo, setMobileNo] = useState("");
  let [password, setPassword] = useState("");
  const updateMobile = (e) => {
    const numeric = e.target.value.replace(/[^\d]/gi, "");
    setMobileNo(numeric);
  };
  const updatePass = (e) => setPassword(e.target.value);
  const login = (e) => {
    e.preventDefault();
    if (formEl.current.checkValidity()) {
      dispatch(
        logIn({
          mobileNumber: mobileNo,
          password: password,
        })
      );
      setPassword("");
      setMobileNo("");
    } else {
      e.stopPropagation();
      formEl.current.classList.add("was-validated");
    }
  };
  const auth=localStorage.getItem("Auth");
  if ( auth !== null) {
    history.push("/home");
  }
    return(
<div
      className=" d-flex justify-content-center align-items-center bgimglogin"
    >
      <div className="bgimg" >
      {state.login.loginFailure === true && (
            <h3
              style={{ borderRadius: "25px" }}
              className="alert alert-danger text-center"
            >
              Invalid Credentials
            </h3>
          )}
          <h1 className="text-dark text-center">Log In</h1>
        <form ref={formEl} className="needs-validation" noValidate>
          <table className="mt-5" >
            <tbody>
              <tr>
                <td className="ltd">
                  <label>Mobile number :</label>
                </td>
                <td className="ltd">
                  <input className="form-control bg-dark text-light" type="text" 
                  value={mobileNo}
                  onChange={updateMobile}
                  required
                  minLength="10"
                  maxLength="10"
                  />
                </td>
              </tr>
              <tr>
                <td className="ltd">
                  <label>Password :</label>
                </td>
                <td className="ltd">
                  <input className="form-control bg-dark text-light" type="password"
                  value={password}
                  minLength="6"
                  onChange={updatePass}
                  required
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <input className="btnsup mt-5 " type="button" value="Log In" 
          onClick={login} 
          />
        </form>
      </div>
    </div>

    )
}