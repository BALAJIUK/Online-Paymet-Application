import axios from "axios";

const initState = {
  contacts: [],
  getBySearch: {},
  msgStatus: false,
  errorStatus: false,
  error: "",
};

const ERROR = "ERROR";
const ERROR_STATUS = "ERROR_STATUS";
const GET_CONTACTS = "GET_CONTACTS";
const GET_BY_MOBILE = "GET_BY_MOBILE";
const MSG_STATUS = "MSG_STATUS";
const token = localStorage.getItem("Auth");
export const getContacts = () => {
  return async (dispatch) => {
    const url = "http://localhost:8080/api/v1/benificiary/view/all";
    const response = await axios.get(url, {
      headers: { Authorization: `Bearer ${token}` },
    });
    dispatch({ type: GET_CONTACTS, payload: response.data });
    dispatch({ type: GET_BY_MOBILE, payload: [] });
  };
};

export const addNewBenificiary = (payload) => {
  return async (dispatch) => {
    try {
      const url = `http://localhost:8080/api/v1/benificiary/add`;
      await axios.post(url, payload, {
        headers: { Authorization: `Bearer ${token}` },
      });
      dispatch({ type: MSG_STATUS, payload: true });
      setTimeout(() => {
        dispatch({ type: MSG_STATUS, payload: false });
      }, 5000);
    } catch (err) {
      dispatch({ type: ERROR_STATUS, payload: true });
      dispatch({ type: ERROR, payload: err.response.data.message });
      setTimeout(() => {
        dispatch({ type: ERROR_STATUS, payload: false });
      }, 4000);
    }
  };
};

export const getBenificiaryByMobileNumber = (payload) => {
  return async (dispatch) => {
    const url = `http://localhost:8080/api/v1/benificiary/view/${payload}`;
    const response = await axios.get(url, {
      headers: { Authorization: `Bearer ${token}` },
    });
    console.log(response);
    dispatch({
      type: GET_BY_MOBILE,
      payload: response.data,
    });
  };
};

export const deleteBenificiaryFromWallet = (payload) => {
  return async (dispatch) => {
    const url = `http://localhost:8080/api/v1/benificiary/delete/${payload}`;
    await axios.delete(url, {
      headers: { Authorization: `Bearer ${token}` },
    });
    dispatch(getContacts());
    dispatch({ type: MSG_STATUS, payload: true });
    setTimeout(() => {
      dispatch({ type: MSG_STATUS, payload: false });
    }, 3000);
  };
};

export function beneficiaryReducer(state = initState, action) {
  switch (action.type) {
    case GET_CONTACTS:
      return { ...state, contacts: action.payload };
    case GET_BY_MOBILE:
      return { ...state, getBySearch: action.payload };
    case MSG_STATUS:
      return { ...state, msgStatus: action.payload };
    case ERROR:
      return { ...state, error: action.payload };
    case ERROR_STATUS:
      return { ...state, errorStatus: action.payload };
    default:
      return state;
  }
}
