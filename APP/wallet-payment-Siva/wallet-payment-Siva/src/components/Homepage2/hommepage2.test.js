import React from "react";
import {shallow} from "enzyme"
import { Homepage2 } from "./Homepage2";


it("render first button in homepage without errors" , ()=>{
    const wrapper=shallow(<Homepage2/>);
    expect(wrapper.find('#deposit')).toHaveLength(1);
})

it("render second button in homepage without errors" , ()=>{
    const wrapper=shallow(<Homepage2/>);
    expect(wrapper.find('#deposit')).toHaveLength(1);
})

it("render third button in homepage without errors" , ()=>{
    const wrapper=shallow(<Homepage2/>);
    expect(wrapper.find('#bills')).toHaveLength(1);
})

it("render last button in homepage without errors" , ()=>{
    const wrapper=shallow(<Homepage2/>);
    expect(wrapper.find('#addmoney')).toHaveLength(1);
})

