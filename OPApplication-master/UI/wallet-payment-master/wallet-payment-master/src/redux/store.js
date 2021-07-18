import thunk from "redux-thunk";
import { createStore, applyMiddleware } from "redux";
import { registerReducer } from "./reducers/registerReducer";
import { combineReducers } from "@reduxjs/toolkit";
import { loginReducer } from "./reducers/loginReducer";
import { bankReducer } from "./reducers/bankReducer";
import { walletReducer } from "./reducers/walletReducer";
import { beneficiaryReducer } from "./reducers/beneficiaryReducer";
import { billReducer } from "./reducers/billReducer";
import { TransactionReducer } from "./reducers/transactionReducer";

const rootReducer=combineReducers(
  {
    register:registerReducer,
    login:loginReducer,
    bank:bankReducer,
    wallet: walletReducer,
    beneficiary : beneficiaryReducer,
    bill: billReducer,
    transaction:TransactionReducer,
  }
)

export const store=createStore(rootReducer,applyMiddleware(thunk));