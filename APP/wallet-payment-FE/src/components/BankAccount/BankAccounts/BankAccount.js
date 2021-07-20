import { useEffect } from "react";
import { Card, ListGroup, ListGroupItem } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { getAccounts } from "../../../redux/reducers/bankReducer";
import HDFC from "./hdfc.png";
import AXIS from "./axis.jpg";
import ICICI from "./icici.jpg";
import INDIAN from "./indian.jpg";
import SBI from "./sbi.png";
import SC from "./sc.jpg";
import YES from "./YesBank.jpg";
import UNION from "./union.jpg";
import "../../common.css"
export const BankAccount = () => {
  const dispatch = useDispatch();
  const history = useHistory();
  useEffect(() => {
    dispatch(getAccounts());
  }, []);
  const state = useSelector((state) => state);
  const remove = () => {
    history.push("/removeAccount");
  };
  const add = () => {
    history.push("/addAccount");
  };
  return (
    <div className="bg">
      <div className="container">
        <div className="row mt-3">
          <div className="col-sm ">
            <button className="text-center notactive h4 mt-3 p-1" onClick={add}>
              Add Account
            </button>
          </div>
          <div className="col-sm">
            <button className="text-center h4 active mt-3 p-1">
              Bank Accounts
            </button>
          </div>
          <div className="col-sm">
            <button
              className="text-center h4 notactive mt-3 p-1"
              onClick={remove}
            >
              Remove Account
            </button>
          </div>
        </div>


        {state.bank.accounts[0] === undefined && (
          <div
            className="d-flex justify-content-center align-items-center"
            style={{ height: "60vh" }}
          >
            <h1 className="text-center">NO ACCOUNTS FOUND...</h1>
          </div>
        )}

        <div className="mt-3">
          <div className="row">
            {state.bank.accounts[0] !== undefined &&
              state.bank.accounts.map((item, index) => (
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

                      <Card.Title className="text-center">
                        {item.bankName}
                      </Card.Title>
                      <ListGroup className="list-group-flush text-center">
                        <ListGroupItem className="b">
                          Account No:{item.accountNo}
                        </ListGroupItem>
                        <ListGroupItem className="b">
                          Balance: ₹{item.balance}
                        </ListGroupItem>
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
