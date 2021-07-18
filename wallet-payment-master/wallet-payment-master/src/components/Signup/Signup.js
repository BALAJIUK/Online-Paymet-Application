import { useRef, useState } from "react";
import "./signup.css";
import IMG from "./RegSuccess.jpg";
import { useDispatch, useSelector } from "react-redux";
import { registration } from "../../redux/reducers/registerReducer";
export const Signup = () => {
  const formEl = useRef();
  const dispatch = useDispatch();
  const state = useSelector((state) => state);
  let [name, setName] = useState("");
  let [mobileNo, setMobileNo] = useState("");
  let [password, setPassword] = useState("");
  const updateNmae = (e) => setName(e.target.value);
  const updateMobile = (e) => {
    const numeric = e.target.value.replace(/[^\d]/gi, "");
    setMobileNo(numeric);
  };
  const updatePass = (e) => setPassword(e.target.value);
  const register = (e) => {
    e.preventDefault();
    console.log(name, mobileNo, password);
    if (formEl.current.checkValidity()) {
      dispatch(
        registration({
          name: name,
          mobileNumber: mobileNo,
          password: password,
        })
      );
      setName("");
      setPassword("");
      setMobileNo("");
    } else {
      e.stopPropagation();
      formEl.current.classList.add("was-validated");
    }
  };
  return (
    <div
      className=" d-flex justify-content-center align-items-center bgimgsignup"
      style={{ height: "100vh", fontSize: "30px" }}
    >
      {state.register.regSuccess === true && (
        <img className="mt-5 mb-5" src={IMG} alt="" width="360" height="360" />
      )}
      {state.register.regSuccess === false && (
        <div className="bgimg">
          {state.login.loginFailure === true && (
            <h3
              style={{ borderRadius: "25px" }}
              className="alert alert-danger text-center"
            >
              Invalid Credentials
            </h3>
          )}
          {state.register.errorStatus === true && (
            <h3
              style={{ borderRadius: "25px" }}
              className="alert alert-danger text-center"
            >
              {state.register.error}
            </h3>
          )}
          <h1 className="text-center mt-1">Sign Up</h1>
          <form ref={formEl} className="needs-validation" noValidate>
            <table className="mt-5">
              <tbody>
                <tr>
                  <td className="suptd">
                    <label>Name :</label>
                  </td>
                  <td className="suptd">
                    <input
                      className="form-control bg-dark text-light"
                      type="text"
                      value={name}
                      onChange={updateNmae}
                      required
                      maxLength="30"
                      minLength="3"
                    />
                  </td>
                </tr>
                <tr>
                  <td className="suptd">
                    <label>Mobile number :</label>
                  </td>
                  <td className="suptd">
                    <input
                      className="form-control bg-dark text-light"
                      type="text"
                      value={mobileNo}
                      onChange={updateMobile}
                      required
                      minLength="10"
                      maxLength="10"
                    />
                  </td>
                </tr>
                <tr>
                  <td className="suptd">
                    <label>Password :</label>
                  </td>
                  <td className="suptd">
                    <input
                      className="form-control bg-dark text-light"
                      type="password"
                      value={password}
                      onChange={updatePass}
                      required
                      minLength="6"
                    />
                  </td>
                </tr>
              </tbody>
            </table>
            <input
              className="btnsup mt-5 "
              type="button"
              value="Register"
              onClick={register}
            />
          </form>
        </div>
      )}
    </div>
  );
};
