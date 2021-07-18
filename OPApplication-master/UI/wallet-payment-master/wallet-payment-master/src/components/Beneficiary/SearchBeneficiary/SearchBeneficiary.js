import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { getBenificiaryByMobileNumber } from "../../../redux/reducers/beneficiaryReducer";

export const SearchBeneficiary = () => {
  const dispatch = useDispatch();

  const state = useSelector((state) => state);
    let[counter ,setcounter]=useState(false);
  const [inputText, setInputText] = useState("");
  const update = (e) => {
    setInputText(e.target.value);
  };
  const search = () => {
    console.log("Im called");
    console.log(inputText);
    dispatch(getBenificiaryByMobileNumber(inputText));
    setcounter(true);
    setInputText("");
  };
  const history=useHistory();
  const add = () => {
    history.push("./addbeneficiary");
  };
  const remove=()=>{
    history.push("/deletebeneficiary");
}
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
            <button className="text-center h4 active mt-3 p-1">
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
        <form className="d-flex justify-content-center ">
          <input
            type="text"
            onChange={update}
            placeholder="Enter the Number"
            className="mr-2 form-control"
            minLength="10"
            maxLength="10"
          />
          <input
            type="button"
            value="Search"
            onClick={search}
            className="btn btn-outline-danger btn-sm"
          />
        </form>
        {state.beneficiary.getBySearch.name=== undefined&& counter===true && (
        <h1 className=" text-dark d-flex justify-content-center">
          No Records Found
        </h1>
      )}
      </div> 
       <div className=" grid container   ">
          {state.beneficiary.getBySearch.name!==undefined &&(
            <div style={{ width: "13rem" }} className="box App ">
              <div className="wrapper">
                <div className="icon popup">
                  <div className="tooltip">{state.beneficiary.getBySearch.name}</div>
                  <span>
                    <b
                      className=" avatar__letters  "
                      style={{
                        fontWeight: "bold",
                        fontSize: "30px",
                      }}
                    >
                      {state.beneficiary.getBySearch.name!==undefined && state.beneficiary.getBySearch.name.charAt(0)}
                    </b>
                  </span>
                </div>
              </div>
              <p className="users">{state.beneficiary.getBySearch.mobileNumber}</p>
            </div>
          )}
        </div>
    </div>
  );
};
