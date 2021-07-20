import {SendMoney} from "./SendMoney"
import configureMockStore from 'redux-mock-store'
import { Provider } from "react-redux";
import { shallow } from "enzyme";
import thunk from "redux-thunk";


const mockStore=configureMockStore([thunk]);
it("Sendmoney page render with initial value in amount input", () => {
  const store = mockStore({
    startup: { complete: false }
  });
  const wrapper = shallow(
    <Provider store={store}>
      <SendMoney />
    </Provider>
  )
  expect(wrapper.find('#amount').value).toBe(undefined);
});