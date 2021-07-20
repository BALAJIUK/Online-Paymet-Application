import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { getContacts } from "../../../redux/reducers/beneficiaryReducer";
import "./viewallbeneficiary.css";
export const ViewAllBeneficiary = () => {
  const history = useHistory();
  const dispatch = useDispatch();
  const add = () => {
    history.push("./addbeneficiary");
  };
  const search = () => {
    history.push("./searchbeneficiary");
  };
  useEffect(() => {
    dispatch(getContacts());
  },[]);
  const remove=()=>{
    history.push("/deletebeneficiary");
}
  const state = useSelector((state) => state);
console.log(state.beneficiary.contacts);
  return (
    <div className="bg" style={{ height: "86vh" }}>
      <div className="container">
        <div className="row mt-3">
          <div className="col-sm">
            <button
              onClick={add}
              className="text-center notactive h4 mt-3 p-1"
            >
              Add Beneficiary
            </button>
          </div>
          <div className="col-sm">
            <button onClick={search}  className="text-center h4 notactive mt-3 p-1">
              Search
            </button>
          </div>
          <div className="col-sm">
            <button onClick={remove}  className="text-center h4 notactive mt-3 p-1">
              Remove Beneficiary
            </button>
          </div>
        </div>
        {state.beneficiary.contacts[0]=== undefined && (
        <h1 className="mt-5 text-dark d-flex justify-content-center">
          No Records Found
        </h1>
      )}
        <div className=" grid container center  ">
          {state.beneficiary.contacts.map((item, index) => (
            <div key={index} className="box App wid13rem">
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
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};
