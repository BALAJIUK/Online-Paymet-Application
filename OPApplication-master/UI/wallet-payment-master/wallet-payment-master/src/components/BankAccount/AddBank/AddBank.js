import { useRef, useState } from "react";
import { Form } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { addAccount } from "../../../redux/reducers/bankReducer";
import "./addbank.css";
import { MdAccountBalance } from "react-icons/md";
export const AddBank = () => {
  const history = useHistory();
  const state = useSelector((state) => state);
  const formEl = useRef();
  const logo = useRef();
  const remove = () => {
    history.push("/removeAccount");
  };
  const banks = () => {
    history.push("/bank");
  };
  let [account, setaccount] = useState("");
  let [code, setcode] = useState("");
  let [name, setname] = useState("");
  let [show1, setshow1] = useState(false);
  const updateaccount = (e) => {
    const numeric = e.target.value.replace(/[^\d]/gi, "");
    setaccount(numeric);
  };
  const updatecode = (e) => {
    const alphanumeric = e.target.value.replace(/[^a-zA-Z0-9-]/g, "");
    setcode(alphanumeric);
  };
  const dispatch = useDispatch();
  const addaccount = (e) => {
    e.preventDefault();
    let balance = Math.floor(Math.random() * (1000000 - 1000) + 700);
    if (formEl.current.checkValidity()) {
      dispatch(
        addAccount({
          bankName: name,
          accountNo: account,
          ifscCode: code,
          balance: balance,
        })
      );

      setshow1(false);
      setaccount("");
      setcode("");
    } else {
      e.stopPropagation();
      formEl.current.classList.add("was-validated");
    }
  };
  const logoselect = (e) => {
    logo.current.classList.remove("hdfc");
    logo.current.classList.remove("sbi");
    logo.current.classList.remove("indian");
    logo.current.classList.remove("axis");
    logo.current.classList.remove("sc");
    logo.current.classList.remove("sbank");
    logo.current.classList.remove("union");
    logo.current.classList.remove("icici");
    logo.current.classList.add("bg");
    if (e.target.value !== "") {
      if (e.target.value === "hdfc") {
        logo.current.classList.remove("bg");
        logo.current.classList.add("hdfc");
        setshow1(true);
        setname("HDFC");
      }
      if (e.target.value === "sbi") {
        logo.current.classList.remove("bg");
        logo.current.classList.add("sbi");
        setshow1(true);
        setname("SBI");
      }
      if (e.target.value === "indian") {
        logo.current.classList.remove("bg");
        logo.current.classList.add("indian");
        setshow1(true);
        setname("INDIAN");
      }
      if (e.target.value === "axis") {
        logo.current.classList.remove("bg");
        logo.current.classList.add("axis");
        setshow1(true);
        setname("AXIS");
      }
      if (e.target.value === "sc") {
        logo.current.classList.remove("bg");
        logo.current.classList.add("sc");
        setshow1(true);
        setname("Standard Chatered");
      }
      if (e.target.value === "sbank") {
        logo.current.classList.remove("bg");
        logo.current.classList.add("sbank");
        setshow1(true);
        setname("Yes Bank");
      }
      if (e.target.value === "union") {
        logo.current.classList.remove("bg");
        logo.current.classList.add("union");
        setshow1(true);
        setname("Union Bank of India");
      }
      if (e.target.value === "icici") {
        logo.current.classList.remove("bg");
        logo.current.classList.add("icici");
        setshow1(true);
        setname("ICICI");
      }
    }
  };

  return (
    <div ref={logo} className="bg" style={{ height: "86vh" }}>
      <center>
        <div className=" display-4  icons " style={{ width: "60vh" }}>
          <MdAccountBalance />
        </div>
      </center>
      <div className="container">
        <div className="row mt-3">
          <div className="col-sm ">
            <button className="text-center active h4 mt-3 p-1">
              Add Account
            </button>
          </div>
          <div className="col-sm">
            <button
              className="text-center h4 notactive mt-3 p-1"
              onClick={banks}
            >
              Bank Accounts
            </button>
          </div>
          <div className="col-sm">
            <button
              className="text-center h4 notactive mt-3 p-1"
              onClick={remove}
            >
              Remove Account
            </button>
          </div>
        </div>
        <div className="container">
          {state.bank.addSuccess === true && (
            <h4
              className="alert alert-success text-center"
              style={{ borderRadius: "25px" }}
            >
              {state.bank.message}
            </h4>
          )}
          {state.bank.errorStatus === true && (
            <h4
              className="alert alert-danger text-center"
              style={{ borderRadius: "25px" }}
            >
              {state.bank.error}
            </h4>
          )}
        </div>
        <Form.Control
          className="mt-5 bg-dark text-light"
          required
          as="select"
          type="select"
          onChange={logoselect}
        >
          <option value="">Select Your Bank</option>
          <option value="hdfc">HDFC Bank</option>
          <option value="indian">Indian Bank</option>
          <option value="sbi">State Bank Of India</option>
          <option value="axis">Axis Bank</option>
          <option value="sc">Standard Chatered Bank</option>
          <option value="sbank">Yes Bank</option>
          <option value="union">Union Bank Of India</option>
          <option value="icici">ICICI Bank</option>
        </Form.Control>
        {show1 === true && (
          <form ref={formEl} className="need-validation" noValidate>
            <div className="row mt-5">
              <label className="b">Account Number</label>
              <input
                type="text"
                className="form-control"
                placeholder="Account Number"
                onChange={updateaccount}
                value={account}
                required
                minLength="6"
                maxLength="6"
              />
              <label className="b mt-5">IFSC Code</label>
              <input
                type="text"
                className="form-control"
                placeholder="IFSC Code"
                onChange={updatecode}
                value={code}
                required
                minLength="10"
                maxLength="11"
              />
              <input
                type="button"
                value="Add Account"
                className="button mt-5"
                onClick={addaccount}
              />
            </div>
          </form>
        )}
      </div>
    </div>
  );
};
