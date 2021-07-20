import axios from "axios";
//init
const initState = {
  transactionsList: [],
};

const token = localStorage.getItem("Auth");

//actiontypes

const TRANSACTION_GET_BY_ID_ACTION_TYPE = "TRANSACTION_GET_BY_ID_ACTION_TYPE";
const TRANSACTION_GET_BY_TYPE_ACTION_TYPE =
  "TRANSACTION_GET_BY_TYPE_ACTION_TYPE";
const TRANSACTION_GET_BY_DATE_ACTION_TYPE =
  "TRANSACTION_GET_BY_DATE_ACTION_TYPE";

//actions

export const getTransactionById = () => {
  return async (dispatch) => {
    const url = `http://localhost:8080/api/v1/transactions/id`;
    let response = await axios.get(url, {
      headers: { Authorization: `Bearer ${token}` },
    });

    console.log(response);

    //UI update
    dispatch({
      type: "TRANSACTION_GET_BY_ID_ACTION_TYPE",
      payload: response.data,
    });
  };
};

export const getTransactionByType = (payload) => {
  return async (dispatch) => {
    const url = `http://localhost:8080/api/v1/transaction/type/${payload}`;
    const response = await axios.get(url, {
      headers: { Authorization: `Bearer ${token}` },
    });

    dispatch({
      type: TRANSACTION_GET_BY_TYPE_ACTION_TYPE,
      payload: response.data,
    });
  };
};

export const getTransactionByDate = (payload1, payload2) => {
  return async (dispatch) => {
    const url = `http://localhost:8080/api/v1/transactions/${payload1}/${payload2}`;
    const response = await axios.get(url, {
      headers: { Authorization: `Bearer ${token}` },
    });

    dispatch({
      type: TRANSACTION_GET_BY_DATE_ACTION_TYPE,
      payload: response.data,
    });
  };
};

//reducer
export function TransactionReducer(state = initState, action) {
  switch (action.type) {
    case TRANSACTION_GET_BY_ID_ACTION_TYPE:
      return { ...state, transactionsList: action.payload };
    case TRANSACTION_GET_BY_TYPE_ACTION_TYPE:
      return { ...state, transactionsList: action.payload };
    case TRANSACTION_GET_BY_DATE_ACTION_TYPE:
      return { ...state, transactionsList: action.payload };
    default:
      return state;
  }
}