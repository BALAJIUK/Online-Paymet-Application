import {AddMoney} from "./AddMoney"
import configureMockStore from 'redux-mock-store'
import { Provider } from "react-redux";
import { shallow } from "enzyme";
import thunk from "redux-thunk";


const mockStore=configureMockStore([thunk]);
it("Addmoney page render with initial value in amount input", () => {
  const store = mockStore({
    startup: { complete: false }
  });
  const wrapper = shallow(
    <Provider store={store}>
      <AddMoney />
    </Provider>
  )
  expect(wrapper.find('#amount').value).toBe(undefined);
});