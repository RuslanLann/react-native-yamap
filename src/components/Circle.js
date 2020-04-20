import React from 'react';
import {requireNativeComponent} from 'react-native';
import {processColorProps} from '../utils';

const NativeCircle = requireNativeComponent('YamapCircle');

export default class Circle extends React.Component {
  render() {
    const props = {...this.props};
    processColorProps(props, 'fillColor');
    processColorProps(props, 'strokeColor');
    return <NativeCircle {...props} />;
  }
}
