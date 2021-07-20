import axios from "axios";

const initState = {
  transactionSuccess: false,
  transactionFailure: false,
  message: "",
  msgStatus: false,
  balance: "",
  customer: {},
};

const TRANSACTION_SUCCESS = "TRANSACTION_SUCCESS";
const TRANSACTION_FAILURE = "TRANSACTION_FAILURE";
const SET_MESSAGE = "SET_MESSAGE";
const MESSAGE_STATUS = "MESSAGE_STATUS";
const SHOW_BALANCE = "SHOW_BALANCE";
const GET_CUSTOMER = "GET_CUSTOMER";

const token = localStorage.getItem("Auth");

export const addMoney = (payload) => {
  return async (dispatch) => {
    const url = "http://localhost:8080/api/v1/addmoney";
    let response = null;
    try {
      response = await axios.post(url, payload, {
        headers: { Authorization: `Bearer ${token}` },
      });
      dispatch({ type: TRANSACTION_SUCCESS, payload: true });
      dispatch({ type: MESSAGE_STATUS, payload: true });
      dispatch({ type: SET_MESSAGE, payload: response.data });
      setTimeout(() => {
        dispatch({ type: TRANSACTION_SUCCESS, payload: false });
        dispatch({ type: MESSAGE_STATUS, payload: false });
        dispatch({ type: SET_MESSAGE, payload: "" });
      }, 3000);
    } catch (err) {
      console.log(err.response.data.message);
      dispatch({ type: TRANSACTION_FAILURE, payload: true });
      dispatch({ type: MESSAGE_STATUS, payload: true });
      dispatch({ type: SET_MESSAGE, payload: err.response.data.message });
      setTimeout(() => {
        dispatch({ type: TRANSACTION_FAILURE, payload: false });
        dispatch({ type: MESSAGE_STATUS, payload: false });
        dispatch({ type: SET_MESSAGE, payload: "" });
      }, 3000);
    }
  };
};

export const depositMoney = (payload) => {
  return async (dispatch) => {
    const url = "http://localhost:8080/api/v1/deposit";
    let response = null;
    try {
      response = await axios.post(url, payload, {
        headers: { Authorization: `Bearer ${token}` },
      });
      dispatch({ type: TRANSACTION_SUCCESS, payload: true });
      dispatch({ type: MESSAGE_STATUS, payload: true });
      dispatch({ type: SET_MESSAGE, payload: response.data });
      setTimeout(() => {
        dispatch({ type: TRANSACTION_SUCCESS, payload: false });
        dispatch({ type: MESSAGE_STATUS, payload: false });
        dispatch({ type: SET_MESSAGE, payload: "" });
      }, 3000);
    } catch (err) {
      console.log(err.response.data.message);
      dispatch({ type: TRANSACTION_FAILURE, payload: true });
      dispatch({ type: MESSAGE_STATUS, payload: true });
      dispatch({ type: SET_MESSAGE, payload: err.response.data.message });
      setTimeout(() => {
        dispatch({ type: TRANSACTION_FAILURE, payload: false });
        dispatch({ type: MESSAGE_STATUS, payload: false });
        dispatch({ type: SET_MESSAGE, payload: "" });
      }, 3000);
    }
  };
};

export const sendMoney = (payload) => {
  return async (dispatch) => {
    const url = "http://localhost:8080/api/v1/moneytransfer";
    let response = null;
    try {
      response = await axios.post(url, payload, {
        headers: { Authorization: `Bearer ${token}` },
      });
      dispatch({ type: TRANSACTION_SUCCESS, payload: true });
      dispatch({ type: MESSAGE_STATUS, payload: true });
      dispatch({ type: SET_MESSAGE, payload: response.data });
      setTimeout(() => {
        dispatch({ type: TRANSACTION_SUCCESS, payload: false });
        dispatch({ type: MESSAGE_STATUS, payload: false });
        dispatch({ type: SET_MESSAGE, payload: "" });
      }, 3000);
    } catch (err) {
      console.log(err.response.data.message);
      dispatch({ type: TRANSACTION_FAILURE, payload: true });
      dispatch({ type: MESSAGE_STATUS, payload: true });
      dispatch({ type: SET_MESSAGE, payload: err.response.data.message });
      setTimeout(() => {
        dispatch({ type: TRANSACTION_FAILURE, payload: false });
        dispatch({ type: MESSAGE_STATUS, payload: false });
        dispatch({ type: SET_MESSAGE, payload: "" });
      }, 3000);
    }
  };
};

export const getBalance = () => {
  return async (dispatch) => {
    const url = "http://localhost:8080/api/v1/balance";
    const response = await axios.get(url, {
      headers: { Authorization: `Bearer ${token}` },
    });
    console.log(response);
    console.log(token);
    dispatch({ type: SHOW_BALANCE, payload: response.data });
  };
};

export const getCustomer = () => {
  return async (dispatch) => {
    const url = "http://localhost:8080/api/v1/customer";
    const response = await axios.get(url, {
      headers: { Authorization: `Bearer ${token}` },
    });
    dispatch({ type: GET_CUSTOMER, payload: response.data });
  };
};

export const updatepassword = (payload) => {
  return async (dispatch) => {
    const url = "http://localhost:8080/api/v1/updatepass";
    const response = await axios.put(url, payload, {
      headers: { Authorization: `Bearer ${token}` },
    });
    dispatch({ type: TRANSACTION_SUCCESS, payload: true });
      dispatch({ type: MESSAGE_STATUS, payload: true });
      dispatch({ type: SET_MESSAGE, payload: response.data });
      setTimeout(() => {
        dispatch({ type: TRANSACTION_SUCCESS, payload: false });
        dispatch({ type: MESSAGE_STATUS, payload: false });
        dispatch({ type: SET_MESSAGE, payload: "" });
      }, 3000);
  };
};

export function walletReducer(state = initState, action) {
  switch (action.type) {
    case TRANSACTION_SUCCESS:
      return { ...state, transactionSuccess: action.payload };
    case TRANSACTION_FAILURE:
      return { ...state, transactionFailure: action.payload };
    case SET_MESSAGE:
      return { ...state, message: action.payload };
    case MESSAGE_STATUS:
      return { ...state, msgStatus: action.payload };
    case SHOW_BALANCE:
      return { ...state, balance: action.payload };
    case GET_CUSTOMER:
      return { ...state, customer: action.payload };
    default:
      return state;
  }
}
