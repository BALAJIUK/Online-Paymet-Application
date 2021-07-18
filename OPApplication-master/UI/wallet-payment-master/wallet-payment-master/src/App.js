import { Route, useHistory } from "react-router-dom";
import { Signup } from "./components/Signup/Signup";
import { Navbar2 } from "./components/NavBar2/Navbar2";
import { Navbar1 } from "./components/NavBar1/Navbar1";
import { Login } from "./components/Login/Login";
import { Homepage1 } from "./components/Homepage1/Homepage1";
import { Homepage2 } from "./components/Homepage2/Homepage2";
import { useSelector } from "react-redux";
import { SendMoney } from "./components/SendMoney/SendMoney";
import { AddMoney } from "./components/AddMoney/AddMoney";
import { Deposit } from "./components/Deposit/Deposit";
import { Wallet } from "./components/Wallet/Wallet";
import { Profile } from "./components/Profile/Profile";
import { UpdatePass } from "./components/Updatepass/Updatepass";
import { Broadband } from "./components/Bills/Broadband/Broadband";
import { Cable } from "./components/Bills/Cable/Cable";
import { Dth } from "./components/Bills/DTH/Dth";
import { Electricity } from "./components/Bills/Electric/Electricity";
import { Gas } from "./components/Bills/Gas/Gas";
import { Piped } from "./components/Bills/Piped/Piped";
import { Landline } from "./components/Bills/Landline/Landline";
import { Phone } from "./components/Bills/Phone/Phone";
import { Bills } from "./components/Bills/BillPay/Bills";
import { Viewbills } from "./components/Bills/ViewBills/Viewbills";
import { BankAccount } from "./components/BankAccount/BankAccounts/BankAccount";
import { AddBank } from "./components/BankAccount/AddBank/AddBank";
import { RemoveAccount } from "./components/BankAccount/RemoveAccount/RemoveAccount";
import { Transaction } from "./components/Transactions/Transaction";
import { AddBeneficiary } from "./components/Beneficiary/AddBeneficiary/AddBeneficiary";
import { ViewAllBeneficiary } from "./components/Beneficiary/ViewAllBeneficiary/ViewAllBeneficiary";
import { DeleteBeneficiary } from "./components/Beneficiary/DeleteBeneficiary/DeleteBeneficiary";
import { SearchBeneficiary } from "./components/Beneficiary/SearchBeneficiary/SearchBeneficiary";



function App() {
  const history=useHistory();
  const state=useSelector(state=>(state));
  const Authenticate=localStorage.getItem("Auth");
  if(Authenticate===null)
  {
  history.push("/signup");
  }
  return (
    <>
      {state.login.loginSuccess === false && Authenticate ===null && <Navbar1 />}
      {Authenticate !==null && <Navbar2 />}
      <Route exact path="/" component={Homepage1}></Route>
      <Route exact path="/home" component={Homepage2}></Route>
      <Route exact path="/sendmoney" component={SendMoney}></Route>
      <Route exact path="/addmoney" component={AddMoney}></Route>
      <Route exact path="/deposit" component={Deposit}></Route>
      <Route exact path="/bank" component={BankAccount}></Route>
      <Route exact path="/wallet" component={Wallet}></Route>
      <Route exact path="/profile" component={Profile}></Route>
      <Route exact path="/updatepassword" component={UpdatePass}></Route>
      <Route exact path="/bills" component={Bills}></Route>
      <Route exact path="/broadband" component={Broadband}></Route>
      <Route exact path="/cable" component={Cable}></Route>
      <Route exact path="/dth" component={Dth}></Route>
      <Route exact path="/electricity" component={Electricity}></Route>
      <Route exact path="/gas" component={Gas}></Route>
      <Route exact path="/pipedgas" component={Piped}></Route>
      <Route exact path="/landline" component={Landline}></Route>
      <Route exact path="/recharge" component={Phone}></Route>
      <Route exact path="/viewbills" component={Viewbills}></Route>
      <Route exact path="/addAccount" component={AddBank}></Route>
      <Route exact path="/removeAccount" component={RemoveAccount}></Route>
      <Route exact path="/transactions" component={Transaction}></Route>
      <Route exact path="/beneficiary" component={ViewAllBeneficiary}></Route>
      <Route exact path="/addbeneficiary" component={AddBeneficiary}></Route>
      <Route exact path="/deletebeneficiary" component={DeleteBeneficiary}></Route>
      <Route exact path="/searchbeneficiary" component={SearchBeneficiary}></Route>
      <Route exact path="/signup" component={Signup}></Route>
      <Route exact path="/login" component={Login}></Route>
  </>
  );
}

export default App;
