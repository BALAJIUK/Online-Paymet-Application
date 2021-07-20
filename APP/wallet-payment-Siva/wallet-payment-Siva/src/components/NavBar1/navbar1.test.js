import React from "react";
import { shallow } from "enzyme";
import {Navbar1} from "./Navbar1"

it("render third wallet div in homepage without errors" , ()=>{
    const wrapper=shallow(<Navbar1/>);
    expect(wrapper.find('#signup')).toHaveLength(1);
})

it("render fourth wallet div in homepage without errors" , ()=>{
    const wrapper=shallow(<Navbar1/>);
    expect(wrapper.find('#login')).toHaveLength(1);
})