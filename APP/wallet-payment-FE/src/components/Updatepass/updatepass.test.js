import { shallow } from "enzyme";
import { Provider } from "react-redux";
import thunk from "redux-thunk";
import {UpdatePass} from "./Updatepass"
import configureMockStore from 'redux-mock-store'


const mockStore=configureMockStore([thunk]);

it("Updatepass page render with initial value in password input", () => {
    const store = mockStore({
      startup: { complete: false }
    });
    const wrapper = shallow(
      <Provider store={store}>
        <UpdatePass />
      </Provider>
    )
    expect(wrapper.find('#password').value).toBe(undefined);
  });
  
  it("Updatepass page render with initial value in repassword input", () => {
    const store = mockStore({
      startup: { complete: false }
    });
    const wrapper = shallow(
      <Provider store={store}>
        <UpdatePass />
      </Provider>
    )
    expect(wrapper.find('#repassword').value).toBe(undefined);
  });
  