import axios from "axios";

const initState={
    bills:[],
    transactionSuccess: false,
    transactionFailure: false,
    message: "",
    msgStatus: false,
}

const TRANSACTION_SUCCESS = "TRANSACTION_SUCCESS";
const TRANSACTION_FAILURE = "TRANSACTION_FAILURE";
const SET_MESSAGE = "SET_MESSAGE";
const MESSAGE_STATUS = "MESSAGE_STATUS";
const GET_BILLS="GET BILLS";

const token = localStorage.getItem("Auth");

export const paybill = (payload) => {
  return async (dispatch) => {
    const url = "http://localhost:8080/api/v1/billpayment/add";
    let response = null;
    try {
      response = await axios.post(url, payload, {
        headers: { Authorization: `Bearer ${token}` },
      });
      console.log(response.data);
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

export const getbills = () => {
    return async (dispatch) => {
      const url = "http://localhost:8080/api/v1/billpayments/view";
      const response = await axios.get(url, {
        headers: { Authorization: `Bearer ${token}` },
      });
      console.log(response);
      console.log(token);
      dispatch({ type: GET_BILLS, payload: response.data });
    };
  };

  export function billReducer(state = initState, action) {
    switch (action.type) {
      case TRANSACTION_SUCCESS:
        return { ...state, transactionSuccess: action.payload };
      case TRANSACTION_FAILURE:
        return { ...state, transactionFailure: action.payload };
      case SET_MESSAGE:
        return { ...state, message: action.payload };
      case MESSAGE_STATUS:
        return { ...state, msgStatus: action.payload };
      case GET_BILLS:
        return { ...state, bills: action.payload };
      default:
        return state;
    }
  }
  