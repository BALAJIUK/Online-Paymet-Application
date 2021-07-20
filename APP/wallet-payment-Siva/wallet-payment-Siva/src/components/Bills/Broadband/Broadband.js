import { useState } from "react";
import { useRef } from "react";
import { Form } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { paybill } from "../../../redux/reducers/billReducer";
import "./broadband.css";
import "../../common.css"
let Airtel = [
  { plan: "Entertainment (200 Mbps) 999 per month", price: "999" },
  { plan: "Premium (100 Mbps) 799 per month", price: "799" },
  { plan: "Ultra (300 Mbps) 1499 per month", price: "1499" },
  { plan: "Vip (1 Gbps) 3499 per month", price: "3999" },
];
let Bsnl = [
  { plan: "Fibre Basic (30 Mbps) 449 per month", price: "449" },
  { plan: "Fibre premium plus (200 Mbps) 7024 for 6 months", price: "7024" },
  { plan: "Fibre Basic 7188 (60 Mbps) for 12 month", price: "7188" },
  { plan: "Fibre premium plus (200 Mbps) 26179 for 24 months", price: "26179" },
];
let Act = [
  { plan: "Act Starter (40 Mbps) 549 per month", price: "549" },
  { plan: "Act Basic (75 Mbps) 820 per month", price: "820" },
  { plan: "Act Blaze (125 Mbps) 1020 per month", price: "1020" },
];
export const Broadband = () => {
  const bgpic = useRef();
  const formEl = useRef();
  const dispatch=useDispatch();
  let [show1, setshow1] = useState(false);
  let [show2, setshow2] = useState(false);
  let [show3, setshow3] = useState(false);
  let [price, setprice] = useState("");
  let [brand, setbrand] = useState("");
  let [customer, setcustomer] = useState("");
  const picselect = (e) => {
    bgpic.current.classList.add("bg");
    bgpic.current.classList.remove("bsnl");
    bgpic.current.classList.remove("act");
    bgpic.current.classList.remove("airtelbb");
    setshow1(false);
    if (e.target.value !== "") {
      console.log(e.target.value);
      if (e.target.value === "airtel") {
        bgpic.current.classList.add("airtelbb");
        bgpic.current.classList.remove("bg");
        setbrand(Airtel);
        setshow1(true);
      }
      if (e.target.value === "bsnl") {
        bgpic.current.classList.add("bsnl");
        bgpic.current.classList.remove("bg");
        setbrand(Bsnl);
        setshow1(true);
      }
      if (e.target.value === "act") {
        bgpic.current.classList.add("act");
        bgpic.current.classList.remove("bg");
        setbrand(Act);
        setshow1(true);
      }
    }
  };
  const custid = (e) => {
    e.preventDefault();
    if (formEl.current.checkValidity()) {
      console.log(customer);
      setshow2(true);
      setshow1(false);

    } else {
      formEl.current.classList.add("was-validated");
      e.stopPropagation();
    }
  };
  const updatecust = (e) => {
    const numeric = e.target.value.replace(/[^\d]/gi, "");
    setcustomer(numeric);
  };
  const plan = (e) => {
    if (e.target.value !== "") {
      console.log(e.target.value);
      setshow3(true);
      setprice(e.target.value);
    }
  };
  const pay = () => {
    dispatch(paybill(
      {
        billType:"Broadband bill",
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
    <div ref={bgpic} className="bg" >
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
          <label className="mt-5 b">Provider:</label>
          <Form.Control
            className="bg-dark text-light"
            required
            as="select"
            type="select"
            onChange={picselect}
          >
            <option value="">Select Your Broadband brand</option>
            <option value="airtel">Airtel</option>
            <option value="bsnl">BSNL</option>
            <option value="act">ACT</option>
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
            <label className="mt-5 b">Plan:</label>
            <Form.Control
              className=" bg-dark text-light"
              required
              as="select"
              type="select"
              onChange={plan}
            >
              <option value="">Select your plan....</option>
              {brand.map((item, index) => (
                <option key={index} value={item.price}>
                  {item.plan}
                </option>
              ))}
            </Form.Control>
          </div>
        )}
        {show3 === true && (
          <div className="mt-5 row">
            <div className="col-sm-10">
              <input
                type="text"
                value={price}
                onChange={plan}
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
};
