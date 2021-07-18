import { useRef, useState } from "react";
import { Form } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { paybill } from "../../../redux/reducers/billReducer";
import "./dth.css"
let tatasky = [
    { plan: "All Channels 3999 per month", price: "3999" },
    { plan: "Sports Pack  1399 per month", price: "1399" },
    { plan: "South Pack  999 per month", price: "999" },
    { plan: "Regional 599 per month", price: "599" },
  ];
  let sundirect = [
    { plan: "HD Pack 1949 per month", price: "1949" },
    { plan: "Sports Pack 1024 per month", price: "1024" },
    { plan: "Regional Pack 688 per month", price: "688" },
    { plan: "Kids Pack 199 per month", price: "199" },
  ];
  let airtel = [
    { plan: "HD Pack 2049 per month", price: "2049" },
    { plan: "Sports Pack 1224 per month", price: "1224" },
    { plan: "Regional Pack 988 per month", price: "988" },
    { plan: "Kids Pack 1099 per month", price: "1099" },
  ];
export const Dth=()=>{
    const bgpic = useRef();
    const formEl = useRef();
    let [show1, setshow1] = useState(false);
    let [show2, setshow2] = useState(false);
    let [show3, setshow3] = useState(false);
    let [price, setprice] = useState("");
    let [brand, setbrand] = useState("");
    let [customer, setcustomer] = useState("");
    const picselect = (e) => {
      bgpic.current.classList.add("bg");
      bgpic.current.classList.remove("sundirect");
      bgpic.current.classList.remove("airtel");
      bgpic.current.classList.remove("tatasky");
      setshow1(false);
      if (e.target.value !== "") {
        console.log(e.target.value);
        if (e.target.value === "sundirect") {
          bgpic.current.classList.remove("bg");
          bgpic.current.classList.add("sundirect");
          setbrand(sundirect);
          setshow1(true);
        }
        if (e.target.value === "tatasky") {
          bgpic.current.classList.remove("bg");
          bgpic.current.classList.add("tatasky");
          setbrand(tatasky);
          setshow1(true);
        }
        if (e.target.value === "airtel") {
          bgpic.current.classList.remove("bg");
            bgpic.current.classList.add("airtel");
            setbrand(airtel);
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
    const dispatch=useDispatch();
    const pay = () => {
      dispatch(paybill(
        {
          billType:"DTH bill",
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
      <div ref={bgpic} className="bg" style={{ height: "86vh" }}>
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
            <Form.Control
              className="mt-5 bg-dark text-light"
              required
              as="select"
              type="select"
              onChange={picselect}
            >
              <option value="">Select Your DTH Operator</option>
              <option value="airtel">Airtel</option>
              <option value="sundirect">Sun Direct</option>
              <option value="tatasky">Tata Sky</option>
            </Form.Control>
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
  
              <Form.Control
                className="mt-5 bg-dark text-light"
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
}