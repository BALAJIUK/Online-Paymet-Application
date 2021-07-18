import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import DatePicker from "react-datepicker";
import moment from "moment";
import "react-datepicker/dist/react-datepicker.css";
import "./transaction.css";
import {
  getTransactionByDate,
  getTransactionById,
  getTransactionByType,
} from "../../redux/reducers/transactionReducer";

export const Transaction = () => {
  const dispatch = useDispatch();
  const state = useSelector((state) => state);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");

  useEffect(() => {
    dispatch(getTransactionById());
  }, [dispatch]);

  const viewRecordbytype = (item) => {
    console.log("viewRecord", item.target.value);

    dispatch(getTransactionByType(item.target.value));
  };

  const viewRecord = () => {
    console.log(
      "viewRecord",
      moment(startDate).format("YYYY-MM-DD"),
      moment(endDate).format("YYYY-MM-DD")
    );

    dispatch(
      getTransactionByDate(
        moment(startDate).format("YYYY-MM-DD"),
        moment(endDate).format("YYYY-MM-DD")
      )
    );
  };

  return (
    <>
      <div className="bg" style={{ height: "86vh" }}>
        <div className="container-lg">
          <h1 className="display -3 text-center">TRANSACTIONS</h1>

          <div className="row mt-4">
            <select
              id="type"
              className="form-control  col-sm-4 btn btn-light"
              onChange={viewRecordbytype}
              placeholder="Select Type"
            >
              <option>Select Transaction By Type</option>
              <option value="Transfer">Transfer</option>
              <option value="bill payment">bill payment</option>
            </select>

            <div id="date" className="row mt-4 pr-4">
              <div className="pr-2">
                <DatePicker
                  className="form-control btn btn-light"
                  selected={startDate}
                  onChange={(date) => setStartDate(date)}
                  maxDate={new Date()}
                  placeholderText="From"
                  isClearable
                  required
                />
              </div>
              <div className="pr-2">
                <DatePicker
                  className="form-control btn btn-light"
                  selected={endDate}
                  onChange={(date) => setEndDate(date)}
                  maxDate={new Date()}
                  placeholderText="To"
                  isClearable
                  required
                />
              </div>
              {startDate <= endDate && (
                <div className="pr-1">
                  <button className="btn btn-dark" onClick={viewRecord}>
                    Search
                  </button>
                </div>
              )}
            </div>
          </div>
        </div>

        <div className="container">
          <table id="myTable" className="table">
            <thead className="thead-dark">
              <tr>
                <th scope="col">TRANSACTION DISCRIPTION</th>
                <th scope="col">TRANSACTION DATE</th>
                <th scope="col">TRANSACTION AMOUNT</th>
              </tr>
            </thead>

            <tbody>
              {state.transaction.transactionsList.length > 0 &&
                state.transaction.transactionsList.map((item, index) => (
                  <tr key={index}>
                    {item.description.includes("from") && (
                      <>
                        <th className="added" scope="row">
                          {item.description}
                        </th>
                        <th className="added" scope="row">
                          {item.transactionDate}
                        </th>
                        <th className="added" scope="row">
                          ₹{item.amount}
                        </th>
                      </>
                    )}
                    {item.description.includes("from")===false && (
                      <>
                        <th className="subtracted" scope="row">
                          {item.description}
                        </th>
                        <th className="subtracted" scope="row">
                          {item.transactionDate}
                        </th>
                        <th className="subtracted" scope="row">
                          ₹{item.amount}
                        </th>
                      </>
                    )}
                  </tr>
                ))}
            </tbody>
          </table>
          {state.transaction.transactionsList.length === 0 && (
            <h1 className="text-danger">
              <center>No Record Found</center>
            </h1>
          )}
        </div>
      </div>
    </>
  );
};
