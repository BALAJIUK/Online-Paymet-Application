import { useState } from "react";
import { useRef } from "react";
import { Form } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { paybill } from "../../../redux/reducers/billReducer";
import "./electric.css"
import "../../common.css"
export const Electricity=()=>{
    const bgpic = useRef();
    const formEl = useRef();
    let [show1, setshow1] = useState(false);
    let [show2, setshow2] = useState(false);
    let [show3, setshow3] = useState(false);
    let [price, setprice] = useState("");
    let [customer, setcustomer] = useState("");
    const picselect = (e) => {
      bgpic.current.classList.add("bg");
      bgpic.current.classList.remove("kseb");
      bgpic.current.classList.remove("tneb");
      bgpic.current.classList.remove("ap");
      setshow1(false);
      if (e.target.value !== "") {
        console.log(e.target.value);
        if (e.target.value === "tn") {
          bgpic.current.classList.add("tneb");
          bgpic.current.classList.remove("bg");
          setshow1(true);
        }
        if (e.target.value === "ap") {
          bgpic.current.classList.add("ap");
          bgpic.current.classList.remove("bg");
          setshow1(true);
        }
        if (e.target.value === "kr") {
          bgpic.current.classList.add("kseb");
          bgpic.current.classList.remove("bg");
          setshow1(true);
        }
      }
    };
    const custid = (e) => {
      e.preventDefault();
      if (formEl.current.checkValidity()) {
        let cost=Math.floor(Math.random() * (2001 - 700) + 700);
        setprice(cost);
        setshow2(true);
        setshow1(false);
        setshow3(true);
      } else {
        formEl.current.classList.add("was-validated");
        e.stopPropagation();
      }
    };
    const updatecust = (e) => {
      const numeric = e.target.value.replace(/[^\d]/gi, "");
      setcustomer(numeric);
    };
    const dispatch=useDispatch();
    const pay = () => {
      dispatch(paybill(
        {
          billType:"Electricity bill",
          amount:price,
        }
      ))
      setcustomer("");
      setshow3(false);
        setshow2(false);
        setshow1(false);
    };
    const state=useSelector(state=>(state));
    return (
      <div ref={bgpic} className="bg">
        <div className="container">
        <div className="container">
          {state.bill.transactionSuccess === true &&
          state.bill.msgStatus === true && (
            <h4 className="alert alert-success text-center" style={{borderRadius:"25px"}}>{state.wallet.message}</h4>
          )}
        {state.bill.transactionFailure === true &&
          state.bill.msgStatus === true && (
            <h4 className="alert alert-danger text-center" style={{borderRadius:"25px"}}>{state.wallet.message}</h4>
          )}
          </div>
          {show2 === false && (
            <>
            <label className="mt-5 b">State:</label>
            <Form.Control
              className=" bg-dark text-light"
              required
              as="select"
              type="select"
              onChange={picselect}
            >
              <option value="">Select Your State</option>
              <option value="tn">Tamil nadu</option>
              <option value="ap">Andhra Pradesh</option>
              <option value="kr">Kerala</option>
            </Form.Control>
            </>
          )}
          {show1 === true && (
            <form ref={formEl} className="need-validation" noValidate>
              <div className="row mt-5">
                <div className="col-sm-10">
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Registered Mobile Number"
                    onChange={updatecust}
                    value={customer}
                    required
                    minLength="10"
                    maxLength="10"
                  />
                </div>
                <div className="col-sm-2 d-flex justify-content-center">
                  <input
                    type="button"
                    value="Submit"
                    className="button"
                    onClick={custid}
                  />
                </div>
              </div>
            </form>
          )}
          {show1 === false && show2 === true && (
            <div>
              <h1 className="custid mt-5" style={{ borderRadius: "18px" }}>
                {customer}
              </h1>
            </div>
          )}
          {show3 === true && (
            <div className="mt-5 row">
              <div className="col-sm-10">
                <input
                  type="text"
                  defaultValue={price}
                  className="disable form-control "
                />
              </div>
              <div className="col-sm-2 d-flex justify-content-center">
                <input
                  type="button"
                  value="Pay"
                  className="button"
                  onClick={pay}
                />
              </div>
            </div>
          )}
        </div>
      </div>
    );
}