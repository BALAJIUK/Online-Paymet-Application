import { useRef } from "react";
import { useEffect, useState } from "react";
import { FaLongArrowAltRight } from "react-icons/fa";
import { IoWalletSharp } from "react-icons/io5";
import { useDispatch, useSelector } from "react-redux";
import { getContacts } from "../../redux/reducers/beneficiaryReducer";
import { sendMoney } from "../../redux/reducers/walletReducer";
import "./sendmoney.css";
export const SendMoney = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getContacts());
  }, []);
  const state = useSelector((state) => state);
  console.log(state.beneficiary.contacts);
  const formEl = useRef();
  let [benef, setbenef] = useState("");
  let [amount, setamount] = useState("");
  const updatebenef = (e) => {
    const numeric = e.target.value.replace(/[^\d]/gi, "");
    setbenef(numeric);
  };
  const updateamount = (e) => {
    const numeric = e.target.value.replace(/[^\d]/gi, "");
    setamount(numeric);
  };
  const sendmoney = (e) => {
    e.preventDefault();
    if (formEl.current.checkValidity()) {
      console.log(benef, amount);
      dispatch(
        sendMoney({
          target: benef,
          amount: amount,
        })
      );

      setbenef("");
      setamount("");
    } else {
      e.stopPropagation();
      formEl.current.classList.add("was-validated");
    }
  };
  return (
    <div className="bg" style={{ height: "86vh" }}>
      <div className="container">
        <h1 className="headbg text-center mt-3 p-1">Send Money</h1>
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
            <IoWalletSharp />
          </div>
          <table className="mt-3">
            <tbody>
              <tr>
                <td className="std">Mobile Number</td>
                <td className="std">
                  <input
                    list="contacts"
                    className="form-control bg-dark text-white"
                    value={benef}
                    onChange={updatebenef}
                    required
                    maxLength="10"
                    minLength="10"
                  />
                  <datalist id="contacts">
                    {state.beneficiary.contacts[0] !== undefined &&
                      state.beneficiary.contacts.map((item, index) => (
                        <option value={item.mobileNumber}></option>
                      ))}
                  </datalist>
                </td>
              </tr>
              <tr>
                <td className="std">Amount</td>
                <td className="std">
                  <input
                    type="text"
                    className="form-control bg-dark text-light"
                    required
                    value={amount}
                    onChange={updateamount}
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <input
            type="button"
            value="SEND"
            className="btnsm mt-5"
            onClick={sendmoney}
          />
        </form>
      </div>
    </div>
  );
};
