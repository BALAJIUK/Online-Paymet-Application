import { useEffect } from "react";
import { AiFillDelete } from "react-icons/ai";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { deleteBenificiaryFromWallet, getContacts } from "../../../redux/reducers/beneficiaryReducer";

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
    <div className="bg" style={{ height: "86vh" }}>
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
        <div className="grid container center">
          {state.beneficiary.contacts.map((item, index) => (
              <div key={index}>
            <div style={{ width: "13rem" }}  className="box App ">
              <div className="wrapper">
                <div className="icon popup">
                  <div className="tooltip">{item.name}</div>
                  <span>
                    <b
                      className=" avatar__letters  "
                      style={{
                        fontWeight: "bold",
                        fontSize: "30px",
                      }}
                    >
                      {item.name.charAt(0)}
                    </b>
                  </span>
                </div>
              </div>
              <p className="users">{item.mobileNumber}</p>
              <button style={{width:"100%"}} className="delete" onClick={remove} value={item.mobileNumber}><AiFillDelete/></button>

            </div>
            </div>
 ))}
        </div>
      </div>
    </div>
  );
};
