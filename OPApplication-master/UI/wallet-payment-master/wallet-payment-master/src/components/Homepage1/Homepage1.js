import "./homepage1.css";
import { MdAccountBalance } from "react-icons/md";
import { BiMoveHorizontal } from "react-icons/bi";
import { IoWalletSharp } from "react-icons/io5";
import { CgProfile } from "react-icons/cg";
import { AiOutlineSwap, AiOutlineUserAdd } from "react-icons/ai";
import { FaMobileAlt } from "react-icons/fa";
import { GiRadarDish, GiWifiRouter } from "react-icons/gi";
let style = {
  backgroundColor: "#001a02",
  height: "50vh",
  borderRadius: "18px",
};
export const Homepage1 = () => {
  return (
    <div className="bgimg-homepage ">
      <div className="container ">
        <div className="row">
          <div
            className="col-sm mt-5 display-2 d-flex align-items-center justify-content-center"
            style={style}
          >
            <div>
              <MdAccountBalance color="white" />
              <BiMoveHorizontal color="white" />
              <IoWalletSharp color="white" />
            </div>
          </div>
          <div className="col-sm mt-5" style={style}>
            <div>
              <h1 className="text-light text-center mt-5">Add Bank Account</h1>
              <h5 className="text-light mt-5">
                {" "}
                <li>Can add more than one Bank Account to your wallet.</li>
              </h5>
              <h5 className="text-light  mt-3">
                {" "}
                <li>Add money from your Bank Account to your wallet.</li>
              </h5>
            </div>
          </div>
        </div>
        <div className="row ">
          <div className="col-sm mt-5 " style={style}>
            <div>
              <h1 className="text-light text-center mt-5">
                Add Beneficiary
              </h1>
              <h5 className="text-light mt-5">
                {" "}
                <li>Can add more than one Beneficiary Account.</li>
              </h5>
              <h5 className="text-light  mt-3">
                {" "}
                <li>Can add only if the person is registered.</li>
              </h5>
            </div>
          </div>
          <div
            className="col-sm mt-5 display-2 d-flex align-items-center justify-content-center"
            style={style}
          >
            <div>
            <AiOutlineUserAdd color="white" />
              <CgProfile color="white" />
              <CgProfile color="white" />
            </div>
          </div>
        </div>
        <div className="row">
          <div
            className="col-sm mt-5 display-2  d-flex align-items-center justify-content-center"
            style={style}
          >
            <div>
              <IoWalletSharp color="white" />
              <AiOutlineSwap color="white" />
              <IoWalletSharp color="white" />
            </div>
          </div>
          <div className="col-sm mt-5" style={style}>
            <div>
              <h1 className="text-light text-center mt-5">
                Wallet to Wallet
              </h1>
              <h5 className="text-light mt-5">
                {" "}
                <li>Transfer money from one wallet to another.</li>
              </h5>
              <h5 className="text-light  mt-3">
                {" "}
                <li>Can transfer only if the person is in your Beneficiary.</li>
              </h5>
            </div>
          </div>
        </div>
        <div className="row">
        <div className="col-sm mt-5" style={style}>
            <div>
              <h1 className="text-light text-center mt-5">
                Wallet to Bank
              </h1>
              <h5 className="text-light mt-5">
                {" "}
                <li>Deposit money from wallet to Bank.</li>
              </h5>
              <h5 className="text-light  mt-3">
                {" "}
                <li>Can transfer only if the Account is linked to wallet.</li>
              </h5>
            </div>
          </div>
          <div
            className="col-sm mt-5 display-2  d-flex align-items-center justify-content-center"
            style={style}
          >
            <div>
              <IoWalletSharp color="white" />
              <AiOutlineSwap color="white" />
              <MdAccountBalance color="white" />
            </div>
          </div>
        </div>
        <div className="row">
          <div
            className="col-sm mt-5 display-2  d-flex align-items-center justify-content-center"
            style={style}
          >
            <div>
              <GiRadarDish color="white" />
              <FaMobileAlt color="white" />
              <GiWifiRouter color="white" />
            </div>
          </div>
          <div className="col-sm mt-5 mb-5" style={style}>
            <div>
              <h1 className="text-light text-center mt-5">
                Pay Bills
              </h1>
              <h5 className="text-light mt-5">
                {" "}
                <li>Pay bills using wallet.</li>
              </h5>
              <h5 className="text-light  mt-3">
                {" "}
                <li>DTH,Mobile,Broadband etc...</li>
              </h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
