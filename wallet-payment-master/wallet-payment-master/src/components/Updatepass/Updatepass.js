import { useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { updatepassword } from "../../redux/reducers/walletReducer";
import "./updatepass.css";
export const UpdatePass = () => {
  const formEl = useRef();
  const state=useSelector(state=>(state));
  let [password, setpassword] = useState("");
  let [reenterpassword, setreenterpass] = useState("");
  const pass = useRef();
  const reenterpass = useRef();
  const dispatch = useDispatch();
  const updatepass = (e) => {
    setpassword(e.target.value);
    if (e.target.value.length < 6) {
      pass.current.style.borderColor = "red";
      pass.current.style.borderStyle = "solid";
      pass.current.style.borderWidth = "3px";
    } else {
      pass.current.style.borderColor = "green";
      pass.current.style.borderStyle = "solid";
      pass.current.style.borderWidth = "3px";
    }
  };
  const updatereenterpass = (e) => {
    setreenterpass(e.target.value);
    if (password !== e.target.value) {
      reenterpass.current.style.borderColor = "red";
      reenterpass.current.style.borderStyle = "solid";
      reenterpass.current.style.borderWidth = "3px";
    } else {
      reenterpass.current.style.borderColor = "green";
      reenterpass.current.style.borderStyle = "solid";
      reenterpass.current.style.borderWidth = "3px";
    }
  };
  const update = (e) => {
    e.preventDefault();
    if (
      formEl.current.checkValidity() &&
      password === reenterpassword &&
      password.length >= 6
    ) {
      dispatch(updatepassword({ password: password }));
      setpassword("");
      setreenterpass("");
    } else {
      reenterpass.current.style.borderColor = "red";
      reenterpass.current.style.borderStyle = "solid";
      reenterpass.current.style.borderWidth = "3px";
      pass.current.style.borderColor = "red";
      pass.current.style.borderStyle = "solid";
      pass.current.style.borderWidth = "3px";
      e.stopPropagation();
    }
  };
  return (
    <div
      className=" d-flex justify-content-center align-items-center bg"
      style={{ height: "86vh", fontSize: "30px" }}
    >
      <div className="logo">
        <div>
          {state.wallet.transactionSuccess===true && state.wallet.msgStatus===true && (
            <h3 style={{borderRadius:"25px"}} className="alert alert-success text-center">{state.wallet.message}</h3>
          )}
        </div>
        <h1 className="text-dark text-center">Update Password</h1>
        <form ref={formEl}>
          <table className="mt-5">
            <tbody>
              <tr>
                <td>
                  <label>Enter Password:</label>
                </td>
                <td>
                  <input
                    ref={pass}
                    className="form-control bg-dark text-light"
                    type="password"
                    value={password}
                    onChange={updatepass}
                    required
                    minLength="6"
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label>Re-Enter Password :</label>
                </td>
                <td>
                  <input
                    ref={reenterpass}
                    className="form-control bg-dark text-light error"
                    type="password"
                    value={reenterpassword}
                    minLength="6"
                    onChange={updatereenterpass}
                    required
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <input
            className="btnsup mt-5 "
            type="button"
            value="Update"
            onClick={update}
          />
        </form>
      </div>
    </div>
  );
};
