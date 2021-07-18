import axios from "axios";

const initState = {
  accounts: [],
  addSuccess: false,
  deleteSuccess: false,
  message: "",
  error: "",
  errorStatus: false,
};

// const ADD_ACOOUNT="ADD_ACOOUNT";
// const REMOVE_ACCOUNT="REMOVE_ACCOUNT";
const ADD_SUCCESS = "ADD_SUCCESS";
const ADD_MESSAGE = "ADD_MESSAGE";
const DELETE_SUCCESS = "DELETE_SUCCESS";
const ERROR_STATUS = "ERROR_STATUS";
const ERROR = "ERROR";
const GET_ACCOUNTS = "GET_ACCOUNTS";
const token = localStorage.getItem("Auth");
export const getAccounts = () => {
  return async (dispatch) => {
    const url = "http://localhost:8080/api/v1/bankaccounts";
    const response = await axios.get(url, {
      headers: { Authorization: `Bearer ${token}` },
    });
    console.log(response.data);
    dispatch({ type: GET_ACCOUNTS, payload: response.data });
  };
};

export const addAccount = (payload) => {
  return async (dispatch) => {
    const url = "http://localhost:8080/api/v1/add/bankaccount";
    let response = null;
    const token = localStorage.getItem("Auth");
    try {
      response = await axios.post(url, payload, {
        headers: { Authorization: `Bearer ${token}` },
      });
      if (response.data !== "") {
        dispatch({ type: ADD_SUCCESS, payload: true });
        dispatch({ type: ADD_MESSAGE, payload: "Account added Successfully" });
        setTimeout(() => {
          dispatch({ type: ADD_SUCCESS, payload: false });
          dispatch({ type: ADD_MESSAGE, payload: "" });
        }, 3000);
      }
    } catch (err) {
      dispatch({ type: ERROR_STATUS, payload: true });
      dispatch({ type: ERROR, payload: err.response.data.message });
      setTimeout(() => {
        dispatch({ type: ERROR_STATUS, payload: false });
      }, 4000);
    }
  };
};

export const deleteAccount=(payload)=>{
  return async (dispatch) => {
    const url = `http://localhost:8080/api/v1/bankaccount/delete/${payload}`;
    let response = null;
    const token = localStorage.getItem("Auth");
    try {
      response = await axios.delete(url, {
        headers: { Authorization: `Bearer ${token}` },
      });
      if (response.data !== "") {
        dispatch({ type: DELETE_SUCCESS, payload: true });
        dispatch({ type: ADD_MESSAGE, payload: "Account deleted Successfully" });
        setTimeout(() => {
          dispatch({ type: DELETE_SUCCESS, payload: false });
          dispatch({ type: ADD_MESSAGE, payload: "" });
        }, 5000);
        dispatch(getAccounts());
      }
    } catch (err) {
      dispatch({ type: ERROR_STATUS, payload: true });
      dispatch({ type: ERROR, payload: err.response.data.message });
      setTimeout(() => {
        dispatch({ type: ERROR_STATUS, payload: false });
      }, 4000);
    }
  };
}

export function bankReducer(state = initState, action) {
  switch (action.type) {
    case GET_ACCOUNTS:
      return { ...state, accounts: action.payload };
    case ADD_SUCCESS:
      return { ...state, addSuccess: action.payload };
    case DELETE_SUCCESS:
      return { ...state, deleteSuccess: action.payload };
    case ADD_MESSAGE:
      return { ...state, message: action.payload };
    case ERROR_STATUS:
      return { ...state, errorStatus: action.payload };
    case ERROR:
      return { ...state, error: action.payload };
    default:
      return state;
  }
}
