import { useEffect } from "react";
import { AiFillDelete } from "react-icons/ai";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { deleteBenificiaryFromWallet, getContacts } from "../../../redux/reducers/beneficiaryReducer";
import "../../common.css"
import "../ViewAllBeneficiary/viewallbeneficiary.css"
export const DeleteBeneficiary = () => {
  const state = useSelector((state) => state);
  const history = useHistory();
  const add = () => {
    history.push("/addbeneficiary");
  };
  const remove = (e) => {
    console.log(e.target.value);
    if(e.target.value!==undefined){
        let mobileNumber=e.target.value;
        dispatch(deleteBenificiaryFromWallet(mobileNumber));
    }
  };
  const dispatch=useDispatch();
  useEffect(() => {
    dispatch(getContacts());
  },[]);
  const search = () => {
    history.push("./searchbeneficiary");
  };
  return (
    <div className="bg" >
      <div className="container">
        <div className="row mt-3">
          <div className="col-sm">
            <button onClick={add} className="text-center notactive h4 mt-3 p-1">
              Add Beneficiary
            </button>
          </div>
          <div className="col-sm">
            <button onClick={search} className="text-center h4 notactive mt-3 p-1">
              Search
            </button>
          </div>
          <div className="col-sm">
            <button className="text-center h4 active mt-3 p-1">
              Remove Beneficiary
            </button>
          </div>
        </div>
        <div className="container">
          {state.beneficiary.msgStatus === true && (
            <h4
              className="alert alert-success text-center"
              style={{ borderRadius: "25px" }}
            >
              Removed Account Successfully
            </h4>
          )}
        </div >
        {state.beneficiary.contacts[0]=== undefined && (
        <h1 className="mt-5 text-dark d-flex justify-content-center">
          No Records Found
        </h1>
      )}
        <div className="grid container center">
          {state.beneficiary.contacts.map((item, index) => (
              <div key={index}>
            <div   className="box App wid13rem">
              <div className="wrapper">
                <div className="icon popup">
                  <div className="tooltip">{item.name}</div>
                  <span>
                    <b
                      className=" avatar__letters  fontsstyle"
                    >
                      {item.name.charAt(0).toUpperCase()}
                    </b>
                  </span>
                </div>
              </div>
              <p className="users">{item.mobileNumber}</p>
              <button  className="delete wid100" onClick={remove} value={item.mobileNumber}><AiFillDelete/></button>

            </div>
            </div>
 ))}
        </div>
      </div>
    </div>
  );
};
