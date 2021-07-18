import { useHistory } from "react-router-dom";
import "./bills.css";
export const Bills = () => {
  const history = useHistory();
  const broadband = () => {
    history.push("/broadband");
  };
  const cable = () => {
    history.push("/cable");
  };
  const dth = () => {
    history.push("/dth");
  };
  const electric = () => {
    history.push("/electricity");
  };
  const gas = () => {
    history.push("/gas");
  };
  const pipedgas = () => {
    history.push("/pipedgas");
  };
  const landline = () => {
    history.push("/landline");
  };
  const recharge = () => {
    history.push("/recharge");
  };
  const view = () => {
    history.push("/viewbills");
  };
  return (
    <div className="bg" style={{ height: "86vh" }}>
      <div className="container">
        <div className="row mt-3">
          <div className="col-sm ">
            <button className="text-center active h4 mt-3 p-1">
              Pay Bills
            </button>
          </div>
          <div className="col-sm">
            <button
              className="text-center h4 notactive mt-3 p-1"
              onClick={view}
            >
              View Bills
            </button>
          </div>
        </div>
        <div className="row mt-5">
          <div className="col-sm ">
            <div className="d-flex justify-content-center">
              <button className="landline buttons" onClick={landline}></button>
            </div>
            <p className="ptag">Landline</p>
          </div>
          <div className="col-sm ">
            <div className="d-flex justify-content-center">
              <button className="gas buttons" onClick={gas}></button>
            </div>
            <p className="ptag">Gas Cylinder</p>
          </div>
          <div className="col-sm ">
            <div className="d-flex justify-content-center">
              <button className="piped buttons" onClick={pipedgas}></button>
            </div>
            <p className="ptag">Piped Gas</p>
          </div>
          <div className="col-sm ">
            <div className="d-flex justify-content-center">
              <button className="electric buttons" onClick={electric}></button>
            </div>
            <p className="ptag">Electricity</p>
          </div>
        </div>
        <div className="row mt-5">
          <div className="col-sm ">
            <div className="d-flex justify-content-center">
              <button className="phone buttons" onClick={recharge}></button>
            </div>
            <p className="ptag">Mobile recharge</p>
          </div>
          <div className="col-sm ">
            <div className="d-flex justify-content-center">
              <button
                className="broadband buttons"
                onClick={broadband}
              ></button>
            </div>
            <p className="ptag">Broadband</p>
          </div>
          <div className="col-sm ">
            <div className="d-flex justify-content-center">
              <button className="cable buttons" onClick={cable}></button>
            </div>
            <p className="ptag">Cable</p>
          </div>
          <div className="col-sm ">
            <div className="d-flex justify-content-center">
              <button className="dth buttons" onClick={dth}></button>
            </div>
            <p className="ptag">DTH</p>
          </div>
        </div>
      </div>
    </div>
  );
};
