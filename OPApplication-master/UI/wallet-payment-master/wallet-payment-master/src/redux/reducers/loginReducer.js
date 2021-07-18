import axios from "axios";
import { useHistory } from "react-router-dom";

const initState = {
  loginSuccess: false,
  loginFailure: false,
};

const LOGIN_SUCCESS = "LOGIN_SUCCESS";
const LOGIN_FAILURE = "LOGIN_FAILURE";
export const logIn = (payload) => {
  return async (dispatch) => {
    const url = "http://localhost:8080/api/v1/authenticate";
    let response = null;
    let response2=null;
    let token=null;
    try {
      response = await axios.post(url, payload);
      if (response.data !== null) {
        console.log(response.data);
        localStorage.setItem("Auth",response.data);
        token=response.data;
        dispatch({ type: LOGIN_SUCCESS, payload: true });
          const url1 = "http://localhost:8080/api/v1/createwallet";
          response2=await axios.get(url1, {headers :{Authorization:`Bearer ${token}`}});
          console.log(response2);
          window.location.reload();
      }
    } catch (err) {
      dispatch({ type: LOGIN_FAILURE, payload: true });
      setTimeout(() => {
        dispatch({ type: LOGIN_FAILURE, payload: false });
      }, 3000);
    }
  };
};
export const logOut = () => {
  return async (dispatch)=>{
    localStorage.removeItem("Auth");
    dispatch({ type: LOGIN_SUCCESS, payload: false });
  }
};

export function loginReducer(state = initState, action) {
  switch (action.type) {
    case LOGIN_SUCCESS:
      return { ...state, loginSuccess: action.payload };
    case LOGIN_FAILURE:
      return { ...state, loginFailure: action.payload };
    default:
      return state;
  }
}
