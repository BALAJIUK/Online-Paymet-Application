import { FaLongArrowAltRight, FaMobileAlt } from "react-icons/fa";
import { GiRadarDish, GiWifiRouter } from "react-icons/gi";
import { IoWalletSharp } from "react-icons/io5";
import { MdAccountBalance } from "react-icons/md";
import { useHistory } from "react-router-dom";

import "./homepage2.css";
export const Homepage2 = () => {
  const history=useHistory();
    const sendmoney=()=>{
      history.push("/sendmoney");
    }
    const addmoney=()=>{
      history.push("/addmoney");
    }
    const deposit=()=>{
      history.push("/deposit");
    }
    const bills=()=>{
      history.push("/bills");
    }
  return (
    <div className="bg" style={{ height: "86vh"}}>
      <div className="container">
        <div className="row mt-5">
          <div className="col-sm d-flex align-items-center justify-content-center">
            <div>
              <button className="butn" onClick={sendmoney}>
                <div className="display-4">
                <IoWalletSharp color="white" className="ml-5" />
                <FaLongArrowAltRight color="white" />
                <IoWalletSharp color="white" className="mr-5" />
                </div>
                <p className="p mt-5 text-light">Send Money</p>
              </button>
            </div>
            </div>
            <div className="col-sm d-flex align-items-center justify-content-center">
            <div>
              <button className="butn" onClick={addmoney}>
                <div className="display-4">
                <MdAccountBalance color="white" className="ml-5" />
                <FaLongArrowAltRight color="white" />
                <IoWalletSharp color="white" className="mr-5" />
                </div>
                <p className="p mt-5 text-light">Add Money</p>
              </button>
            </div>
            </div>
        </div>
        <div className="row mt-5">
          <div className="col-sm d-flex align-items-center justify-content-center">
            <div>
              <button className="butn" onClick={deposit}>
                <div className="display-4">
                <IoWalletSharp color="white" className="ml-5" />
                <FaLongArrowAltRight color="white" />
                <MdAccountBalance color="white" className="mr-5" />
                </div>
                <p className="p mt-5 text-light">Deposit Money</p>
              </button>
            </div>
            </div>
            <div className="col-sm d-flex align-items-center justify-content-center">
            <div>
              <button className="butn" onClick={bills}>
                <div className="display-4">
                <GiRadarDish color="white" className="ml-5" />
                <FaMobileAlt color="white" />
                <GiWifiRouter color="white" className="mr-5" />
                </div>
                <p className="p mt-5 text-light">Pay bills</p>
              </button>
            </div>
            </div>
        </div>
      </div>
    </div>
  );
};
