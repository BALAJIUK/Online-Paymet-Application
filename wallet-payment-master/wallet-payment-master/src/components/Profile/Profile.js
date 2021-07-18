import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getAccounts } from "../../redux/reducers/bankReducer";
import { getContacts } from "../../redux/reducers/beneficiaryReducer";
import { getCustomer } from "../../redux/reducers/walletReducer";
import "./profile.css";

export const Profile = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getCustomer());
  },[]);
  useEffect(() => {
    dispatch(getAccounts());
  }, []);
  useEffect(() => {
    dispatch(getContacts());
  }, []);
  const state=useSelector(state=>(state));
  const customer=state.wallet.customer;
  console.log(customer);
  return (
    <div className="bg" style={{ height: "86vh" }}>
      <div className="container d-flex justify-content-center">
        <div className="green text-light mt-5  avatar d-flex align-items-center justify-content-center">
         {customer.name!==undefined &&(
        customer.name[0].toUpperCase()
         )}
        </div>
      </div>
      <h1 className="text-center bold">{state.wallet.customer.name}</h1>
      <div className="container">
        <div className=" text-light mt-1 d-flex align-items-center justify-content-center">
          <div className="green text-light p-3 brad">
            <table>
              <tbody>
                <tr>
                  <td>
                    <label>Mobile Number :</label>
                  </td>
                  <td>
                    <h4>{state.wallet.customer.mobileNumber}</h4>
                  </td>
                </tr>
                <tr>
                  <td>
                    <label>Bank Accounts :</label>
                  </td>
                  <td>
                    <h4>{state.bank.accounts.length}</h4>
                  </td>
                </tr>
                <tr>
                  <td>
                    <label>Beneficiary Accounts :</label>
                  </td>
                  <td>
                    <h4>{state.beneficiary.contacts.length}</h4>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};
