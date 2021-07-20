import React from "react";
import {shallow} from "enzyme"
import { Homepage1 } from "./Homepage1";

it("render first wallet div in homepage without errors" , ()=>{
    const wrapper=shallow(<Homepage1/>);
    expect(wrapper.find('.add-bank')).toHaveLength(1);
})

it("render second wallet div in homepage without errors" , ()=>{
    const wrapper=shallow(<Homepage1/>);
    expect(wrapper.find('.add-beneficiary')).toHaveLength(1);
})

it("render third wallet div in homepage without errors" , ()=>{
    const wrapper=shallow(<Homepage1/>);
    expect(wrapper.find('.wallet-wallet')).toHaveLength(1);
})

it("render fourth wallet div in homepage without errors" , ()=>{
    const wrapper=shallow(<Homepage1/>);
    expect(wrapper.find('.wallet-bank')).toHaveLength(1);
})

it("render last wallet div in homepage without errors" , ()=>{
    const wrapper=shallow(<Homepage1/>);
    expect(wrapper.find('.pay-bills')).toHaveLength(1);
})