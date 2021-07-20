import { shallow } from "enzyme";
import { Signup } from "./Signup";
import React from "react";
import thunk from "redux-thunk";
import configureMockStore from 'redux-mock-store'
import { Provider } from "react-redux";

const mockStore=configureMockStore([thunk]);
it("Signup page render with initial value in name input", () => {
  const store = mockStore({
    startup: { complete: false }
  });
  const wrapper = shallow(
    <Provider store={store}>
      <Signup />
    </Provider>
  )
  expect(wrapper.find('#name').value).toBe(undefined);
});


it("Signup page render with initial value in mobilenumber input", () => {
  const store = mockStore({
    startup: { complete: false }
  });
  const wrapper = shallow(
    <Provider store={store}>
      <Signup />
    </Provider>
  )
  expect(wrapper.find('#mobile').value).toBe(undefined);
});

it("Signup page render with initial value in password input", () => {
  const store = mockStore({
    startup: { complete: false }
  });
  const wrapper = shallow(
    <Provider store={store}>
      <Signup />
    </Provider>
  )
  expect(wrapper.find('#password').value).toBe(undefined);
});

