import { Card, ListGroup, ListGroupItem } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import HDFC from "./hdfc.png";
import AXIS from "./axis.jpg";
import ICICI from "./icici.jpg";
import INDIAN from "./indian.jpg";
import SBI from "./sbi.png";
import SC from "./sc.jpg";
import YES from "./YesBank.jpg";
import UNION from "./union.jpg";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import {
  deleteAccount,
  getAccounts,
} from "../../../redux/reducers/bankReducer";
import { AiFillDelete } from "react-icons/ai";
import "./removeaccount.css";
import { MdAccountBalance } from "react-icons/md";

export const RemoveAccount = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getAccounts());
  }, []);
  const state = useSelector((state) => state);
  const history = useHistory();
  const add = () => {
    history.push("/addAccount");
  };
  const banks = () => {
    history.push("/bank");
  };
  const remove = (e) => {
    let account = e.target.value;
    dispatch(deleteAccount(account));
  };
  return (
    <div className="bg" style={{ height: "86vh" }}>
      <center>
        <div className=" display-4  icons " style={{ width: "60vh" }}>
          <MdAccountBalance />
        </div>
      </center>
      <div className="container">
        <div className="row mt-3">
          <div className="col-sm ">
            <button className="text-center notactive h4 mt-3 p-1" onClick={add}>
              Add Account
            </button>
          </div>
          <div className="col-sm">
            <button
              className="text-center h4 notactive mt-3 p-1"
              onClick={banks}
            >
              Bank Accounts
            </button>
          </div>
          <div className="col-sm">
            <button className="text-center  h4 active mt-3 p-1">
              Remove Account
            </button>
          </div>
        </div>
        <div className="container">
          {state.bank.deleteSuccess === true && (
            <h4
              className="alert alert-success text-center"
              style={{ borderRadius: "25px" }}
            >
              {state.bank.message}
            </h4>
          )}
        </div>
        <div className="mt-3">
          <div className="row">
            {state.bank.accounts.map((item, index) => (
              <div className="col-sm mt-5" key={index}>
                <div className="d-flex justify-content-center">
                  <Card style={{ width: "30vh" }}>
                    {item.bankName === "HDFC" && (
                      <Card.Img
                        variant="top"
                        src={HDFC}
                        style={{ height: "20vh" }}
                      />
                    )}
                    {item.bankName === "INDIAN" && (
                      <Card.Img
                        variant="top"
                        src={INDIAN}
                        style={{ height: "20vh" }}
                      />
                    )}
                    {item.bankName === "SBI" && (
                      <Card.Img
                        variant="top"
                        src={SBI}
                        style={{ height: "20vh" }}
                      />
                    )}
                    {item.bankName === "Standard Chatered" && (
                      <Card.Img
                        variant="top"
                        src={SC}
                        style={{ height: "20vh" }}
                      />
                    )}
                    {item.bankName === "Union Bank of India" && (
                      <Card.Img
                        variant="top"
                        src={UNION}
                        style={{ height: "20vh" }}
                      />
                    )}
                    {item.bankName === "AXIS" && (
                      <Card.Img
                        variant="top"
                        src={AXIS}
                        style={{ height: "20vh" }}
                      />
                    )}
                    {item.bankName === "ICICI" && (
                      <Card.Img
                        variant="top"
                        src={ICICI}
                        style={{ height: "20vh" }}
                      />
                    )}
                    {item.bankName === "Yes Bank" && (
                      <Card.Img
                        variant="top"
                        src={YES}
                        style={{ height: "20vh" }}
                      />
                    )}
                    <div className="bg-info">
                      <Card.Title className="text-center">
                        {item.bankName}
                      </Card.Title>
                    </div>
                    <ListGroup className="list-group-flush text-center ">
                      <ListGroupItem className="b bg-info">
                        Account No:{item.accountNo}
                      </ListGroupItem>

                      <button
                        className="delete"
                        onClick={remove}
                        value={item.accountNo}
                      >
                        <AiFillDelete />
                      </button>
                    </ListGroup>
                  </Card>
                </div>
              </div>
            ))}
          </div>
          ​
        </div>
      </div>
    </div>
  );
};
