import { useRef } from "react";
import { useEffect, useState } from "react";
import { Form } from "react-bootstrap";
import { FaLongArrowAltRight } from "react-icons/fa";
import { IoWalletSharp } from "react-icons/io5";
import { MdAccountBalance } from "react-icons/md";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { getAccounts } from "../../redux/reducers/bankReducer";
import { addMoney } from "../../redux/reducers/walletReducer";
import "../common.css"
import "./addmoney.css";
export const AddMoney = () => {
  const history = useHistory();
  const deposit = () => {
    history.push("/deposit");
  };
  const formEl = useRef();
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getAccounts());
  }, []);
  let [account, setaccount] = useState("");
  let [amount, setamount] = useState("");
  const updateaccount = (e) => {
    console.log(e.target.value);
    setaccount(e.target.value);
  };
  const updateamount = (e) => {
    const numeric = e.target.value.replace(/[^\d]/gi, "");
    setamount(numeric);
  };
  const state = useSelector((state) => state);
  const addmoney = (e) => {
    e.preventDefault();
    if (formEl.current.checkValidity()) {
      dispatch(
        addMoney({
          accountNumber: account,
          amount: amount,
        })
      );
      setamount("");
      formEl.current.reset();
    } else {
      e.stopPropagation();
      formEl.current.classList.add("was-validated");
    }
  };
  return (
    <div className="bg" >
      <div className="container">
        <div className="row mt-3">
          <div className="col-sm ">
            <button className="text-center active h4 mt-3 p-1">
              Add Money
            </button>
          </div>
          <div className="col-sm">
            <button
              onClick={deposit}
              className="text-center h4 notactive mt-3 p-1"
            >
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
        className="d-flex justify-content-center align-items-center h66"
      >
        <form ref={formEl} className="needs-validation" noValidate>
          <div className="text-center display-4  icons">
            <MdAccountBalance />
            <FaLongArrowAltRight />
            <IoWalletSharp />
          </div>
          <table className="mt-3">
            <tbody>
              <tr>
                <td className="atd">Account Number</td>
                <td className="atd">
                  <Form.Control
                    className="bg-dark text-light"
                    required
                    as="select"
                    type="select"
                    onChange={updateaccount}
                  >
                    <option value="">Account number</option>
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
                <td className="atd">Amount</td>
                <td className="atd">
                  {" "}
                  <input
                    type="text"
                    id="amount"
                    className="form-control bg-dark text-light"
                    onChange={updateamount}
                    value={amount}
                    required
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <input
            type="button"
            value="SEND"
            className="btnsm mt-5"
            onClick={addmoney}
          />
        </form>
      </div>
    </div>
  );
};
