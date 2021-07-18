import { useRef } from "react";
import { useEffect, useState } from "react";
import { Form } from "react-bootstrap";
import { FaLongArrowAltRight } from "react-icons/fa";
import { IoWalletSharp } from "react-icons/io5";
import { MdAccountBalance } from "react-icons/md";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { getAccounts } from "../../redux/reducers/bankReducer";
import { depositMoney } from "../../redux/reducers/walletReducer";
import "./deposit.css";
export const Deposit = () => {
  const history = useHistory();
  const addmoney = () => {
    history.push("/addmoney");
  };
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getAccounts());
  }, []);
  const state = useSelector((state) => state);
  let [account,setaccount]=useState("Account number");
  const formEl=useRef();
  let [amount,setamount]=useState("");
  const updateaccount=(e)=>{
    console.log(e.target.value);
    setaccount(e.target.value);
  }
  const updateamount = (e) => {
    const numeric = e.target.value.replace(/[^\d]/gi, "");
    setamount(numeric);
  };
  const deposit=(e)=>{
    e.preventDefault();
    if(formEl.current.checkValidity()){
        dispatch(depositMoney({
          accountNumber:account,
          amount:amount
        }));
        setamount("");
    }
    else{
        e.stopPropagation();
        formEl.current.classList.add("was-validated");
    }
  }
  return (
    <div className="bg" style={{ height: "86vh" }}>
      <div className="container">
        <div className="row mt-3">
          <div className="col-sm ">
            <button
              onClick={addmoney}
              className="text-center notactive h4 mt-3 p-1"
            >
              Add Money
            </button>
          </div>
          <div className="col-sm">
            <button className="text-center h4 active mt-3 p-1">
              Deposit Money
            </button>
          </div>
        </div>
      </div>
      <div className="container">
          {state.wallet.transactionSuccess === true &&
          state.wallet.msgStatus === true && (
            <h4 className="alert alert-success text-center" style={{borderRadius:"25px"}}>{state.wallet.message}</h4>
          )}
        {state.wallet.transactionFailure === true &&
          state.wallet.msgStatus === true && (
            <h4 className="alert alert-danger text-center" style={{borderRadius:"25px"}}>{state.wallet.message}</h4>
          )}
          </div>
      <div
        className="d-flex justify-content-center align-items-center"
        style={{ height: "66vh" }}
      >
        <form ref={formEl} className="needs-validation" noValidate>
          <div className="text-center display-4  icons">
            <IoWalletSharp />
            <FaLongArrowAltRight />
            <MdAccountBalance />
          </div>
          <table className="mt-3">
            <tbody>
              <tr>
                <td className="dtd">Account Number</td>
                <td className="dtd">
                <Form.Control className="bg-dark text-light" required as="select" type="select" onChange={updateaccount}>
                    <option value="">{account}</option>
                    {state.bank.accounts[0] !== undefined &&
                      state.bank.accounts.map((item, index) => (
                        <option
                          className="h6"
                          key={index}
                          value={item.accountNo}
                        >
                          {item.accountNo} ({item.bankName})
                        </option>
                      ))}
       </Form.Control>
                </td>
              </tr>
              <tr>
                <td className="dtd">Amount</td>
                <td className="dtd">
                  <input
                    type="text"
                    className="form-control bg-dark text-light"
                    value={amount}
                    onChange={updateamount}
                    required
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <input type="button" value="DEPOSIT" className="btnsm mt-5" onClick={deposit} />
        </form>
      </div>
    </div>
  );
};
