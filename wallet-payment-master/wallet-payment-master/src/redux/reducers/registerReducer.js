import axios from "axios";

const initState = {
  regSuccess: false,
  regFailure: false,
  errorStatus: false,
  error: "",
};
const REG_SUCCESS = "REG_SUCCESS";
const REG_FAILURE = "REG_FAILURE";
const ERROR_STATUS = "ERROR_STATUS";
const ERROR = "ERROR";

export const registration = (payload) => {
  return async (dispatch) => {
    let response = null;
    try {
      const url = "http://localhost:8080/api/v1/register";
      response = await axios.post(url, payload);
      if (response.data === "Registered Successfully") {
        dispatch({ type: REG_SUCCESS, payload: true });

        setTimeout(() => {
          dispatch({ type: REG_SUCCESS, payload: false });
        }, 3000);
      } else {
        dispatch({ type: REG_FAILURE, payload: true });
        setTimeout(() => {
          dispatch({ type: REG_FAILURE, payload: false });
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

export function registerReducer(state = initState, action) {
  switch (action.type) {
    case REG_SUCCESS:
      return { ...state, regSuccess: action.payload };
    case REG_FAILURE:
      return { ...state, regFailure: action.payload };
    case ERROR_STATUS:
      return { ...state, errorStatus: action.payload };
    case ERROR:
      return { ...state, error: action.payload };
    default:
      return state;
  }
}
