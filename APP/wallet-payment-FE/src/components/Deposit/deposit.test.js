import { shallow } from "enzyme";
import { Provider } from "react-redux";
import thunk from "redux-thunk";
import {Deposit} from "./Deposit"
import configureMockStore from 'redux-mock-store'


const mockStore=configureMockStore([thunk]);
it("Deposit page render with initial value in amount input", () => {
  const store = mockStore({
    startup: { complete: false }
  });
  const wrapper = shallow(
    <Provider store={store}>
      <Deposit />
    </Provider>
  )
  expect(wrapper.find('#amount').value).toBe(undefined);
});