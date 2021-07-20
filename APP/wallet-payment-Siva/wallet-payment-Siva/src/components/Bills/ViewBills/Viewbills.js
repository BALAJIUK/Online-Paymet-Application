import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { getbills } from "../../../redux/reducers/billReducer";
import "../../common.css";
export const Viewbills = () => {
  const history = useHistory();
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getbills());
  }, []);
  const pay = () => {
    history.push("/bills");
  };
  const state = useSelector((state) => state);
  return (
    <div className="bg" style={{ height: "86vh" }}>
      <div className="container">
        <div className="row mt-3">
          <div className="col-sm">
            <button onClick={pay} className="text-center notactive h4 mt-3 p-1">
              Pay Bills
            </button>
          </div>
          <div className="col-sm">
            <button className="text-center h4 active mt-3 p-1">
              View Bills
            </button>
          </div>
        </div>
        {state.bill.bills[0] === undefined && (
            <h1 className="mt-5 text-dark d-flex justify-content-center">
              No Records Found
            </h1>
          )}
        <table className="table" id="myTable">
          <thead className="thead-dark">
            <tr>
              <th scope="col">DESCRIPTION</th>
              <th scope="col">DATE</th>
              <th scope="col">AMOUNT</th>
            </tr>
          </thead>
          <tbody>
            {state.bill.bills.map((item, index) => (
              <tr key={index}>
                {" "}
                <th className="subtracted" scope="row">
                  {item.billType}
                </th>
                <th className="subtracted" scope="row">
                  {item.paymentDate}
                </th>
                <th className="subtracted" scope="row">
                  ₹{item.amount}
                </th>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};
