import { useState } from "react";
import { useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { addNewBenificiary } from "../../../redux/reducers/beneficiaryReducer";
import { BsFillPersonPlusFill } from "react-icons/bs";
import { useHistory } from "react-router-dom";
export const AddBeneficiary = () => {
  const formEl = useRef();
  const [mobileNumber, setMobileNumber] = useState("");
  const [name, setName] = useState("");

  const dispatch = useDispatch();

  const state = useSelector((state) => state);

  const updateMobileNumber = (e) => {
    console.log(e.target.value);
    const numericValue = e.target.value.replace(/[^\d]/gi, "");
    setMobileNumber(numericValue);
  };
  const updateName = (e) => {
    const charValue = e.target.value.replace(/[\d]/gi, "");
    setName(charValue);
  };

  const addNew = (e) => {
    console.log(formEl.current);
    e.preventDefault();

    console.log(formEl.current.checkValidity());
    if (formEl.current.checkValidity()) {
      dispatch(
        addNewBenificiary({
          mobileNumber: mobileNumber,
          name: name,
        })
      );

      // clear the form
      setMobileNumber("");
      setName("");
    } else {
      e.stopPropagation();
      formEl.current.classList.add("was-validated");
    }
  };
  const history=useHistory();
  const search = () => {
    history.push("./searchbeneficiary");
  };
  const remove=()=>{
    history.push("/deletebeneficiary");
}
  return (
    <div className="bg" style={{ height: "86vh" }}>
      <div className="container">
        <div className="row mt-3">
          <div className="col-sm">
            <button className="text-center active h4 mt-3 p-1">
              Add Beneficiary
            </button>
            </div>

          <div className="col-sm">
              <button onClick={search} className="text-center h4 notactive mt-3 p-1">
                Search
              </button>
          </div>
          <div className="col-sm">
              <button onClick={remove} className="text-center h4 notactive mt-3 p-1">
                Remove Beneficiary
              </button>
          </div>
        </div>
      </div>
      <div className="container">
          {state.beneficiary.msgStatus === true && (
            <h4 className="alert alert-success text-center" style={{borderRadius:"25px"}}>Account Added Successfully</h4>
          )}
        {state.beneficiary.errorStatus === true && (
            <h4 className="alert alert-danger text-center" style={{borderRadius:"25px"}}>{state.beneficiary.error}</h4>
          )}
          </div>
     <div
     className="d-flex justify-content-center align-items-center"
     style={{ height: "66vh" }}
   >
     <form ref={formEl} className="needs-validation" noValidate>
       <div className="text-center display-4  icons">
       <BsFillPersonPlusFill />
       </div>
       <table className="mt-3">
         <tbody>
           <tr>
             <td className="atd">Name</td>
             <td className="atd">
             <input
                 type="text"
                 className="form-control bg-dark text-light"
                 onChange={updateName}
                 value={name}
                 required
               />
             </td>
           </tr>
           <tr>
             <td className="atd">Mobile No:</td>
             <td className="atd">
               {" "}
               <input
                 type="text"
                 className="form-control bg-dark text-light"
                 onChange={updateMobileNumber}
                 value={mobileNumber}
                 maxLength="10"
                 minLength="10"
                 required
               />
             </td>
           </tr>
         </tbody>
       </table>
       <input
         type="button"
         value="ADD"
         className="btnsm mt-5"
         onClick={addNew}
       />
     </form>
   </div>
   </div>
 
  );
};
