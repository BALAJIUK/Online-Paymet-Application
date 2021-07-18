import "./wallet.css";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  getBalance,
} from "../../redux/reducers/walletReducer";
import { useHistory } from "react-router-dom";
let balstyle = {
  backgroundColor: "black",
  height: "50vh",
  borderRadius: "18px",
};
export const Wallet = () => {
  const history = useHistory();
  const state = useSelector((state) => state);
  const dispatch = useDispatch();
 
  useEffect(() => {
    dispatch(getBalance()); 
  },[]);

  console.log(state.wallet.balance);
  const addmoney = () => {
    history.push("/addmoney");
  };
  return (
    <div className="bg" style={{ height: "86vh" }}>
      <div className="mt-5 d-flex align-items-center justify-content-center">
        <div className="container">
          <div className="row">
            <div
              className="col-sm mt-5 d-flex align-items-center justify-content-center"
              style={balstyle}
            >
              <div>
                <button onClick={addmoney} className="butnadd">
                  Add Money
                </button>
              </div>
            </div>
            <div className="col-sm mt-5 mb-5 " style={balstyle}>
              <p className="text-center text-light h3 ">Balance</p>
              <div
                className="d-flex align-items-center justify-content-center"
                style={{ height: "40vh" }}
              >
                <h1 className="text-center text-light">
                  ₹ {state.wallet.balance}
                </h1>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
