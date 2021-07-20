import { shallow } from "enzyme";
import { Provider } from "react-redux";
import thunk from "redux-thunk";
import {Login} from "./Login"
import configureMockStore from 'redux-mock-store'


const mockStore=configureMockStore([thunk]);
it("Login page render with initial value in mobilenumber input", () => {
  const store = mockStore({
    startup: { complete: false }
  });
  const wrapper = shallow(
    <Provider store={store}>
      <Login />
    </Provider>
  )
  expect(wrapper.find('#mobile').value).toBe(undefined);
});


it("Logiin page render with initial value in password input", () => {
  const store = mockStore({
    startup: { complete: false }
  });
  const wrapper = shallow(
    <Provider store={store}>
      <Login />
    </Provider>
  )
  expect(wrapper.find('#password').value).toBe(undefined);
});