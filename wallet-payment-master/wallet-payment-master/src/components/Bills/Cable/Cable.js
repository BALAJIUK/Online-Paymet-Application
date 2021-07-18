import { useRef, useState } from "react";
import { Form } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { paybill } from "../../../redux/reducers/billReducer";
import "./cable.css"
let acttv = [
    { plan: "All Channels 1999 per month", price: "1999" },
    { plan: "Sports Pack  999 per month", price: "999" },
    { plan: "South Pack  1499 per month", price: "1499" },
    { plan: "Regional 599 per month", price: "599" },
  ];
  let asianet = [
    { plan: "Karnataka Pack 1049 per month", price: "1049" },
    { plan: "Kerala Pack 924 per month", price: "924" },
    { plan: "Sports Pack 988 per month", price: "988" },
    { plan: "Kids Pack 999 per month", price: "999" },
  ];
export const Cable=()=>{
  const dispatch=useDispatch();
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
      bgpic.current.classList.remove("asianettv");
      bgpic.current.classList.remove("actdigtv");
      setshow1(false);
      if (e.target.value !== "") {
        console.log(e.target.value);
        if (e.target.value === "acttv") {
          bgpic.current.classList.add("actdigtv");
          bgpic.current.classList.remove("bg");
          setbrand(acttv);
          setshow1(true);
        }
        else if (e.target.value === "asianet") {
          bgpic.current.classList.add("asianettv");
          bgpic.current.classList.remove("bg");
          setbrand(asianet);
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
          billType:"Cable bill",
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
              <option value="">Select Your Cable Operator</option>
              <option value="asianet">Asianet</option>
              <option value="acttv">Act Digital</option>
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